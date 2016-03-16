package com.example.weather.broadcast;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.weather.dailytest.R;

/**
 * Created by Gypsophila on 2016/2/5.
 */
public class BroadCastAty extends Activity {
    private BCR1 bcr1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.broadcast);
        //动态注册的广播接收器优先接收
//        bcr1 = new BCR1();
//        IntentFilter intentFilter = new IntentFilter("com.example.gypsophila.broadcast.BroadCastAty");
//        registerReceiver(bcr1, intentFilter);
    }

    public void doClick(View v) {
        switch (v.getId()) {
            case R.id.btn_broadcast1: {
                //发送一条普通广播,同时要注册广播接收器，并设置过滤条件
                Intent intent = new Intent();
                intent.putExtra("msg", "这是一条普通广播");
                intent.setAction("com.example.gypsophila.broadcast.BroadCastAty");
                sendBroadcast(intent);
                Toast.makeText(this, "发送出一条普通广播", Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.btn_broadcast2: {
                //发送一条有序广播
                Intent intent = new Intent();
                intent.putExtra("msg", "这是一条有序广播");
                intent.setAction("com.example.gypsophila.broadcast.BroadCastAty");
                sendOrderedBroadcast(intent, null);
                Toast.makeText(this, "发送出一条有序广播", Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.btn_broadcast3: {
                Intent intent = new Intent();
                intent.putExtra("msg", "这是一条异步广播");
                intent.setAction("BCR3");
                sendStickyBroadcast(intent);
                IntentFilter intentFilter = new IntentFilter("BCR3");
                BCR3 bcr3 = new BCR3();
                registerReceiver(bcr3, intentFilter);
                break;
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //动态注册的广播接收器一定要释放掉
//        unregisterReceiver(bcr1);
    }
}
