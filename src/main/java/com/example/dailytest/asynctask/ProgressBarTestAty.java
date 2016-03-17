package com.example.dailytest.asynctask;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ProgressBar;

import com.example.weather.dailytest.R;

/**
 * Created by Gypsophila on 2016/2/24.
 */
public class ProgressBarTestAty extends Activity {

    private ProgressBar asynctask_pb;
    private MyAsyncTask myAsyncTask;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.asynctask_pb);
        asynctask_pb = (ProgressBar) findViewById(R.id.asynctask_pb2);
        myAsyncTask = new MyAsyncTask();
        myAsyncTask.execute();
    }


    @Override
    protected void onPause() {
        super.onPause();
        /**
         * 这个地方使得效果上线程生命周期与activity保持了一致
         */
        if (myAsyncTask != null && myAsyncTask.getStatus() == AsyncTask.Status.RUNNING) {
            myAsyncTask.cancel(true);
        }
    }

    class MyAsyncTask extends AsyncTask<Void, Integer, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            for (int i = 0; i < 100; i++) {
                if (isCancelled()) {
                    break;
                }
                publishProgress(i);
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            if (isCancelled()) {
                return;
            }
            asynctask_pb.setProgress(values[0]);
        }
    }
}
