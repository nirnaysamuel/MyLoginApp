package com.example.myloginapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Registration extends AppCompatActivity {
    EditText act2_emailet,act2_passwordet,act2_repasswordet;
    Button act2_registerButton;

    public static final String myPreferences = "myPrefs";

    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        act2_emailet = (EditText)findViewById(R.id.act2emailet);
        act2_passwordet = (EditText)findViewById(R.id.act2passwordet);
        act2_repasswordet = (EditText)findViewById(R.id.act2repasswordet);
        act2_registerButton = (Button)findViewById(R.id.act2registerbt);

        Intent intent = getIntent();
        String text = intent.getStringExtra("username");
        act2_emailet.setText(text);

        sharedPreferences = getSharedPreferences(myPreferences, Context.MODE_PRIVATE);

        act2_registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveTosharedPreferences();
            }
        });
    }

    public void openActivity1(){
        Intent returnIntent = new Intent();
        //sending text to another activity
        returnIntent.putExtra("resultToMainActivity",act2_emailet.getText().toString());
        setResult(RESULT_OK,returnIntent);
        finish();
        //startActivity(intent);
    }
    public void saveTosharedPreferences(){
        String act2_getEmail = act2_emailet.getText().toString();
        String act2_getPassword = act2_passwordet.getText().toString();
        String act2_getrepassword = act2_repasswordet.getText().toString();

        if(act2_getEmail.equals("") || act2_getPassword.equals("") || act2_getrepassword.equals("")){
            Toast.makeText(Registration.this,"All fields are mandatory!",Toast.LENGTH_LONG).show();
        }
        else{
            //if both the passwords matches
            if(act2_getPassword.equals(act2_getrepassword)){
                String passwordKey = act2_getEmail;
                //to check whether email (key) already exists in sharedPreferences or not
                if(sharedPreferences.contains(passwordKey)){
                    Toast.makeText(Registration.this,"Username already exist!",Toast.LENGTH_LONG).show();
                }
                else {
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    //saving values to sharedPreferences
                    editor.putString(passwordKey, act2_getPassword);
                    editor.commit();
                    Toast.makeText(Registration.this, "Successfully registered!", Toast.LENGTH_LONG).show();
                    openActivity1();
                }
            }
            else{
                Toast.makeText(Registration.this,"Password didn't match!",Toast.LENGTH_LONG).show();
                act2_passwordet.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                act2_repasswordet.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            }
        }
    }
}
