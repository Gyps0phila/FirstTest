package com.example.gypsophila.dailytest;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.gypsophila.notification.NotificationAty;
import com.example.gypsophila.dialog.DialogAty;
import com.example.gypsophila.gallery.GalleryAty;
import com.example.gypsophila.scrollview.ScrollViewAty;
import com.example.gypsophila.seekbar.SeekBarAty;
import com.example.gypsophila.toast.ToastAty;
import com.example.gypsophila.viewflipper.ViewFlipperAty;


public class MainActivity extends ActionBarActivity implements View.OnClickListener {
    private Button fragment,viewPager,btn_viewFlipper,btn_scrollView,btn_gallery;
    private Button btn_seekBar,btn_toast,btn_dialog,btn_notification;
    private int mark;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragment = (Button) findViewById(R.id.fragment);
        viewPager = (Button) findViewById(R.id.viewPager);
        btn_viewFlipper = (Button) findViewById(R.id.btn_viewFlipper);
        btn_scrollView = (Button) findViewById(R.id.btn_scrollView);
        btn_gallery = (Button) findViewById(R.id.btn_gallery);
        btn_seekBar = (Button) findViewById(R.id.btn_seekBar);
        btn_toast = (Button) findViewById(R.id.btn_toast);
        btn_dialog = (Button) findViewById(R.id.btn_dialog);
        btn_notification = (Button) findViewById(R.id.btn_notification);
        viewPager.setOnClickListener(this);
        btn_viewFlipper.setOnClickListener(this);
        btn_scrollView.setOnClickListener(this);
        btn_gallery.setOnClickListener(this);
        btn_seekBar.setOnClickListener(this);
        btn_toast.setOnClickListener(this);
        btn_dialog.setOnClickListener(this);
        btn_notification.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fragment: {
                break;
            }
            case R.id.viewPager: {
                Intent intent = new Intent(this, ViewPagerAty.class);
                startActivity(intent);
                break;
            }
            case R.id.btn_viewFlipper: {
                Intent intent = new Intent(this, ViewFlipperAty.class);
                startActivity(intent);
                break;
            }
            case R.id.btn_scrollView: {
                Intent intent = new Intent(this, ScrollViewAty.class);
//                startActivity(intent);
//                将上次记录浏览位置传值给ScrollViewAty
                intent.putExtra("mark", mark);
                startActivityForResult(intent, 1);
                break;
            }
            case R.id.btn_gallery: {
                Intent intent = new Intent(this, GalleryAty.class);
                startActivity(intent);
            }

            case R.id.btn_seekBar: {
                Intent intent = new Intent(this, SeekBarAty.class);
                startActivity(intent);
            }
            case R.id.btn_toast: {
                Intent intent = new Intent(this, ToastAty.class);
                startActivity(intent);
            }
            case R.id.btn_dialog: {
                Intent intent = new Intent(this, DialogAty.class);
                startActivity(intent);
            }
            case R.id.btn_notification: {
                Intent intent = new Intent(this, NotificationAty.class);
                startActivity(intent);
            }

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==1&&resultCode==2) {
            mark = data.getIntExtra("mark", 0);
        }
    }
}
