package com.example.weather.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.weather.dailytest.R;

/**
 * Created by Gypsophila on 2016/1/28.
 */
public class DialogAty extends Activity implements View.OnClickListener{

    private Button dialog_btn1,dialog_btn2,dialog_btn3,dialog_btn4,dialog_btn5;

    private String[] singleList = {"男", "女", "女博士", "程序员"};
    private String[] multiList = {"足球", "篮球", "排球", "乒乓球"};
    private String[] itemList = {"项目经理","策划","测试","美工","程序员"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog);
        initEvent();
    }

    private void initEvent() {
        dialog_btn1 = (Button) findViewById(R.id.dialog_btn1);
        dialog_btn2 = (Button) findViewById(R.id.dialog_btn2);
        dialog_btn3 = (Button) findViewById(R.id.dialog_btn3);
        dialog_btn4 = (Button) findViewById(R.id.dialog_btn4);
        dialog_btn5 = (Button) findViewById(R.id.dialog_btn5);
        dialog_btn1.setOnClickListener(this);
        dialog_btn2.setOnClickListener(this);
        dialog_btn3.setOnClickListener(this);
        dialog_btn4.setOnClickListener(this);
        dialog_btn5.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.dialog_btn1: {
                showDialog1();
                break;
            }
            case R.id.dialog_btn2: {
                showDialog2();
                break;
            }
            case R.id.dialog_btn3: {
                showDialog3();
                break;
            }
            case R.id.dialog_btn4: {
                showDialog4();
                break;
            }
            case R.id.dialog_btn5: {
                showDialog5();
                break;
            }
        }
    }
    //显示确认对话框
    private void showDialog1() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setTitle("确认对话框");
        builder.setMessage("确认对话框提示内容");
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(DialogAty.this, "点击了确认", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(DialogAty.this, "点击了取消", Toast.LENGTH_SHORT).show();
            }
        });

        Dialog dialog = builder.create();
        dialog.show();
    }

    //显示单选对话框
    private void showDialog2() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(R.mipmap.item1);
        builder.setTitle("单选对话框");
        builder.setSingleChoiceItems(singleList, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String str = singleList[which];
                Toast.makeText(DialogAty.this, "点击了" + str, Toast.LENGTH_SHORT).show();
            }
        });
        Dialog dialog = builder.create();
        dialog.show();
    }

    //显示多选对话框
    private void showDialog3() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setTitle("爱好");
        builder.setMultiChoiceItems(multiList, null, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {

                if (isChecked) {
                    Toast.makeText(DialogAty.this, "我喜欢" + multiList[which], Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(DialogAty.this, "我不喜欢" + multiList[which], Toast.LENGTH_SHORT).show();
                }
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        Dialog dialog = builder.create();
        dialog.show();
    }

    //显示列表对话框
    private void showDialog4() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setTitle("列表对话框");
        builder.setItems(itemList, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(DialogAty.this, "点击了" + itemList[which], Toast.LENGTH_SHORT).show();
            }
        });
        Dialog dialog = builder.create();
        dialog.show();
    }

    //显示自定义对话框
    private void showDialog5() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setTitle("自定义对话框");
        LayoutInflater inflater = LayoutInflater.from(this);
        View dialog_view = inflater.inflate(R.layout.dialog_layout, null);
        builder.setView(dialog_view);
        Dialog dialog = builder.create();
        dialog.show();
    }
}
