package com.example.gypsophila.dailytest;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.gypsophila.contextmenu.ContextMenuAty;
import com.example.gypsophila.notification.NotificationAty;
import com.example.gypsophila.dialog.DialogAty;
import com.example.gypsophila.gallery.GalleryAty;
import com.example.gypsophila.scrollview.ScrollViewAty;
import com.example.gypsophila.seekbar.SeekBarAty;
import com.example.gypsophila.sharedpreferences.SharedPreferencesAty;
import com.example.gypsophila.sqlitedatabase.SQLiteDataBaseAty;
import com.example.gypsophila.toast.ToastAty;
import com.example.gypsophila.viewflipper.ViewFlipperAty;


public class MainActivity extends ActionBarActivity implements View.OnClickListener {
    private Button fragment,viewPager,btn_viewFlipper,btn_scrollView,btn_gallery;
    private Button btn_seekBar,btn_toast,btn_dialog,btn_notification,btn_contextMenu;
    private Button btn_sharedPreferences;
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
        btn_contextMenu = (Button) findViewById(R.id.btn_contextMenu);
        btn_sharedPreferences = (Button) findViewById(R.id.btn_sharedPreferences);
        viewPager.setOnClickListener(this);
        btn_viewFlipper.setOnClickListener(this);
        btn_scrollView.setOnClickListener(this);
        btn_gallery.setOnClickListener(this);
        btn_seekBar.setOnClickListener(this);
        btn_toast.setOnClickListener(this);
        btn_dialog.setOnClickListener(this);
        btn_notification.setOnClickListener(this);
        btn_contextMenu.setOnClickListener(this);
        btn_sharedPreferences.setOnClickListener(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        /**
         * 加载xml方式
         */
        //getMenuInflater().inflate(R.menu.menu_main, menu);
        /**
         * 代码动态加载
         */
//        menu.add(1, 1, 1, "菜单一");
//        menu.add(1, 2, 1, "菜单二");

        /**
         * 子菜单,代码动态加载
         */
//        SubMenu file = menu.addSubMenu("文件");
//        SubMenu edit = menu.addSubMenu("编辑");
//        file.setHeaderTitle("文件操作");
//        file.setHeaderIcon(R.mipmap.item1);
//        file.add(1, 1, 1, "新建");
//        file.add(1, 2, 1, "保存");
//        file.add(1, 3, 1, "打开");
//        edit.setHeaderTitle("编辑操作");
//        edit.setHeaderIcon(R.mipmap.item4);
//        edit.add(2, 1, 1, "复制");
//        edit.add(2, 2, 1, "粘贴");
//        edit.add(2, 3, 1, "重命名");
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_submenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        /**
         * 加载xml
         */
//        switch (item.getItemId()) {
//            case R.id.action_menu_item1: {
//                Toast.makeText(this, "点击了菜单一", Toast.LENGTH_SHORT).show();
//
//                break;
//            }
//            case R.id.action_menu_item2: {
//                Toast.makeText(this, "点击了菜单二", Toast.LENGTH_LONG).show();
//                break;
//            }
//        }

        /**
         * 代码动态加载
         */
//        switch (item.getItemId()) {
//            case 1: {
//                Toast.makeText(this, "点击了菜单一", Toast.LENGTH_SHORT).show();
//                break;
//            }
//            case 2: {
//                Toast.makeText(this, "点击了菜单二", Toast.LENGTH_LONG).show();
//                break;
//            }
//        }
//        if (item.getGroupId()==1) {
//            switch (item.getItemId()) {
//                case 1: {
//                    Toast.makeText(this, "点击了新建", Toast.LENGTH_SHORT).show();
//                    break;
//                }
//                case 2: {
//                    Toast.makeText(this, "点击了保存", Toast.LENGTH_LONG).show();
//                    break;
//                }
//                case 3: {
//                    Toast.makeText(this, "点击了打开", Toast.LENGTH_LONG).show();
//                    break;
//                }
//            }
//        }
//        else if (item.getGroupId() == 2) {
//            switch (item.getItemId()) {
//                case 1: {
//                    Toast.makeText(this, "点击了复制", Toast.LENGTH_LONG).show();
//                    break;
//                }
//                case 2: {
//                    Toast.makeText(this, "点击了粘贴", Toast.LENGTH_LONG).show();
//                    break;
//                }
//                case 3: {
//                    Toast.makeText(this, "点击了重命名", Toast.LENGTH_LONG).show();
//                    break;
//                }
//            }
//        }

        /**
         * xml加载
         */

        switch (item.getItemId()) {
            case R.id.submenu_item1: {
                Toast.makeText(this, "sharedpreferences", Toast.LENGTH_SHORT).show();
                //跳转页面
                Intent intent = new Intent(MainActivity.this, SharedPreferencesAty.class);
                item.setIntent(intent);
                break;
            }
            case R.id.submenu_item2: {
                Toast.makeText(this, "sqlitedatabase", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, SQLiteDataBaseAty.class);
                item.setIntent(intent);
                break;
            }
            case R.id.submenu_item3: {
                Toast.makeText(this, "点击了打开", Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.submenu_item4: {
                Toast.makeText(this, "点击了复制", Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.submenu_item5: {
                Toast.makeText(this, "点击了粘贴", Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.submenu_item6: {
                Toast.makeText(this, "点击了重命名", Toast.LENGTH_SHORT).show();
                break;
            }
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
                break;
            }

            case R.id.btn_seekBar: {
                Intent intent = new Intent(this, SeekBarAty.class);
                startActivity(intent);
                break;
            }
            case R.id.btn_toast: {
                Intent intent = new Intent(this, ToastAty.class);
                startActivity(intent);
                break;
            }
            case R.id.btn_dialog: {
                Intent intent = new Intent(this, DialogAty.class);
                startActivity(intent);
                break;
            }
            case R.id.btn_notification: {
                Intent intent = new Intent(this, NotificationAty.class);
                startActivity(intent);
                break;
            }
            case R.id.btn_contextMenu: {
                Intent intent = new Intent(this, ContextMenuAty.class);
                startActivity(intent);
                break;
            }
            case R.id.btn_sharedPreferences: {
                Intent intent = new Intent(this, SharedPreferencesAty.class);
                startActivity(intent);
                break;
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
