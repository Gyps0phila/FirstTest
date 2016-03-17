package com.example.dailytest.service;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.dailytest.dailytest.BaseAty;
import com.example.weather.dailytest.R;

/**
 * Created by Gypsophila on 2016/3/16.
 */
public class MyIntentServiceAty extends BaseAty {


    private Button btn_start, btn_stop;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intentservice);
        btn_start = (Button) findViewById(R.id.start_intent_service);
        btn_stop = (Button) findViewById(R.id.stop_intent_service);

        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("MyIntentService", "main---->" + Thread.currentThread().getId());
                Intent intent = new Intent(MyIntentServiceAty.this, MyIntentService.class);
                startService(intent);
            }
        });
    }
}
