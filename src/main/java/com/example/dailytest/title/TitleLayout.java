package com.example.dailytest.title;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.weather.dailytest.R;

/**
 * Created by Gypsophila on 2016/3/11.
 */
public class TitleLayout extends LinearLayout {

    public TitleLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        //这边inflater第二个参数null和this的区别。
        LayoutInflater.from(context).inflate(R.layout.titlelayout,this);
        Button title_back = (Button) findViewById(R.id.title_back);
        Button title_edit = (Button) findViewById(R.id.title_edit);
        title_back.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Activity) getContext()).finish();
            }
        });
        title_edit.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "click edit", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
