package com.example.dailytest.service;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.SystemClock;
import android.util.Log;

/**
 * Created by Gypsophila on 2016/3/16.
 */
public class LongRunningService extends Service {

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //开启线程记录时间
        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.i("LongRunningService", System.currentTimeMillis() + "");
            }
        }).start();

        AlarmManager manager = (AlarmManager) getSystemService(ALARM_SERVICE);
        int tenSeconds = 10 * 1000;
        long triggerAtTime = SystemClock.elapsedRealtime() + tenSeconds;
        Intent i = new Intent(this, AlarmReceiver.class);
        PendingIntent pi = PendingIntent.getBroadcast(this, 0, i, 0);
        manager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, triggerAtTime, pi);

        return super.onStartCommand(intent, flags, startId);
    }
}
