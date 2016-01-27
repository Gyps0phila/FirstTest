package com.example.gypsophila.seekbar;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.gypsophila.dailytest.R;

/**
 * Created by Gypsophila on 2016/1/27.
 */
public class SeekBarAty extends Activity implements SeekBar.OnSeekBarChangeListener{

    private SeekBar seekBar;
    private TextView seekBar_tv1,seekBar_tv2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.seekbar);
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        seekBar_tv1 = (TextView) findViewById(R.id.seekBar_tv1);
        seekBar_tv1.setText("hhe");
        seekBar_tv2 = (TextView) findViewById(R.id.seekBar_tv2);

        seekBar.setOnSeekBarChangeListener(this);

    }

    //正在拖动
    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        seekBar_tv1.setText("正在拖动");
        Log.v("log", "我是verbose");
        Log.d("log", "我是debug");
        Log.i("log", "我是info");
        Log.w("log", "我是warn");
        Log.e("log", "我是error");
        seekBar_tv2.setText("数值为：" + progress);
    }
    //开始拖动
    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        seekBar_tv1.setText("开始拖动");

    }
    //停止拖动
    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        seekBar_tv1.setText("停止拖动");
    }
}
