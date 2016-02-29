package com.example.gypsophila.httpconn;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.gypsophila.dailytest.R;

/**
 * Created by Gypsophila on 2016/2/28.
 */
public class HttpConnAty extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.httpconn);
    }

    public void doClick(View v) {
        switch (v.getId()) {
            case R.id.btn_httpconn1: {
                startActivity(new Intent(this,HttpConnAty1.class));
            }
            case R.id.btn_httpconn2: {
                startActivity(new Intent(this, JsonAty.class));
            }
        }
    }

}
