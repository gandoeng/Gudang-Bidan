package com.example.gudangbidan;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    //Deklarasi nama database
    public static final String DATABASE_NAME = "gudangBidan.db";

    //Deklarasi tabel pasien
    public static final String pasien = "pasien";

    //Deklarasi kolom tabel pasien
    public static final String id_pasien = "id_pasien";
    public static final String nama = "nama";
    public static final String tanggal_lahir = "tanggal_lahir";
    public static final String umur = "umur";

    //Deklarasi tabel penyakit
    public static final String penyakit = "penyakit";

    //Deklarasi kolom tabel penyakit
    public static final String diagnosa = "diagnosa";
    public static final String keluhan = "keluhan";
    public static final String id_pasien2 = "id_pasien2";


    //Setiap method ini dipanggil maka database akan terbentuk
    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }


    //Membuat tabel
    @Override
    public void onCreate(SQLiteDatabase db) {

        //create table pasien
        db.execSQL(
                "CREATE TABLE " + pasien +
                "(" + id_pasien + " INTEGER PRIMARY KEY," + nama + " TEXT," + tanggal_lahir +
                        " TEXT,"+umur+" INTEGER" + ");"
        );

        //create table penyakit
        db.execSQL(
                "CREATE TABLE " + penyakit +
                "(" + id_pasien2 + " INTEGER," + keluhan + " TEXT," + diagnosa + " TEXT," +
                        "FOREIGN KEY ("+ id_pasien2 + ") REFERENCES " + pasien + "(" + id_pasien + ") );"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //on upgrade drop older table

        //drop tabel pasien
        db.execSQL(
                "DROP TABLE IF EXISTS " + pasien +";"
        );

        //drop tabel penyakit
        db.execSQL(
                "DROP TABLE IF EXISTS " + penyakit+";"
        );

        //create table
        onCreate(db);
    }

    // ---------------------------------------------- Methode tabel pasien ----------------------------------------------- //

    //method menambahkan data pada tabel pasien
    public boolean insertDataPasien (pasien n){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(this.id_pasien,n.getId());
        contentValues.put(this.nama, n.getNama());
        contentValues.put(this.tanggal_lahir, n.getTanggal_lahir());
        contentValues.put(this.umur, n.getUmur());

        long result = db.insert(pasien, null, contentValues);

        if(result == -1)
            return false;
        else
            return true;
    }

    // ---------------------------------------------- Methode tabel penyakit --------------------------------------------- //

    public boolean insertDataPenyakit(penyakit t){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(this.id_pasien2, t.getId_pasien());
        contentValues.put(this.diagnosa,t.getDiagnosa());
        contentValues.put(this.keluhan, t.getKeluhan());

        long result = db.insert(penyakit, null, contentValues );

        if(result == -1)
            return false;
        else
            return true;
    }
}
