package com.mathgoproject.mathgoedu;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

public class Userdatabase extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "userdata.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "userdatabase";

    private static final String KEY_ID = "id";
    private static final String KEY_NAMA = "nama";
    private static final String KEY_NAMASEKOLAH = "nama_sekolah";

    // Table
    private static final String CREATE_CONTENT_DATABASE = "CREATE TABLE " + TABLE_NAME +
            " (" + KEY_ID + " INTEGER PRIMARY KEY,"
            + KEY_NAMA    + " TEXT,"
            + KEY_NAMASEKOLAH + " TEXT"
            + " )";

    Usergetsetdata usergetsetdata = new Usergetsetdata();

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
                    usergetsetdata.set_nama(cursor.getString(1));
                    usergetsetdata.set_namasekolah(cursor.getString(2));
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

    // for add or check/get data user if exist
    private boolean addorgetdatauser(int index){
        int profilecount = (int) getprofilecount();
        List<Usergetsetdata> userontentlist = new ArrayList<Usergetsetdata>();
        boolean check;

        if(profilecount == 0){
            // getuserdata();
            check = false;
        } else {
            userontentlist = getAlldata();
            usergetsetdata = userontentlist.get(index);
            check = true;
        }

        return check;
    }

    // for get data and insert to userdatabase
    private void getuserdata(TextInputEditText user_name,TextInputEditText user_sekolah_name){

    }

    // for set data from userdatabase
    private void setuserdata(int index, TextView user_name, TextView user_sekolah_name){
        int profilecount = (int) getprofilecount();

        if(index <= profilecount) {
            user_name.setText(getAlldata().get(index).get_nama());
            user_sekolah_name.setText(getAlldata().get(index).get_namasekolah());
        }
    }
}
