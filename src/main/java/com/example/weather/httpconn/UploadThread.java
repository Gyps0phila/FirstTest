package com.example.weather.httpconn;

import android.util.Log;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Gypsophila on 2016/3/2.
 */
public class UploadThread extends Thread {

    private String mUrl;
    private String fileName;

    public UploadThread(String mUrl, String fileName) {
        this.mUrl = mUrl;
        this.fileName = fileName;
    }

    public void httpUpload() {
        String boundary = "----WebKitFormBoundaryjQcNM0M3aDUQLmR5";
        String prefix = "--";
        String end = "\r\n";
        try {
            URL url = new URL(mUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            //设置允许向服务器写入数据
            conn.setDoOutput(true);
            //设置允许向服务器读出数据
            conn.setDoInput(true);
            //主要是设置HttpURLConnection请求头里面的属性
            conn.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
            //准备将本地文件输出
            DataOutputStream out = new DataOutputStream(conn.getOutputStream());
            out.writeBytes(prefix + boundary + end);
            out.writeBytes("Content-Disposition: form-data; name=\"file\"; filename=\"" + fileName + "\"" + end);
            out.writeBytes(end);
            FileInputStream fis = new FileInputStream(new File(fileName));
            byte[] buf = new byte[4 * 1024];
            int len;
            while ((len = fis.read(buf)) != -1) {
                out.write(buf, 0, len);
            }

            out.writeBytes(end);
            out.writeBytes(prefix + boundary + prefix + end);
            out.flush();
            //接下来要读取出服务器返回的信息
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String str;
            StringBuffer sb = new StringBuffer();

            while ((str = br.readLine()) != null) {
                sb.append(str);
            }

            Log.i("uploadFile", sb.toString());

            if (out != null) {
                out.close();
            }
            if (br != null) {
                br.close();
            }
            if (fis != null) {
                fis.close();
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        super.run();
        httpUpload();
    }
}
