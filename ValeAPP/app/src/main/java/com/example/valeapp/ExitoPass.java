package com.example.valeapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class ExitoPass extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exito_pass);
/*
        //Boton Atras
        final ImageButton volverInicioSesionPass = findViewById(R.id.idBotonVolverPass);

        volverInicioSesionPass.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                inicioSesionPass();
            }
        });*/
    }

    private void inicioSesionPass() {
        Intent intent = new Intent(this, InicioSesionPass1.class);
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
