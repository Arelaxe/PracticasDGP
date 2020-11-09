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
            inicioSesionPass(nombreUsuario);
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