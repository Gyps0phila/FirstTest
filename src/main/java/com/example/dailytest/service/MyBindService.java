package com.example.dailytest.service;

import android.app.Service;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

/**
 * Created by Gypsophila on 2016/2/7.
 */
public class MyBindService extends Service {

    public class MyBinder extends Binder {
        public MyBindService getService() {
            return MyBindService.this;
        }
    }
    @Override
    public void onCreate() {
        Log.i("service", "service======onCreate()");
        super.onCreate();
    }

    @Override
    public void unbindService(ServiceConnection conn) {
        Log.i("service", "service======unbindService()");
        super.unbindService(conn);
    }
    @Override
    public void onDestroy() {
        Log.i("service", "service======onDestroy()");

        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.i("service", "service======onBind()");

        return new MyBinder();
    }

    public void play() {
        Log.i("service", "播放");
    }
    public void pause() {
        Log.i("service", "暂停");
    }
    public void next() {
        Log.i("service", "下一首");
    }

    public void previous() {
        Log.i("service", "上一首");
    }
}
