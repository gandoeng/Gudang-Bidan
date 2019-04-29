package com.example.gudangbidan;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 */

public class updateData extends Fragment {

    //inisialisasi class DatabaseHelper
    DatabaseHelper myDB;

    //inisialisasi id dari atribut yang ingin dipanggil
    EditText idPasien, keluhan, diagnosa;
    Button tambah;

    //inisialisasi class penyakit
    penyakit t;

    public updateData() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_update_data, container, false);

        //memanggil class
        myDB = new DatabaseHelper(getActivity());
        t = new penyakit();

        //memanggil atribut yang ada di layout
        idPasien = view.findViewById(R.id.editIdPasien);
        keluhan = view.findViewById(R.id.editKeluhan);
        diagnosa = view.findViewById(R.id.editDiagnosa);
        tambah = view.findViewById(R.id.btnSubmitPenyakit);

        return  view;
    }

    //simpan data ke class penyakit
    public penyakit tambahPenyakit(){
        t.setId_pasien(Integer.parseInt(String.valueOf(idPasien.getText())));
        t.setKeluhan(keluhan.getText().toString());
        t.setDiagnosa(diagnosa.getText().toString());

        return  t;
    }

    //tambah data ke kolom penyakit
    public void addData(){

        //membuat fungsi klik tombol
        tambah.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //menyimpan data ke dalam tabel penyakit
                        boolean insertDataPenyakit = myDB.insertDataPenyakit(tambahPenyakit());

                        //cek apakah berhasil ditambahkan atau tidak
                        if(insertDataPenyakit == true){
                            Toast.makeText(getActivity(),"Tersimpan",Toast.LENGTH_LONG).show();
                            idPasien.getText().clear();
                            keluhan.getText().clear();
                            diagnosa.getText().clear();
                        } else {
                            Toast.makeText(getActivity(),"Gagal",Toast.LENGTH_LONG).show();
                        }
                    }
                }
        );
    }



}
