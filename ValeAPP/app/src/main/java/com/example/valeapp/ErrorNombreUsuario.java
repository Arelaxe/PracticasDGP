package com.example.valeapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONObject;

import java.util.HashMap;

public class ErrorNombreUsuario extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.error_nombre_usuario);

        //Boton Atras
        final ImageButton volverInicioSesionNombreUsuario = findViewById(R.id.idBotonVolverNombreUsuario);

        volverInicioSesionNombreUsuario.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                inicioSesionNombreUsuario();
            }
        });
    }

    private void inicioSesionNombreUsuario() {
        Intent intent = new Intent(this, InicioSesionNombreUsuario.class);
        startActivity(intent);
    }
}
