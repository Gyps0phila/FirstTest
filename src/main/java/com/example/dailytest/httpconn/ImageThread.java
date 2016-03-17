package com.example.dailytest.httpconn;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.widget.ImageView;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Gypsophila on 2016/2/29.
 */
public class ImageThread extends Thread {

    private String mUrl;
    private ImageView mImageView;
    private Handler mHandler;

    public ImageThread(String url, ImageView imageView, Handler handler) {
        mUrl = url;
        mImageView = imageView;
        mHandler = handler;
    }

    @Override
    public void run() {
        super.run();
        try {
            URL url = new URL(mUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            BufferedInputStream bis = new BufferedInputStream(conn.getInputStream());
            final Bitmap bitmap = BitmapFactory.decodeStream(bis);
            //利用handler机制更新UI
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
