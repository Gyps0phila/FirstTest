package com.example.dailytest.sms;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dailytest.dailytest.BaseAty;
import com.example.weather.dailytest.R;

/**
 * Created by Gypsophila on 2016/3/16.
 * 接受短信在模拟器上可以，在小米3真机中接受不到，有可能被系统短信给截断了
 */
public class SMS_ReceivedAty extends BaseAty {

    private TextView sender,content;
    private MessageReceiver receiver;
    private IntentFilter receiveFilter;

    private EditText to,send_contnt;
    private Button btn_send;

    private IntentFilter sendFilter;
    private SendStatusReceiver sendReceiver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sms_received);
        sender = (TextView) findViewById(R.id.sender);
        content = (TextView) findViewById(R.id.content);
        to = (EditText) findViewById(R.id.to);
        send_contnt = (EditText) findViewById(R.id.send_content);
        btn_send = (Button) findViewById(R.id.btn_send);
        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SmsManager manager = SmsManager.getDefault();
                Intent sendIntent = new Intent("SENT_SMS_ACTION");
                PendingIntent pi = PendingIntent.getBroadcast(SMS_ReceivedAty.this, 0, sendIntent, 0);
                manager.sendTextMessage(to.getText().toString(), null, send_contnt.getText().toString(), pi, null);
            }
        });
        sendFilter = new IntentFilter();
        sendFilter.addAction("SENT_SMS_ACTION");
        sendReceiver = new SendStatusReceiver();
        registerReceiver(sendReceiver, sendFilter);
        receiveFilter = new IntentFilter();
        receiveFilter.addAction("android.provider.Telephony.SMS_RECEIVED");
        receiver = new MessageReceiver();
        registerReceiver(receiver, receiveFilter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);

    }

    class SendStatusReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (getResultCode() == RESULT_OK) {
                //短信发送成功 小米短信发送成功的提示也应是类似
                Toast.makeText(context, "短信发送成功", Toast.LENGTH_SHORT).show();
            } else {
                // 短信发送失败
                Toast.makeText(context, "Send failed", Toast.LENGTH_LONG).show();
            }
        }
    }

    class MessageReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            Bundle bundle = intent.getExtras();
            Object[] pdus = (Object[]) bundle.get("pdus");
            SmsMessage[] messages = new SmsMessage[pdus.length];
            for (int i = 0; i < messages.length; i++) {
                messages[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
            }
            String address = messages[0].getOriginatingAddress();
            String fullMessage = "";
            for (SmsMessage message : messages) {
                fullMessage += message.getMessageBody();
            }
            sender.setText(address);
            content.setText(fullMessage);

        }
    }
}
