package com.example.dailytest.httpconn;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.weather.dailytest.R;

/**
 * Created by Gypsophila on 2016/2/28.
 */
public class HttpConnAty1 extends Activity {


    private Button btn_register;
    private EditText et_name;
    private EditText et_age;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.httpconn_register);
        btn_register = (Button) findViewById(R.id.btn_submit);
        et_name = (EditText) findViewById(R.id.httpconn_name);
        et_age = (EditText) findViewById(R.id.httpconn_age);
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "http://192.168.220.1:8080/http_androidserver/MyServlet";
                new HttpConnThread1(url,et_name.getText().toString(),et_age.getText().toString()).start();
            }
        });
    }
}
