package com.mathgoproject.mathgoedu;

public class Jawabangetsetdata {
    private int _id;
    private String _jawaban;
    private int _image_jawaban;
    private String _opsi_jawaban;

    public Jawabangetsetdata(){}

    public Jawabangetsetdata(int _id, String _jawaban, int _image_jawaban,String _opsi_jawaban) {
        this._id = _id;
        this._jawaban = _jawaban;
        this._image_jawaban = _image_jawaban;
        this._opsi_jawaban = _opsi_jawaban;
    }

    public Jawabangetsetdata(String _jawaban, int _image_jawaban, String _opsi_jawaban){
        this._jawaban = _jawaban;
        this._image_jawaban = _image_jawaban;
        this._opsi_jawaban = _opsi_jawaban;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String get_jawaban() {
        return _jawaban;
    }

    public void set_jawaban(String _jawaban) {
        this._jawaban = _jawaban;
    }

    public int get_image_jawaban() {
        return _image_jawaban;
    }

    public void set_image_jawaban(int _image_jawaban) {
        this._image_jawaban = _image_jawaban;
    }

    public String get_opsi_jawaban() {
        return _opsi_jawaban;
    }

    public void set_opsi_jawaban(String _opsi_jawaban) {
        this._opsi_jawaban = _opsi_jawaban;
    }
}
