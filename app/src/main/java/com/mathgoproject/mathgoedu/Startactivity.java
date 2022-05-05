package com.mathgoproject.mathgoedu;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

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

public class Startactivity extends AppCompatActivity {
    private final int Timesplash = 3000;
    Animation topAnim,bottomAnim;
    ImageView logoimage;
    TextView logodesc,companydesc;
    private int index = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_startactivity);

        Context context;
        topAnim = AnimationUtils.loadAnimation(this,R.anim.top_animation );
        bottomAnim = AnimationUtils.loadAnimation(this,R.anim.bottom_animation );

        logoimage = findViewById(R.id.logo_image);
        logodesc = findViewById(R.id.logo_title);
        companydesc = findViewById(R.id.company_desc);

        logoimage.setAnimation(topAnim);
        logodesc.setAnimation(bottomAnim);
        companydesc.setAnimation(bottomAnim);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                check();
            }
        }, Timesplash);
    }

    public void check(){
        if (Sharepreference.getLoggerInStatus(getBaseContext())) {
            Intent intent_data = new Intent(getBaseContext(), Dashboard.class); // if a sharepreference data is exist
            startActivity(intent_data);
            finish();
        } else {
            Intent activityintent = new Intent(getBaseContext(), Registeractivity.class); // if a sharepreference data not exist (Null)
            Animation(activityintent);
            finish();
        }
    }

    private void Animation(Intent intent){
        Pair[] pairs = new Pair[2];
        pairs[0] = new Pair<View,String>(logoimage,"Logo_image");
        pairs[1] = new Pair<View,String>(logodesc,"From_title");

        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(Startactivity.this,pairs);
        startActivity(intent,options.toBundle());
    }

}