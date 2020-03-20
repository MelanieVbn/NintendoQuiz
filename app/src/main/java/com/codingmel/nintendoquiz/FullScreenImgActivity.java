package com.codingmel.nintendoquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

public class FullScreenImgActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_screen_img);
        getSupportActionBar().hide();

        Intent srcIntent = getIntent();
        int imgId = srcIntent.getIntExtra("imgId",R.drawable.red_mushroom);
        ImageView img = findViewById(R.id.fullScreenImageView);
        img.setImageResource(imgId);
    }
}
