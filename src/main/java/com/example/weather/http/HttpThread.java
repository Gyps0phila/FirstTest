package com.example.weather.http;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.os.Handler;
import android.widget.ImageView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Gypsophila on 2016/2/27.
 */
public class HttpThread extends Thread {

//    private Handler mHandler;
//    private WebView mWebView;
//    private String mUrl;
//
//    public HttpThread(Handler handler, WebView webView, String url) {
//        mHandler = handler;
//        mWebView = webView;
//        mUrl = url;
//    }
    private Handler mHandler;
    private ImageView mImageView;
    private String mUrl;

    public HttpThread(Handler handler, ImageView imageView, String url) {
        mHandler = handler;
        mImageView = imageView;
        mUrl = url;
    }

    @Override
    public void run() {
        super.run();
//        //连接网络获取资源后，handler通知更新UI
//        try {
//            URL url = new URL(mUrl);
////            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
//            conn.setRequestMethod("GET");
////            conn.setReadTimeout(5000);
//            final StringBuffer sb = new StringBuffer();
//            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//            String str;
//            while ((str = br.readLine()) != null) {
//                sb.append(str);
//            }
//            br.close();
//
//            mHandler.post(new Runnable() {
//                @Override
//                public void run() {
//                    mWebView.loadData(sb.toString(), "text/html;charset=utf-8", null);
//                }
//            });
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        try {
            URL url = new URL(mUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//            BufferedInputStream bir = new BufferedInputStream(conn.getInputStream());
//            final Bitmap bitmap = BitmapFactory.decodeStream(bir);
//            bir.close();
            //存在本地
            InputStream is = conn.getInputStream();
            FileOutputStream fos = null;
            File download = null;
            String fileName = String.valueOf(System.currentTimeMillis());
            if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                File parent = Environment.getExternalStorageDirectory();
                download = new File(parent, fileName);
                fos = new FileOutputStream(download);
            }

            byte[] buf = new byte[2 * 1024];
            int len;
            if (fos != null) {
                while ((len = is.read(buf)) != -1) {
                    fos.write(buf, 0, len);
                }
            }
            fos.close();
            final Bitmap bitmap = BitmapFactory.decodeFile(download.getAbsolutePath());
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    mImageView.setImageBitmap(bitmap);
                }
            });
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
