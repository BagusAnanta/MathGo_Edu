package com.mathgoproject.mathgoedu;

import android.app.ActivityManager;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

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


public class maingamefragment extends Fragment {

    private ImageView imagesoal2;
    private TextView soaltext;
    private RadioButton opsia,opsib,opsic,opsid;
    private RadioGroup radioGroup;
    private Button submit;
    private View view;


    public maingamefragment(){

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_maingamefragment, container, false);


        imagesoal2 = view.findViewById(R.id.gambar_soal);
        soaltext = view.findViewById(R.id.soal);
        radioGroup = view.findViewById(R.id.radiogrup);
        opsia = view.findViewById(R.id.opsiA);
        opsib = view.findViewById(R.id.opsiB);
        opsic = view.findViewById(R.id.opsiC);
        opsid = view.findViewById(R.id.opsiD);
        submit = view.findViewById(R.id.submit_button);


        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        view = null;
    }
}