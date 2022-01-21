package com.mathgoproject.mathgoedu;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;


/***
 * Minigame without a score
 */

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

public class minigameactivity extends AppCompatActivity {

    private ImageView imagesoal;
    private TextView soaltext;
    private RadioButton opsia, opsib, opsic, opsid;
    private RadioGroup radioGroup;
    private Button submit;
    int arr;
    int x;
    String jawaban;
    int score = 0;

    private static final String KEY_ID = "id";
    private static final String KEY_IMAGE = "image";
    private static final String KEY_SOAL = "soal";
    private static final String KEY_PIL_A = "pil_a";
    private static final String KEY_PIL_B = "pil_b";
    private static final String KEY_PIL_C = "pil_c";
    private static final String KEY_PIL_D = "pil_d";
    private static final String KEY_JAWABAN = "jawaban";
    private static final String TABLE_CONTENT = "content";

    SQlitedatabase createDB = new SQlitedatabase(this,"MinigameDB",1);
    dbcontain contain = new dbcontain();

    // minigamearray minigame = new minigamearray();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.minigameactivity);

        imagesoal = findViewById(R.id.gambar_minigame);
        soaltext = findViewById(R.id.soal_minigame);
        radioGroup = findViewById(R.id.radiogrup);
        opsia = findViewById(R.id.opsiA);
        opsib = findViewById(R.id.opsiB);
        opsic = findViewById(R.id.opsiC);
        opsid = findViewById(R.id.opsiD);
        submit = findViewById(R.id.submit_button);

        minidatadb();
        setcontenttest();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkjawaban();
            }
        });




        createDB.Contentquery(TABLE_CONTENT);
        createDB.Tablequery("CREATE TABLE " + TABLE_CONTENT +
                "(" + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_IMAGE + " IMAGE,"
                + KEY_SOAL + " TEXT,"
                + KEY_PIL_A + " TEXT,"
                + KEY_PIL_B + " TEXT,"
                + KEY_PIL_C + " TEXT,"
                + KEY_PIL_D + " TEXT,"
                + KEY_JAWABAN + " TEXT"
                + ")");

    }

    //-----------------------------------------------------------------------------------------------------------------------------------------
    // Data Insert

    public void minidatadb(){
        Log.d("Inserting Data:","Inserting....");

        adddata(new dbcontain(R.drawable.asset_fortester,"Suatu hari ibu meminta square membeli sesuatu diwarung \\n \" +\n" +
                "                    \"Ibu : Square, belikan ibu 1 botol minyak, kalau ada telur beli 6\\n\" +\n" +
                "                    \"Square : Baik bu \\n\" +\n" +
                "                    \"dan beberapa menit kemudian ibu square terkejut dengan apa yang dibawa square\\n\\n\" +\n" +
                "                    \"pertanyaan :\\n\\n\" +\n" +
                "                    \"Apa yang dibawa square sehingga membuat ibunya terkejut ?","1 Minyak","6 Telur","6 Minyak, 1 Telur","6 Minyak","6 Telur"));


        Log.d("Reading :","Reading....");
        List<dbcontain> contentlist = new ArrayList<>();

        contentlist = getAlldata();

        contain = contentlist.get(x);
        //  deleteall(contentlist);

        for (dbcontain cn : contentlist){
            String log = "Id: " + cn.get_id() + ",Image:" + cn.get_image() + ",Soal:" + cn.get_Soal() + ",Pil_A:" + cn.get_Pil_A() + ",Pil_B:" + cn.get_Pil_B() + ",Pil_C:" + cn.get_Pil_C() + ",Pil_D:" + cn.get_Pil_D() + ",Jawaban:" + cn.get_Jawaban();
            Log.d("Check fill data :", log);
        }
    }

    //---------------------------------------------------------------------------------------------------------------------------------------------

    void adddata(dbcontain contain){
        SQLiteDatabase db = createDB.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("KEY_IMAGE",contain.get_image());
        values.put(KEY_SOAL,contain.get_Soal());
        values.put(KEY_PIL_A,contain.get_Pil_A());
        values.put(KEY_PIL_B,contain.get_Pil_B());
        values.put(KEY_PIL_C,contain.get_Pil_C());
        values.put(KEY_PIL_D,contain.get_Pil_D());
        values.put(KEY_JAWABAN,contain.get_Jawaban());

        db.insert(TABLE_CONTENT,null,values);
        db.close();
    }

    public List<dbcontain> getAlldata(){
        List<dbcontain> datalist = new ArrayList<dbcontain>();
        String SelectQuery = "SELECT * FROM " + TABLE_CONTENT;

        SQLiteDatabase db = createDB.getReadableDatabase();
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
        SQLiteDatabase db = createDB.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_IMAGE,contain.get_image());
        values.put(KEY_SOAL,contain.get_Soal());
        values.put(KEY_PIL_A,contain.get_Pil_A());
        values.put(KEY_PIL_B,contain.get_Pil_B());
        values.put(KEY_PIL_C,contain.get_Pil_C());
        values.put(KEY_PIL_D,contain.get_Pil_D());

        return db.update(TABLE_CONTENT,values,KEY_ID + " =?",new String[]{String.valueOf(contain.get_id())});
    }

    public void delete(){
        SQLiteDatabase db = createDB.getWritableDatabase();
        db.delete(TABLE_CONTENT,null,null);
        db.execSQL("delete from " + TABLE_CONTENT );
        db.close();
    }

    public long getprofilecount(){
        SQLiteDatabase db = createDB.getReadableDatabase();
        long count = DatabaseUtils.queryNumEntries(db,TABLE_CONTENT);
        db.close();
        return count;
    }


    // setcontent function

    public void setcontenttest(){ // TODO: fungsi buat get data dari db
        int profilecount = (int) getprofilecount();
        createDB.close();

        radioGroup.clearCheck();
        if(x >= profilecount){ //TODO: Produce IllegalStateException
            // updatenilai(score);
            Intent intentskore = new Intent(minigameactivity.this,SkoreActivity.class);
            startActivity(intentskore);
            finish();
        } else {
            soaltext.setText(getAlldata().get(x).get_Soal());
            opsia.setText(getAlldata().get(x).get_Pil_A());
            opsib.setText(getAlldata().get(x).get_Pil_B());
            opsic.setText(getAlldata().get(x).get_Pil_C());
            opsid.setText(getAlldata().get(x).get_Pil_D());
            jawaban = getAlldata().get(x).get_Jawaban();
        }
        x++;
    }

    public void checkjawaban(){
        if(opsia.isChecked()){
            if(opsia.getText().toString().equals(jawaban)){
                score = score + 5;
                setcontenttest();
            } else {
                // kenapa kita ngak nentuin poinnya disini, ngak usah kasih pake Array
                score = score + 1; // contoh : kalo salah di A nilainya tetap 1
                // generaterandom(score);
                Intent exitintent = new Intent(minigameactivity.this,SkoreActivity.class);
                startActivity(exitintent);
                finish();
            }
        } else if(opsib.isChecked()){
            if(opsib.getText().toString().equals(jawaban)){
                score = score + 5;
                setcontenttest();
            } else {
                score = score + 2; // kalo salah di B dia dikasih nilai 2
                // generaterandom(score);
                Intent exitintent = new Intent(minigameactivity.this,SkoreActivity.class);
                startActivity(exitintent);
                finish();
            }
        } else if(opsic.isChecked()){
            if(opsic.getText().toString().equals(jawaban)){
                score = score + 5;
                setcontenttest();
            } else {
                score = score + 3; // kalo salah di c dikasih nilai 3
                // generaterandom(score);
                Intent exitintent = new Intent(minigameactivity.this,SkoreActivity.class);
                startActivity(exitintent);
                finish();
            }
        } else if(opsid.isChecked()){
            if(opsid.getText().toString().equals(jawaban)){
                score = score + 5;
                setcontenttest();
            } else {
                score = score + 4; //kalo salah di c dikasih nilai 4
                // generaterandom(score);
                Intent exitintent = new Intent(minigameactivity.this,SkoreActivity.class);
                startActivity(exitintent);
                finish();
            }
        } else {
            Toast.makeText(this,"Mohon pilih jawaban anda",Toast.LENGTH_SHORT).show();
        }
    }







}