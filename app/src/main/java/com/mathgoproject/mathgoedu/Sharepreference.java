package com.mathgoproject.mathgoedu;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

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

public class Sharepreference {
    static final String KEY_USERNAME_REGISTER = "Name";
    static final String KEY_USERNAME_ON_LOGIN = "Username_loggin_in";
    static final String KEY_STATUS_ON_LOGIN = "Status_loggin_in";
    static final String KEY_GETNILAI = "Nilai";
    static final String KEY_GETNILAI_LEV2 = "Nilai2";
    static final String KEY_GETNILAI_LEV3 = "Nilai3";
    static final String KEY_DAYNIGHT = "status";
    static final String KEY_PREVIOUS_VALUE = "prevvalue";
    static final String KEY_INDEX = "INDEXLENGTH";

    // deklarasi sharepreference
    private static SharedPreferences getSharePreference(Context context){
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static void setStatus(Context context,boolean status){
        SharedPreferences.Editor editor = getSharePreference(context).edit();
        editor.putBoolean(KEY_DAYNIGHT,status);
        editor.apply();
    }

    public static boolean getstatus(Context context){
        return getSharePreference(context).getBoolean(KEY_DAYNIGHT,false);
    }

    // ini buat set nilai
    public static void setNilai(Context context, int Nilai){
        SharedPreferences.Editor editor = getSharePreference(context).edit();
        editor.putInt(KEY_GETNILAI,Nilai);
        editor.apply();
    }

    public static int getnilai(Context context){
        return getSharePreference(context).getInt(KEY_GETNILAI,0);
    }

    // ini buat set nilai level 2
    public static void setNilai_lev2(Context context, int Nilai){
        SharedPreferences.Editor editor = getSharePreference(context).edit();
        editor.putInt(KEY_GETNILAI_LEV2,Nilai);
        editor.apply();
    }

    public static int getnilai_lev2(Context context){
        return getSharePreference(context).getInt(KEY_GETNILAI_LEV2,0);
    }

    // ini buat set nilai level 3
    public static void setNilai_lev3(Context context, int Nilai){
        SharedPreferences.Editor editor = getSharePreference(context).edit();
        editor.putInt(KEY_GETNILAI_LEV3,Nilai);
        editor.apply();
    }

    public static int getnilai_lev3(Context context){
        return getSharePreference(context).getInt(KEY_GETNILAI_LEV3,0);
    }

    public static void settempnilai(Context context, int Previousvalue){
        SharedPreferences.Editor editor = getSharePreference(context).edit();
        editor.putInt(KEY_PREVIOUS_VALUE,Previousvalue);
        editor.apply();
    }

    public static int gettempnilai(Context context){
        return getSharePreference(context).getInt(KEY_PREVIOUS_VALUE,0);
    }

    // Ini buat set Usernamernya
    public static void setRegisterUser(Context context,String Username){
        SharedPreferences.Editor editor = getSharePreference(context).edit();
        editor.putString(KEY_USERNAME_REGISTER,Username);
        editor.apply();
    }

    // ini buat return nilai KEY_USERNAME_REGISTER
    public static String getRegisterUser(Context context){
        return getSharePreference(context).getString(KEY_USERNAME_REGISTER,"");
    }

    // ini untuk login kalo satanya sudah diregister
    public static void setLoggerInUser(Context context,String Username){
        SharedPreferences.Editor editor = getSharePreference(context).edit();
        editor.putString(KEY_USERNAME_ON_LOGIN,Username);
        editor.apply();
    }

    // Return nilai set untuk didalam aplikasinnya
    public static String getLoggerInUser(Context context){
        return getSharePreference(context).getString(KEY_USERNAME_ON_LOGIN,"");
    }

    // deklarasi edit preference
    public static void setLoggerInStatus(Context context,boolean status){
        SharedPreferences.Editor editor = getSharePreference(context).edit();
        editor.putBoolean(KEY_STATUS_ON_LOGIN,status);
        editor.apply();
    }

    public static boolean getLoggerInStatus(Context context){
        return getSharePreference(context).getBoolean(KEY_STATUS_ON_LOGIN,false);
    }

    // catch array length
    public static void setarrayindex(Context context,int index){
        SharedPreferences.Editor editor = getSharePreference(context).edit();
        editor.putInt(KEY_INDEX,index);
        editor.apply();
    }

    public static int getarrayindex(Context context){
        return getSharePreference(context).getInt(KEY_INDEX,0);
    }

    // ini buat ganti datanya dengan cara meremove
    public static void ClearLoggerInUser(Context context){
        SharedPreferences.Editor editor = getSharePreference(context).edit();
        editor.remove(KEY_USERNAME_ON_LOGIN);
        editor.remove(KEY_STATUS_ON_LOGIN);
        editor.apply();
    }


    public static void clearnilai(Context context){
        SharedPreferences.Editor editor = getSharePreference(context).edit();
        editor.remove(KEY_GETNILAI);
        editor.apply();
    }

    public static void cleartempnilai(Context context){
        SharedPreferences.Editor editor = getSharePreference(context).edit();
        editor.remove(KEY_PREVIOUS_VALUE);
        editor.apply();
    }

    public static void clearindexarray(Context context){
        SharedPreferences.Editor editor = getSharePreference(context).edit();
        editor.remove(KEY_INDEX);
        editor.apply();
    }

}
