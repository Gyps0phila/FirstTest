package com.example.dailytest.file;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.weather.dailytest.R;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Gypsophila on 2016/2/23.
 */
public class FileAty extends Activity {
    private Button btn_write;
    private TextView file_tv;
    private EditText file_et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.file);
        Log.i("fileinfo", Environment.getExternalStorageState());


        /*  可能由于未root，其他路径写入失败*/
//        File file = new File("/data/data/com.example.gypsophila.dailytest/files/test");
//        if (!file.exists()) {
//            try {
//                file.createNewFile();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        } else {
//            Toast.makeText(this, "文件已经存在", Toast.LENGTH_SHORT).show();
//        }
//        file.delete();
//        File file1 = this.getFilesDir();//这个目录是当前应用程序默认的数据存储目录
//        Log.i("fileinfo", file1.toString());
//        File file2 = this.getCacheDir();//这个目录是当前应用程序默认的缓存数据存储目录，如果内存不足，将自动清除此部分数据，切勿放重要数据
//        Log.i("fileinfo", file2.toString());
//        // /data/data/com.example.gypsophila.dailytest/app_mydir也是与files和cache同级目录
//        // 格式为 /data/data/<包名>/
//        File file3 = this.getDir("mydir", MODE_PRIVATE);//这个目录为自定义权限的存储目录
//        Log.i("fileinfo", file3.toString());
        //M3真机上是/storage/emulated/0/Android/data/com.example.gypsophila.dailytest/cache
        //模拟器上则是/storage/sdcard/Android/data/《包名》/
        /*
        * 这个是外部的存储位置，该位置的数据与内置的使用是一样的，如果app数据都不放入这两个地方，卸载app将不会自动清除
        * 具体看使用情景
        * */
//        File file4 = this.getExternalCacheDir();
//        Log.i("fileinfo", file4.toString());

        btn_write = (Button) findViewById(R.id.btn_write);
        file_et = (EditText) findViewById(R.id.file_et);
        file_tv = (TextView) findViewById(R.id.file_tv);
        btn_write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WriteFile(file_et.getText().toString());
                file_tv.setText(ReadFile());

            }
        });
    }

    public void WriteFile(String content) {
        //应该是自动写入该程序的files目录下
        try {
            FileOutputStream fos = openFileOutput("a.txt", MODE_PRIVATE);
            fos.write(content.getBytes());
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String ReadFile() {
        String content = null;

        try {
            FileInputStream fis = openFileInput("a.txt");
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = fis.read(buffer)) != -1) {
                baos.write(buffer, 0, len);
            }
            content = baos.toString();
            fis.close();
            baos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }
}
