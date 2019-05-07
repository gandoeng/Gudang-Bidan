package com.example.gudangbidan;

public class pasien {
    int id_pasien;
    String nama,tanggal_lahir;


    //constructor
    public pasien(){

    }

    public pasien(String nama){
        this.nama = nama;
    }

    public pasien (int id_pasien,String nama, String tanggal_lahir, String status) {
        this.id_pasien = id_pasien;
        this.nama = nama;
        this.tanggal_lahir = tanggal_lahir;

    }

    //setters
    public void setIdPasien(int id_pasien){
        this.id_pasien = id_pasien;
    }

    public void setNama(String nama){
        this.nama = nama;
    }

    public void  setTanggal_lahir(String tanggal_lahir) {
        this.tanggal_lahir = tanggal_lahir;
    }



    //getters
    public int getId(){
        return this.id_pasien;
    }

    public String getNama(){
        return this.nama;
    }

    public String getTanggal_lahir(){
        return this.tanggal_lahir;
    }

    public int getIdPasien(){ return this.id_pasien;}


}
