package com.codingmel.nintendoquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.q42.android.scrollingimageview.ScrollingImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_main);
        ScrollingImageView scrollingBackground = findViewById(R.id.bgImageView);
        scrollingBackground.stop();
        scrollingBackground.start();
        /*ImageView backgroundImage = findViewById(R.id.bgImageView);
        backgroundImage.setImageResource(R.drawable.nintendo_characters);
        Animation animation = AnimationUtils.loadAnimation(MainActivity.this, R.anim.defilement_loop);
        backgroundImage.startAnimation(animation);*/
    }
}
