package com.example.gypsophila.sqlitedatabase;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.example.gypsophila.dailytest.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.lang.Object;

/**
 * Created by Gypsophila on 2016/2/4.
 */
public class SQLiteDataBaseAty extends Activity {

    private ListView listView_sqlite;
//    private List<UsertbBase> userList;
    private List<Map<String, Object>> userList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sqlite_listview);

        //每个程序都有自己的数据库，默认情况下互不干扰
        //创建一个数据库并打开，如已存在直接打开
        SQLiteDatabase db = openOrCreateDatabase("user.db", MODE_PRIVATE, null);
        //创建表语句
        db.execSQL("create table if not exists usertb(_id integer primary key autoincrement, name text not null, age integer not null, sex text not null)");
        //插入几条数据
        //第一种是直接db.execSQL
        //db.execSQL("insert into usertb(name,age,sex) values('张三',18,'女')");
        //第二种是insert语句(插入的表名，空列的默认值，键值)

        ContentValues values = new ContentValues();
        values.put("name", "张三");
        values.put("age", 19);
        values.put("sex", "男");

        db.insert("usertb", null, values);

        values.clear();
        values.put("name", "李四");
        values.put("age", 29);
        values.put("sex", "女");
        db.insert("usertb", null, values);


        //查询插入的记录，然后并放入listview中
//        Cursor c1 = db.query("usertb", null, null, null, null, null, null);
        Cursor c1 = db.rawQuery("select * from usertb", null);
        userList = new ArrayList<Map<String, Object>>();
        if (c1 != null) {
            while (c1.moveToNext()) {
                int _id = c1.getInt(c1.getColumnIndex("_id"));
                String name = c1.getString(c1.getColumnIndex("name"));
                int age = c1.getInt(c1.getColumnIndex("age"));
                String sex = c1.getString(c1.getColumnIndex("sex"));
//                UsertbBase user = new UsertbBase(_id, age, name, sex);
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("_id",_id);
                map.put("name", name);
                map.put("sex", sex);
                map.put("age", age);
                userList.add(map);
            }
            c1.close();
        }
        db.close();

        SimpleAdapter simpleAdapter = new SimpleAdapter(this, userList, R.layout.sqlite_layout, new String[]{"_id", "name", "age", "sex"}, new int[]{R.id.et_id, R.id.et_name, R.id.et_age, R.id.et_sex});
        listView_sqlite = (ListView) findViewById(R.id.listView_sqlite);
        listView_sqlite.setAdapter(simpleAdapter);
        //给view注册上下文菜单，点击整个item并不会弹出
        this.registerForContextMenu(listView_sqlite);

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
