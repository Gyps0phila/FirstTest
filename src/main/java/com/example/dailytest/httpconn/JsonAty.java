package com.example.dailytest.httpconn;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ListView;

import com.example.weather.dailytest.R;

/**
 * Created by Gypsophila on 2016/2/29.
 */
public class JsonAty extends Activity {

    private ListView listView;
    private JsonAdapter adapter;
    private Handler handler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.json_listview);
        listView = (ListView) findViewById(R.id.json_listview);
        String url = "http://192.168.1.111:8080/http_androidserver/JsonServlet";
        //要在主线程中刷出listview，所以传入主线程上下文用于跟Item布局
        adapter = new JsonAdapter(this);
        new JsonStringThread(url,adapter,handler,listView).start();

    }
}
