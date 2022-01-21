package com.mathgoproject.mathgoedu;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

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

public class maingamedb extends SQLiteOpenHelper {
        private static final int DATABASE_VERSION = 1;
        private static final String DATABASE_NAME = "maingame.db";
        private static final String TABLE_CONTENT = "content";

        // TODO: (DATABASE KEY)
        private static final String KEY_ID = "id";
        private static final String KEY_IMAGE = "image";
        private static final String KEY_SOAL = "soal";
        private static final String KEY_PIL_A = "pil_a";
        private static final String KEY_PIL_B = "pil_b";
        private static final String KEY_PIL_C = "pil_c";
        private static final String KEY_PIL_D = "pil_d";
        private static final String KEY_JAWABAN = "jawaban";


    public maingamedb(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTENT_TABLE = "CREATE TABLE " + TABLE_CONTENT +
                "(" + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_IMAGE + " IMAGE,"
                + KEY_SOAL + " TEXT,"
                + KEY_PIL_A + " TEXT,"
                + KEY_PIL_B + " TEXT,"
                + KEY_PIL_C + " TEXT,"
                + KEY_PIL_D + " TEXT,"
                + KEY_JAWABAN + " TEXT"
                + ")";

        db.execSQL(CREATE_CONTENT_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTENT);
        onCreate(db);
    }

   void adddata(dbcontain contain){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_IMAGE,contain.get_image());
        values.put(KEY_SOAL,contain.get_Soal());
        values.put(KEY_PIL_A,contain.get_Pil_A());
        values.put(KEY_PIL_B,contain.get_Pil_B());
        values.put(KEY_PIL_C,contain.get_Pil_C());
        values.put(KEY_PIL_D,contain.get_Pil_D());
        values.put(KEY_JAWABAN,contain.get_Jawaban());

        db.insert(TABLE_CONTENT,null,values);
        db.close();
   }

   dbcontain getdata(int id){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_CONTENT,new String[]{KEY_ID,KEY_IMAGE,KEY_SOAL,KEY_PIL_A,KEY_PIL_B,KEY_PIL_C,KEY_PIL_D,KEY_JAWABAN}, KEY_ID + "=?",new String[]{String.valueOf(id)},null,null,null,null);

        if(cursor != null)
            cursor.moveToFirst();

            dbcontain dbcontain = new dbcontain(Integer.parseInt(cursor.getString(0)), // TODO: may can produce "NullPointerException"
                    cursor.getInt(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5),cursor.getString(6),cursor.getString(7));

            return dbcontain;
        }

   public List<dbcontain> getAlldata(){ // check this
        List<dbcontain> datalist = new ArrayList<dbcontain>();
        String SelectQuery = "SELECT * FROM " + TABLE_CONTENT;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(SelectQuery,null);

        if(cursor.moveToFirst()){
            do{
                dbcontain containdb = new dbcontain();
                containdb.set_id(Integer.parseInt(cursor.getString(0)));
                containdb.set_image(cursor.getInt(1));
                containdb.set_Soal(cursor.getString(2));
                containdb.set_Pil_A(cursor.getString(3));
                containdb.set_Pil_B(cursor.getString(4));
                containdb.set_Pil_C(cursor.getString(5));
                containdb.set_Pil_D(cursor.getString(6));
                containdb.set_Jawaban(cursor.getString(7));

                datalist.add(containdb);
            } while(cursor.moveToNext());
        }
        return datalist;
   }

   public int updatedata(dbcontain contain){
        SQLiteDatabase db = this.getWritableDatabase();

       ContentValues values = new ContentValues();
       values.put(KEY_IMAGE,contain.get_image());
       values.put(KEY_SOAL,contain.get_Soal());
       values.put(KEY_PIL_A,contain.get_Pil_A());
       values.put(KEY_PIL_B,contain.get_Pil_B());
       values.put(KEY_PIL_C,contain.get_Pil_C());
       values.put(KEY_PIL_D,contain.get_Pil_D());

       return db.update(TABLE_CONTENT,values,KEY_ID + " =?",new String[]{String.valueOf(contain.get_id())});
   }

   public void deletedata(dbcontain contain){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CONTENT,KEY_ID + " =?",new String[]{String.valueOf(contain.get_id())});
        db.close();
   }

   public void delete(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CONTENT,null,null);
        db.execSQL("delete from " + TABLE_CONTENT );
        db.close();
   }


   public long getprofilecount(){
        SQLiteDatabase db = this.getReadableDatabase();
        long count = DatabaseUtils.queryNumEntries(db,TABLE_CONTENT);
        db.close();
        return count;
   }

}
