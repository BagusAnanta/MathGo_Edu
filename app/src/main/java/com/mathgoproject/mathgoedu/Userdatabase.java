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

public class Userdatabase extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "userdata.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "userdatabase";

    private static final String KEY_ID = "id";
    private static final String KEY_FOTO = "foto";
    private static final String KEY_NAMA = "nama";
    private static final String KEY_NAMASEKOLAH = "nama_sekolah";

    // Table
    private static final String CREATE_CONTENT_DATABASE = "CREATE TABLE " + TABLE_NAME +
            " (" + KEY_ID + " INTEGER PRIMARY KEY,"
            + KEY_FOTO    + " IMAGE,"
            + KEY_NAMA    + " TEXT,"
            + KEY_NAMASEKOLAH + " TEXT"
            + " )";


    public Userdatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_CONTENT_DATABASE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    void adduserdata(Usergetsetdata usergetsetdata){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_FOTO,usergetsetdata.get_foto());
        values.put(KEY_NAMA,usergetsetdata.get_nama());
        values.put(KEY_NAMASEKOLAH, usergetsetdata.get_namasekolah());

        db.insert(TABLE_NAME,null,values);
        db.close();
    }

    public List<Usergetsetdata> getAlldata(){
        List<Usergetsetdata> userdatabaseList = new ArrayList<>();
        String SelectQuery = "SELECT * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(SelectQuery,null);

        if(cursor.moveToFirst()){
            do{
                Usergetsetdata usergetsetdata = new Usergetsetdata();
                try{
                    usergetsetdata.set_id(Integer.parseInt(cursor.getString(0)));
                    usergetsetdata.set_foto(cursor.getInt(1));
                    usergetsetdata.set_nama(cursor.getString(2));
                    usergetsetdata.set_namasekolah(cursor.getString(3));
                } catch (Exception e){
                    // print in stacktrace

                }
            } while (cursor.moveToNext());
        }
        return userdatabaseList;
    }

    public int updateuserdata(Usergetsetdata usersetget){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(KEY_FOTO,usersetget.get_foto());
        values.put(KEY_NAMA,usersetget.get_nama());
        values.put(KEY_NAMASEKOLAH, usersetget.get_namasekolah());

        return db.update(TABLE_NAME,values,KEY_ID + " =?", new String[]{String.valueOf(usersetget.get_id())});
    }

    public void delete(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME,null,null);
        db.execSQL("delete from " + TABLE_NAME);
        db.close();
    }

    public long getprofilecount(){
        SQLiteDatabase db = this.getReadableDatabase();
        long count = DatabaseUtils.queryNumEntries(db,TABLE_NAME);
        db.close();
        return count;
    }
}
