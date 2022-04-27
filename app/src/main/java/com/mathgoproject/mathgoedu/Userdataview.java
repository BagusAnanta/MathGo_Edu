package com.mathgoproject.mathgoedu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Userdataview extends AppCompatActivity {

    Userdatabase userdb = new Userdatabase(this);
    Usergetsetdata setgetdata = new Usergetsetdata();

    private TextView nama_user,nama_sekolah_user;
    private int x = 0;
    private int profilecount = (int) userdb.getprofilecount();
    List<Usergetsetdata> userlist = new ArrayList<Usergetsetdata>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userdataview);

        nama_user = findViewById(R.id.namauser);
        nama_sekolah_user = findViewById(R.id.sekolahuser);

        if(profilecount == 0){
            getuserdatafromdb();
        } else {
            userlist = userdb.getAlldata();
            setgetdata = userlist.get(x);
        }
    }


    // for view data from database
    private void getuserdatafromdb(){
        nama_user.setText(userdb.getAlldata().get(x).get_nama());
        nama_sekolah_user.setText(userdb.getAlldata().get(x).get_namasekolah());
        x++;
    }


}