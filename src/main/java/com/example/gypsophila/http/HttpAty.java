package com.example.gypsophila.http;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.gypsophila.dailytest.R;

/**
 * Created by Gypsophila on 2016/2/27.
 */
public class HttpAty extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.http);

    }

    public void doClick(View v) {
        switch (v.getId()) {
            case R.id.btn_httpWebView: {
                startActivity(new Intent(this, HttpWebViewAty.class));
                break;
            }
            case R.id.btn_httpImage: {
                startActivity(new Intent(this, HttpImageAty.class));
                break;
            }
        }
    }
}
