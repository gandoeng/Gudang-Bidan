package com.example.gudangbidan;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class dataPasien extends AppCompatActivity {

    //memanggil database
    DatabaseHelper myDB;
    pasien p;
    penyakit t;

    //mendeklarasikan textPlan dan btn
    EditText editNama,editTanggalLahir,editUmur,editIdPasien,editKeluhan,editDiagnosa;
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
        editIdPasien = (EditText) findViewById(R.id.editIdPasien);
        editKeluhan = (EditText) findViewById(R.id.editKeluhan);
        editDiagnosa = (EditText) findViewById(R.id.editDiagnosa);
        btnSubmitPasien = (Button) findViewById(R.id.btnSubmitPasien);

        //memanggil fungsi addData
        addData();


    }

    public pasien tambahDataPasien(){
        p.setIdPasien(Integer.parseInt(String.valueOf(editIdPasien.getText())));
        p.setNama(editNama.getText().toString());
        p.setTanggal_lahir(editTanggalLahir.getText().toString());
        p.setUmur(Integer.parseInt(String.valueOf(editUmur.getText())));
        return p;
    }

    public penyakit tambahDataPenyakit(){
        t.setDiagnosa(editDiagnosa.getText().toString());
        t.setKeluhan(editKeluhan.getText().toString());
        t.setId_pasien(p.getIdPasien());
        return t;
    }

    public void addData(){

        //membuat fungsi klik pada tombol
        btnSubmitPasien.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //mendeklarasikan insertDataPasien
                        boolean insertDataPasien = myDB.insertDataPasien(tambahDataPasien());

                        //mendeklarasikan inserDataPenyakit
                        boolean insertDataPenyakit = myDB.insertDataPenyakit(tambahDataPenyakit());

                        /* jika data berhasil ditambahkan maka akan muncul toast data tersimpan
                            sedangkan jika data belum ditambahkan maka akan muncul toast gagal
                         */

                        if(insertDataPasien == true && insertDataPenyakit == true){
                            Toast.makeText(dataPasien.this,"Tesimpan",Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(dataPasien.this,"Gagal",Toast.LENGTH_LONG).show();
                        }

                    }
                }
        );
    }
}
