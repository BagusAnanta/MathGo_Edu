package com.mathgoproject.mathgoedu;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class Jawabandatabase extends SQLiteOpenHelper {
    private static final String DB_NAMA = "Jawabandb.db";
    private static final int DB_VERSION = 1;
    private static final String TABLE_CONTENT = "jawabantablecontent";

    // KEY
    private static final String KEY_ID = "id";
    private static final String KEY_JAWABAN = "jawaban";
    private static final String KEY_OPSI = "opsijawaban";
    private static final String KEY_IMAGE = "jawabanimage";

    private static final String CREATE_JAWABAN_TABLECONTENT = "CREATE TABLE " + TABLE_CONTENT +
            " (" + KEY_ID + " INTEGER PRIMARY KEY,"
            + KEY_JAWABAN + " TEXT,"
            + KEY_OPSI    + " TEXT,"
            + KEY_IMAGE   + " IMAGE"
            + ")";

    // setter getter jawaban declaration
    Jawabangetsetdata jawabangetsetdata = new Jawabangetsetdata();


    public Jawabandatabase(@Nullable Context context) {
        super(context, DB_NAMA, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_JAWABAN_TABLECONTENT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTENT);
        onCreate(db);
    }

    void adddatajawaban(Jawabangetsetdata jawabangetsetdata){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_JAWABAN,jawabangetsetdata.get_jawaban());
        values.put(KEY_IMAGE,jawabangetsetdata.get_image_jawaban());
        values.put(KEY_OPSI,jawabangetsetdata.get_opsi_jawaban());

        db.insert(TABLE_CONTENT,null,values);
        db.close();
    }

    public List<Jawabangetsetdata> getalljawabandata(){
        List<Jawabangetsetdata> datalist = new ArrayList<Jawabangetsetdata>();
        String SelectQuery = "SELECT * FROM " + TABLE_CONTENT;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(SelectQuery,null);

        if(cursor.moveToFirst()){
            do{
                Jawabangetsetdata contentjawaban = new Jawabangetsetdata();

                try{
                    contentjawaban.set_id(Integer.parseInt(cursor.getString(0)));
                    contentjawaban.set_jawaban(cursor.getString(1));
                    contentjawaban.set_opsi_jawaban(cursor.getString(2));
                    contentjawaban.set_image_jawaban(cursor.getInt(3));
                } catch (Exception e){
                    // print at stacktrace
                    Log.v("Exception detected :", String.valueOf(e));
                }

                datalist.add(contentjawaban);
            } while(cursor.moveToNext());
        }
        return datalist;
    }

    public void delete_jawaban(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CONTENT,null,null);
        db.close();
    }

    public long getprofilecountjawaban(){
        SQLiteDatabase db = this.getReadableDatabase();
        long count = DatabaseUtils.queryNumEntries(db,TABLE_CONTENT);
        db.close();
        return count;
    }

    public void datajawabandb(int index){
        int profilecount = (int) getprofilecountjawaban();
        List<Jawabangetsetdata> jawabancontentlist = new ArrayList<Jawabangetsetdata>();

        if(profilecount == 0){
            // bakal create data disini
            datajawaban();
        } else { // kalo datanya sudah ada (if data is exists)
            jawabancontentlist = getalljawabandata();
            jawabangetsetdata = jawabancontentlist.get(index);
        }

        Log.v("List Size checker : ",String.valueOf(jawabancontentlist.size()));

        for(Jawabangetsetdata jawaban_data : jawabancontentlist){
            String log = "Id: " + jawaban_data.get_id() + ", Opsi_jawaban: " + jawaban_data.get_opsi_jawaban() + ", Jawaban: " + jawaban_data.get_jawaban() + ", Image: " + jawaban_data.get_image_jawaban();
            Log.d("Check fill data :", log);
        }
    }

    public void datajawaban(){
        // datanya masukkan disini (a data input in here)
        adddatajawaban(new Jawabangetsetdata("Agar kamu mudah menjawabnya kamu coba bisa membuat diagram seperti diatas! dapat dilihat bahwa tidak mungkin bus mencapai kota E dan kota C",R.drawable.easy_1,"A. Kota E"));
        adddatajawaban(new Jawabangetsetdata("Jika kita susun kartunya secara tersusun, maka dapat dilihat di gambar atas susunan kartunya",R.drawable.easy_2,"B. HATIHATIDIJALAN"));
        adddatajawaban(new Jawabangetsetdata("Jika Kita lihat dari urutan gambar diatas, maka nilai yang ketiga (ditengah) adalah 10, data dari mobil berwarna biru",R.drawable.easy_3,"B. Biru"));
        adddatajawaban(new Jawabangetsetdata("Kita dapat membandingkan antara petani A,B,dan C dengan cara sebagai berikut :\n" +
                "1. Petani A memiliki perhitungan sederhana, masing masing upah 50 ribu/hari sehingga memperoleh 500 ribu selama 10 hari\n" +
                "2. Petani B memiliki perhitungan rumit, jika dianalisa dapat dilihat sebagai berikut :\n" +
                "- Square dapat : 10+20+30+40+50+60+70+80+90+100\n" +
                "- Slide  dapat : 100+90+80+70+60+50+40+30+21+10\n" +
                "Dari data diatas selama 2 hari mereka mendua mendapatkan 110 ribu, yang artinya selama 10 hari mereka mendapatkan 1100 ribu, jika dibagi akan mendapatkan 550 ribu\n" +
                "3. Petani C memiliki perhitungan lebih susah lagi,dapat dilihat perhitungannya :\n" +
                "1 + 1 + 2 + 4 + 8 + 16 + 32 + 64 + 128 + 256 + 512 =\n" +
                " 2 + 2 + 4 + 8 + 16 + 32 + 64 + 128 + 256 + 512 =\n" +
                " 4 + 4 + 8 + 16 + 32 + 64 + 128 + 256 + 512 =\n" +
                "maka akan terlihat pola dan kita abaikan sisanya yang akan berakhir dengan 512 + 512 = 1024, jika dibagi maka setiap anak memperoleh 512",0,"B. Petani B"));
        adddatajawaban(new Jawabangetsetdata("Untuk jawaban, dapat dilihat melalui gambar diatas!",R.drawable.medium_2,"B. 6 Bagian"));
        adddatajawaban(new Jawabangetsetdata("Jika kamu analisa dengan melihat jalannya, maka akan mendapatkan jawaban seperti gambar diatas",R.drawable.medium_3,"B. B"));
        adddatajawaban(new Jawabangetsetdata("Untuk menganalisa jawaban soal ini, kita akan membandingkan pasangan sub-string, dimulai dari karakter T awal dan berakhir sebelum T berikutnya" +
                "TCTACTAACCTACTAACAC\n" +
                "TCTACTAACCTACTAACAC\n" +
                "TCTACTAACCTACTAACAC\n" +
                "TCTACTAACCTACTAACAC\n" +
                "Dapat dilihat bahwa T hanya terdapat randem dengan satu huruf.\n" +
                "Kemudian, bandingkan sub string yang mulai dengan karakter kedua C dengan cara yang sama\n" +
                "TCTACTAACCTACTAACAC randem dengan panjang=6\n" +
                "TCTACTAACCTACTAACAC\n" +
                "TCTACTAACCTACTAACAC randem dengan panjang =16\n" +
                "Untuk menentukan semua randem, kita bisa memakai algoritma yang dimana beg_1 posisi pertama dari sub string pertama dan beg_2 posisi pertama dari sub string kedua.\n" +
                "poz_1 dan poz_2 adalah posisi saat ini dari setiap sub-string. A(pos_1) dan A(poz_2) adalah\n" +
                "karakter yang ada pada posisi yang sama pada setiap sub-string.\n",0,"A. 10 atau lebih"));
        adddatajawaban(new Jawabangetsetdata("Dapat dilihat dari tabel diatas,BEAR akan membentuk kode pada gambar diatas",R.drawable.hard_2,"C. 9 huruf"));
        adddatajawaban(new Jawabangetsetdata("Dilihat dari gambar diatas, maka dapat disimpulkan kita bisa mengumpulkan permen sebanyak 14 permen",R.drawable.hard_3,"A. 14 permen"));

    }

    // called in Maingameactivity
    public void setjawabancontent(int index, TextView opsi_benar, TextView jawaban, ImageView image_jawaban){
        int profilecount = (int) getprofilecountjawaban();

        if(index <= profilecount){
            opsi_benar.setText(getalljawabandata().get(index).get_opsi_jawaban());
            jawaban.setText(getalljawabandata().get(index).get_jawaban());
            image_jawaban.setImageResource(getalljawabandata().get(index).get_image_jawaban());
        }
    }
}
