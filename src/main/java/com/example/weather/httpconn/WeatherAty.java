package com.example.weather.httpconn;

import android.os.Bundle;
import android.util.Log;

import com.example.weather.dailytest.BaseAty;
import com.example.weather.dailytest.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by Gypsophila on 2016/3/13.
 */
public class WeatherAty extends BaseAty {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final String urlString = "https://api.heweather.com/x3/weather?city=福清&key=9939d20a58bf4b08bca00f9c56ff7217";
        new Thread(new Runnable() {
            @Override
            public void run() {
                StringBuilder sb = null;
                BufferedReader br = null;
                HttpsURLConnection conn;
                try {
                    URL url = new URL(urlString);
                    conn = (HttpsURLConnection) url.openConnection();
                    conn.setRequestMethod("GET");
                    conn.setReadTimeout(5000);

                    String str;
                    sb = new StringBuilder();
                    br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    while ((str = br.readLine()) != null) {
                        sb.append(str);
                    }
                    Log.i("weather", sb.toString());

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (br != null) {
                        try {
                            br.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }

            }
        }).start();

    }
}
