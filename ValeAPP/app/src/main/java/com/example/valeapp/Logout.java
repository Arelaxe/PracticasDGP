package com.example.valeapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

public class Logout extends AppCompatActivity {
    private Logout that;
    private Runnable task = new Runnable() {
        public void run() {
            that.finishAffinity();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.logout);

        that = this;

        Handler handler = new Handler();
        handler.postDelayed(task, 3000);

    }

    @Override
    public void onBackPressed() {
    }
}
