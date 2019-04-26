package com.example.gudangbidan;


import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
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
public class tambahData extends Fragment {
    //memanggil database
    DatabaseHelper myDB;
    pasien p;
    penyakit t;

    //mendeklarasikan textPlan dan btn
    EditText editNama,editTanggalLahir,editUmur,editIdPasien,editKeluhan,editDiagnosa;
    Button btnSubmitPasien;

    public tambahData() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tambah_data2, container, false);

        //mengaktifkan database
        myDB = new DatabaseHelper(getActivity());

        editNama = (EditText) view.findViewById(R.id.editNama);
        editTanggalLahir = (EditText) view.findViewById(R.id.editTanggalLahir);
        editUmur = (EditText) view.findViewById(R.id.editUmur);
        editIdPasien = (EditText) view.findViewById(R.id.editIdPasien);
        editKeluhan = (EditText) view.findViewById(R.id.editKeluhan);
        editDiagnosa = (EditText) view.findViewById(R.id.editDiagnosa);
        btnSubmitPasien = (Button) view.findViewById(R.id.btnSubmitPasien);

        //memanngil kelas lain
        p = new pasien();
        t = new penyakit();

        //memanggil fungsi addData
        addData();

        return view;
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
                            Toast.makeText(getActivity(),"Tesimpan",Toast.LENGTH_LONG).show();
                            editNama.getText().clear();
                            editTanggalLahir.getText().clear();
                            editUmur.getText().clear();
                            editIdPasien.getText().clear();
                            editKeluhan.getText().clear();
                            editDiagnosa.getText().clear();
                        } else {
                            Toast.makeText(getActivity(),"Gagal",Toast.LENGTH_LONG).show();
                        }

                    }
                }
        );
    }

}