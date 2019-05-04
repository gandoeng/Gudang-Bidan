package com.example.gudangbidan;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

public class Login extends AppCompatActivity {
    EditText mTextUsername;
    EditText mTextPassword;
    Button mButtonLogin;
    TextView mTextViewRegister;
    DatabaseHelper db;
    private static final String TAG = "AndroidClarified";
    private GoogleSignInClient googleSignInClient;
    ImageButton googleSignInButton;
    private GoogleSignInAccount googleSignInAccount;


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

        googleSignInButton = findViewById(R.id.imagebuttonGoogle);
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getResources().getString(R.string.idToken))
                .build();
        googleSignInClient = GoogleSignIn.getClient(this, gso);
        googleSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signInIntent = googleSignInClient.getSignInIntent();
                signInIntent.putExtra(Profil.GOOGLE_ACCOUNT, googleSignInAccount);
                startActivityForResult(signInIntent, 101);
            }
        });//onClick



    }//onCreate

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK)
            switch (requestCode) {
                case 101:
                    try {

                        Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
                        GoogleSignInAccount account = task.getResult(ApiException.class);
                        String idToken = account.getIdToken();
                        onLoggedIn(account);
                    } catch (ApiException e) {

                        Log.w(TAG, "signInResult:failed code=" + e.getStatusCode());
                    }
                    break;
            }
    }//onActivity

    private void onLoggedIn(GoogleSignInAccount googleSignInAccount) {
        Intent berandaintent = new Intent(this, BerandaLogin.class);
        startActivity(berandaintent);
    }//onLoggedIn

    @Override
    public void onStart() {
        super.onStart();
        GoogleSignInAccount alreadyloggedAccount = GoogleSignIn.getLastSignedInAccount(this);
        if (alreadyloggedAccount != null) {
            Toast.makeText(this, "Sudah Masuk", Toast.LENGTH_SHORT).show();
            onLoggedIn(alreadyloggedAccount);
        } else {
            Log.d(TAG, "Belum Masuk");
        }
    }




}//end