package com.example.weather.service;

import android.app.Activity;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;

import com.example.weather.dailytest.R;

/**
 * Created by Gypsophila on 2016/2/6.
 */
public class ServiceAty extends Activity {
    private Intent intent1, intent2;
    private MyBindService service;
    private ServiceConnection conn = new ServiceConnection() {
        //当启动源跟service成功连接之后将自动调用这个方法
        @Override
        public void onServiceConnected(ComponentName name, IBinder binder) {
            service = ((MyBindService.MyBinder) binder).getService();
        }
        //当启动源跟service的连接意外丢失时候会调用这个方法
        //比如当service崩溃或者被强行杀死
        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.service);
    }

    @Override
    protected void onDestroy() {
        stopService(intent2);
        unbindService(conn);
        super.onDestroy();
    }

    public void doClick(View v) {
        switch (v.getId()) {
            case R.id.btn_startService: {
                intent1 = new Intent(ServiceAty.this, MyStartService.class);
                startService(intent1);
                break;
            }
            case R.id.btn_stopService: {
                stopService(intent1);
                break;
            }

            case R.id.btn_bindService: {
                intent2 = new Intent(ServiceAty.this, MyBindService.class);
                startService(intent2);
                bindService(intent2, conn, Service.BIND_AUTO_CREATE);
                break;
            }

            case R.id.btn_unbindService: {
                unbindService(conn);
                break;
            }

            case R.id.btn_play: {
                service.play();
                break;
            }
            case R.id.btn_pause: {
                service.pause();
                break;
            }
            case R.id.btn_next: {
                service.next();
                break;
            }
            case R.id.btn_previous: {
                service.previous();
                break;
            }
        }
    }
}
