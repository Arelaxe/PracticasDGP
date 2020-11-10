package com.example.valeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;

public class MainActivity extends AppCompatActivity {

    String nombreUsuario;

    private Runnable taskNombreUsuario = new Runnable() {
        public void run() {
            inicioSesionNombreUsuario();
        }
    };

    private Runnable taskContraseña = new Runnable() {
        public void run() {
            inicioSesionPass(nombreUsuario);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AlmacenamientoInformacion infoUsuario = new AlmacenamientoInformacion();
        Context mContext = getApplicationContext();

        nombreUsuario = infoUsuario.getData(mContext);

        Handler handler = new Handler();


        if(nombreUsuario == null) {
            //Login Usuario
            handler.postDelayed(taskNombreUsuario, 1500);
        }
        else {
            // Login contraseña
            handler.postDelayed(taskContraseña, 1500);
        }


    }

    private void inicioSesionNombreUsuario() {
        Intent intent = new Intent(this, InicioSesionNombreUsuario.class);
        startActivity(intent);
    }

    private void inicioSesionPass(String usuario) {
        Intent intent = new Intent(this, InicioSesionPass1.class);
        intent.putExtra("usuario", usuario);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);

    }
}