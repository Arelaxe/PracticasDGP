package com.example.valeapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONObject;

import java.util.HashMap;

public class ErrorNombreUsuario extends AppCompatActivity {

    private Runnable task = new Runnable() {
        public void run() {
            inicioSesionNombreUsuario();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.error_nombre_usuario);

        Handler handler = new Handler();
        handler.postDelayed(task, 3000);
    }

    private void inicioSesionNombreUsuario() {
        Intent intent = new Intent(this, InicioSesionNombreUsuario.class);
        startActivity(intent);
    }
    @Override
    public void onBackPressed() {
    }
}
