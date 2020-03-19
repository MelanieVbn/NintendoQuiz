package com.codingmel.nintendoquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;

import com.q42.android.scrollingimageview.ScrollingImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        final MediaPlayer mp = MediaPlayer.create(MainActivity.this, R.raw.mario_medley);
        mp.setLooping(true);
        mp.start();

        /*ObjectAnimator animator = ObjectAnimator.ofFloat(R.id.titleGameTextView,"translationY",-500f);
        animator.setDuration(5000);
        animator.start();*/

        setContentView(R.layout.activity_main);
        ScrollingImageView scrollingBackground = findViewById(R.id.bgImageView);
        scrollingBackground.stop();
        scrollingBackground.start();


        findViewById(R.id.aboutButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,AboutActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.playButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.stop();
                Intent intent = new Intent(MainActivity.this,AboutActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.allQuestionsButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, QuestionListActivity.class);
                startActivity(intent);
            }
        });

    }
}
