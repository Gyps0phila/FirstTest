package com.example.weather.service;

import android.content.Intent;
import android.os.Bundle;

import com.example.weather.dailytest.BaseAty;

/**
 * Created by Gypsophila on 2016/3/16.
 */
public class LongRunningServiceAty extends BaseAty {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = new Intent(this, LongRunningService.class);
        startService(intent);
    }
}
