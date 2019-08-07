package com.oleg.profileapp.main;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.oleg.profileapp.R;
import com.oleg.profileapp.onboarding.OnBoardingActivity;

// Tanggal Pengerjaan : 19 Mei 2019
// NIM : 10116347
// Nama : Lukmannudin
// Kelas :IF - 8

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

        new Handler().postDelayed(() -> {
            startActivity(new Intent(SplashScreen.this, OnBoardingActivity.class));
            finish();
        }, 1000L);
    }
}
