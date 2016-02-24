package com.example.gypsophila.asynctask;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.gypsophila.dailytest.R;

/**
 * Created by Gypsophila on 2016/2/24.
 */
public class MyAsyncTaskAty extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.asynctask);
        MyAsyncTask myAsyncTask = new MyAsyncTask();
        myAsyncTask.execute();
    }

    public void doClick(View v) {
        switch (v.getId()) {
            case R.id.btn_asynctaskImg: {
                startActivity(new Intent(this, ImageTestAty.class));
                break;
            }
            case R.id.btn_asynctaskPb: {
                startActivity(new Intent(this, ProgressBarTestAty.class));
                break;
            }
        }
    }
}
