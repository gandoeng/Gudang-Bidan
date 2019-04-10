package com.example.gudangbidan;

public class pasien {
    int id;
    String nama,tanggal_lahir;
    int umur;


    //constructor
    public pasien(){

    }

    public pasien (String nama, int umur, String tanggal_lahir) {
        this.nama = nama;
        this.umur = umur;
        this.tanggal_lahir = tanggal_lahir;
    }

    //setters
    public void setId(int id){
        this.id = id;
    }

    public void setNama(String nama){
        this.nama = nama;
    }

    public void setUmur(int umur){
        this.umur = umur;
    }

    public void  setTanggal_lahir(String tanggal_lahir) {
        this.tanggal_lahir = tanggal_lahir;
    }

    //getters
    public int getId(){
        return this.id;
    }

    public String getNama(){
        return this.nama;
    }

    public int getUmur(){
        return this.umur;
    }

    public String getTanggal_lahir(){
        return this.tanggal_lahir;
    }

}
