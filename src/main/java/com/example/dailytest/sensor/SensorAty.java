package com.example.dailytest.sensor;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dailytest.dailytest.BaseAty;
import com.example.weather.dailytest.R;

/**
 * Created by Gypsophila on 2016/3/9.
 */
public class SensorAty extends BaseAty {


    private TextView sensor_tv;
    private SensorManager manager;
    private ImageView compassImg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sensorcp);
//        sensor_tv = (TextView) findViewById(R.id.sensor_tv);
        compassImg = (ImageView) findViewById(R.id.compass_img);
        /**
         * 光线传感器
         */
//        manager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
//        Sensor sensor = manager.getDefaultSensor(Sensor.TYPE_LIGHT);
//        manager.registerListener(listener, sensor, SensorManager.SENSOR_DELAY_NORMAL);

        /**
         * 加速度传感器
         */

//        manager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
//        Sensor sensor = manager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
//        manager.registerListener(listener, sensor, SensorManager.SENSOR_DELAY_NORMAL);

        /**
         * 方向传感器
         */

        manager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        Sensor accelerometerSensor = manager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        Sensor magneticSensor = manager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        manager.registerListener(listener, magneticSensor, SensorManager.SENSOR_DELAY_GAME);
        manager.registerListener(listener, accelerometerSensor, SensorManager.SENSOR_DELAY_GAME);


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        manager.unregisterListener(listener);
    }

    private SensorEventListener listener = new SensorEventListener() {
        float[] accelerometerValues = new float[3];
        float[] magneticValues = new float[3];
        //旋转起始角度
        private float lastRotateDegree;
        @Override
        public void onSensorChanged(SensorEvent event) {
            //光线获得数据
//            float level = event.values[0];
//            sensor_tv.setText("current light level :" + level);
            //加速度获得数据
//            float xValue = Math.abs(event.values[0]);
//            float yValue = Math.abs(event.values[1]);
//            float zValue = Math.abs(event.values[2]);
//            if (xValue > 12 || yValue > 12 || zValue > 12) {
//                Toast.makeText(SensorAty.this, "摇一摇", Toast.LENGTH_SHORT).show();
//            }


            //判断当前是加速度传感器还是地磁传感器
            if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
                //注意赋值是要用clone方法
                accelerometerValues = event.values.clone();
            } else if (event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) {
                magneticValues = event.values.clone();
            }
            float[] r = new float[9];
            float[] values = new float[3];
            //获得旋转矩阵，赋值到这个R数组中
            SensorManager.getRotationMatrix(r, null, accelerometerValues, magneticValues);
            SensorManager.getOrientation(r, values);
//            Log.i("SensorAty", "values[0] is " + Math.toDegrees(values[0]));
            //计算出的角度取反，用于旋转指南针背景图

            float rotateDegree = -(float) Math.toDegrees(values[0]);
            if (Math.abs(rotateDegree - lastRotateDegree) > 1) {
                RotateAnimation animation = new RotateAnimation(lastRotateDegree, rotateDegree, Animation.RELATIVE_TO_SELF
                        , 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                animation.setFillAfter(true);
                compassImg.startAnimation(animation);
                lastRotateDegree = rotateDegree;
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };


}
