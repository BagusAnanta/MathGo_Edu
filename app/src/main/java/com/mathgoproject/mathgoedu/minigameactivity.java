package com.mathgoproject.mathgoedu;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;


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
    private RadioButton opsia,opsib,opsic,opsid;
    private RadioGroup radioGroup;
    private Button submit;
    int arr;
    int x;
    String jawaban;
    int score = 0;

    minigamearray minigame = new minigamearray();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_minigameactivity);

        imagesoal = findViewById(R.id.gambarminigame);
        soaltext = findViewById(R.id.title_question);
        radioGroup = findViewById(R.id.radiogrup);
        opsia = findViewById(R.id.opsiA);
        opsib = findViewById(R.id.opsiB);
        opsic = findViewById(R.id.opsiC);
        opsid = findViewById(R.id.opsiD);
        submit = findViewById(R.id.submit_button);

        // setkontent disini

        setkonten();

        // tombol submit
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkjawaban();
            }
        });


    }

    public void setkonten(){
        radioGroup.clearCheck();
        arr = minigame.pertanyaan.length;

        if(x >= arr){
            // nanti langsung ke activity terima kasih aj atau gimana ?
            final AlertDialog.Builder builder = new AlertDialog.Builder(minigameactivity.this);
            builder.setTitle("Selamat !");
            builder.setMessage("Terima kasih telah bermain minigame, kamu hebat");
            builder.setCancelable(false); // back key doesn't close a dialog box
            builder.setPositiveButton(
                    "Keluar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(minigameactivity.this,Dashboard.class);
                            startActivity(intent);
                            finish();
                        }
                    });

            AlertDialog alertDialog = builder.create();
            alertDialog.show();

        } else { // isi content lagi
            gambarsoal();
            soaltext.setText(minigame.getpertanyaan(x));
            opsia.setText(minigame.getpilihanjawaban1(x));
            opsib.setText(minigame.getpilihanjawaban2(x));
            opsic.setText(minigame.getpilihanjawaban3(x));
            opsid.setText(minigame.getpilihanjawaban4(x));
            jawaban = minigame.getjawabanbenar(x);
        }
        x++;
    }

    public void checkjawaban(){
        if(opsia.isChecked()){
            if(opsia.getText().toString().equals(jawaban)){
                setkonten();
            } else {
                // kenapa kita ngak nentuin poinnya disini, ngak usah kasih pake Array
                setkonten();
            }
        } else if(opsib.isChecked()){
            if(opsib.getText().toString().equals(jawaban)){
                setkonten();
            } else {
                setkonten();
            }
        } else if(opsic.isChecked()){
            if(opsic.getText().toString().equals(jawaban)){
                setkonten();
            } else {
                setkonten();
            }
        } else if(opsid.isChecked()){
            if(opsid.getText().toString().equals(jawaban)){
                setkonten();
            } else {
                setkonten();
            }
        } else {
            Toast.makeText(this,"Mohon pilih jawaban anda",Toast.LENGTH_SHORT).show();
        }
    }

    public void gambarsoal(){
        Resources res = getResources();
        String photo = minigame.getgambar(x);
        int resid = res.getIdentifier(photo,"drawable",getPackageName());
        Drawable drawable = res.getDrawable(resid);
        imagesoal.setImageDrawable(drawable);
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(this,"Mohon selesaikan terlebih dahulu",Toast.LENGTH_SHORT).show();
    }

}