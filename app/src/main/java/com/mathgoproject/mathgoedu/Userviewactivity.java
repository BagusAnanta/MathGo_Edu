package com.mathgoproject.mathgoedu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Userviewactivity extends AppCompatActivity {

    private TextView userName,schoolName,highValue,lowerValue,intervalGame;
    Userdatabase userdatabase = new Userdatabase(this);
    int Index = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_userviewactivity);

        userName = findViewById(R.id.Name_profile);
        schoolName = findViewById(R.id.Instance_profile);
        highValue = findViewById(R.id.Nilaiterbesarprofile);
        lowerValue = findViewById(R.id.Nilaiterkecilprofile);
        intervalGame = findViewById(R.id.Intervalmainprofile);

        // We gonna set nama,school name,highval,lowval,interval in here
        getnameandschoolname(Index);
        Index++;

        if(Sharepreference.getnilai(this) != 0 && Sharepreference.getintervalgame(this) != 0){
            highValue.setText(Sharepreference.gettempnilai(this));
            lowerValue.setText(Sharepreference.getlowervalue(this));
            intervalGame.setText(Sharepreference.getintervalgame(this));
        } else {
            highValue.setText("0");
            lowerValue.setText("0");
            intervalGame.setText("0");
        }

    }

    private void getnameandschoolname(int index){
        // getprofilecount in here from Userdatabase
        int getprofilecount = (int) userdatabase.getprofilecount();

        if(index <= getprofilecount){
            userName.setText(userdatabase.getAlldata().get(index).get_nama());
            schoolName.setText(userdatabase.getAlldata().get(Index).get_namasekolah());
        }
    }
}