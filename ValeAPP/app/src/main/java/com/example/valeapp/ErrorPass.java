package com.example.valeapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Lifecycle;

import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

import static androidx.lifecycle.Lifecycle.State.RESUMED;
import static androidx.lifecycle.Lifecycle.State.STARTED;

public class ErrorPass extends AppCompatActivity {
    private String usuario;
    private boolean active = false;
    private Lifecycle estadoEnCicloVida;
    private Runnable task = new Runnable() {
        public void run() {
            inicioSesionPass();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.error_pass);

        Bundle bundle = getIntent().getExtras();

        usuario = bundle.getString("usuario");

        Handler handler = new Handler();
        handler.postDelayed(task, 3000);
    }

    private void inicioSesionPass() {
        Intent intent = new Intent(this, InicioSesionPass1.class);
        intent.putExtra("usuario", usuario);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
    }
}
