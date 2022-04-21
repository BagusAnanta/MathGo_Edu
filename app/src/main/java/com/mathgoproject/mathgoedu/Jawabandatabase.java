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
    private int index = 0;


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

    public void datajawabandb(){
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
    }

    // called in Maingameactivity
    public void setjawabancontent(int index, TextView opsi_benar, TextView jawaban, ImageView image_jawaban){
        int profilecount = (int) getprofilecountjawaban();

        if(index <= profilecount){
            opsi_benar.setText(getalljawabandata().get(index).get_opsi_jawaban());
            jawaban.setText(getalljawabandata().get(index).get_jawaban());
            image_jawaban.setImageResource(getalljawabandata().get(index).get_image_jawaban());
        }
        index++;
    }
}
