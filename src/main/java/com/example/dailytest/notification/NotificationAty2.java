package com.example.dailytest.notification;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.dailytest.dailytest.BaseAty;
import com.example.weather.dailytest.R;

import java.io.File;

/**
 * Created by Gypsophila on 2016/3/16.
 */
public class NotificationAty2 extends BaseAty implements View.OnClickListener{

    private Button btn_send;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notification);
        btn_send = (Button) findViewById(R.id.btn_send);
        btn_send.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_send: {
                NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                Notification notification = new Notification(R.mipmap.item2, "ticker", System.currentTimeMillis());
                Intent intent = new Intent(this, NotificationAty.class);
                PendingIntent pi = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
                notification.setLatestEventInfo(this, "title", "content", pi);
                Uri soundUri = Uri.fromFile(new File("/system/media/audio/ringtones/Basic_tone.ogg"));
                notification.sound = soundUri;
                long[] vibrates = {0, 1000, 1000, 1000};
                notification.vibrate = vibrates;
                notification.ledARGB = Color.GREEN;
                notification.ledOnMS = 1000;
                notification.ledOffMS = 1000;
                notification.flags = Notification.FLAG_SHOW_LIGHTS;
                manager.notify(1, notification);
                break;
            }
        }
    }
}
