package com.example.gypsophila.contentprovider;

import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract.CommonDataKinds.Email;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.Contacts;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.example.gypsophila.dailytest.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Gypsophila on 2016/2/4.
 */
public class ContentResolverAty extends Activity {

    private List<Map<String, Object>> contactsList;
    private ListView listView_contacts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contactslistview);
        contactsList = new ArrayList<Map<String, Object>>();
        listView_contacts = (ListView) findViewById(R.id.listView_contacts);
        ContentResolver cr = getContentResolver();
        Cursor c1 = cr.query(Contacts.CONTENT_URI, new String[]{Contacts._ID, Contacts.DISPLAY_NAME}, null, null, null);
        if (c1 != null) {
            while (c1.moveToNext()) {
                Map<String, Object> map = new HashMap<String, Object>();
                //取出id和名字
                int id = c1.getInt(c1.getColumnIndex(Contacts._ID));
                String name = c1.getString(c1.getColumnIndex(Contacts.DISPLAY_NAME));
                map.put("_id", c1.getInt(c1.getColumnIndex(Contacts._ID)));
                Log.i("contacts", "id：" + id);
                map.put("name", c1.getString(c1.getColumnIndex(Contacts.DISPLAY_NAME)));
                Log.i("contacts", "name：" + name);
                contactsList.add(map);
                //查询号码
                Cursor c2 = cr.query(Phone.CONTENT_URI, new String[]{Phone.NUMBER, Phone.TYPE}, Phone.CONTACT_ID + "=" + id, null, null);
                if (c2!=null) {
                    while (c2.moveToNext()) {
                        int type = c2.getInt(c2.getColumnIndex(Phone.TYPE));
                        if (type == Phone.TYPE_HOME) {
//                            map.put("phone_home", c2.getString(c2.getColumnIndex(Phone.NUMBER)));
                            Log.i("contacts", "家庭电话：" + c2.getString(c2.getColumnIndex(Phone.NUMBER)));
                        } else if (type == Phone.TYPE_MOBILE) {
//                            map.put("phone_mobile", c2.getString(c2.getColumnIndex(Phone.NUMBER)));
                            Log.i("contacts", "移动电话：" + c2.getString(c2.getColumnIndex(Phone.NUMBER)));
                        }
//                        contactsList.add(map);
                    }
                    c2.close();
                }

                //查询邮箱,联系人填写邮箱即可！成功
                Cursor c3 = cr.query(Email.CONTENT_URI, new String[]{Email.DATA, Email.TYPE}, Email.CONTACT_ID + "=" + id, null, null);
                if (c3 != null) {
                    while (c3.moveToNext()) {
                        int type = c3.getInt(c3.getColumnIndex(Email.TYPE));
                        if (type == Email.TYPE_HOME) {
                            Log.i("contacts", "家庭邮箱：" + c3.getString(c3.getColumnIndex(Email.DATA)));
                        } else if (type == Email.TYPE_WORK) {
                            Log.i("contacts", "工作邮箱：" + c3.getString(c3.getColumnIndex(Email.DATA)));
                        }
                    }
                    c3.close();
                }
            }
            c1.close();
        }

        SimpleAdapter simpleAdapter = new SimpleAdapter(this, contactsList, R.layout.contact_layout, new String[]{"_id", "name","phone_home","phone_mobile"}, new int[]{R.id.et_contacts_id, R.id.et_contacts_name,R.id.et_phone_home,R.id.et_phone_mobile});
        listView_contacts.setAdapter(simpleAdapter);
        //点击listview的item不会有contextmenu,需要去点击editText才会弹出
//        this.registerForContextMenu(listView_contacts);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderIcon(R.mipmap.item1);
        menu.setHeaderTitle("operation");
        /**
         * 使用代码动态添加，也可以使用xml加载
         */
        menu.add(1, 1, 1, "复制");
        menu.add(1, 2, 1, "删除");


    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case 1: {
                Toast.makeText(this, "点击了复制", Toast.LENGTH_SHORT).show();
                break;
            }
            case 2: {
//                SQLiteDatabase db = openOrCreateDatabase("user.db", MODE_PRIVATE, null);
                Toast.makeText(this, "点击了删除", Toast.LENGTH_SHORT).show();
                break;
            }
        }
        return super.onContextItemSelected(item);
    }
}
