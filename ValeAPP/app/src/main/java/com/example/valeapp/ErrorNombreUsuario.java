package com.example.valeapp;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONObject;

import java.util.HashMap;

public class ErrorNombreUsuario extends AppCompatActivity {
    private EditText usuarioT;
    private String usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.error_nombre_usuario);
    }
}
