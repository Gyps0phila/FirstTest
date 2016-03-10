package com.example.gypsophila.animation;

import android.os.Bundle;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.gypsophila.dailytest.BaseAty;
import com.example.gypsophila.dailytest.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gypsophila on 2016/3/10.
 */
public class AniListViewAty extends BaseAty {


    private ListView ani_lv;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ani_listview);

        ani_lv = (ListView) findViewById(R.id.ani_lv);
        List<String> list=new ArrayList<String>();
        for(int i=0;i<20;i++)
        {
            list.add("慕课网"+i);
        }
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
        ani_lv.setAdapter(adapter);

        //设置动画
        LayoutAnimationController lac = new LayoutAnimationController(AnimationUtils.loadAnimation(this, R.anim.zone_in));
        //设置顺序
        lac.setOrder(LayoutAnimationController.ORDER_RANDOM);
        ani_lv.setLayoutAnimation(lac);
        ani_lv.startLayoutAnimation();
    }
}
