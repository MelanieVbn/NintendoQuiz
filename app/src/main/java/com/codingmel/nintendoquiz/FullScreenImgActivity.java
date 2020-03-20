package com.codingmel.nintendoquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

public class FullScreenImgActivity extends AppCompatActivity {
    //FULL SCREEN IMG ACTIVITY
    //This activity is only for show img in full screen
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_screen_img);
        getSupportActionBar().hide();//Hide action bar

        Intent srcIntent = getIntent();//Get the parent Intent
        int imgId = srcIntent.getIntExtra("imgId",R.drawable.red_mushroom);//get Extra from the parent Intent
        ImageView img = findViewById(R.id.fullScreenImageView);
        img.setImageResource(imgId);
        //Set img with image id that's come from parent intent extra
    }
}
