package com.example.dailytest.baseadapter;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.weather.dailytest.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gypsophila on 2016/2/23.
 */
public class BaseAdapterAty extends Activity {

    private ListView base_listview;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.baseadapter_listview);
        //准备数据源
        List<ItemBean> list = new ArrayList<ItemBean>();
        for (int i = 0; i < 20; i++) {
            list.add(new ItemBean(R.mipmap.item12, "我是标题" + i, "我是内容" + i));
        }

        base_listview = (ListView) findViewById(R.id.base_listview);
        base_listview.setAdapter(new MyAdapter(list, this));
    }
}
