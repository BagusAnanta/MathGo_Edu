package com.mathgoproject.mathgoedu;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class SQlitedatabase extends SQLiteOpenHelper {
    private static final String TABLE_QUERY  = "";
    private static final String TABLE_CONTENT = "";


    public SQlitedatabase(Context context,String DATABASE_NAME,int DATABASE_VERSION) {
        super(context, DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Tablequery(TABLE_QUERY));
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Contentquery(TABLE_CONTENT));
        onCreate(db);
    }

    public String Tablequery(String Table){
        return Table;
    }
    public String Contentquery(String content){return content;}

}
