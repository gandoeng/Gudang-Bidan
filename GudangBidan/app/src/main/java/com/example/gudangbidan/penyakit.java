package com.example.gudangbidan;

public class penyakit {

    String diagnosa,keluhan;

    //construktor
    public penyakit(){

    }

    //setter
    public void setDiagnosa(String diagnosa){
        this.diagnosa = diagnosa;
    }

    public void setKeluhan(String keluhan){
        this.keluhan = keluhan;
    }

    //getter
    public String getDiagnosa(){
        return this.diagnosa;
    }

    public String getKeluhan(){
        return this.keluhan;
    }

}
