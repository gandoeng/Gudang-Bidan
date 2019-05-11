package com.example.gudangbidan;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {

    private TextView mTextMessage;
    private final String TAG ="MainActivity2";

    //Memdeklarasikan database yang sudah dibuat
    DatabaseHelper myDB;

    //membuat bottom navbar
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    setTitle("Data");
                    namaBayi fragment3 = new namaBayi();
                    FragmentTransaction fragmentTransaction3 = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction3.replace(R.id.fram, fragment3, "Beranda");
                    fragmentTransaction3.commit();
                    return true;
                case R.id.navigation_pasien:
                    setTitle("Imunisasi");
                    updateImunisasi fragment2 = new updateImunisasi();
                    FragmentTransaction fragmentTransaction2 = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction2.replace(R.id.fram, fragment2, "Diagnosa");
                    fragmentTransaction2.commit();
                    return true;
                case R.id.navigation_tambahData:
                    setTitle("Tambah Data");
                    tambahImunisasi fragment = new tambahImunisasi();
                    FragmentTransaction fragmentTransaction1 = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction1.replace(R.id.fram, fragment, "Tambah Data");
                    fragmentTransaction1.commit();
                    return true;
            }
            return false;
        }
    };

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //back activity
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);

        //bottom navigation
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


        //Membentuk database
        myDB = new DatabaseHelper(this);


        //tampilan pertama kali
        setTitle("Beranda");
        namaBayi fragment3 = new namaBayi();
        FragmentTransaction fragmentTransaction3 = getSupportFragmentManager().beginTransaction();
        fragmentTransaction3.replace(R.id.fram, fragment3, "Beranda");
        fragmentTransaction3.commit();

    }

    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() != 0) {
            getFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }

}