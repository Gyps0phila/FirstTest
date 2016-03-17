package com.example.dailytest.sharedpreferences;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.weather.dailytest.R;

/**
 * Created by Gypsophila on 2016/2/3.
 */
public class SharedPreferencesAty extends Activity {
    private Button btn_login;
    private TextView tv_loginName,tv_passWord;
    private EditText et_loginName,et_passWord;
    private CheckBox chk_saveName;
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sharedpreferences);
        initEvent();
        String name = pref.getString("loginName", "");
        if (name.equals("")) {
            chk_saveName.setChecked(false);
        } else {
            et_loginName.setText(name);
            chk_saveName.setChecked(true);
        }
    }

    public void doClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login: {
                String name = et_loginName.getText().toString().trim();
                String password = et_passWord.getText().toString().trim();
                if ("admin".equals(name) && "123456".equals(password)) {
                    if (chk_saveName.isChecked()) {
                        editor.putString("loginName","admin");
                        editor.commit();
                    } else {
                        editor.remove("loginName");
                        editor.commit();
                    }
                    Toast.makeText(this, "login successfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "forbid loginging", Toast.LENGTH_SHORT).show();
                }
                    break;
            }
        }
    }

    private void initEvent() {

        btn_login = (Button) findViewById(R.id.btn_login);
        tv_loginName = (TextView) findViewById(R.id.tv_loginName);
        tv_passWord = (TextView) findViewById(R.id.tv_passWord);
        et_loginName = (EditText) findViewById(R.id.et_loginName);
        et_passWord = (EditText) findViewById(R.id.et_passWord);
        chk_saveName = (CheckBox) findViewById(R.id.chk_saveName);
        pref = getSharedPreferences("mypref", MODE_PRIVATE);
        editor = pref.edit();
    }
}
