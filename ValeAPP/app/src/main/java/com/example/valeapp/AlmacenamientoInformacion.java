package com.example.valeapp;

import android.content.Context;
import android.content.SharedPreferences;

public class AlmacenamientoInformacion {

    public static final String PREF_NAME = "ValeAPP.shared.pref";
    public static final String PREF_KEY = "ValeAPP.shared.username";

    public AlmacenamientoInformacion() {
    }

    public void save(Context context, String text) {
        SharedPreferences sharePref;
        SharedPreferences.Editor editor;
        sharePref = context.getSharedPreferences(PREF_NAME,Context.MODE_PRIVATE);
        editor = sharePref.edit();
        editor.putString(PREF_KEY,text);
        editor.apply();
    }

    public String getData(Context context) {
        SharedPreferences sharePref;
        String text;
        sharePref = context.getSharedPreferences(PREF_NAME,Context.MODE_PRIVATE);
        text = sharePref.getString(PREF_KEY,null);
        return text;
    }
}
