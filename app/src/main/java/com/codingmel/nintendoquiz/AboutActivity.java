package com.codingmel.nintendoquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class AboutActivity extends AppCompatActivity {
    //ABOUT ACTIVITY
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        getSupportActionBar().hide();

        //Set text with App's config version
        TextView versionName = findViewById(R.id.versionTextView);
        versionName.setText("V. "+BuildConfig.VERSION_NAME);
    }
}
