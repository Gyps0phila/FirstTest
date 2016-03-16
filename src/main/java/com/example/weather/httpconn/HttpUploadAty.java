package com.example.weather.httpconn;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.weather.dailytest.R;

import java.io.File;

/**
 * Created by Gypsophila on 2016/3/2.
 */
public class HttpUploadAty extends Activity {

    private Button btn_upload;
    private ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.httpconn_upload);
        btn_upload = (Button) findViewById(R.id.btn_httpconnUpload);
        img = (ImageView) findViewById(R.id.img_httpconnUpload);
        btn_upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "http://192.168.1.111:8080/http_androidserver/UploadServlet";
                //构造本地文件的路径和名字
                File parentpath = Environment.getExternalStorageDirectory();
                File file = new File(parentpath, "class.JPG");
                String fileName = file.getAbsolutePath();
                UploadThread uploadThread = new UploadThread(url, fileName);
                uploadThread.start();
            }
        });
    }
}
