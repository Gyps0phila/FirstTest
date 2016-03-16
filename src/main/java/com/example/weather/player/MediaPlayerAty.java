package com.example.weather.player;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;

import com.example.weather.dailytest.BaseAty;
import com.example.weather.dailytest.R;

import java.io.File;
import java.io.IOException;

/**
 * Created by Gypsophila on 2016/3/16.
 */
public class MediaPlayerAty extends BaseAty implements View.OnClickListener{

    private Button play,pause, stop;

    private MediaPlayer mediaPlayer = new MediaPlayer();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mediaplayer);
        play = (Button) findViewById(R.id.btn_play);
        pause = (Button) findViewById(R.id.btn_pause);
        stop = (Button) findViewById(R.id.btn_stop);
        play.setOnClickListener(this);
        pause.setOnClickListener(this);
        stop.setOnClickListener(this);
        initMediaPlayer();

    }
    private void initMediaPlayer() {
        File file = new File(Environment.getExternalStorageDirectory() + "/qqmusic/song", "Capo Productions - WalkAbout [mqms2].mp3");
        try {
            mediaPlayer.setDataSource(file.getPath());
            mediaPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
        }
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btn_play: {
                if (!mediaPlayer.isPlaying()) {
                    mediaPlayer.start();
                }
                break;
            }
            case R.id.btn_pause: {
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();
                }
                break;
            }
            case R.id.btn_stop: {
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.reset();//停止播放,那stop()方法呢
                    initMediaPlayer();
                }
                break;
            }
        }
    }
}
