package com.example.weather.httpconn;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.weather.dailytest.R;

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
                break;
            }
            case R.id.btn_httpconn2: {
                startActivity(new Intent(this, JsonAty.class));
                break;
            }
            case R.id.btn_httpconn3: {
                startActivity(new Intent(this, XmlAty.class));
                break;
            }
            case R.id.btn_httpconn4: {
                startActivity(new Intent(this, DownloadAty.class));
                break;
            }
            case R.id.btn_httpconn5: {
                startActivity(new Intent(this, HttpUploadAty.class));
                break;
            }

        }
    }

}
