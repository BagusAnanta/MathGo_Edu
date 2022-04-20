package com.mathgoproject.mathgoedu;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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
    private static final String KEY_IMAGE = "jawabanimage";

    private static final String CREATE_JAWABAN_TABLECONTENT = "CREATE TABLE " + TABLE_CONTENT +
            " (" + KEY_ID + " INTEGER PRIMARY KEY,"
            + KEY_JAWABAN + " TEXT,"
            + KEY_IMAGE   + " IMAGE"
            + ")";


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

                } catch (Exception e){
                    // print at stacktrace
                }
            } while(cursor.moveToNext());
        }

        return datalist;
    }
}
