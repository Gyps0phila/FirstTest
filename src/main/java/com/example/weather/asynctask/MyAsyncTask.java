package com.example.weather.asynctask;

import android.os.AsyncTask;
import android.util.Log;

/**
 * Created by Gypsophila on 2016/2/24.
 */
public class MyAsyncTask extends AsyncTask<Void, Void, Void> {

/*   执行顺序
    onPreExecute
    doInBackground
    onProgressUpdate
    onPostExecute
    doInBackground:必须重写,异步执行后台线程要完成的任务,耗时操作将在此方法中完成.
    onPreExecute:执行后台耗时操作前被调用,通常用于进行初始化操作.
    onPostExecute:当doInBackground方法完成后,系统将自动调用此方法,并将doInBackground方法返回的值传入此方法.通过此方法进行UI的更新.
    onProgressUpdate:当在doInBackground方法中调用publishProgress方法更新任务执行进度后,将调用此方法.通过此方法我们可以知晓任务的完成进度.

    */
    @Override
    protected Void doInBackground(Void... params) {
        publishProgress();
        Log.i("asynctask", "doInBackground");
        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Log.i("asynctask", "onPreExecute");
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        Log.i("asynctask", "onPostExecute");
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
        Log.i("asynctask", "onProgressUpdate");
    }
}
