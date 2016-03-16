package com.example.weather.player;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.VideoView;

import com.example.weather.dailytest.R;

import java.io.File;

/**
 * Created by Gypsophila on 2016/3/16.
 */
public class VideoPlayerAty extends Activity implements View.OnClickListener{


    private Button btn_play,btn_pause, btn_replayer;
    private VideoView videoView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.videoplayer);
        btn_play = (Button) findViewById(R.id.btn_play);
        btn_pause = (Button) findViewById(R.id.btn_pause);
        btn_replayer = (Button) findViewById(R.id.btn_replay);
        videoView = (VideoView) findViewById(R.id.videoView);
        btn_play.setOnClickListener(this);
        btn_pause.setOnClickListener(this);
        btn_replayer.setOnClickListener(this);

        initVideoPlayer();
    }

    private void initVideoPlayer() {
        File file = new File(Environment.getExternalStorageDirectory() + "/DCIM/Camera/" + "VID_20160211_105015.mp4");
        videoView.setVideoPath(file.getPath());
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btn_play: {
                if (!videoView.isPlaying()) {
                    videoView.start();
                }
                break;
            }
            case R.id.btn_pause: {
                if (videoView.isPlaying()) {
                    videoView.pause();
                }
                break;
            }
            case R.id.btn_replay: {
                if (videoView.isPlaying()) {
                    videoView.resume();
                }
                break;
            }
            default:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (videoView != null) {
            videoView.suspend();
        }
    }
}
