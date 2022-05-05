package com.mathgoproject.mathgoedu;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Pair;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

// import com.github.dhaval2404.imagepicker.ImagePicker;
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
    private TextInputEditText Nameinput, Namasekolahuser;
    private TextView Introduce_text;
    private Button register, profilefoto;
    Dashboard dashboard;
    int SELECT_PICTURE = 200;

    Userdatabase userdata = new Userdatabase(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_registeractivity);


        // buat sementara
        Nameinput = findViewById(R.id.Input_name);
        Namasekolahuser = findViewById(R.id.usersekolah);
        register = findViewById(R.id.Button_input);
        Introduce_text = findViewById(R.id.Title_logo);


        Nameinput.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_NULL) {
                check_nama_and_namasekolah();
                return true;
            }
            return false;
        });

        Namasekolahuser.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_NULL) {
                check_nama_and_namasekolah();
                return true;
            }
            return false;
        });

        try {
            register.setOnClickListener(v -> {
                check_nama_and_namasekolah();
            });
        } catch (Exception e) {
            Toast.makeText(this, "Mohon,masukkan input dengan benar", Toast.LENGTH_SHORT).show();
        }

        /*profilefoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imagechooser();
            }
        });*/

    }

    private void check_nama_and_namasekolah(){
        //declarating default textinput
        Nameinput.setError(null);
        Namasekolahuser.setError(null);
        View focus = null;
        boolean cancel = false;

        // convert from textinput to string
        String Nama = Nameinput.getText().toString();
        String Namasekolah = Namasekolahuser.getText().toString();

        if(TextUtils.isEmpty(Nama)){
            Nameinput.setError("Masukkan bagian yang kosong!");
            focus = Nameinput;
            cancel = true;
        } else if(TextUtils.isEmpty(Namasekolah)){
            Namasekolahuser.setError("Masukkan bagian yang kosong!");
            focus = Namasekolahuser;
            cancel = true;
        }

        if(cancel){
            focus.requestFocus();
        } else {
            userdata.getuserdata(Nama,Namasekolah);
            Sharepreference.setLoggerInStatus(getBaseContext(),true);
            Intent intent = new Intent(getBaseContext(), Dashboard.class);
            startActivity(intent);
            finish();
        }
    }


    /*private void imagechooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), SELECT_PICTURE);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == RESULT_OK){
            if(requestCode == SELECT_PICTURE){
                Uri selectimageurl = data.getData();
                if(null != selectimageurl){
                    // you can get image and place in here
                }
            }
        }
    }*/

    //TODO: Change this
    /*private void login() {
        Sharepreference.setLoggerInUser(getBaseContext(), Sharepreference.getRegisterUser(getBaseContext()));
        Sharepreference.setLoggerInStatus(getBaseContext(), true);
        Intent intent = new Intent(getBaseContext(), Dashboard.class);
        startActivity(intent);
        finish();
    }*/
}