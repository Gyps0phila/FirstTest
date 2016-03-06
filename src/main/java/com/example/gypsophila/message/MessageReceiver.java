package com.example.gypsophila.message;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;

/**
 * Created by Gypsophila on 2016/3/6.
 */
public class MessageReceiver extends BroadcastReceiver {

    public String address;
    public String content;
    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();//
        Object[] pdus = (Object[]) bundle.get("pdus");//提取短信消息
        SmsMessage[] messages = new SmsMessage[pdus.length];
        for (int i = 0; i < messages.length; i++) {
            messages[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
        }
        address = messages[0].getOriginatingAddress();//获取发送方的号码
        String fullMess = "";
        for (SmsMessage message : messages) {
            fullMess += message.getMessageBody();//获取短信内容
        }

        content = fullMess;
    }
}
