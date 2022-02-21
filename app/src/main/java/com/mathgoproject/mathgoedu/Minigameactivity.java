package com.mathgoproject.mathgoedu;

import android.content.Intent;
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


    public class Minigameactivity extends AppCompatActivity {

        private ImageView imagesoal;
        private TextView soaltext;
        private RadioButton opsia, opsib, opsic, opsid;
        private RadioGroup radioGroup;
        private Button submit;
        int arr;
        int x = 0;
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

            minidb.adddata(new dbcontain(R.drawable.asset_mathgo_soal_no3,"Saat menunggu bus datang, square mengamati beberapa bus yang terparkir di stasiun. Dan\n" +
                    "ia menghitung bus warna-warni yang berjejer di parkiran stasiun. Saat square telah sampai di\n" +
                    "tujuannya ia pun mencatat :\n" +
                    "\uF0A7 Bus biru ada 10\n" +
                    "\uF0A7 Bus merah ada 44\n" +
                    "\uF0A7 Bus putih ada 3\n" +
                    "\uF0A7 Bus hijau ada 8\n" +
                    "\uF0A7 Bus hitam ada 15\n" +
                    "Square mengetikkan angka itu memakai program komputer dan hasilnya sebuah diagram\n" +
                    "batang sebagai berikut","Merah","Biru","Putih","Hitam","Biru"));

            Log.d("Reading :", "Reading....");
            List<dbcontain> minicontentdata = new ArrayList<dbcontain>();
            minicontentdata = minidb.getAlldata();
            contain = minicontentdata.get(x); // TODO: Produce IndexOutOfBoundsException

            //  deleteall(contentlist);

            for (dbcontain cn : minicontentdata) {
                String log = "Id: " + cn.get_id() + ",Image:" + cn.get_image() + ",Soal:" + cn.get_Soal() + ",Pil_A:" + cn.get_Pil_A() + ",Pil_B:" + cn.get_Pil_B() + ",Pil_C:" + cn.get_Pil_C() + ",Pil_D:" + cn.get_Pil_D() + ",Jawaban:" + cn.get_Jawaban();
                Log.d("Check fill data :", log);
            }
        }

        //---------------------------------------------------------------------------------------------------------------------------------------------

        public void setcontenttest() { // TODO: fungsi buat get data dari db
            int profilecount = (int) minidb.getprofilecount();
            minidb.close();

            radioGroup.clearCheck();
            if (x >= profilecount) {
                // updatenilai(score);
                Intent intentskore = new Intent(Minigameactivity.this, SkoreActivity.class);
                startActivity(intentskore);
                finish();
            } else {
                imagesoal.setImageResource(minidb.getAlldata().get(x).get_image());
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
                    Intent exitintent = new Intent(Minigameactivity.this, SkoreActivity.class);
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
                    Intent exitintent = new Intent(Minigameactivity.this, SkoreActivity.class);
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
                    Intent exitintent = new Intent(Minigameactivity.this, SkoreActivity.class);
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
                    Intent exitintent = new Intent(Minigameactivity.this, SkoreActivity.class);
                    startActivity(exitintent);
                    finish();
                }
            } else {
                Toast.makeText(this, "Mohon pilih jawaban anda", Toast.LENGTH_SHORT).show();
            }
        }
        private void checklength(List checklist){
            // for check list content
            Log.v("List Length","Check: " + checklist.toArray().length); // TODO : This list length = 0
        }

        private void deleteall(List contentlist){
            for(int i = 0; i < contentlist.toArray().length;i++){
                if(!contentlist.equals(0)){
                    minidb.delete();
                }
            }
        }
}