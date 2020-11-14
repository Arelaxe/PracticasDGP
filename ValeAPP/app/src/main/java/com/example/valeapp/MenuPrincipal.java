package com.example.valeapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MenuPrincipal extends AppCompatActivity {
    private String usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_principal);

        Bundle bundle = getIntent().getExtras();
        usuario = bundle.getString("usuario");

        final Button botonTareas = findViewById(R.id.botonMisTareas);
        final Button botonProfes = findViewById(R.id.botonMisProfes);
        final Button botonPreferencias = findViewById(R.id.botonMisPreferencias);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.barra_de_tareas);

        //Modiciar Barra de Tareas para esta pantalla
        final ImageButton flechaAtras = findViewById(R.id.flechaVolverMenuAnterior);
        flechaAtras.setVisibility(View.INVISIBLE);
        final TextView textoFlechaAtras = findViewById(R.id.textoVolverAMenuAnterior);
        textoFlechaAtras.setVisibility(View.INVISIBLE);

        final ImageButton botonLogout = findViewById(R.id.botonLogout);

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

        //Boton logout
        botonLogout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                irALogout();
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

    private void irALogout(){
        Intent intent = new Intent(this, Logout.class);
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
