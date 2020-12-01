package com.example.valeapp;

import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.universalvideoview.UniversalMediaController;
import com.universalvideoview.UniversalVideoView;

import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;

public class Texto extends AppCompatActivity{

    private EditText usuarioT;
    private String usuario;
    private String creador;
    private String nombreTarea;
    private Boolean guardarRespuesta;
    private Boolean tareaDetallada;
    private String nombreMultimadia;
    private Toolbar myToolbar;
    private String tipo;
    private String mote;
    private String texto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.texto);

        Bundle bundle = getIntent().getExtras();
        usuario = bundle.getString("usuario");
        creador = bundle.getString("creador");
        nombreTarea = bundle.getString("nombreTarea");
        guardarRespuesta = bundle.getBoolean("guardarRespuesta");
        nombreMultimadia = bundle.getString("nombreMultimedia");
        tipo = bundle.getString("tipo");
        tareaDetallada = bundle.getBoolean("tareaDetallada");

        if (guardarRespuesta){

        }

        myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.barra_de_tareas);

        //Modicar Barra de Tareas para esta pantalla
        if (tareaDetallada){
            texto = bundle.getString("texto");
            final ImageButton flechaAtras = findViewById(R.id.flechaVolverMenuAnterior);
            flechaAtras.setVisibility(View.VISIBLE);
            flechaAtras.setContentDescription("Volver a la tarea");
            final TextView textoFlechaAtras = findViewById(R.id.textoVolverAMenuAnterior);
            textoFlechaAtras.setText("VOLVER A LA TAREA");
            textoFlechaAtras.setVisibility(View.VISIBLE);
            final ImageButton botonLogout = findViewById(R.id.botonLogout);
            final ImageButton botonAtras = findViewById(R.id.flechaVolverMenuAnterior);

            //Boton logout
            botonLogout.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    irALogout();
                }
            });

            //Boton Atrás
            botonAtras.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    volverATarea();
                }
            });
        }
        else {
            cargarTextoRespuesta();
            final ImageButton flechaAtras = findViewById(R.id.flechaVolverMenuAnterior);
            flechaAtras.setVisibility(View.VISIBLE);
            flechaAtras.setContentDescription("Volver a respuesta");
            final TextView textoFlechaAtras = findViewById(R.id.textoVolverAMenuAnterior);
            textoFlechaAtras.setText("VOLVER A LA RESPUESTA");
            textoFlechaAtras.setVisibility(View.VISIBLE);
            final ImageButton botonLogout = findViewById(R.id.botonLogout);
            final ImageButton botonAtras = findViewById(R.id.flechaVolverMenuAnterior);

            //Boton logout
            botonLogout.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    irALogout();
                }
            });

            //Boton Atrás
            botonAtras.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    volverARespuestaTarea();
                }
            });
            mote = bundle.getString("mote");
        }


        mostrarTexto();
    }

    private void mostrarTexto(){
        TextView descripcionTarea =findViewById(R.id.mostrar_texto);
        descripcionTarea.setText(texto.toUpperCase());
        descripcionTarea.setContentDescription(texto);
    }

   private void cargarTextoRespuesta(){

   }

    @Override
    public void onBackPressed() {
        if(tareaDetallada){
            volverATarea();
        }
        else {
            volverARespuestaTarea();
        }
    }

    public void volverATarea(){
        Intent intent = new Intent(this, TareaDetallada.class);
        intent.putExtra("usuario", usuario);
        intent.putExtra("creador", creador);
        intent.putExtra("nombreTarea", nombreTarea);
        intent.putExtra("guardarRespuesta", guardarRespuesta);
        if (guardarRespuesta){

        }
        startActivity(intent);
    }

    public void volverARespuestaTarea(){
        Intent intent = new Intent(this, RespuestaTarea.class);
        intent.putExtra("usuario", usuario);
        intent.putExtra("creador", creador);
        intent.putExtra("nombreTarea", nombreTarea);
        intent.putExtra("guardarRespuesta", guardarRespuesta);
        intent.putExtra("mote", mote);
        intent.putExtra("tipoRespuesta",tipo);
        if (guardarRespuesta){

        }
        startActivity(intent);
    }

    private void irALogout(){
        Intent intent = new Intent(this, Logout.class);
        startActivity(intent);
    }
}
