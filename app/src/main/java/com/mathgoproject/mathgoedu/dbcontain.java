package com.mathgoproject.mathgoedu;

/**
 * =============
 * UNDER DEVELOP
 * =============
 * Pesan :
 * 1. Semangat buat perbaiki perlu
 * 2. Instirahat perlu
 * 3. Stuck ? Jangan paksa ngegame aja dulu atau ngeIG
 * 4. Cari solusi di Google
 * ===================================================
 *                  Important Notice
 * This code under develope by Bagussoftwarefoundation
 * for copy this code or publish this code, you must
 * have permission developer
 * ==================================================
 * DO NOT SHARE AND PUBLISH THIS CODE !!!
 * ==================================================
 */

public class dbcontain  {
    private int _id;
    private int _image;
    private String _Soal;
    private String _Pil_A;
    private String _Pil_B;
    private String _Pil_C;
    private String _Pil_D;
    private String _Jawaban;

    dbcontain(){}

     dbcontain(int _id,int _image, String _Soal, String _Pil_A, String _Pil_B, String _Pil_C, String _Pil_D, String _Jawaban) {
        this._id = _id;
        this._image = _image;
        this._Soal = _Soal;
        this._Pil_A = _Pil_A;
        this._Pil_B = _Pil_B;
        this._Pil_C = _Pil_C;
        this._Pil_D = _Pil_D;
        this._Jawaban = _Jawaban;
    }

     dbcontain(int _image,String _Soal,String _Pil_A,String _Pil_B,String _Pil_C,String _Pil_D,String _Jawaban){
        this._image = _image;
        this._Soal = _Soal;
        this._Pil_A = _Pil_A;
        this._Pil_B = _Pil_B;
        this._Pil_C = _Pil_C;
        this._Pil_D = _Pil_D;
        this._Jawaban = _Jawaban;
    }


    public int get_id() {
        return this._id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public int get_image() {
        return this._image;
    }

    public void set_image(int _image) {
        this._image = _image;
    }

    public String get_Soal() {
        return this._Soal;
    }

    public void set_Soal(String _Soal) {
        this._Soal = _Soal;
    }

    public String get_Pil_A() {
        return this._Pil_A;
    }

    public void set_Pil_A(String _Pil_A) {
        this._Pil_A = _Pil_A;
    }

    public String get_Pil_B() {
        return this._Pil_B;
    }

    public void set_Pil_B(String _Pil_B) {
        this._Pil_B = _Pil_B;
    }

    public String get_Pil_C() {
        return this._Pil_C;
    }

    public void set_Pil_C(String _Pil_C) {
        this._Pil_C = _Pil_C;
    }

    public String get_Pil_D() {
        return this._Pil_D;
    }

    public void set_Pil_D(String _Pil_D) {
        this._Pil_D = _Pil_D;
    }

    public String get_Jawaban() {
        return this._Jawaban;
    }

    public void set_Jawaban(String _Jawaban) {
        this._Jawaban = _Jawaban;
    }
}
