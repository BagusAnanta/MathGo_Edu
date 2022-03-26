package com.mathgoproject.mathgoedu;

public class Usergetsetdata {
    private int _id;
    private int _foto;
    private String _nama;
    private String _namasekolah;

    Usergetsetdata(){}

    Usergetsetdata(int _id,int _foto,String _nama,String _namasekolah){
        this._id = _id;
        this._foto = _foto;
        this._nama = _nama;
        this._namasekolah = _namasekolah;
    }

    Usergetsetdata(int _foto,String _nama,String _namasekolah){
        this._foto = _foto;
        this._nama = _nama;
        this._namasekolah = _namasekolah;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public int get_foto() {
        return _foto;
    }

    public void set_foto(int _foto) {
        this._foto = _foto;
    }

    public String get_nama() {
        return _nama;
    }

    public void set_nama(String _nama) {
        this._nama = _nama;
    }

    public String get_namasekolah() {
        return _namasekolah;
    }

    public void set_namasekolah(String _namasekolah) {
        this._namasekolah = _namasekolah;
    }
}
