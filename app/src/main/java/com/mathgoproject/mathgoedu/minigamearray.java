package com.mathgoproject.mathgoedu;

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

    public class minigamearray {

        public String[] pertanyaan = {
                "Suatu hari ibu meminta square membeli sesuatu diwarung \n " +
                        "Ibu : Square, belikan ibu 1 botol minyak, kalau ada telur beli 6\n" +
                        "Square : Baik bu \n" +
                        "dan beberapa menit kemudian ibu square terkejut dengan apa yang dibawa square\n\n" +
                        "pertanyaan :\n\n" +
                        "Apa yang dibawa square sehingga membuat ibunya terkejut ?",
                "Jika kalian tersesat dihutan dan sangat kelaparan, dan kalian menemukan makanan berupa daging busuk supaya kalian tidak sakit memakannya, apa yang dilakukan ?",
                "Jika perlu waktu merebus 20 menit agar sebutir telur bisa matang, berapa lama waktu merebus yang dibutuhkan agar 10 butir telur bisa matang?",
                "jika berupa kalimat berapakah Hasil dari '1' + '1' = ",
                "2004 = ?",
                "Apa huruf kelima dalam abjad ?",
        };

        private String[][] pilihanjawaban = {
                {"1 Minyak, 6 Telur","6 Minyak, 1 Telur","6 Minyak","6 Telur"},
                {"Langsung dimakan","Masak","Tinggalkan","lari"},
                {"20 menit","30 menit","40 menit","50 menit"},
                {"12","11","13","14"},
                {"2004","Dua Ribu Empat","Dua Ribu = Empat","Tak tahu lah :("},
                {"e","d","E","f"},
        };

        private String[] image = {
                "maskot2",
                "test",
                "maskot2",
                "test",
                "maskot2",
                "test",
        };


        private String[] jawabanbenar = {
                "6 Minyak",
                "Tinggalkan",
                "20 menit",
                "11",
                "Dua Ribu = Empat",
                "d",
        };


        public String getpertanyaan(int x) {
            String soal = pertanyaan[x];
            return soal;
        }

        public String getpilihanjawaban1(int x){
            String jawaban1 = pilihanjawaban[x][0];
            return jawaban1;
        }

        public String getpilihanjawaban2(int x){
            String jawaban2 = pilihanjawaban[x][1];
            return jawaban2;
        }

        public String getpilihanjawaban3(int x){
            String jawaban3 = pilihanjawaban[x][2];
            return jawaban3;
        }

        public String getpilihanjawaban4(int x){
            String jawaban4 = pilihanjawaban[x][3];
            return jawaban4;
        }

        public  String getgambar(int x){
            String gambar = image[x];
            return gambar;
        }

        public String getjawabanbenar(int x){
            String jawaban = jawabanbenar[x];
            return jawaban;
        }

    }

