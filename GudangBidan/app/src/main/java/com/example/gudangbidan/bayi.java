package com.example.gudangbidan;

public class bayi {
    int id_bayi;
    String nama_bayi,namaIbu_bayi, namaAyah_bayi, tglLahir_bayi;


    //constructor
    public bayi(){

    }

    public bayi(int id_bayi,String nama_bayi){
        this.nama_bayi = nama_bayi;
        this.id_bayi = id_bayi;
    }

    public bayi (int id_bayi,String nama_bayi, String tglLahir_bayi, String namaIbu_bayi, String namaAyah_bayi) {
        this.id_bayi = id_bayi;
        this.nama_bayi = nama_bayi;
        this.tglLahir_bayi = tglLahir_bayi;
        this.namaIbu_bayi = namaIbu_bayi;
        this.namaAyah_bayi = namaAyah_bayi;

    }

    //setters
    public void setIdBayi(int id_bayi){
        this.id_bayi = id_bayi;
    }

    public void setNamaBayi(String nama_bayi){
        this.nama_bayi = nama_bayi;
    }

    public void  setTglLahir_Bayi(String tglLahir_bayi) {
        this.tglLahir_bayi = tglLahir_bayi;
    }

    public void  setNamaIbu_bayi(String namaIbu_bayi) {
        this.namaIbu_bayi = namaIbu_bayi;
    }

    public void  setNamaAyah_bayi(String namaAyah_bayi) {
        this.namaAyah_bayi = namaAyah_bayi;
    }



    //getters
    public int getIdBayi(){
        return this.id_bayi;
    }

    public String getNamaBayi(){
        return this.nama_bayi;
    }

    public String getNamaIbu_bayi(){
        return this.namaIbu_bayi;
    }

    public String getNamaAyah_bayi(){
        return this.namaAyah_bayi;
    }

    public String getTglLahir_Bayi(){
        return this.tglLahir_bayi;
    }



}
