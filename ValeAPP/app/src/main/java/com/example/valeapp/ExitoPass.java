package com.example.valeapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class ExitoPass extends AppCompatActivity {

    private Runnable task = new Runnable() {
        public void run() {
            irAMenuPrincipal();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exito_pass);

        Handler handler = new Handler();
        handler.postDelayed(task, 3000);
    }

    private void irAMenuPrincipal() {
        Intent intent = new Intent(this, MenuPrincipal.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
    }
}
