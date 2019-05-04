package com.example.gudangbidan;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Login extends AppCompatActivity {
    EditText mTextUsername;
    EditText mTextPassword;
    Button mButtonLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mTextUsername = (EditText) findViewById(R.id.editUsername);
        mTextPassword = (EditText) findViewById(R.id.editPassword);
        mButtonLogin = (Button) findViewById(R.id.buttonLogin);

        mButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent LoginIntent = new Intent(Login.this, MainActivity.class);
                startActivity(LoginIntent);
            }
        });//onClick

    }//onCreate

}//end
