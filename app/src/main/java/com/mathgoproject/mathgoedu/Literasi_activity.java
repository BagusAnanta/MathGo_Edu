package com.mathgoproject.mathgoedu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

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

public class Literasi_activity extends YouTubeBaseActivity {
    String Key = "AIzaSyAShuc8UEPVoyUCjZQNOkKO3u_ImtpfdRI";
    ImageView backbutton;
    TextView CTtext,Mathgotext;
    YouTubePlayerView youtubePlayerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_literasi);

        backbutton = findViewById(R.id.backbutton);
        CTtext = findViewById(R.id.ct_desc);
        Mathgotext = findViewById(R.id.mathgo_desc);
        youtubePlayerView = findViewById(R.id.youtubeplayer);

        // set textdesc
        CTtext.setText("Computational Thinking adalah konsep berfikir untuk menyelesaikan masalah melalui informatika");
        Mathgotext.setText("Mathgo adalah aplikasi yang berbasis pertanyaan yang memuat persoalan computational thinking dan beserta gambar");



            youtubePlayerView.initialize(
                    Key,
                    new YouTubePlayer.OnInitializedListener() {
                        @Override
                        public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                            youTubePlayer.loadVideo("Lhp5HwCkIVw"); //TODO: change a link if have a video
                            youTubePlayer.play();
                        }
                        @Override
                        public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
                            Toast.makeText(getApplicationContext(),"Maaf video mengalami masalah,silahkan ulangi nanti",Toast.LENGTH_SHORT).show();
                        }
                    }
            );


        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Literasi_activity.this,Dashboard.class);
                startActivity(intent);
                finish();
            }
        });
    }


    @Override
    public void onBackPressed() {
        Intent backintent = new Intent(Literasi_activity.this,Dashboard.class);
        startActivity(backintent);
        finish();
    }
}