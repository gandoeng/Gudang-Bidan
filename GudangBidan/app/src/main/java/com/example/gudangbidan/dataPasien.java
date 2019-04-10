package com.example.gudangbidan;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class dataPasien extends AppCompatActivity {

    //memanggil database
    DatabaseHelper myDB;

    //mendeklarasikan textPlan dan btn
    EditText editNama,editTanggalLahir,editUmur;
    Button btnSubmitPasien;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_pasien);

        //mengaktifkan database
        myDB = new DatabaseHelper(this);

        /* menyimpan textPlan dan btn dari file data_pasien.xml ke dalam
           textPlan dan btn yang dideklarasikan disini.
         */

        editNama = (EditText) findViewById(R.id.editNama);
        editTanggalLahir = (EditText) findViewById(R.id.editTanggalLahir);
        editUmur = (EditText) findViewById(R.id.editUmur);


    }
}
