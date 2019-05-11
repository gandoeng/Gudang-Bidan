package com.example.gudangbidan;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.bumptech.glide.Glide;


public class Profil extends AppCompatActivity {
    GoogleSignInClient mGoogleSignInClient;
    Button SignOut;
    TextView nameprofil;
    TextView emailprofil;
    ImageView fotoprofil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);
        nameprofil = findViewById(R.id.profilname);
        emailprofil = findViewById(R.id.profil_email);
        fotoprofil = findViewById(R.id.profile_image);
        SignOut = findViewById(R.id.buttonSignout);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleSignInClient =GoogleSignIn.getClient(this,gso);

        //memanggil session
        SharedPreferences mSettings = Profil.this.getSharedPreferences("akun", Context.MODE_PRIVATE);
        String uname = mSettings.getString("username","keluar");
        String pas = mSettings.getString("password","keluar");

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(Profil.this);
        if(acct !=null){
            String personName = acct.getDisplayName();
            String personEmail = acct.getEmail();
            Uri personPhoto = acct.getPhotoUrl();

            nameprofil.setText("Nama: " + personName);
            emailprofil.setText("Email: " + personEmail);
            Glide.with(this).load(personPhoto).into(fotoprofil);
        } else if ( uname != "keluar" && pas !="keluar"){
            nameprofil.setText("Nama : " +uname);
        }





        SignOut.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick (View view){
                sign_Out();

                //session menyimpan data login
                SharedPreferences mSettings = Profil.this.getSharedPreferences("akun", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = mSettings.edit();
                editor.putString("username", "keluar");
                editor.putString("password", "keluar");
                editor.apply();
            }//onClick
        });//setOnClick

    }

    private void sign_Out() {
        mGoogleSignInClient.signOut()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(Profil.this,"Berhasil Keluar",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(Profil.this, Login.class));
                        finish();
                    }
                });
    }//sign_out


}
