package com.example.dailytest.http;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import com.example.weather.dailytest.R;

/**
 * Created by Gypsophila on 2016/2/27.
 */
public class HttpImageAty extends Activity {

    private ImageView imageView;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.http_image);
        String url= "http://img.mukewang.com/566ebf160001e72e03000527-200-200.jpg";
        imageView = (ImageView) findViewById(R.id.http_image);
        new HttpThread(handler, imageView, url).start();
    }
}
