package com.example.dailytest.httpconn;

import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by Gypsophila on 2016/3/1.
 */
public class Download {

    private Handler handler;

    public Download(Handler handler) {
        this.handler = handler;
    }

    //创建线程池对象
    private Executor threadPool = Executors.newFixedThreadPool(3);

    static class DownloadRunnable implements Runnable {

        private String mUrl;
        //是写到本地存储是的名字
        private String fileName;
        private long mStart,mEnd;
        private Handler mHandler;

        public DownloadRunnable(String mUrl, String fileName, long mStart, long mEnd,Handler handler) {
            this.mUrl = mUrl;
            this.fileName = fileName;
            this.mStart = mStart;
            this.mEnd = mEnd;
            this.mHandler = handler;
        }

        @Override
        public void run() {
            try {
                URL url = new URL(mUrl);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestProperty("Range", "bytes=" + mStart + "-" + mEnd);
                conn.setRequestMethod("GET");
                conn.setReadTimeout(5000);

                //对本地文件进行读写的操作
                RandomAccessFile accessFile = new RandomAccessFile(new File(fileName), "rwd");
                //寻找开始位置Moves this file's file pointer to a new position, from where following
                accessFile.seek(mStart);
                InputStream is = conn.getInputStream();
                int len;
                byte[] buf = new byte[4 * 1024];
                while ((len = is.read(buf)) != -1) {
                    accessFile.write(buf, 0, len);
                }

                if (is != null) {
                    is.close();
                }
                if (accessFile != null) {
                    accessFile.close();
                }
                Message message = Message.obtain();
                message.what=1;
                message.obj = fileName;
                mHandler.sendMessage(message);



            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }



    public void downloadFile(String netUrl) {
        //通过url获取网络资源，然后确定大小，给每个线程相近大小，再进行多线程下载
        try {
            URL url = new URL(netUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setReadTimeout(5000);

            //内容长度
            int contentLen = conn.getContentLength();
            int block = contentLen/3;
            String name = getFileName(netUrl);
            File parent = Environment.getExternalStorageDirectory();
            File file = new File(parent, name);
            Log.i("download", "contentLen=" + contentLen + "   block=" + block);
            for (int i = 0; i < 3; i++) {
                long start = i * block;
                long end = (i + 1) * block;
                if (i == 2) {
                    end = contentLen;
                }
                //开始多个线程下载
                DownloadRunnable runnable = new DownloadRunnable(netUrl, file.getAbsolutePath(), start, end, handler);
                threadPool.execute(runnable);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public String getFileName(String url) {
        return url.substring(url.lastIndexOf("/")+1);
    }
}
