package com.oleg.profileapp.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.oleg.profileapp.model.User;

// Tanggal Pengerjaan : 7 Agustus 2019
// NIM : 10116347
// Nama : Lukmannudin
// Kelas :IF - 8
public class Preferences {

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    private static Preferences INSTANCE = null;

    public static Preferences getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new Preferences(context);
        }
        return INSTANCE;
    }

    Preferences(Context context) {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        editor = sharedPreferences.edit();
    }

    public void setOnBoardingStopped(boolean status) {
        editor.putBoolean(ONBOARDINGSTOPPED, status);
        editor.apply();
    }

    public boolean onBoardingStopped() {
        return sharedPreferences.getBoolean(ONBOARDINGSTOPPED, false);
    }

    public void saveLogin(User user) {
        editor.putString(NIM, user.getNim());
        editor.putString(USERNAME, user.getUsername());
        editor.putString(KELAS, user.getKelas());
        editor.putString(TELEPON, user.getTelepon());
        editor.putString(EMAIL, user.getEmail());
        editor.putString(TWITTER, user.getTwitter());
        editor.putString(INSTAGRAM, user.getInstagram());
        editor.putString(FACEBOOK, user.getFacebook());
        editor.putString(PASSWORD, user.getPassword());
        editor.putString(DESCRIPTION, user.getDescription());
        editor.apply();
    }

    public User getUserLogin() {
        User user = new User();
        user.setNim(sharedPreferences.getString(NIM, ""));
        user.setUsername(sharedPreferences.getString(USERNAME, ""));
        user.setKelas(sharedPreferences.getString(KELAS, ""));
        user.setTelepon(sharedPreferences.getString(TELEPON, ""));
        user.setEmail(sharedPreferences.getString(EMAIL, ""));
        user.setTwitter(sharedPreferences.getString(TWITTER, ""));
        user.setInstagram(sharedPreferences.getString(INSTAGRAM, ""));
        user.setFacebook(sharedPreferences.getString(FACEBOOK, ""));
        user.setPassword(sharedPreferences.getString(PASSWORD, ""));
        user.setDescription(sharedPreferences.getString(DESCRIPTION,""));
        return user;
    }

    public void deleteLogin() {
        editor.clear();
        editor.apply();
    }


    static String NIM = "nim";
    static String USERNAME = "username";
    static String KELAS = "kelas";
    static String TELEPON = "telepon";
    static String EMAIL = "email";
    static String TWITTER = "twitter";
    static String INSTAGRAM = "instagram";
    static String FACEBOOK = "facebook";
    static String PASSWORD = "password";
    static String DESCRIPTION = "description";

    static String ONBOARDINGSTOPPED = "onboardingstopped";


}
