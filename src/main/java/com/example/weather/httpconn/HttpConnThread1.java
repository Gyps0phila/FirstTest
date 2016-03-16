package com.example.weather.httpconn;

import android.util.Log;

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

        /**
         * 此时我没有URLEncoder.encode("张三", "utf-8"),java ee控制台显示也是正确的，没有乱码。
         *突然有个想法：应该是URL类在这个时候帮忙转的吧？！
         */
        //        try {
//             //get方式将参数在浏览器url地址上显示出来name=%E6%98%AF&age=1，所以中文需要被转码
//             //而不使用URLEncoder方法转码也可以正确在java ee控制台显示原因可能是android手机操作系统，默认编码是utf-8
//            connUrl = mUrl + "?name=" + URLEncoder.encode(mName, "utf-8") + "&age=" + mAge;
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }

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
