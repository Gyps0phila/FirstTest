package com.example.gypsophila.toast;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.gypsophila.dailytest.R;

/**
 * Created by Gypsophila on 2016/1/28.
 */
public class ToastAty extends Activity implements View.OnClickListener{
    private Button toast_btn1,toast_btn2,toast_btn3,toast_btn4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.toast);
        initEvent();
    }

    private void initEvent() {
        toast_btn1 = (Button) findViewById(R.id.toast_btn1);
        toast_btn2 = (Button) findViewById(R.id.toast_btn2);
        toast_btn3 = (Button) findViewById(R.id.toast_btn3);
        toast_btn4 = (Button) findViewById(R.id.toast_btn4);
        toast_btn1.setOnClickListener(this);
        toast_btn2.setOnClickListener(this);
        toast_btn3.setOnClickListener(this);
        toast_btn4.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.toast_btn1: {
                showToast1();
                break;
            }
            case R.id.toast_btn2: {
                showToast2();
                break;
            }
            case R.id.toast_btn3: {
                showToast3();
                break;
            }
            case R.id.toast_btn4: {
                showToast4();
                break;
            }

        }
    }
    //默认toast
    private void showToast1() {
        Toast toast = Toast.makeText(this, "默认toast", Toast.LENGTH_SHORT);
        toast.show();
    }

    //改变位置toast
    private void showToast2() {
//        Toast toast = new Toast(this);
//        toast.setText("改变位置toast");
//        toast.setDuration(Toast.LENGTH_SHORT);
//        toast.setGravity(Gravity.CENTER, 0, 100);
//        toast.show();
        Toast toast = Toast.makeText(this, "改变位置toast", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 100);
        toast.show();
    }

    //带有图片toast
    private void showToast3() {
        Toast toast = Toast.makeText(this, "带有图片toast", Toast.LENGTH_SHORT);
        LinearLayout toast_layout = (LinearLayout) toast.getView();
        //动态生成图片
        ImageView imageView = new ImageView(this);
        imageView.setImageResource(R.mipmap.item2);
        toast_layout.addView(imageView, 0);
        toast.show();
    }
    //完全自定义toast
    private void showToast4() {
        LayoutInflater inflater = LayoutInflater.from(this);
        View toast_view = inflater.inflate(R.layout.toast_layout, null);
        //这边的toast text并不会出现
        //Toast toast = Toast.makeText(this, "完全自定义toast", Toast.LENGTH_SHORT);
        Toast toast = new Toast(this);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 180, 90);
        toast.setView(toast_view);
        toast.show();
    }
}
