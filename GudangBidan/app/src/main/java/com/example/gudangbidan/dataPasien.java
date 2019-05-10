package com.example.gudangbidan;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class dataPasien extends AppCompatActivity {

    //tanggal
    final Calendar myCalendar = Calendar.getInstance();

    //inisialisasi
    TextView editIdPasien;
    EditText editNama, editTanggalLahir;
    Button btnUpdatePasien, btnHapusPasien;

    //database
    DatabaseHelper db;

    //inisialisasi pasien
    pasien p;
    penyakit t;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_pasien);

        //memanggil pasien
        p = new pasien();
        t = new penyakit();

        //memanggil id
        editIdPasien = (TextView) findViewById(R.id.editIdPasien);
        editNama = (EditText) findViewById(R.id.editNama);
        editTanggalLahir = (EditText) findViewById(R.id.editTanggalLahir);
        btnHapusPasien = findViewById(R.id.btnHapusPasien);
        btnUpdatePasien = findViewById(R.id.btnUpdatePasien);


        //memanggil database
        db = new DatabaseHelper(dataPasien.this);

        //memanggil session
        SharedPreferences mSettings = this.getSharedPreferences("simpan", Context.MODE_PRIVATE);
        int id = mSettings.getInt("idPasien",0);

        //menampilkan data
        editIdPasien.setText(Integer.toString(db.cari(id).getIdPasien()));
        editNama.setText(db.cari(id).getNama());
        editTanggalLahir.setText(db.cari(id).getTanggal_lahir());

        //Datepicker tanggal lahir
        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, month);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                String myFormat = "yyyy-MM-dd";
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
                editTanggalLahir.setText(sdf.format(myCalendar.getTime()));

            }
        };

        editTanggalLahir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(dataPasien.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        //memanggil update data
        updateData();

        //memanggil hapus data
        hapusData();
    }

    public pasien dataBaru(){
        p.setNama(editNama.getText().toString());
        p.setTanggal_lahir(editTanggalLahir.getText().toString());
        p.setIdPasien(Integer.parseInt(editIdPasien.getText().toString()));

        return p;
    }

    public pasien idPasien(){
        pasien e = new pasien();
        e.setIdPasien(Integer.parseInt(editIdPasien.getText().toString()));
        return e;
    }

    public  penyakit idPenyakit(){
        t.setId_pasien(Integer.parseInt(editIdPasien.getText().toString()));

        return t;
    }

    public void updateData(){
        btnUpdatePasien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editNama.getText().toString().length() == 0){
                    editNama.setError("Wajib diisi");
                } else if(editTanggalLahir.getText().toString().length() == 0){
                    editTanggalLahir.setError("Wajib diisi");
                } else {

                    boolean updatePasien = db.updatePasien(dataBaru());

                    if(updatePasien == true){
                        Toast.makeText(dataPasien.this, "Update berhasil", Toast.LENGTH_LONG).show();
                    } else{
                        Toast.makeText(dataPasien.this, "Update gagal", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }

    public void hapusData(){
        btnHapusPasien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean hapusPasien = db.hapusPasien(idPasien());
                boolean hapusPenyakit = db.hapusPenyakit(idPenyakit());

                if(hapusPasien==true && hapusPenyakit == true){
                    Intent i = new Intent(dataPasien.this, MainActivity.class);
                    startActivity(i);

                    Toast.makeText(dataPasien.this,"Hapus berhasil",Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(dataPasien.this,"Hapus gagal", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
