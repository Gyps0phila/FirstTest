package com.example.gypsophila.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by Gypsophila on 2016/2/5.
 */
public class BCR2 extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        String msg = intent.getStringExtra("msg");
        Log.i("broadcast", "receiver2接收到：" + msg);
//        Bundle bundle = getResultExtras(true);
//        String str = bundle.getString("test");
//        Log.i("broadcast", "得到的数据：" + str);
    }
}
