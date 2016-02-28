package com.example.gypsophila.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by Gypsophila on 2016/2/5.
 */
public class BCR1 extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        String msg = intent.getStringExtra("msg");
        Log.i("broadcast", "receiver1接收到：" + msg);
        //普通广播不可进行截断和处理，有序广播则可以
//        abortBroadcast();
//        Bundle bundle = new Bundle();
//        bundle.putString("test", "广播处理数据");
//        setResultExtras(bundle);

    }
}