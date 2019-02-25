package com.example.myloginapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText et_email,et_password;
    Button loginBtn;
    TextView signup;

    public static final String myPreferences = "myPrefs";
    public static int requestCode = 1;

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_email = (EditText)findViewById(R.id.emailet);
        et_password = (EditText)findViewById(R.id.passwordet);
        signup = (TextView)findViewById(R.id.signuptv);
        loginBtn = (Button)findViewById(R.id.loginbt);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                    openActivity2();
                }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                retrieveFromSharedPreferrence();
            }
        });
    }

    public void openActivity2(){
        et_password.setText("");
        Intent intent = new Intent(this,Registration.class);
        intent.putExtra("username",et_email.getText().toString());
        //To request for a result back from Registration
        startActivityForResult(intent,requestCode);
    }
    public void openActivity3(){
        Intent intent = new Intent(this,Loggedin.class);
        intent.putExtra("welcomeUsername",et_email.getText().toString());
        startActivity(intent);
    }
    public void retrieveFromSharedPreferrence(){
        String getEmail = et_email.getText().toString();
        String getPassword = et_password.getText().toString();

        if (getEmail.equals("") || getPassword.equals("")) {
            Toast.makeText(MainActivity.this,"Fields should not be empty!",Toast.LENGTH_LONG).show();
        }
        else {
            sharedPreferences = getSharedPreferences(myPreferences,Context.MODE_PRIVATE);
            //checking whether email is present already in sharedPreference
            if(sharedPreferences.contains(getEmail)){
                //storing the password for the email in a string
                String checkPassword = sharedPreferences.getString(getEmail,"");
                if(checkPassword.equals(getPassword)){
                    et_password.setText("");
                    openActivity3();
                }
                else{
                    Toast.makeText(MainActivity.this,"E-mail or password not found!",Toast.LENGTH_LONG).show();
                }
            }
            else{
                Toast.makeText(MainActivity.this,"E-mail or password not found!",Toast.LENGTH_LONG).show();
            }
        }
    }
    @Override//
    //To check the returned result
    protected void onActivityResult(int RequestCode, int resultCode, Intent data){
        if (RequestCode == requestCode) {
            if (resultCode == RESULT_OK) {
                String text = data.getStringExtra("resultToMainActivity");
                et_email.setText(text);
            }
        }
    }

}
