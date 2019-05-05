package com.example.gudangbidan;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Register extends AppCompatActivity {
    DatabaseHelper db;
    EditText mTextUsername;
    EditText mTextPassword;
    EditText mTextCnfPassword;
    Button mButtonRegister;
    TextView mTextViewLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        db = new DatabaseHelper(this);
        mTextUsername = (EditText)findViewById(R.id.editUsername);
        mTextPassword = (EditText)findViewById(R.id.editPassword);
        mTextCnfPassword = (EditText)findViewById(R.id.cnf_Password);
        mButtonRegister = (Button)findViewById(R.id.button_Register);
        mTextViewLogin = (TextView)findViewById(R.id.textviewLogin);
        mTextViewLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent RegistIntent = new Intent(Register.this,Login.class);
                startActivity(RegistIntent);
            }
        });//onClick

        mButtonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = mTextUsername.getText().toString();
                String pwd = mTextPassword.getText().toString().trim();
                String cnf_pwd = mTextCnfPassword.getText().toString().trim();

                if(mTextUsername.getText().toString().length() == 0){
                    mTextUsername.setError("Nama harus diisi");
                } else if (mTextPassword.getText().toString().length() == 0){
                    mTextPassword.setError("Password harus diisi");
                } else if (mTextCnfPassword.getText().toString().length() == 0){
                    mTextCnfPassword.setError("Verifikasi Pasword");
                } else {
                    if (pwd.equals(cnf_pwd)) {
                        boolean val = db.addUser(user, mTextPassword.getText().toString());
                        if (val == true) {
                            Toast.makeText(Register.this, "Berhasil Mendaftar", Toast.LENGTH_SHORT).show();
                            Intent moveToLogin = new Intent(Register.this, Login.class);
                            startActivity(moveToLogin);
                        } else {
                            Toast.makeText(Register.this, "Gagal Mendaftar", Toast.LENGTH_SHORT).show();
                        }

                    } else {
                        Toast.makeText(Register.this, "Kata Sandi Tidak Sama", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

}
