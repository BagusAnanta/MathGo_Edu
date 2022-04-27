package com.mathgoproject.mathgoedu;

public class Usergetsetdata {
    private int _id;
    private String _nama;
    private String _namasekolah;

    Usergetsetdata(){}

    Usergetsetdata(int _id,String _nama,String _namasekolah){
        this._id = _id;
        this._nama = _nama;
        this._namasekolah = _namasekolah;
    }

    Usergetsetdata(String _nama,String _namasekolah){
        this._nama = _nama;
        this._namasekolah = _namasekolah;
    }


    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
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
