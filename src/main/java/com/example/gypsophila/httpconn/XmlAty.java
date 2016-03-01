package com.example.gypsophila.httpconn;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import com.example.gypsophila.dailytest.R;

/**
 * Created by Gypsophila on 2016/3/1.
 */
public class XmlAty extends Activity {

    private TextView xml_tv;
    private Handler handler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.httpconn_xml);

        xml_tv = (TextView) findViewById(R.id.xml_tv);
        String url = "http://192.168.1.111:8080/http_androidserver/girls.xml";
        new XmlThread(url,xml_tv,handler).start();

    }
}
