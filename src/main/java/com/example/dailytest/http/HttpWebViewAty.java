package com.example.dailytest.http;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.webkit.WebView;
import android.widget.ImageView;

import com.example.weather.dailytest.R;

/**
 * Created by Gypsophila on 2016/2/27.
 */
public class HttpWebViewAty extends Activity {

    private WebView webView;
    private Handler handler = new Handler();
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.http_webview);
        String url = "https://www.baidu.com";
//        String url = "http://www.sohu.com";
        String imaUrl = "";
        webView = (WebView) findViewById(R.id.http_webview);
        imageView = (ImageView) findViewById(R.id.http_image);
//        new HttpThread(handler, webView, url).start();
        new HttpThread(handler, imageView, imaUrl).start();
    }
}
