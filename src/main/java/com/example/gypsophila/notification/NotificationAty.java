package com.example.gypsophila.notification;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.gypsophila.dailytest.MainActivity;
import com.example.gypsophila.dailytest.R;

/**
 * Created by Gypsophila on 2016/1/28.
 */
public class NotificationAty extends Activity implements View.OnClickListener{

    private Button btn_send,btn_cancel;
    private NotificationManager manager;
    private int notification_ID;
    private static int number=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notification);
        initEvent();
    }

    private void initEvent() {

        btn_send = (Button) findViewById(R.id.btn_send);
        btn_cancel = (Button) findViewById(R.id.btn_cancel);
        manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        btn_send.setOnClickListener(this);
        btn_cancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btn_send: {
                sendNotification();
                break;
            }
            case R.id.btn_cancel: {
                manager.cancel(notification_ID);
                break;
            }
        }
    }

    //send notification
    private void sendNotification() {

        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pintent = PendingIntent.getActivity(this, 0, intent, 0);
        Notification.Builder builder = new Notification.Builder(this);
        builder.setSmallIcon(R.mipmap.pic2);//设置图标
        builder.setTicker("hello"); //设置手机状态栏提示
        builder.setWhen(System.currentTimeMillis()); //设置时间
        builder.setContentTitle("通知栏通知");
        builder.setContentText("我是来自notification包的notification类，你好啊世界！");
//        builder.setNumber(++number);
        builder.setContentIntent(pintent);
        builder.setDefaults(Notification.DEFAULT_SOUND); //设置声音
        builder.setDefaults(Notification.DEFAULT_LIGHTS); //设置指示灯
        builder.setDefaults(Notification.DEFAULT_VIBRATE); //设置震动
        Notification notification = builder.build();
        manager.notify(notification_ID,notification);
    }

}
