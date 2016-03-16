package com.example.weather.handler;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.weather.dailytest.R;

/**
 * Created by Gypsophila on 2016/3/2.
 */
public class HandlerAty extends Activity {

    private TextView tv;
    private Handler handler = new Handler();
    private Handler handler1 = new Handler(new Handler.Callback() {
        //callback可以截获handler发送的消息，false为让下面的handleMessage也可以执行到，true则截获后，handleMessage不执行
        @Override
        public boolean handleMessage(Message msg) {
            Toast.makeText(getApplicationContext(),"1",Toast.LENGTH_SHORT).show();
            return false;
        }
    }) {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Toast.makeText(getApplicationContext(),"1",Toast.LENGTH_SHORT).show();

        }
    };
//    private Handler handler = new Handler() {
//
//        @Override
//        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
////            tv.setText(""+msg.arg1);
//            tv.setText(msg.obj+"");
//
//        }
//    };
    private Button btn_stop;
    private int[] imgs = {R.mipmap.item1, R.mipmap.item12, R.mipmap.item3};
    private int index;
    private MyRunnable myRunnable = new MyRunnable();
    private ImageView imageView;

    class Person {
        String name;
        int age;

        @Override
        public String toString() {
            return "name=" + name + " age=" + age;
        }
    }
    class MyRunnable implements Runnable {

        @Override
        public void run() {
            index++;
            index = index % 3;
            imageView.setImageResource(imgs[index]);
            handler.postDelayed(myRunnable, 1000);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.handler);
        tv = (TextView) findViewById(R.id.handler_tv);
        imageView = (ImageView) findViewById(R.id.handler_img);
        btn_stop = (Button) findViewById(R.id.btn_handlerStop);
        final Person p = new Person();
        p.age = 1;
        p.name = "g";
        new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    Thread.sleep(1000);

//                    tv.setText("change");
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            tv.setText("change");
                        }
                    });
                    handler.postDelayed(myRunnable, 1000);

                    //handler传递消息
//                    Message message = new Message();
//                    Message message = Message.obtain();//仅仅是得到一个可复用的message对象而已
//                    Message message = handler.obtainMessage();//这边底层调用message.obtain，同时也指定了发送给对象为本身handler
//                    message.arg1 = 88;
//                    message.obj = p;



//                    handler.sendMessage(message);
                    /**
                     * 由于message指定了发送的对象，所以message本身也能处理自己的发送，但其实还是调用了
                     * handler.sendMessage
                     * target.sendMessage(this);
                     */
//                    message.sendToTarget();


                    btn_stop.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            handler.removeCallbacks(myRunnable);
                        }
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }.start();
    }
}
