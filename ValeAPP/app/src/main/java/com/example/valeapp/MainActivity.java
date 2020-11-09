package com.example.valeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AlmacenamientoInformacion infoUsuario = new AlmacenamientoInformacion();
        Context mContext = getApplicationContext();

        String nombreUsuario = infoUsuario.getData(mContext);

        if(nombreUsuario == null) {
            //Login Usuario
            inicioSesionNombreUsuario();
        }
        else {
            // Login contraseña
            inicioSesionPass();
        }
    }

    private void inicioSesionNombreUsuario() {
        Intent intent = new Intent(this, InicioSesionNombreUsuario.class);
        startActivity(intent);
    }

    private void inicioSesionPass() {
        Intent intent = new Intent(this, InicioSesionPass1.class);
        startActivity(intent);
    }
}