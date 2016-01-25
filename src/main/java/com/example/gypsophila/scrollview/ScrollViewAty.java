package com.example.gypsophila.scrollview;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;

import com.example.gypsophila.dailytest.R;

/**
 * Created by Gypsophila on 2016/1/24.
 */
public class ScrollViewAty extends Activity implements View.OnClickListener{
    private Button btn_up,btn_down,btn_back;
    private ScrollView scrollView;
    private int mark;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scrollview);
        btn_up = (Button) findViewById(R.id.btn_up);
        btn_down = (Button) findViewById(R.id.btn_down);
        btn_back = (Button) findViewById(R.id.btn_back);
        scrollView = (ScrollView) findViewById(R.id.scrollView);

        btn_up.setOnClickListener(this);
        btn_down.setOnClickListener(this);
        btn_back.setOnClickListener(this);
        //获得从MainActivity传来的值，并且初始scrollview
        mark = getIntent().getIntExtra("mark", 0);
        if (mark > 0) {
            scrollView.post(new Runnable() {
                @Override
                public void run() {
                    scrollView.scrollTo(0, mark);
                }
            });
        }

        scrollView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {

                        break;
                    }
                    case MotionEvent.ACTION_MOVE: {
                        break;
                    }
                    case MotionEvent.ACTION_UP: {
                        //回传回去滑动的距离
                        mark = scrollView.getScrollY();
                        break;
                    }
                }


                return false;
            }
        });






    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_up: {
                scrollView.scrollBy(0, -30);
                break;
            }
            case R.id.btn_down: {
                scrollView.scrollBy(0, 30);
                break;
            }
            case R.id.btn_back: {
                //将当前滑动的距离回传回去
                Intent data = new Intent();
                data.putExtra("mark", mark);
                setResult(2,data);
                finish();
                break;
            }
        }
    }
}
