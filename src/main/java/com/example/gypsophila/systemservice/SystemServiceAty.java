package com.example.gypsophila.systemservice;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.media.AudioManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.example.gypsophila.dailytest.R;

import java.util.zip.Inflater;

/**
 * Created by Gypsophila on 2016/2/7.
 */
public class SystemServiceAty extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        LayoutInflater inflater = getLayoutInflater();
//        View view = inflater.inflate(R.layout.systemservice, null);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.systemservice, null);
        setContentView(view);

    }

    public void doClick(View v) {

        switch (v.getId()) {
            case R.id.btn_netState: {
                if (isNetWorkConnected(this) == true) {
                    Toast.makeText(this, "网络已打开", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "网络未打开", Toast.LENGTH_SHORT).show();
                }
                break;
            }

            case R.id.btn_wifi: {
                WifiManager wifiManager = (WifiManager) this.getSystemService(WIFI_SERVICE);
                if (wifiManager.isWifiEnabled() == true) {
                    //关闭wifi
                    wifiManager.setWifiEnabled(false);
                    Toast.makeText(this, "wifi已经关闭", Toast.LENGTH_SHORT).show();
                } else {
                    wifiManager.setWifiEnabled(true);
                    Toast.makeText(this, "wifi已经打开", Toast.LENGTH_SHORT).show();
                }
            }
            case R.id.btn_voice: {
                AudioManager audioManager = (AudioManager) this.getSystemService(AUDIO_SERVICE);
                int max = audioManager.getStreamMaxVolume(AudioManager.STREAM_SYSTEM);
                int ring = audioManager.getStreamVolume(AudioManager.STREAM_RING);
                int music = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
                Toast.makeText(this, "系统最大音量：" + max + " 当前音量为：" + ring + " 音乐音量为：" + music,Toast.LENGTH_SHORT).show();

            }

            case R.id.btn_packagename: {
                ActivityManager activityManager = (ActivityManager) this.getSystemService(ACTIVITY_SERVICE);
                String str = activityManager.getRunningTasks(1).get(0).topActivity.getPackageName();
                Toast.makeText(this, "包名：" + str, Toast.LENGTH_SHORT).show();
                break;
            }

        }
    }

    public boolean isNetWorkConnected(Context context) {
        if (context != null) {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            if (networkInfo != null) {
                return networkInfo.isAvailable();
            }
        }
        return false;
    }
}
