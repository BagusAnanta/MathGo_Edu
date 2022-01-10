package com.mathgoproject.mathgoedu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Pair;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

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

public class Registeractivity extends AppCompatActivity {
    private TextInputEditText Nameinput;
    private TextView Introduce_text;
    Button register;
    Dashboard dashboard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_registeractivity);


        // buat sementara
        Nameinput = findViewById(R.id.Input_name);
        register = findViewById(R.id.Button_input);
        Introduce_text = findViewById(R.id.Title_logo);


        Nameinput.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_NULL){
                    checker_name();
                    return true;
                }
                return  false;
            }
        });

        try {
           register.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checker_name();
                }
            });
        } catch (Exception e){
            Toast.makeText(this,"Mohon,masukkan input dengan benar",Toast.LENGTH_SHORT).show();
        }
    }

    private void checker_name(){
        //* Reset semua *//*
        Nameinput.setError(null);
        View focus = null;
        boolean cancel = false;

        String Name = Nameinput.getText().toString(); // text dari edittext

        if (TextUtils.isEmpty(Name)){
            Nameinput.setError("Mohon isi bagian ini");
            focus = Nameinput;
            cancel = true;
        }

        if(cancel){
            focus.requestFocus();
        } else {
            Sharepreference.setRegisterUser(getBaseContext(),Name); // ini gunanya buat masukin datanya ke sharepreference aku lali masukin ini :)
            login();
            finish();
        }

    }
    private void login(){
        Sharepreference.setLoggerInUser(getBaseContext(),Sharepreference.getRegisterUser(getBaseContext()));
        Sharepreference.setLoggerInStatus(getBaseContext(),true);
        Intent intent = new Intent(getBaseContext(),Dashboard.class);
        startActivity(intent);
        finish();
    }


}