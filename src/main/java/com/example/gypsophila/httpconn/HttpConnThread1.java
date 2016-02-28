package com.example.gypsophila.httpconn;

import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Gypsophila on 2016/2/28.
 */
public class HttpConnThread1 extends Thread {

    private String mUrl;
    private String mName;
    private String mAge;

    public HttpConnThread1(String url, String name, String age) {
        this.mUrl = url;
        this.mName = name;
        this.mAge = age;
    }

    /**
     * get方式要将参数在url上添加好
     */
    public void doGet() {
        mUrl = mUrl + "?name=" + mName + "&age=" + mAge;
        try {
            URL url = new URL(mUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setReadTimeout(5000);
            String str;
            StringBuffer sb = new StringBuffer();
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            while ((str = br.readLine()) != null) {
                sb.append(str);
            }
            br.close();
            Log.i("doGet", sb.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * post方式要想象是将参数以流方式写到服务器吧
     */
    public void doPost() {
        try {
            URL url = new URL(mUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setReadTimeout(5000);
            OutputStream out = conn.getOutputStream();
            String content = "name=" + mName + "&age=" + mAge;
            out.write(content.getBytes());
            String str;
            StringBuffer sb = new StringBuffer();
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            while ((str = br.readLine()) != null) {
                sb.append(str);
            }
            br.close();
            Log.i("doPost", sb.toString());

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        super.run();
//        doGet();
        doPost();
    }
}
