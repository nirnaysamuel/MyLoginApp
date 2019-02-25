package com.example.myloginapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Loggedin extends AppCompatActivity {

    TextView showUser;
    Button logoutbt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loggedin);

        showUser = (TextView) findViewById(R.id.act3emailtv);
        logoutbt = (Button) findViewById(R.id.act3logoutbt);

        Intent intent = getIntent();
        String message = intent.getStringExtra("welcomeUsername");
        showUser.setText("Welcome \n" + message);

        logoutbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
