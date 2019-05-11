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
public class tambahImunisasi extends Fragment {

    //tanggal
    final Calendar myCalendar = Calendar.getInstance();

    //memanggil database
    DatabaseHelper myDB;
    bayi b;
    imunisasi i;

    //mendeklarasikan textPlan dan btn
    EditText editNamaBayi, editNamaIbu, editNamaAyah, editTanggalLahirBayi,editIdBayi
            ,editTanggalImunisasi, editTambah;
    Button btnSubmitBayi;
    Spinner jenis;

    public tambahImunisasi() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tambah_imunisasi, container, false);

        //mengaktifkan database
        myDB = new DatabaseHelper(getActivity());

        editNamaBayi = view.findViewById(R.id.editNamaBayi);
        editTanggalLahirBayi = view.findViewById(R.id.editTanggalLahirBayi);
        editTanggalImunisasi = view.findViewById(R.id.editTanggalImunisasi);
        editIdBayi = view.findViewById(R.id.editIdBayi);
        editNamaIbu = view.findViewById(R.id.editNamaIbu);
        editNamaAyah = view.findViewById(R.id.editNamaAyah);
        btnSubmitBayi = view.findViewById(R.id.btnSubmitBayi);
        jenis = view.findViewById(R.id.jenis);

        //Datepicker tanggal lahir
        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, month);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                String myFormat = "yyyy-MM-dd";
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
                editTanggalLahirBayi.setText(sdf.format(myCalendar.getTime()));

            }
        };

        editTanggalLahirBayi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(getActivity(), date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

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


        //memanngil kelas lain
        b = new bayi();
        i = new imunisasi();

        //memanggil fungsi addData
        addData();

        return view;
    }

    public bayi tambahDataBayi(){
        b.setIdBayi(Integer.parseInt(String.valueOf(editIdBayi.getText())));
        b.setNamaBayi(editNamaBayi.getText().toString());
        b.setTglLahir_Bayi(editTanggalLahirBayi.getText().toString());
        b.setNamaAyah_bayi(editNamaAyah.getText().toString());
        b.setNamaIbu_bayi(editNamaIbu.getText().toString());
        return b;
    }

    public imunisasi tambahDataImunisasi(){

        i.setId_bayi(Integer.parseInt(editIdBayi.getText().toString()));
        i.setTgl_imunisasi(editTanggalImunisasi.getText().toString());
        i.setJenis(jenis.getSelectedItem().toString());
        return i;
    }

    public void addData(){

        //membuat fungsi klik pada tombol
        btnSubmitBayi.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if(editIdBayi.getText().toString().length() == 0){
                            editIdBayi.setError("Wajib diisi");
                        } else if(editNamaBayi.getText().toString().length() == 0){
                            editNamaBayi.setError("Wajib diisi");
                        } else if(editTanggalLahirBayi.getText().toString().length() == 0){
                            editTanggalLahirBayi.setError("Wajib diisi");
                        } else if(editTanggalImunisasi.getText().toString().length() == 0){
                            editTanggalImunisasi.setError("Wajib diisi");
                        } else if(editNamaAyah.getText().toString().length() == 0){
                            editNamaAyah.setError("Wajib diisi");
                        } else if(editNamaIbu.getText().toString().length() == 0){
                            editNamaIbu.setError("Wajib diisi");
                        } else {
                            //mendeklarasikan insertDataPasien
                            boolean insertDataBayi = myDB.insertDataBayi(tambahDataBayi());

                            //mendeklarasikan inserDataPenyakit
                            boolean insertDataImunisasi = myDB.insertDataImunisasi(tambahDataImunisasi());

                             /* jika data berhasil ditambahkan maka akan muncul toast data tersimpan
                                sedangkan jika data belum ditambahkan maka akan muncul toast gagal
                            */

                            if (insertDataBayi == true && insertDataImunisasi == true) {
                                Toast.makeText(getActivity(), "Tersimpan", Toast.LENGTH_LONG).show();
                                editNamaBayi.getText().clear();
                                editTanggalLahirBayi.getText().clear();
                                editIdBayi.getText().clear();
                                editNamaIbu.getText().clear();
                                editNamaAyah.getText().clear();
                                editTanggalImunisasi.getText().clear();

                            } else {
                                Toast.makeText(getActivity(), "Gagal", Toast.LENGTH_LONG).show();
                            }

                        }

                    }
                }
        );
    }

}
