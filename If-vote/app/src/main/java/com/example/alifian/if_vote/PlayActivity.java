package com.example.alifian.if_vote;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.WindowManager;
import android.widget.MediaController;
import android.widget.VideoView;

public class PlayActivity extends AppCompatActivity {
    VideoView videoView;
    public String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        Intent intent = getIntent();
        String url_get = intent.getStringExtra(Intent.EXTRA_TEXT);

        url = url_get.toString();
        videoView = (VideoView) findViewById(R.id.play_video);

        //setting media controller
        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(videoView);

        Uri uri = Uri.parse(url);

        //set the video
        videoView.setMediaController(mediaController);
        videoView.setVideoURI(uri);
//        videoView.requestFocus();
        videoView.start();
    }
}
