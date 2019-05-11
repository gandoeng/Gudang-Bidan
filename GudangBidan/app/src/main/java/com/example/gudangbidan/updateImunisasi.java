package com.example.gudangbidan;


import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

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
    EditText editIdBayi,editTanggalImunisasi;
    Button tambah;
    Spinner jenis;

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
        tambah = view.findViewById(R.id.btnSubmitBayi);
        jenis = view.findViewById(R.id.jenis);



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
        addData();

        return  view;
    }

    //simpan data ke class penyakit
    public imunisasi tambahBayi(){
        i.setId_bayi(Integer.parseInt(editIdBayi.getText().toString()));

        boolean cek = myDB.cekBayi(i.getId_bayi());

        if(cek == true){
            i.setTgl_imunisasi(editTanggalImunisasi.getText().toString());
            i.setJenis(jenis.getSelectedItem().toString());
            return  i;
        } else {
            return null;
        }
    }

    //tambah data ke kolom imunisasi
    public void addData(){

        //membuat fungsi klik tombol
        tambah.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if(editIdBayi.getText().toString().length() == 0){
                            editIdBayi.setError("Wajib Diisi");
                        } else if(editTanggalImunisasi.getText().toString().length() == 0){
                            editTanggalImunisasi.setError("Wajib diisi");
                        } else {
                            if (tambahBayi() == null) {
                                Toast.makeText(getActivity(), "No. Register tidak ditemukan",
                                        Toast.LENGTH_LONG).show();
                            } else {
                                //menyimpan data ke dalam tabel penyakit
                                boolean insertDataPenyakit = myDB.insertDataImunisasi(tambahBayi());

                                //cek apakah berhasil ditambahkan atau tidak
                                if (insertDataPenyakit == true) {
                                    Toast.makeText(getActivity(), "Tersimpan",
                                            Toast.LENGTH_LONG).show();
                                    editIdBayi.getText().clear();
                                    editTanggalImunisasi.getText().clear();
                                } else {
                                    Toast.makeText(getActivity(), "Gagal",
                                            Toast.LENGTH_LONG).show();
                                }
                            }

                        }

                    }
                }
        );
    }


}
