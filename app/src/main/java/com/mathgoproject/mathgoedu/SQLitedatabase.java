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

class SQLitedatabase extends SQLiteOpenHelper { // Outer Class

    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_CONTENT = "tablecontent";

    // Database key ID
    private static final String KEY_ID = "id";
    private static final String KEY_IMAGE = "image";
    private static final String KEY_SOAL = "soal";
    private static final String KEY_PIL_A = "pil_a";
    private static final String KEY_PIL_B = "pil_b";
    private static final String KEY_PIL_C = "pil_c";
    private static final String KEY_PIL_D = "pil_d";
    private static final String KEY_JAWABAN = "jawaban";

    // inner class declaration

    public SQLitedatabase(@Nullable Context context,String DATABASE_NAME) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE_CONTENT = "CREATE TABLE " + TABLE_CONTENT +
                "(" + KEY_ID + "INTEGER PRIMARY KEY,"
                + KEY_IMAGE + " IMAGE,"
                + KEY_SOAL + " TEXT,"
                + KEY_PIL_A + " TEXT,"
                + KEY_PIL_B + " TEXT,"
                + KEY_PIL_C + " TEXT,"
                + KEY_PIL_D + " TEXT,"
                + KEY_JAWABAN + " TEXT"
                + ")";

        db.execSQL(CREATE_TABLE_CONTENT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTENT);
        onCreate(db);
    }

    void adddata(dbcontain contain) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("KEY_IMAGE", contain.get_image());
        values.put(KEY_SOAL, contain.get_Soal());
        values.put(KEY_PIL_A, contain.get_Pil_A());
        values.put(KEY_PIL_B, contain.get_Pil_B());
        values.put(KEY_PIL_C, contain.get_Pil_C());
        values.put(KEY_PIL_D, contain.get_Pil_D());
        values.put(KEY_JAWABAN, contain.get_Jawaban());

        db.insert(TABLE_CONTENT, null, values);
        db.close();
    }

    public List<dbcontain> getAlldata() {
        List<dbcontain> datalist = new ArrayList<dbcontain>();
        String SelectQuery = "SELECT * FROM " + TABLE_CONTENT;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(SelectQuery, null);

        if (cursor.moveToFirst()) {
            do {
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
            } while (cursor.moveToNext());
        }
        return datalist;
    }

    public int updatedata(dbcontain contain) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_IMAGE, contain.get_image());
        values.put(KEY_SOAL, contain.get_Soal());
        values.put(KEY_PIL_A, contain.get_Pil_A());
        values.put(KEY_PIL_B, contain.get_Pil_B());
        values.put(KEY_PIL_C, contain.get_Pil_C());
        values.put(KEY_PIL_D, contain.get_Pil_D());

        return db.update(TABLE_CONTENT, values, KEY_ID + " =?", new String[]{String.valueOf(contain.get_id())});
    }

    public void delete() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CONTENT, null, null);
        db.execSQL("delete from " + TABLE_CONTENT);
        db.close();
    }


    public long getprofilecount() {
        SQLiteDatabase db = this.getReadableDatabase();
        long count = DatabaseUtils.queryNumEntries(db, TABLE_CONTENT);
        db.close();
        return count;
    }
}
