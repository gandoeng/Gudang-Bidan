package com.example.gudangbidan;

public class imunisasi {
    String tgl_imunisasi, jenis_imunisasi;
    int id_bayi;

    public imunisasi(){

    }

    public imunisasi (String tgl_imunisasi, String jenis_imunisasi, int id_bayi){
        this.id_bayi = id_bayi;
        this.jenis_imunisasi = jenis_imunisasi;
        this.tgl_imunisasi = tgl_imunisasi;
    }

    public void setTgl_imunisasi (String tgl_imunisasi){
        this.tgl_imunisasi = tgl_imunisasi;
    }

    public void setJenis_imunisasi (String jenis_imunisasi){
        this.jenis_imunisasi = jenis_imunisasi;
    }

    public void setId_bayi (int id_bayi){
        this.id_bayi = id_bayi;
    }

    public String getTgl_imunisasi (){
        return tgl_imunisasi;
    }
    public String getJenis_imunisasi (){
        return jenis_imunisasi;
    }
    public int getId_bayi(){
        return id_bayi;
    }


}
