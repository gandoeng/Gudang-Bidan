package com.example.gudangbidan;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import com.example.gudangbidan.pasien;

public class DatabaseHelper extends SQLiteOpenHelper {
    //Deklarasi nama database
    public static final String DATABASE_NAME = "gudangBidan.db";
    private static final String LOG = "DatabaseHelper";

    //Deklarasi tabel pasien
    public static final String pasien = "pasien";

    //Deklarasi kolom tabel pasien
    public static final String id_pasien = "id_pasien";
    public static final String nama = "nama";
    public static final String tanggal_lahir = "tanggal_lahir";


    //Deklarasi tabel penyakit
    public static final String penyakit = "penyakit";

    //Deklarasi kolom tabel penyakit
    public static final String tanggal_periksa = "tanggal_periksa";
    public static final String diagnosa = "diagnosa";
    public static final String keluhan = "keluhan";
    public static final String id_pasien2 = "id_pasien2";

    //Deklarasi tabel user
    public static final String user = "user";

    //Deklarasi kolom tabel user
    public static final String id_user = "id_user";
    public static final String username = "username";
    public static final String password = "password";

    //Deklarasi tabel bayi
    public static final String bayi = "bayi";

    //Deklarasi kolom tabel bayi
    public static final String id_bayi = "id_bayi";
    public static final String nama_bayi = "nama_bayi";
    public static final String tgllahir_bayi = "tglLahir_bayi";
    public static final String namaIbu_bayi = "namaIbu_bayi";
    public static final String namaAyah_bayi = "namaAyah_bayi";

    //Deklarasi tabel imunisasi
    public static final String imunisasi = "imunisasi";

    //Deklarasi kolom tabel imunisasi
    public static final String tanggal_imunisasi = "tanggal_imunisasi";
    public static final String jenis_imunisasi = "jenis_imunisasi";
    public static final String id_bayi2 = "id_bayi2";


    //Setiap method ini dipanggil maka database akan terbentuk
    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 2);
    }


    //Membuat tabel
    @Override
    public void onCreate(SQLiteDatabase db) {

        //create table pasien
        db.execSQL(
                "CREATE TABLE " + pasien +
                "(" + id_pasien + " INTEGER PRIMARY KEY," + nama + " TEXT," + tanggal_lahir +
                        " DATE" + ");"
        );

        //create table penyakit
        db.execSQL(
                "CREATE TABLE " + penyakit +
                "(" + id_pasien2 + " INTEGER," + keluhan + " TEXT," + diagnosa + " TEXT,"+ tanggal_periksa+ " DATE, " +
                        "FOREIGN KEY ("+ id_pasien2 + ") REFERENCES " + pasien + "(" + id_pasien
                        + ") );"
        );

        //create table user
        db.execSQL(
                "CREATE TABLE " + user +
                        "(" + id_user + " INTEGER PRIMARY KEY AUTOINCREMENT," + username + " TEXT,"
                        + password + " TEXT" + " );"
        );

        //create table bayi
        db.execSQL(
                "CREATE TABLE " + bayi +
                        "(" + id_bayi + " INTEGER PRIMARY KEY," + nama_bayi + " TEXT," + tgllahir_bayi +
                        " DATE," + namaIbu_bayi + " TEXT," + namaAyah_bayi + " TEXT" + ");"
        );

        db.execSQL(
                "CREATE TABLE " + imunisasi +
                        "(" + id_bayi2 + " INTEGER," + jenis_imunisasi + " TEXT," + tanggal_imunisasi + " DATE, " +
                        "FOREIGN KEY ("+ id_bayi2 + ") REFERENCES " + bayi + "(" + id_bayi
                        + ") );"
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

        //drop tabel user
        db.execSQL(
                "DROP TABLE IF EXISTS " + user+";"
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

        long result = db.insert(pasien, null, contentValues);

        if(result == -1)
            return false;
        else
            return true;
    }

    //tampilkan semua data pasien
    public List<pasien> getNamaPasien(){
        List<pasien> listPasien = new ArrayList<>();

        String selectQuery = "SELECT * FROM "+ pasien;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if(cursor.moveToFirst()){
            do{
                pasien pas = new pasien(cursor.getInt(0),cursor.getString(1));
                listPasien.add(pas);
            } while (cursor.moveToNext());
        }

        return  listPasien;
    }

    //mengambil 1 data pasien
    public pasien cari(int id){
        String selectQuery = "SELECT * FROM "+ pasien + " WHERE id_pasien = " + id;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if(cursor.moveToFirst()){
            pasien pas = new pasien(cursor.getInt(0),cursor.getString(1),cursor.getString(2));
            return pas;
        } else {
            return null;
        }


    }

    //update data pasien

    public boolean updatePasien(pasien p){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(this.nama,p.getNama());
        contentValues.put(this.tanggal_lahir,p.getTanggal_lahir());

        long result = db.update(pasien, contentValues, "id_pasien = "+p.getIdPasien(), null );

        if(result == -1)
            return false;
        else
            return true;
    }

    //hapus pasien
    public boolean hapusPasien(pasien p){
        SQLiteDatabase db = this.getWritableDatabase();

        long result = db.delete(pasien,"id_pasien = " + p.getId(),null);

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
        contentValues.put(this.tanggal_periksa, t.getTanggal_periksa());

        long result = db.insert(penyakit, null, contentValues );

        if(result == -1)
            return false;
        else
            return true;
    }

    public boolean cekPasien(int p){
        SQLiteDatabase db = this.getWritableDatabase();
        int i = 0;

        //kode SQLite
        String SelectQuery = "SELECT * FROM "+penyakit+" WHERE "+id_pasien2+" = "+p;

        //menampilkan pesan error di log jika ada error
        Log.e(LOG, SelectQuery);

        Cursor c = db.rawQuery(SelectQuery, null);


        //looping data
        if(c.moveToFirst()){
            do{
                i=1;
            }while(c.moveToNext());
        }

        //cek
        if (i == 1)
            return true;
        else
            return false;
    }

    //hapus pasien
    public boolean hapusPenyakit(penyakit p){
        SQLiteDatabase db = this.getWritableDatabase();

        long result = db.delete(penyakit,"id_pasien2 = "+p.getId_pasien(),null);

        if(result == -1)
            return false;
        else
            return true;
    }

    // ---------------------------------------------- Methode tabel bayi ----------------------------------------------- //

    //method menambahkan data pada tabel bayi
    public boolean insertDataBayi (bayi n){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(this.id_bayi,n.getIdBayi());
        contentValues.put(this.nama_bayi, n.getNamaBayi());
        contentValues.put(this.namaIbu_bayi, n.getNamaIbu_bayi());
        contentValues.put(this.namaAyah_bayi, n.getNamaAyah_bayi());
        contentValues.put(this.tgllahir_bayi, n.getTglLahir_Bayi());

        long result = db.insert(bayi, null, contentValues);

        if(result == -1)
            return false;
        else
            return true;
    }

    // ---------------------------------------------- Methode tabel imunisasi --------------------------------------------- //

    public boolean insertDataImunisasi(imunisasi t){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(this.id_bayi2, t.getId_bayi());
        contentValues.put(this.tanggal_imunisasi,t.getTgl_imunisasi());
        contentValues.put(this.jenis_imunisasi, t.getJenis_imunisasi());

        long result = db.insert(imunisasi, null, contentValues );

        if(result == -1)
            return false;
        else
            return true;
    }

    public boolean cekBayi(int p){
        SQLiteDatabase db = this.getWritableDatabase();
        int i = 0;

        //kode SQLite
        String SelectQuery = "SELECT * FROM "+imunisasi+" WHERE "+id_bayi2+" = "+p;

        //menampilkan pesan error di log jika ada error
        Log.e(LOG, SelectQuery);

        Cursor c = db.rawQuery(SelectQuery, null);


        //looping data
        if(c.moveToFirst()){
            do{
                i=1;
            }while(c.moveToNext());
        }

        //cek
        if (i == 1)
            return true;
        else
            return false;
    }

    //hapus bayi
    public boolean hapusBayi(bayi p){
        SQLiteDatabase db = this.getWritableDatabase();

        long result = db.delete(bayi,"id_bayi2 = "+p.getIdBayi(),null);

        if(result == -1)
            return false;
        else
            return true;
    }


    // ---------------------------------------------- Methode tabel user --------------------------------------------- //


    public boolean addUser(String usernm, String pass){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(this.username,usernm);
        contentValues.put(this.password,pass);

        long result = db.insert(user,null,contentValues);

        if(result == -1)
            return false;
        else
            return true;
    }

    public boolean checkUser(String usernm, String pass){
        String[] columns = { id_user };
        SQLiteDatabase db = getReadableDatabase();
        String selection = username + "=?" + " and " + password + "=?";
        String[] selectionArgs = { usernm, pass };
        Cursor cursor = db.query(user,columns,selection,selectionArgs,null,null,null);
        int count = cursor.getCount();
        cursor.close();
        db.close();

        if(count>0)
            return  true;
        else
            return  false;
    }

}
