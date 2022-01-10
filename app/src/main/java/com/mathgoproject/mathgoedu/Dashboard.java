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
    TextView Name,besttitle,bestscore;
    ImageButton setting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_dashboard);

        // initialisasion text
        Name = findViewById(R.id.Name_input);
        setting = findViewById(R.id.Setting_button);
        besttitle = findViewById(R.id.Best_title_mainkan);
        bestscore = findViewById(R.id.best_score);

        if(Sharepreference.getnilai(this) != 0){
            besttitle.setVisibility(View.VISIBLE);
            bestscore.setVisibility(View.VISIBLE);

            bestscore.setText("" + Sharepreference.gettempnilai(this));
        } else {
            besttitle.setVisibility(View.INVISIBLE);
            bestscore.setVisibility(View.INVISIBLE);
        }


        // set Text
        Name.setText(Sharepreference.getLoggerInUser(getBaseContext()));



        // set imagebutton
        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent setintent = new Intent(Dashboard.this,Settingactivity.class);
                startActivity(setintent);
                finish();
            }
        });

    }



    public void Play(View view) {
        Intent intent = new Intent(Dashboard.this,Maingameactivity.class);
        startActivity(intent);
        finish();
    }

    public void Minigame(View view) {
        Intent intent = new Intent(Dashboard.this,minigameactivity.class);
        startActivity(intent);
        finish();
    }

    public void Information(View view) {
        Intent intent = new Intent(Dashboard.this,Literasi_activity.class);
        startActivity(intent);
        finish();
    }


}