package com.example.gypsophila.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

/**
 * Created by Gypsophila on 2016/2/6.
 */
public class MyStartService extends Service {

    @Override
    public void onCreate() {
        Log.i("service", "service======onCreate()");
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("service", "service======onStartCommand()");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Log.i("service", "service======onDestroy()");
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.i("service", "service======onBind()");
        return null;
    }
}
