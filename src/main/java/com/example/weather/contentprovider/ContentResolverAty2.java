package com.example.weather.contentprovider;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract.CommonDataKinds.Email;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.Data;
import android.provider.ContactsContract.CommonDataKinds.StructuredName;
import android.provider.ContactsContract.RawContacts;

import com.example.weather.dailytest.R;

/**
 * Created by Gypsophila on 2016/2/4.
 */
public class ContentResolverAty2 extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sqlite_listview);
        ContentResolver cr = getContentResolver();
        //向联系人中插入一条数据
        ContentValues values = new ContentValues();
        Uri uri = cr.insert(RawContacts.CONTENT_URI, values);
        Long raw_contact_id = ContentUris.parseId(uri);
        values.clear();
        //插入人名
        values.put(StructuredName.RAW_CONTACT_ID, raw_contact_id);
        values.put(StructuredName.DISPLAY_NAME, "张三三");
        values.put(StructuredName.MIMETYPE, StructuredName.CONTENT_ITEM_TYPE);
        uri = cr.insert(Data.CONTENT_URI, values);
        //插入电话号码
        values.clear();
        values.put(Phone.RAW_CONTACT_ID, raw_contact_id);
        values.put(Phone.NUMBER, "13333333333");
        values.put(Phone.TYPE, Phone.TYPE_MOBILE);
        values.put(Phone.MIMETYPE, Phone.CONTENT_ITEM_TYPE);
        //为什么这边是Data，contentprovider这边分散的类太多
        uri = cr.insert(Data.CONTENT_URI, values);

        //插入邮箱
        values.clear();
        values.put(Email.RAW_CONTACT_ID, raw_contact_id);
        values.put(Email.DATA, "hehe@qq.com");
        values.put(Email.TYPE, Email.TYPE_WORK);
        values.put(Email.MIMETYPE, Email.CONTENT_ITEM_TYPE);
        uri = cr.insert(Data.CONTENT_URI, values);
    }
}
