package com.example.gudangbidan;

public class penyakit {

    String diagnosa,keluhan;
    int id_pasien;
    String tanggal_periksa;

    //construktor
    public penyakit(){

    }

    public penyakit(int id_pasien, String diagnosa, String keluhan){
        this.id_pasien = id_pasien;
        this.keluhan = keluhan;
        this.diagnosa = diagnosa;
    }

    public penyakit(String keluhan, String diagnosa, String tanggal_periksa){
        this.keluhan = keluhan;
        this.diagnosa = diagnosa;
        this.tanggal_periksa = tanggal_periksa;
    }

    //setter
    public void setDiagnosa(String diagnosa){
        this.diagnosa = diagnosa;
    }

    public void setKeluhan(String keluhan){
        this.keluhan = keluhan;
    }

    public void setId_pasien(int id_pasien){ this.id_pasien = id_pasien; }

    public void setTanggal_periksa(String tanggal_periksa){ this.tanggal_periksa = tanggal_periksa; }

    //getter
    public String getDiagnosa(){
        return this.diagnosa;
    }

    public String getKeluhan(){
        return this.keluhan;
    }

    public int getId_pasien() { return this.id_pasien; }

    public String getTanggal_periksa() { return this.tanggal_periksa; }
}
