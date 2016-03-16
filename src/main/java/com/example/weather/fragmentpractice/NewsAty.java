package com.example.weather.fragmentpractice;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

import com.example.weather.dailytest.R;

/**
 * Created by Gypsophila on 2016/3/12.
 */
public class NewsAty extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.news_main);
    }
}
