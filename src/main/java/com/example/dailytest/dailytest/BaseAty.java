package com.example.dailytest.dailytest;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by Gypsophila on 2016/3/4.
 */
public class BaseAty extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("BaseActivity", getClass().getSimpleName());
        ActivityCollector.addActivity(this);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(this);
    }
}
