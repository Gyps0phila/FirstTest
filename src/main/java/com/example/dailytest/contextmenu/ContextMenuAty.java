package com.example.dailytest.contextmenu;

import android.app.Activity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.weather.dailytest.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gypsophila on 2016/2/3.
 */
public class ContextMenuAty extends Activity {
    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contextmenu);
        showListView();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        /**
         * 代码动态加载方式
         */
        //设置menu显示内容
        menu.setHeaderIcon(R.mipmap.item4);
        menu.setHeaderTitle("context operation");
//        menu.add(1, 1, 1, "复制");
//        menu.add(1, 1, 1, "粘贴");
//        menu.add(1, 1, 1, "剪切");
//        menu.add(1, 1, 1, "重命名");

        /**
         * 加载xml
         */
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_context, menu);

    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        /**
         * 代码动态加载
         */
//        switch (item.getItemId()) {
//            case 1: {
//                Toast.makeText(this, "点击了复制", Toast.LENGTH_SHORT).show();
//                break;
//            }
//            case 2: {
//                Toast.makeText(this, "点击了粘贴", Toast.LENGTH_SHORT).show();
//                break;
//            }
//            case 3: {
//                Toast.makeText(this, "点击了剪切", Toast.LENGTH_SHORT).show();
//                break;
//            }
//            case 4: {
//                Toast.makeText(this, "点击了重命名", Toast.LENGTH_SHORT).show();
//                break;
//            }
//        }

        switch (item.getItemId()) {
            case R.id.context_menu_item1: {
                Toast.makeText(this, "点击了复制", Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.context_menu_item2: {
                Toast.makeText(this, "点击了粘贴", Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.context_menu_item3: {
                Toast.makeText(this, "点击了剪切", Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.context_menu_item4: {
                Toast.makeText(this, "点击了重命名", Toast.LENGTH_SHORT).show();
                break;
            }
        }



        return super.onContextItemSelected(item);
    }

    private void showListView() {

        listView = (ListView) findViewById(R.id.listView);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, getData());
        listView.setAdapter(arrayAdapter);
        this.registerForContextMenu(listView);
    }

    private List<String> getData() {
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < 5; i++) {
            list.add("file" + (i + 1));
        }
        return list;
    }
}
