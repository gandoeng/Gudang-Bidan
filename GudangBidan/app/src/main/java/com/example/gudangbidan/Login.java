package com.example.gudangbidan;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    EditText mTextUsername;
    EditText mTextPassword;
    Button mButtonLogin;
    TextView mTextViewRegister;
    DatabaseHelper db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        db = new DatabaseHelper(this);

        mTextUsername = (EditText) findViewById(R.id.editUsername);
        mTextPassword = (EditText) findViewById(R.id.editPassword);
        mButtonLogin = (Button) findViewById(R.id.buttonLogin);
        mTextViewRegister = (TextView)findViewById(R.id.textviewRegister);
        mTextViewRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerIntent = new Intent(Login.this,Register.class);
                startActivity(registerIntent);
            }
        });//onClick


        mButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usernm = mTextUsername.getText().toString().trim();
                String pwd = mTextPassword.getText().toString().trim();
                Boolean res = db.checkUser(usernm, pwd);
                if(res == true)
                {
                    Intent LoginIntent = new Intent(Login.this,BerandaLogin.class);
                    startActivity(LoginIntent);
                }
                else
                {
                    Toast.makeText(Login.this,"Gagal Masuk",Toast.LENGTH_SHORT).show();
                }
            }
        });//onClick

    }//onCreate

}//end