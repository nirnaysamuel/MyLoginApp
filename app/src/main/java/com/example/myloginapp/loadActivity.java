package com.example.myloginapp;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class loadActivity extends AppCompatActivity {

    private static int time = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent show = new Intent(loadActivity.this,MainActivity.class);
                startActivity(show);
                finish();
            }
        },time);
    }
}
