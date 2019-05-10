package com.example.gudangbidan;


import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;


/**
 * A simple {@link Fragment} subclass.
 */
public class updateImunisasi extends Fragment {
    //tanggal
    final Calendar myCalendar = Calendar.getInstance();

    //inisialisasi class DatabaseHelper
    DatabaseHelper myDB;

    //inisialisasi id dari atribut yang ingin dipanggil
    EditText editIdBayi,editTanggalImunisasi, imunisasiTambahan;
    Button tambah;

    //inisialisasi class penyakit
    imunisasi i;


    public updateImunisasi() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_update_imunisasi, container, false);
        setHasOptionsMenu(true);

        //memanggil class
        myDB = new DatabaseHelper(getActivity());
        i = new imunisasi();

        //memanggil atribut yang ada di layout
        editIdBayi = view.findViewById(R.id.editIdBayi);
        editTanggalImunisasi = view.findViewById(R.id.editTanggalImunisasi);
        imunisasiTambahan = view.findViewById(R.id.imunisasiTambahan);
        tambah = view.findViewById(R.id.btnSubmitBayi);


        //Datepicker tanggal periksa
        final DatePickerDialog.OnDateSetListener date2 = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, month);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                String myFormat = "yyyy-MM-dd";
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
                editTanggalImunisasi.setText(sdf.format(myCalendar.getTime()));

            }
        };

        editTanggalImunisasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(getActivity(), date2, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });


        //aksi tambah data
        //addData();

        return  view;
    }

    //simpan data ke class penyakit
    public imunisasi tambahBayi(){
        i.setId_bayi(Integer.parseInt(String.valueOf(editIdBayi.getText())));

        boolean cek = myDB.cekPasien(i.getId_bayi());

        if(cek == true){
            i.setTgl_imunisasi(editTanggalImunisasi.getText().toString());

            return  i;
        } else {
            return null;
        }
    }


}
