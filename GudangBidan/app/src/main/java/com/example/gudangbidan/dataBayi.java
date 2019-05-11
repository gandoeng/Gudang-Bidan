package com.example.gudangbidan;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class dataBayi extends AppCompatActivity {

    //inisialisasi array
    private List<imunisasi> imunisasiList = new ArrayList<>();

    //inisialisasi Recyclerview
    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private imunisasiAdapter adapter;
    private TextView txt_resultAdapter;



    //tanggal
    final Calendar myCalendar = Calendar.getInstance();

    //inisialisasi
    TextView editIdBayi;
    EditText editNamaBayi, editNamaAyah, editNamaIbu, editTanggalLahir;
    Button btnUpdateBayi, btnHapusBayi;

    //database
    DatabaseHelper db;

    //inisialisasi pasien
    bayi b;
    imunisasi i;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_bayi);

        //memanggil pasien
        b = new bayi();
        i = new imunisasi();

        //memanggil id
        editIdBayi = (TextView) findViewById(R.id.editIdBayi);
        editNamaBayi = (EditText) findViewById(R.id.editNama);
        editNamaAyah = (EditText) findViewById(R.id.editAyah);
        editNamaIbu = (EditText) findViewById(R.id.editIbu);
        editTanggalLahir = (EditText) findViewById(R.id.editTanggalLahir);
        btnHapusBayi = findViewById(R.id.btnHapusBayi);
        btnUpdateBayi = findViewById(R.id.btnUpdateBayi);


        //memanggil database
        db = new DatabaseHelper(this);

        //memanggil session
        SharedPreferences mSettings = this.getSharedPreferences("simpan1", Context.MODE_PRIVATE);
        int id = mSettings.getInt("idBayi",0);

        //menampilkan data
        editIdBayi.setText(Integer.toString(db.cariBayi(id).getIdBayi()));
        editNamaBayi.setText(db.cariBayi(id).getNamaBayi());
        editNamaAyah.setText(db.cariBayi(id).getNamaAyah_bayi());
        editNamaIbu.setText(db.cariBayi(id).getNamaIbu_bayi());
        editTanggalLahir.setText(db.cariBayi(id).getTglLahir_Bayi());

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
                new DatePickerDialog(dataBayi.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        //memanggil update data
        updateData();

        //memanggil hapus data
        hapusData();

        //init recyclerview beserta adapternya
        recyclerView = findViewById(R.id.listP);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        imunisasiList = db.getImunisasi(Integer.parseInt(editIdBayi.getText().toString()));
        adapter = new imunisasiAdapter(imunisasiList);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        //memanggil data kosong
        txt_resultAdapter = findViewById(R.id.txtResultadapter);

        //mengecek apakah ada data pada recyclerview
        if (adapter.getItemCount() == 0){
            txt_resultAdapter.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        } else {
            txt_resultAdapter.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
        }
    }

    public bayi dataBaru(){
        b.setNamaBayi(editNamaBayi.getText().toString());
        b.setNamaAyah_bayi(editNamaAyah.getText().toString());
        b.setNamaIbu_bayi(editNamaIbu.getText().toString());
        b.setTglLahir_Bayi(editTanggalLahir.getText().toString());
        b.setIdBayi(Integer.parseInt(editIdBayi.getText().toString()));

        return b;
    }

    public bayi idBayi(){
        bayi e = new bayi();
        e.setIdBayi(Integer.parseInt(editIdBayi.getText().toString()));
        return e;
    }

    public  imunisasi idImunisasi(){
        i.setId_bayi(Integer.parseInt(editIdBayi.getText().toString()));

        return i;
    }

    public void updateData(){
        btnUpdateBayi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editNamaBayi.getText().toString().length() == 0){
                    editNamaBayi.setError("Wajib diisi");
                } else if(editNamaIbu.getText().toString().length() == 0){
                    editNamaIbu.setError("Wajib diisi");
                } else if(editNamaAyah.getText().toString().length() == 0){
                    editNamaAyah.setError("Wajib diisi");
                } else if(editTanggalLahir.getText().toString().length() == 0){
                    editTanggalLahir.setError("Wajib diisi");
                } else {

                    boolean updateBayi = db.updateBayi(dataBaru());

                    if(updateBayi == true){
                        Toast.makeText(dataBayi.this, "Update berhasil", Toast.LENGTH_LONG).show();
                    } else{
                        Toast.makeText(dataBayi.this, "Update gagal", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }

    public void hapusData(){
        btnHapusBayi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean hapusBayi = db.hapusBayi(idBayi());
                boolean hapusImunisasi = db.hapusImunisasi(idImunisasi());

                if(hapusImunisasi==true && hapusBayi == true){
                    Intent i = new Intent(dataBayi.this, MainActivity2.class);
                    startActivity(i);

                    Toast.makeText(dataBayi.this,"Hapus berhasil",Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(dataBayi.this,"Hapus gagal", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
