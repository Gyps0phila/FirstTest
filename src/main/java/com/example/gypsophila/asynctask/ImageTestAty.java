package com.example.gypsophila.asynctask;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.URL;

import com.example.gypsophila.dailytest.R;

import java.io.InputStream;
import java.net.URLConnection;

/**
 * Created by Gypsophila on 2016/2/24.
 */
public class ImageTestAty extends Activity {

    private ImageView asynctask_img;
    private ProgressBar asynctask_pb;
    private static String URL = "http://img.my.csdn.net/uploads/201504/12/1428806103_9476.png";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.asynctask_img);
        asynctask_img = (ImageView) findViewById(R.id.asynctask_img1);
        asynctask_pb = (ProgressBar) findViewById(R.id.asynctask_pb1);
        new MyAsyncTask().execute(URL);
    }

    class MyAsyncTask extends AsyncTask<String,Void,Bitmap> {

        //params是一个可变长的参数数组
        @Override
        protected Bitmap doInBackground(String... params) {
            //获取传递进来的参数
            String url = params[0];
            Bitmap bitmap = null;
            URLConnection connection;//定义网络连接对象
            InputStream is;//用于获取数据的输入流
            try {
                connection = new URL(url).openConnection();
                is = connection.getInputStream();
                BufferedInputStream bis = new BufferedInputStream(is);
                Thread.sleep(3000);
                //通过decodeStream解析输入流
                bitmap = BitmapFactory.decodeStream(bis);//将输入流解析为bitmap
                is.close();
                bis.close();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            //将bitmap作为返回值
            return bitmap;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //准备工作应该先将progressbar显示出来
            asynctask_pb.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            asynctask_pb.setVisibility(View.GONE);
            asynctask_img.setImageBitmap(bitmap);
        }
    }
}
