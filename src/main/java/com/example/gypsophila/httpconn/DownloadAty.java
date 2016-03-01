package com.example.gypsophila.httpconn;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.gypsophila.dailytest.R;

import java.io.File;

/**
 * Created by Gypsophila on 2016/3/1.
 */
public class DownloadAty extends Activity {


    private Button download;
    private ImageView img;
    private int count = 0;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int result = msg.what;
            count += result;
            if (count == 3) {
                Log.i("downloadFile", "success");
                File parent = Environment.getExternalStorageDirectory();
                Log.i("downloadFile", msg.obj.toString());
                File file = new File(parent, "class.JPG");
                Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
                if (bitmap != null) {
                    img.setImageBitmap(bitmap);
                } else {
                    Log.i("downloadFile", "something wrong");
                }
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.httpconn_download);
        download = (Button) findViewById(R.id.btn_httpconn_download);
        img = (ImageView) findViewById(R.id.httpconn_img);
        //为什么这边自动加final
//        final String url = "http://192.168.1.111:8080/http_androidserver/class.JPG";
        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new Thread() {
                    @Override
                    public void run() {
                        super.run();
                        String url = "http://192.168.1.111:8080/http_androidserver/class.JPG";
                        Download download = new Download(handler);
                        download.downloadFile(url);
                    }
                }.start();
            }
        });
    }
}
