package com.example.gypsophila.dailytest;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by Gypsophila on 2016/3/5.
 */
public class BaseAty extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("BaseActivity", getClass().getSimpleName());
    }
}
