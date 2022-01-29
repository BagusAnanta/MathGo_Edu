package com.mathgoproject.mathgoedu;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
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

import androidx.annotation.Nullable;
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


    public class minigameactivity extends AppCompatActivity { // Inner Class

        private ImageView imagesoal;
        private TextView soaltext;
        private RadioButton opsia, opsib, opsic, opsid;
        private RadioGroup radioGroup;
        private Button submit;
        int arr;
        int x;
        String jawaban;
        int score = 0;

        dbcontain contain = new dbcontain();
        SQLitedatabase minidb = new SQLitedatabase(this,"Minigame.db");


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

    }
        //-----------------------------------------------------------------------------------------------------------------------------------------
        // Data Insert

        public void minidatadb() {
            Log.d("Inserting Data:", "Inserting....");

            minidb.adddata(new dbcontain(R.drawable.asset_fortester, "Suatu hari ibu meminta square membeli sesuatu diwarung \\n \" +\n" +
                    "                    \"Ibu : Square, belikan ibu 1 botol minyak, kalau ada telur beli 6\\n\" +\n" +
                    "                    \"Square : Baik bu \\n\" +\n" +
                    "                    \"dan beberapa menit kemudian ibu square terkejut dengan apa yang dibawa square\\n\\n\" +\n" +
                    "                    \"pertanyaan :\\n\\n\" +\n" +
                    "                    \"Apa yang dibawa square sehingga membuat ibunya terkejut ?", "1 Minyak", "6 Telur", "6 Minyak, 1 Telur", "6 Minyak", "6 Telur"));


            Log.d("Reading :", "Reading....");
            List<dbcontain> contentlist = new ArrayList<>();

            contentlist = minidb.getAlldata();

            contain = contentlist.get(x);
            //  deleteall(contentlist);

            for (dbcontain cn : contentlist) {
                String log = "Id: " + cn.get_id() + ",Image:" + cn.get_image() + ",Soal:" + cn.get_Soal() + ",Pil_A:" + cn.get_Pil_A() + ",Pil_B:" + cn.get_Pil_B() + ",Pil_C:" + cn.get_Pil_C() + ",Pil_D:" + cn.get_Pil_D() + ",Jawaban:" + cn.get_Jawaban();
                Log.d("Check fill data :", log);
            }
        }

        //---------------------------------------------------------------------------------------------------------------------------------------------

        public void setcontenttest() { // TODO: fungsi buat get data dari db
            int profilecount = (int) minidb.getprofilecount();
            minidb.close();

            radioGroup.clearCheck();
            if (x >= profilecount) { //TODO: Produce IllegalStateException
                // updatenilai(score);
                Intent intentskore = new Intent(minigameactivity.this, SkoreActivity.class);
                startActivity(intentskore);
                finish();
            } else {
                soaltext.setText(minidb.getAlldata().get(x).get_Soal());
                opsia.setText(minidb.getAlldata().get(x).get_Pil_A());
                opsib.setText(minidb.getAlldata().get(x).get_Pil_B());
                opsic.setText(minidb.getAlldata().get(x).get_Pil_C());
                opsid.setText(minidb.getAlldata().get(x).get_Pil_D());
                jawaban = minidb.getAlldata().get(x).get_Jawaban();
            }
            x++;
        }

        public void checkjawaban() {
            if (opsia.isChecked()) {
                if (opsia.getText().toString().equals(jawaban)) {
                    score = score + 5;
                    setcontenttest();
                } else {
                    // kenapa kita ngak nentuin poinnya disini, ngak usah kasih pake Array
                    score = score + 1; // contoh : kalo salah di A nilainya tetap 1
                    // generaterandom(score);
                    Intent exitintent = new Intent(minigameactivity.this, SkoreActivity.class);
                    startActivity(exitintent);
                    finish();
                }
            } else if (opsib.isChecked()) {
                if (opsib.getText().toString().equals(jawaban)) {
                    score = score + 5;
                    setcontenttest();
                } else {
                    score = score + 2; // kalo salah di B dia dikasih nilai 2
                    // generaterandom(score);
                    Intent exitintent = new Intent(minigameactivity.this, SkoreActivity.class);
                    startActivity(exitintent);
                    finish();
                }
            } else if (opsic.isChecked()) {
                if (opsic.getText().toString().equals(jawaban)) {
                    score = score + 5;
                    setcontenttest();
                } else {
                    score = score + 3; // kalo salah di c dikasih nilai 3
                    // generaterandom(score);
                    Intent exitintent = new Intent(minigameactivity.this, SkoreActivity.class);
                    startActivity(exitintent);
                    finish();
                }
            } else if (opsid.isChecked()) {
                if (opsid.getText().toString().equals(jawaban)) {
                    score = score + 5;
                    setcontenttest();
                } else {
                    score = score + 4; //kalo salah di c dikasih nilai 4
                    // generaterandom(score);
                    Intent exitintent = new Intent(minigameactivity.this, SkoreActivity.class);
                    startActivity(exitintent);
                    finish();
                }
            } else {
                Toast.makeText(this, "Mohon pilih jawaban anda", Toast.LENGTH_SHORT).show();
            }
        }
    }
}