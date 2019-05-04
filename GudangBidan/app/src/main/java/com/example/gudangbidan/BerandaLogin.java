package com.example.gudangbidan;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.GridLayout;
import android.widget.Toast;

public class BerandaLogin extends AppCompatActivity {

    GridLayout mainGrid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beranda_login);

        mainGrid = (GridLayout) findViewById(R.id.mainGrid);

        setSingleEvent(mainGrid);
    }

    public void setSingleEvent(GridLayout mainGrid) {

        //loop child di main grid
        for (int i=0; i<mainGrid.getChildCount();i++){
            /*
                imunisasi index ke-0
                pasien umum index ke-1
                keluar index ke-2
                pengaturan index ke-3
             */

            if(i == 1) {
                // karena child adalah cardview maka panggil cardview
                CardView cardView = (CardView) mainGrid.getChildAt(i);
                cardView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent pasien = new Intent(BerandaLogin.this, MainActivity.class);
                        startActivity(pasien);
                    }
                });
            } else if (i == 2){
                // karena child adalah cardview maka panggil cardview
                CardView cardView = (CardView) mainGrid.getChildAt(i);
                cardView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent pasien = new Intent(BerandaLogin.this, Login.class);
                        startActivity(pasien);
                    }
                });
            } else if (i == 3) {
                // karena child adalah cardview maka panggil cardview
                CardView cardView = (CardView) mainGrid.getChildAt(i);
                cardView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent pasien = new Intent(BerandaLogin.this, Profil.class);
                        startActivity(pasien);
                    }
                });
            }
        }
    }
}
