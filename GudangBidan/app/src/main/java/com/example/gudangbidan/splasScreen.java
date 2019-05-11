package com.example.gudangbidan;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class splasScreen extends AppCompatActivity {

    private static int lama = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_splas_screen);



        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                    Intent i = new Intent(splasScreen.this, Login.class);

                    //menghubungkan activity splahscreen ke MainActivity
                    startActivity(i);

                    //jeda selesai splashScreen
                    this.finish();


            }

            private void finish(){

            }
        }, lama);

    }
}
