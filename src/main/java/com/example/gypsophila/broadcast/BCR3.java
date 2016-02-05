package com.example.gypsophila.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by Gypsophila on 2016/2/5.
 */
public class BCR3 extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("broadcast", "收到异步广播");
    }
}
