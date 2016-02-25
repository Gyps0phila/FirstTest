package com.example.gypsophila.asynctaskload;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.example.gypsophila.dailytest.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gypsophila on 2016/2/25.
 */
public class AsyncTaskLoadAty extends Activity {

    private ListView lv;
//    private NewsAdapter newsAdapter;
//    private List<NewsBean> list;

    private static String URL = "http://www.imooc.com/api/teacher?type=4&num=30";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.asyncload_listview);

        lv = (ListView) findViewById(R.id.asyncload_lv);
        NewsAsyncTask asyncTask = new NewsAsyncTask();
        asyncTask.execute(URL);
//        list = new ArrayList<NewsBean>();
//        for (int i = 0; i < 30; i++) {
//            NewsBean bean = new NewsBean();
//            bean.imgUrl = "图片链接" + i;
//            bean.title = "标题" + i;
//            bean.content = "内容" + i;
//            list.add(bean);
//        }
//        newsAdapter = new NewsAdapter(list, this);
//        lv.setAdapter(newsAdapter);

    }

    /**
     * 将url对应的json格式数据转化为封装的bean对象
     * @param netUrl
     * @return
     */
    public List<NewsBean> getJsonData(String netUrl) {
        List<NewsBean> list = new ArrayList<NewsBean>();
        try {
            //new URL(netUrl).openStream()与url.getConnection().getInputStream()相同，简单粗暴
            String jsonString = readStream(new URL(netUrl).openStream());
            Log.i("jsonString", jsonString);
            try {
                JSONObject jsonObject = new JSONObject(jsonString);
                JSONArray jsonArray = jsonObject.getJSONArray("data");
                for (int i = 0; i < jsonArray.length(); i++) {
                    jsonObject = jsonArray.getJSONObject(i);
                    NewsBean newsBean = new NewsBean();
                    newsBean.imgUrl = jsonObject.getString("picSmall");
                    newsBean.title = jsonObject.getString("name");
                    newsBean.content = jsonObject.getString("description");
                    list.add(newsBean);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 通过is解析网络返回的数据
     * 传入一个字节流，然后转化为字符流，提高效率再包裹一个buffer
     * @param is
     * @return
     */
    public String readStream(InputStream is) {
        String result = "";
        InputStreamReader isr;
        try {
            String line = "";
            isr = new InputStreamReader(is, "utf-8");
            BufferedReader br = new BufferedReader(isr);
            while ((line = br.readLine()) != null) {
                result += line;
            }
            br.close();
            isr.close();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 实现网络的异步访问
     * 关于网络加载数据等耗时操作不能放在主线程中执行，直接闪退。
     */

    class NewsAsyncTask extends AsyncTask<String, Void, List<NewsBean>> {

        @Override
        protected List<NewsBean> doInBackground(String... params) {

            return getJsonData(params[0]);
        }

        @Override
        protected void onPostExecute(List<NewsBean> newsBeans) {
            super.onPostExecute(newsBeans);
            NewsAdapter adapter = new NewsAdapter(newsBeans, AsyncTaskLoadAty.this);
            lv.setAdapter(adapter);
        }
    }


}
