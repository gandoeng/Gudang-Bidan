package com.example.gudangbidan;

public class imunisasi {
    String tgl_imunisasi, jenis;
    int id_bayi;

    public imunisasi(){

    }

    public imunisasi (String tgl_imunisasi, String jenis){

        this.tgl_imunisasi = tgl_imunisasi;
        this.jenis = jenis;

    }

    //setter
    public void setTgl_imunisasi (String tgl_imunisasi){
        this.tgl_imunisasi = tgl_imunisasi;
    }

    public void setJenis (String jenis) {
        this.jenis = jenis;
    }

    public void setId_bayi(int id_bayi) {
        this.id_bayi = id_bayi;
    }

    //getter
    public String getTgl_imunisasi (){
        return tgl_imunisasi;
    }

    public String getJenis() {
        return jenis;
    }

    public int getId_bayi() {
        return id_bayi;
    }
}
