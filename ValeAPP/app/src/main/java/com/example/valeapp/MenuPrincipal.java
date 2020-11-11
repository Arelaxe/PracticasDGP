package com.example.valeapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class MenuPrincipal extends AppCompatActivity {
    private String usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_principal);

        final Button botonTareas = findViewById(R.id.botonMisTareas);
        final Button botonProfes = findViewById(R.id.botonMisProfes);
        final Button botonPreferencias = findViewById(R.id.botonMisPreferencias);

        //Boton tareas
        botonTareas.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

            }
        });

        //Boton profes
        botonProfes.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

            }
        });

        //Boton preferencias
        botonPreferencias.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                irAMisPreferencias();
            }
        });
    }

    private void irAMisTareas(){
        /*Intent intent = new Intent(this, MisTareas.class);
        intent.putExtra("usuario", usuario);
        startActivity(intent);*/
    }

    private void irAMisProfes(){
        /*Intent intent = new Intent(this, MisProfes.class);
        intent.putExtra("usuario", usuario);
        startActivity(intent);*/
    }

    private void irAMisPreferencias(){
        Intent intent = new Intent(this, MisPreferencias.class);
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
