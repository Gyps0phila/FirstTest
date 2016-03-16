package com.example.weather.httpconn;

import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gypsophila on 2016/2/29.
 */
public class JsonStringThread extends Thread {

    private String mUrl;
    private Context mContext;
    private JsonAdapter mAdapter;
    private Handler mHander;
    private ListView mListView;

    public JsonStringThread(String mUrl, JsonAdapter mAdapter, Handler mHander, ListView mListView) {
        this.mUrl = mUrl;
        this.mAdapter = mAdapter;
        this.mHander = mHander;
        this.mListView = mListView;
    }

    @Override

    public void run() {
        super.run();
        //连接服务器，去获取json数据字符串
        //恰好无参数，有参数处理参考HttpConnThread1
        try {
            URL url = new URL(mUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setReadTimeout(5000);
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuffer sb = new StringBuffer();
            String str;
            while ((str = br.readLine()) != null) {
                sb.append(str);
            }

            //解析所获取的json数据字符串
            Log.i("==========", sb.toString());
            List<Person> persons = parseJson(sb.toString());
            mAdapter.setData(persons);
            //更新UI
            mHander.post(new Runnable() {
                @Override
                public void run() {
                    mListView.setAdapter(mAdapter);
                }
            });

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public List<Person> parseJson(String jsonString) {

        try {
            List<Person> perList = new ArrayList<Person>();
            JSONObject object = new JSONObject(jsonString);
            int result = object.getInt("result");
            if (result == 1) {
                JSONArray jsonArray = object.getJSONArray("perList");
                //循环解析内层json对象
                for (int i = 0; i < jsonArray.length(); i++) {
                    object = jsonArray.getJSONObject(i);
                    Person person = new Person();
                    person.setName(object.getString("name"));
                    person.setAge(object.getInt("age"));
                    person.setUrl(object.getString("url"));
                    List<SchoolInfo> schList = new ArrayList<SchoolInfo>();
                    JSONArray schools = object.getJSONArray("schList");
                    person.setSchList(schList);
                    for (int j = 0; j < schools.length(); j++) {
                        JSONObject school = schools.getJSONObject(j);
                        SchoolInfo schoolInfo = new SchoolInfo();
                        schoolInfo.setSchoolName(school.getString("schoolName"));
                        schList.add(schoolInfo);
                    }

                    perList.add(person);
                }
                return perList;
            } else {
                Toast.makeText(this.mContext, "error", Toast.LENGTH_SHORT).show();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }
}
