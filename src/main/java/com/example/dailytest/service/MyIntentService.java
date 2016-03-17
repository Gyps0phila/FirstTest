package com.example.dailytest.service;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

/**
 * Created by Gypsophila on 2016/3/16.
 */
public class MyIntentService extends IntentService {
    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public MyIntentService() {
        super("MyIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        Log.i("MyIntentService", "thread-->"+Thread.currentThread().getId());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("MyIntentService", "MyIntentService  onDestroy");
    }
}
