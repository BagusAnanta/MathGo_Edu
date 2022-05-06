package com.mathgoproject.mathgoedu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
// import com.bsoftwarefoundation.mathgo.Sharepreference;

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


public class Dashboard extends AppCompatActivity {
    private TextView Name, Name_sekolah, bestscore, lowerscore, intervalgame;
    private ImageButton Maingame,setting, Information;
    private int Index = 0;

    Userdatabase userdataset = new Userdatabase(this);
    Usergetsetdata usergetsetdata = new Usergetsetdata();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_dashboard);

        // initialisation text
        Name = findViewById(R.id.Name);
        Name_sekolah = findViewById(R.id.namasekolahview);
        Maingame = findViewById(R.id.Maingame_button);
        Information = findViewById(R.id.Information_button);
        setting = findViewById(R.id.Settings_button);
        bestscore = findViewById(R.id.Skortetinggi_input);
        lowerscore = findViewById(R.id.Skorterendah_input);
        intervalgame = findViewById(R.id.Banyakmain_input);

        setuserdata(Index);
        Index++;

        if (Sharepreference.getnilai(this) != 0 && Sharepreference.getintervalgame(this) != 0) {
            bestscore.setText("" + Sharepreference.gettempnilai(this));
            intervalgame.setText("" + Sharepreference.getintervalgame(this));
            lowerscore.setText("" + Sharepreference.getlowervalue(this));
        } else {
            bestscore.setText("0");
            intervalgame.setText("0");
            lowerscore.setText("0");
        }

        Maingame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Dashboard.this, Maingameactivity.class);
                startActivity(intent);
                finish();
            }
        });

        Information.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Dashboard.this, Literasi_activity.class);
                startActivity(intent);
                finish();
            }
        });

        // set imagebutton
        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent setintent = new Intent(Dashboard.this, Settingactivity.class);
                startActivity(setintent);
                finish();
            }
        });

    }

    public void setuserdata(int Index){
        int profilecount = (int) userdataset.getprofilecount();

        if(Index <= profilecount){
            Name.setText(userdataset.getAlldata().get(Index).get_nama());
            Name_sekolah.setText(userdataset.getAlldata().get(Index).get_namasekolah());
        }
    }
}