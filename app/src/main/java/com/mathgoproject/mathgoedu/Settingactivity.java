package com.mathgoproject.mathgoedu;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.Toast;

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

public class Settingactivity extends AppCompatActivity {

    ImageButton backbutton;
    Button change_username;
    EditText change_datausername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_settingactivity);


        change_username = findViewById(R.id.button_namechange);
        backbutton = findViewById(R.id.backbutton);
        change_datausername = findViewById(R.id.Edittext_name);


        change_username.setOnClickListener(v -> {
            final AlertDialog.Builder builder = new AlertDialog.Builder(Settingactivity.this);
            builder.setTitle("Peringatan");
            builder.setMessage("Apakah anda ingin mengganti nama,aplikasi akan direstart");
            builder.setCancelable(false); // back key doesn't close a dialog box
            builder.setPositiveButton(
                    "Ya", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            change_data();
                        }
                    });
            builder.setNegativeButton(
                    "Tidak", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        });


        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Settingactivity.this,Dashboard.class);
                startActivity(intent);
                finish();
            }
        });
    }


    public void ReportButton(View view) {
        Intent sendreport = new Intent(Intent.ACTION_SEND);
        sendreport.setType("Text/Plain");
        sendreport.putExtra(Intent.EXTRA_EMAIL,new String[] {"mathgodotcom@gmail.com"});
        sendreport.putExtra(Intent.EXTRA_SUBJECT,"Bug Report Text");
        sendreport.putExtra(Intent.EXTRA_TEXT,"Apabila anda menemui bug, mohon kirimkan email ini beserta foto Screen Shoot apabila anda mempunyainya dan ceritakan dimana anda menemukan bug tersebut");


        try {
            startActivity(Intent.createChooser(sendreport,"Apakah Anda ingin mengirimkan bug report melalui email ?"));
        } catch (android.content.ActivityNotFoundException e){
            Intent backmenu = new Intent(Settingactivity.this,Dashboard.class);
            startActivity(backmenu);
            finish();
        }

    }

    private void change_data(){
        change_datausername.setError(null);
        View focus = null;
        boolean cancel = false;

        String Name_set = change_datausername.getText().toString();

        if(TextUtils.isEmpty(Name_set)){
            change_datausername.setError("Mohon isi bagian ini");
            focus = change_datausername;
            cancel = true;
        }

        if(cancel){
            focus.requestFocus();
        } else {
            Sharepreference.ClearLoggerInUser(getBaseContext());
            Sharepreference.setRegisterUser(getBaseContext(),Name_set);// ini gunanya buat masukin datanya ke sharepreference aku lali masukin ini :)
            login();
            finish();
        }
    }

    private void login(){
        Sharepreference.setLoggerInUser(getBaseContext(),Sharepreference.getRegisterUser(getBaseContext()));
        Sharepreference.setLoggerInStatus(getBaseContext(),true);
        Intent intent = new Intent(getBaseContext(),Startactivity.class);
        startActivity(intent);
        finish();
    }


    @Override
    public void onBackPressed() {
        Intent backintent = new Intent(Settingactivity.this,Dashboard.class);
        startActivity(backintent);
        finish();
    }
}