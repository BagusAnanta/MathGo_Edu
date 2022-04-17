package com.mathgoproject.mathgoedu;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;
import java.util.List;


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

public class Maingameactivity extends AppCompatActivity {

    private ImageView imagesoal2;
    private TextView soaltext,soal_count_text;
    private RadioButton opsia,opsib,opsic,opsid;
    private RadioGroup radioGroup;
    private Button submit,selanjutnya;
    private Chronometer countup_timer;
    private CardView jawaban_cardview;
    private int score = 0;
    private int arr;
    private int x = 0;
    private int lastindex = 9;
    private boolean resume = false;
    private long elapsetime;
    String jawaban;

    // maindb
    Maingamedatabase maindb = new Maingamedatabase(this,"Maingame.db");
    Setget_maingame_database contain = new Setget_maingame_database();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_maingameactivity);
        show_jawaban_soal_fragment(); // show jawaban fragment

        imagesoal2 = findViewById(R.id.gambar_soal);
        soaltext = findViewById(R.id.soal);
        radioGroup = findViewById(R.id.radiogrup);
        opsia = findViewById(R.id.opsiA);
        opsib = findViewById(R.id.opsiB);
        opsic = findViewById(R.id.opsiC);
        opsid = findViewById(R.id.opsiD);
        countup_timer = findViewById(R.id.timer_count);
        soal_count_text = findViewById(R.id.soal_count);
        submit = findViewById(R.id.submit_button);
        selanjutnya = findViewById(R.id.selanjutnya_button);
        jawaban_cardview = findViewById(R.id.jawaban_card);

        startchronometer();
        datadb();
        setcontenttest(); // TODO: This for show a content

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkjawaban();
            }
        });

        countup_timer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer chronometer) {
                if(!resume){
                  long minutes = ((SystemClock.elapsedRealtime() - countup_timer.getBase())/1000)/60;
                  long second =  ((SystemClock.elapsedRealtime() - countup_timer.getBase())/1000)%60;
                  elapsetime = SystemClock.elapsedRealtime();
                  Log.d("Tag","OnChronometertick" + minutes + " : " + second);
                } else {
                  long minutes = ((elapsetime - countup_timer.getBase()/1000))/60;
                  long second  = ((elapsetime - countup_timer.getBase()/1000))%60;
                  Log.d("Tag","OnChronometertick" + minutes + " : " + second);
                  elapsetime = elapsetime + 1000;
                }
            }
        });

    }

    public void datadb(){

        int profilecount = (int) maindb.getprofilecount(); // for calculate count id in database and for logic in limit inserting
        List<Setget_maingame_database> contentlist = new ArrayList<Setget_maingame_database>(); // berarti kalo kita selesai main dia bakal default ke 0, makannya dia nambah

       /* contentlist = maindb.getAlldata();
        contain = contentlist.get(x);
        deleteall(contentlist);*/


        /*Jika kalo size contentlist == 0 dia bakal tambah data, kalo != 0 dia akan ambil datanya*/

       if(profilecount == 0){ // TODO : contentlist.size() // for calculate list size
           datasoal(); // TODO; Jika ini diapus, data tidak akan bertambah, (artinya data akan ditambah jika ini dipanggil
        } else{
           contentlist = maindb.getAlldata(); // TODO: This code add All data and give logic if data < 9 and this code add data
           contain = contentlist.get(x); // TODO: if a data done get All data and id == 9 this else logic use (Kita pake ini buat get datanya aja)
        }

        Log.v("List Size", String.valueOf(contentlist.size()));
        checklength(contentlist);

            for (Setget_maingame_database cn : contentlist){
                String log = "Id: " + cn.get_id() + ",Image:" + cn.get_image() + ",Soal:" + cn.get_Soal() + ",Pil_A:" + cn.get_Pil_A() + ",Pil_B:" + cn.get_Pil_B() + ",Pil_C:" + cn.get_Pil_C() + ",Pil_D:" + cn.get_Pil_D() + ",Jawaban:" + cn.get_Jawaban();
                Log.d("Check fill data :", log);
            }
        }

    public void setcontenttest(){ // TODO: fungsi buat get data dari db
        int profilecount = (int) maindb.getprofilecount(); // TODO: this function have function get index/id in database
        maindb.close();
        radioGroup.clearCheck();

            if (x >= profilecount) {
                stopchronometer();
                updatenilai(score);
                intervalgame();
                Intent intentskore = new Intent(Maingameactivity.this, SkoreActivity.class);
                startActivity(intentskore);
                finish();
            } else {
                imagesoal2.setImageResource(maindb.getAlldata().get(x).get_image());
                soaltext.setText(maindb.getAlldata().get(x).get_Soal());
                opsia.setText(maindb.getAlldata().get(x).get_Pil_A());
                opsib.setText(maindb.getAlldata().get(x).get_Pil_B());
                opsic.setText(maindb.getAlldata().get(x).get_Pil_C());
                opsid.setText(maindb.getAlldata().get(x).get_Pil_D());
                jawaban = maindb.getAlldata().get(x).get_Jawaban();
            }
            x++;
    }

    public void checkjawaban(){
       if(opsia.isChecked()){
           if(opsia.getText().toString().equals(jawaban)){
               score = score + 5;
               opsia.setBackground(getDrawable(R.drawable.option_shape_true));
               setcontenttest();
           } else {
               // kenapa kita ngak nentuin poinnya disini, ngak usah kasih pake Array
                score = score + 1; // contoh : kalo salah di A nilainya tetap 1
                stopchronometer();
                updatenilai(score);
                intervalgame();
                opsia.setBackground(getDrawable(R.drawable.option_shape_false));
                Intent exitintent = new Intent(Maingameactivity.this,SkoreActivity.class);
                startActivity(exitintent);
                finish();
           }
       } else if(opsib.isChecked()){
           if(opsib.getText().toString().equals(jawaban)){
               score = score + 5;
               opsib.setBackground(getDrawable(R.drawable.option_shape_true));
               setcontenttest();
           } else {
               score = score + 2; // kalo salah di B dia dikasih nilai 2
               stopchronometer();
               updatenilai(score);
               intervalgame();
               opsib.setBackground(getDrawable(R.drawable.option_shape_false));
               Intent exitintent = new Intent(Maingameactivity.this,SkoreActivity.class);
               startActivity(exitintent);
               finish();
           }
       } else if(opsic.isChecked()){
           if(opsic.getText().toString().equals(jawaban)){
               score = score + 5;
               opsic.setBackground(getDrawable(R.drawable.option_shape_true));
               setcontenttest();
           } else {
               score = score + 3; // kalo salah di c dikasih nilai 3
               stopchronometer();
               updatenilai(score);
               intervalgame();
               opsic.setBackground(getDrawable(R.drawable.option_shape_false));
               Intent exitintent = new Intent(Maingameactivity.this,SkoreActivity.class);
               startActivity(exitintent);
               finish();
           }
       } else if(opsid.isChecked()){
           if(opsid.getText().toString().equals(jawaban)){
               score = score + 5;
               opsid.setBackground(getDrawable(R.drawable.option_shape_true));
               setcontenttest();
           } else {
               score = score + 4; //kalo salah di c dikasih nilai 4
               stopchronometer();
               updatenilai(score);
               intervalgame();
               opsid.setBackground(getDrawable(R.drawable.option_shape_false));
               Intent exitintent = new Intent(Maingameactivity.this,SkoreActivity.class);
               startActivity(exitintent);
               finish();
           }
       } else {
           Toast.makeText(this,"Mohon pilih jawaban anda",Toast.LENGTH_SHORT).show();
       }
    }

    private void datasoal(){
        // -----------------------------------------------------------------------------------------
        // TODO : NO 1 (Easy)
        maindb.adddata(new Setget_maingame_database(R.drawable.maskot2," Square ingin melakukan perjalanan untuk mengunjungi 5 kota di negaranya: Kota A, Kota\n" +
                "B, Kota C, Kota D, Kota E, Kota F. Kota-kota tersebut dihubungkan dengan jalur bus. Rute\n" +
                "bus yang tersedia (dalam dua arah) adalah sebagai berikut:\n" +
                "\uF0B7 Kota C – Kota A\n" +
                "\uF0B7 Kota A – Kota B\n" +
                "\uF0B7 Kota F – Kota C\n" +
                "\uF0B7 Kota E – Kota D\n" +
                "Tantangan:\n" +
                "Jika Square memulai perjalanannya dari Kota C dengan bus, kota mana yang tidak dapat\n" +
                "dikunjungi?","Kota E","Kota A","Kota C","Kota B","Kota E"));

        // TODO : NO 2 (Easy)
        maindb.adddata(new Setget_maingame_database(R.drawable.asset_mathgo_soal_no2_new," Sesampainya Square di stasiun bus kota A, ia segera memesan tiket untuk perjalanan ke\n" +
                "kota berikutnya. Namun saat menerima tiket bus. Ia mendapatkan sebuah pesan yang harus\n" +
                "dipecahkan dari penjaga loket di stasiun bus tersebut. Untuk mengerti pesan aslinya, Square\n" +
                "harus mengurutkan kartu sesuai nomor kartu. Misalnya, untuk mengirim pesan\n" +
                "DANCETIME, Square membuat 3 kartu sebagai berikut:","LANHATTIDIHAIJA","HATIHATIDIJALAN","JALANDIHATIHATI","HATIHATIJALANDI","HATIHATIDIJALAN"));

        // TODO : NO 3 (Easy)
        maindb.adddata(new Setget_maingame_database(R.drawable.asset_mathgo_soal_no3,"Saat menunggu bus datang, square mengamati beberapa bus yang terparkir di stasiun. Dan\n" +
                "ia menghitung bus warna-warni yang berjejer di parkiran stasiun. Saat square telah sampai di\n" +
                "tujuannya ia pun mencatat :\n" +
                "\uF0A7 Bus biru ada 10\n" +
                "\uF0A7 Bus merah ada 44\n" +
                "\uF0A7 Bus putih ada 3\n" +
                "\uF0A7 Bus hijau ada 8\n" +
                "\uF0A7 Bus hitam ada 15\n" +
                "Square mengetikkan angka itu memakai program komputer dan hasilnya sebuah diagram\n" +
                "batang sebagai berikut","Merah","Biru","Putih","Hitam","Biru"));
        //------------------------------------------------------------------------------------------

        // TODO: NO 1(Med)
        maindb.adddata(new Setget_maingame_database(R.drawable.maskot2,"Untuk mengisi waktu luangnya selama 6 hari, Square dan Side merencanakan tinggal di\n" +
                "sebuah kampung di rumah paman. Kebetulan, di sana ada tiga petani A, B, dan C yang\n" +
                "membutuhkan bantuan untuk menggarap sawahnya masing masing. Mereka menawari Square\n" +
                "dan Side upah jika mau membantu mereka. Masing-masing petani tersebut memberikan\n" +
                "penawaran yang berbeda:\n" +
                "\uF0B7 Petani A menawarkan 10 ribu rupiah buat masing-masing (Square dan Side) setiap hari.\n" +
                "\uF0B7 Petani B hanya akan memberi Square sepuluh ribu rupiah pada hari pertama kemudian\n" +
                "setiap berikutnya menaikkan sebesar 10 ribu menjadi 20 ribu, 30 ribu, dan seterusnya,\n" +
                "sementara ia akan memberi Side di hari pertama 100 ribu rupiah dan kemudian diturunkan 10\n" +
                "ribu rupiah setiap hari berikutnya menjadi 90 ribu, 80 ribu, dan seterusnya.\n" +
                "\uF0B7 Petani C tidak tertarik dibantu Square, sehingga ia hanya akan memberi 1 ribu rupiah di\n" +
                "hari pertama saja dan tidak akan memberi apapun di hari berikutnya. Sementara untuk Side,\n" +
                "ia akan memberikan seribu rupiah pada hari pertama, lalu setiap hari berikutnya dua kali lipat\n" +
                "sebelumnya. Jadi Side akan mendapatkan seribu rupiah, 2 ribu rupiah, 4 ribu rupiah, 8 ribu\n" +
                "rupiah dan seterusnya.\n" +
                "Mereka berniat untuk melewati setiap hari masa liburnya di kampung paman dengan\n" +
                "membantu petani, dan mereka berdua sudah berjanji untuk bekerja pada petani yang sama.\n" +
                "Mengenai upah, mereka juga diam-diam sudah sepakat untuk membagi sama rata dari yang\n" +
                "diperoleh berdua.\n" +
                "Tantangan: Kepada petani yang mana mereka bekerja sehingga mendapat upah yang paling \n" +
                "banyak ?","A","B","C","A atau B","B"));

        // TODO: NO 2(Med)
        maindb.adddata(new Setget_maingame_database(R.drawable.asset_mathgo_soal_no2_med,"Setelah membantu petani, Square menebang pohon dan memotongnya sehingga setiap\n" +
                "pohon menghasilkan batang pohon yang panjangnya 10 meter.","5","6","7","8","6"));

        // TODO: NO 3(Med)
        maindb.adddata(new Setget_maingame_database(R.drawable.asset_mathgo_soal_no3_med_rev," Side sedang berjalan-jalan di kampung dan berjalan menyusuri jalan dari tempat satu ke\n" +
                "tempat lain mengikuti petunjuk yang diberikan. Pada setiap pertemuan beberapa ruas terdapat\n" +
                "putaran (jalan memutar) dan Side akan memutar berlawanan arah jarum jam dan mengambil\n" +
                "simpangan urutan tertentu sesuai yang petunjuk yang diberikan.\n" +
                "Petunjuk yang diberikan berupa angka-angka urutan simpangan. Misalnya petunjuk \"4 1 2\"\n" +
                "akan diikuti robot sebagai berikut: \n" +
                "\uF0B7 Pada putaran pertama, ambil simpangan ke-4. \n" +
                "\uF0B7 Pada putaran kedua, ambil simpangan ke-1. \n" +
                "\uF0B7 Pada simpangan ketiga, ambil simpangan ke-2.","A","B","C","D","B"));

        //------------------------------------------------------------------------------------------

        // TODO: NO 1 (Hard)
        maindb.adddata(new Setget_maingame_database(R.drawable.maskot2,"Suatu sub-string disebut “randem” jika dinyatakan sebagai dua rangkaian karakter\n" +
                "berturutan yang identik. Banyaknya karakter dalam suatu randem disebut dengan panjang\n" +
                "randem. Misalnya string AABABA mempunyai 3 randem: AA (panjang 2), ABAB dan\n" +
                "BABA (panjang 4).\n" +
                "Tantangan:\n" +
                "Tentukan panjang dari randem terpanjang string TCTACTAACCTACTAACAC","10 atau lebih","8","6","4","10 atau lebih"));

        // TODO: NO 2 (Hard)
        maindb.adddata(new Setget_maingame_database(R.drawable.mathgo_hard_no2_asset,"Sround menciptakan sistem pengkodean kata yang disebut kode berang , dengan memakai peta diatas :\n +" +
                "▪ Setiap pohon di taman diberi nama dengan satu huruf.\n" +
                "▪ Kode untuk setiap huruf ditemukan dengan cara mencapai pohon tersebut dengan berbelok\n" +
                "kiri (L)\n" +
                "dan kanan (R).\n" +
                "▪ Kode untuk setiap huruf selalu dimulai dari pintu masuk taman (bertanda panah).\n" +
                "Contoh-contoh :\n" +
                "▪ Contoh 1: Kode untuk A adalah LL karena untuk mencapai pohon A dari pintu masuk\n" +
                "taman kamu\n" +
                "harus berbelok kiri dua kali.\n" +
                "▪ Contoh 2: Kode untuk kata BAR adalah LRLLLR.\n" +
                "Tantangan:\n" +
                "Berapa banyak huruf dalam kode berang tersebut untuk kata BEAR?","10","8","9","4","9"));

        // TODO: NO 3 (Hard)
        maindb.adddata(new Setget_maingame_database(R.drawable.mathgo_hard_no3_asset,"Candyous adalah robot yang diprogram untuk mengumpulkan permen sebanyak\n" +
                "mungkin yang terhampar di lantai yang terdiri dari petak-petak. Tugas tersebut dilakukan\n" +
                "pada saat robot berjalan melalui petak demi petak lantai. Setiap petak di lantai sebagai\n" +
                "tergambar di bawah ini memiliki 0, 1, 2 atau 3 permen. Candyous mulai dari petak S (untuk\n" +
                "start) di kiri bawah dan berakhir di petak F (untuk finish) di kanan atas. Namun, Candyous\n" +
                "memiliki keterbatasan: setiap kali berpindah, dia hanya bisa berpindah dari satu petak ke\n" +
                "petak berikutnya di sebelah kanannya atau di sebelah atasnya","14","13","12","11","14"));

        //------------------------------------------------------------------------------------------
    }


    public void updatenilai(int skorakhir){
        if(skorakhir != 0){ // jika scorenya ngak sama dengan 0
            // set nilainya simpen nilainya
            Sharepreference.setNilai(this,score);
            Sharepreference.setlowervalue(this,score);
            checklowernilai();
            checktempnilai();
        }
    }

    public void checktempnilai(){
        if(Sharepreference.gettempnilai(this) < Sharepreference.getnilai(this)){ // kalo tempnilai nilainya kurang dari getnilai
            // datanya di update
            Sharepreference.cleartempnilai(this);
            Sharepreference.settempnilai(this,score);
        } else {
            // kalo data gettempnilai lebih besar atau sama dengan dari getnilai, nilainya tetap
            Sharepreference.gettempnilai(this);
        }
    }

    // TODO: Check this
    public void checklowernilai(){
        if(Sharepreference.getprevlowerval(this) == 0){
            // fill preflowerval from getlowervalue if 0
            Sharepreference.setprevlowerval(this,Sharepreference.getlowervalue(this));
        } else if(Sharepreference.getnilai(this) > Sharepreference.getprevlowerval(this)) {
            Sharepreference.getprevlowerval(this);
        }
    }

    private void checklength(List checklist){
        // for check list content
        Log.v("List Length","Check: " + checklist.toArray().length); // TODO : This list length = 0
    }

    private void deleteall(List contentlist){
        try {
            for (int i = 0; i < contentlist.toArray().length; i++) {
                if (!contentlist.equals(0)) {
                    maindb.delete();
                } else {

                }
            }
        } catch (NullPointerException e){
            Log.v("Null Pointer warning : ",e.toString());
        }
    }

    public void intervalgame() {
        int index = 1;
       if (Sharepreference.getintervalgame(this) == 0){
           Sharepreference.setintervalgame(this, index);
       } else {
           Sharepreference.setintervalgame(this,Sharepreference.getintervalgame(this)+1);
       }
    }

    public void startchronometer(){
        if(!resume){
            countup_timer.setBase(SystemClock.elapsedRealtime());
            countup_timer.start();
        } else {
            countup_timer.start();
        }
    }

    private void stopchronometer(){
        countup_timer.stop();
        countup_timer.setText("00:00");
    }

    // for clear up true/false option
    private void clear_opsion(){
        int profilecount = (int) maindb.getprofilecount();
        if(x <= profilecount){
            opsia.setBackground(getDrawable(R.drawable.option_shape));
            opsib.setBackground(getDrawable(R.drawable.option_shape));
            opsic.setBackground(getDrawable(R.drawable.option_shape));
            opsid.setBackground(getDrawable(R.drawable.option_shape));
        }
        x++;
    }

    // for show jawaban
    private void show_jawaban_soal_fragment(){
        // using fragment for jawaban
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.jawaban_fragment, new Jawaban_maingame_fragment());
        ft.replace(R.id.soal_fragment, new Soal_maingame_fragment());
        ft.commit();
    }


    @Override
    public void onBackPressed() {
        Toast.makeText(this,"Mohon selesaikan terlebih dahulu",Toast.LENGTH_SHORT).show();
    }

}