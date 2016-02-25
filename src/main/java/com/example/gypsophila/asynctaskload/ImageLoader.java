package com.example.gypsophila.asynctaskload;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.util.LruCache;
import android.widget.ImageView;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Gypsophila on 2016/2/25.
 */
public class ImageLoader {

    private String mUrl;
    private ImageView mImageView;
    private LruCache<String,Bitmap> mCaches;

    public ImageLoader() {
        int maxMemory = (int) Runtime.getRuntime().maxMemory();
        int maxSize = maxMemory / 4;
        mCaches = new LruCache<String, Bitmap>(maxSize) {
            //每次向缓存中加入时调用
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getByteCount();
            }
        };
    }

    //向缓存中增加bitmap，先检查缓存中是否已存在该图片
    public void addBitmapToCache(String imgUrl, Bitmap bitmap) {
        if (mCaches.get(imgUrl) == null) {
            mCaches.put(imgUrl, bitmap);
        }
    }

    //从缓存中获取bitmap
    public Bitmap getBitmapFromCache(String imgUrl) {
        return mCaches.get(imgUrl);
    }

    private Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (mImageView.getTag().equals(mUrl)) {
                mImageView.setImageBitmap((Bitmap) msg.obj);
            }

        }
    };


    public void showImageByThread(final String imgUrl,ImageView imageView) {
        mImageView = imageView;
        mUrl = imgUrl;
        new Thread() {

            @Override
            public void run() {
                //不可以在非主线程中更新ui，所以将bitmap以message形式发送出去，让handler message进行处理
                super.run();
                Bitmap bitmap = getBitmapFromURL(imgUrl);
                //使用这种方式的message，可以使用现有以及回收的message，提高message的使用效率
                Message message = Message.obtain();
                message.obj = bitmap;
                mHandler.sendMessage(message);

            }
        }.start();
    }

    public Bitmap getBitmapFromURL(String urlString) {
        Bitmap bitmap = null;
        InputStream is = null;
        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            //这个BufferedInputStream没有这句话就加载失败
            is = new BufferedInputStream(connection.getInputStream());
            bitmap = BitmapFactory.decodeStream(is);
            connection.disconnect();
            return bitmap;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }


    public void showImageByAsyncTask(ImageView imageView, String url) {
        //显示图片先查看缓存中是否存在，没有才通过网络加载
        //如果存在，而当前又是在主线程中，所以可以直接更新ui
        Bitmap bitmap = getBitmapFromCache(url);
        if (bitmap == null) {
            new NewsAsyncTask(imageView, url).execute(url);
        } else {
            imageView.setImageBitmap(bitmap);
        }
    }

    private class NewsAsyncTask extends AsyncTask<String, Void, Bitmap> {

        private ImageView mImageView;
        private String mUrl;
        public NewsAsyncTask(ImageView imageView,String url) {
            mImageView = imageView;
            mUrl = url;
        }
        @Override
        protected Bitmap doInBackground(String... params) {
            //通过网络加载图片，同时也要向缓存中去保存
            String url = params[0];
            Bitmap bitmap = getBitmapFromURL(url);
            if (bitmap != null) {
                addBitmapToCache(url, bitmap);
            }
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            if (mImageView.getTag().equals(mUrl)) {
                mImageView.setImageBitmap(bitmap);
            }

        }
    }
}
