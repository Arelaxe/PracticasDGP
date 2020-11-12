package com.example.valeapp;

import android.content.Intent;
import android.media.Image;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.concurrent.ExecutionException;

public class MisPreferencias extends AppCompatActivity {
    private String usuario;
    boolean audioSeleccionado = true;
    boolean videoSeleccionado = true;
    boolean textoSeleccionado = true;
    JSONObject jsonPreferencias;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.preferencias);

        Bundle bundle = getIntent().getExtras();
        usuario = bundle.getString("usuario");

        //Obtener de la base de datos preferencias iniciales
        ////////////////////////////////////////////////////
        try {
            new GetPreferencias().execute().get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (audioSeleccionado){

        }
        if (videoSeleccionado){

        }
        if (textoSeleccionado){

        }

        final Button botonPrefAudio = findViewById(R.id.botonPreferenciaAudio);
        final Button botonPrefVideo = findViewById(R.id.botonPreferenciaVideo);
        final Button botonPrefTexto = findViewById(R.id.botonPreferenciaTexto);

        final ImageView imagenAudio = findViewById(R.id.tickAudioSeleccionado);
        final ImageView imagenVideo = findViewById(R.id.tickVideoSeleccionado);
        final ImageView imagenTexto = findViewById(R.id.tickTextoSeleccionado);

        //Boton Preferencias Audio
        botonPrefAudio.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                cambiarPreferenciaAudio(botonPrefAudio, imagenAudio);
                //Actualizar Base de datos
            }
        });

        //Boton Preferencias Video
        botonPrefVideo.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                cambiarPreferenciaVideo(botonPrefVideo, imagenVideo);
                //Actualizar Base de datos
            }
        });

        //Boton Preferencias Texto
        botonPrefTexto.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                cambiarPreferenciaTexto(botonPrefTexto, imagenTexto);
                //Actualizar Base de datos
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

    private void cambiarPreferenciaAudio(Button boton, ImageView imagen){
        if(audioSeleccionado){
            audioSeleccionado = false;
            boton.setContentDescription(getResources().getString(R.string.opci_n_de_audio_no_seleccionada_pulsa_para_seleccionar));
        }
        else{
            audioSeleccionado = true;
            boton.setContentDescription(getResources().getString(R.string.opci_n_de_audio_seleccionada_pulsa_para_quitar_la_selecci_n));
        }
        cambiarEstadoSelección(imagen, audioSeleccionado);
    }

    private void cambiarPreferenciaVideo(Button boton, ImageView imagen){
        if(videoSeleccionado){
            videoSeleccionado = false;
            boton.setContentDescription(getResources().getString(R.string.opci_n_de_v_deo_no_seleccionada_pulsa_para_seleccionar));
        }
        else{
            videoSeleccionado = true;
            boton.setContentDescription(getResources().getString(R.string.opci_n_de_v_deo_seleccionada_pulsa_para_quitar_la_selecci_n));
        }
        cambiarEstadoSelección(imagen, videoSeleccionado);
    }

    private void cambiarPreferenciaTexto(Button boton, ImageView imagen){
        if(textoSeleccionado){
            textoSeleccionado = false;
            boton.setContentDescription(getResources().getString(R.string.opci_n_de_texto_no_seleccionada_pulsa_para_seleccionar));
        }
        else{
            textoSeleccionado = true;
            boton.setContentDescription(getResources().getString(R.string.opci_n_de_texto_seleccionada_pulsa_para_quitar_la_selecci_n));
        }
        cambiarEstadoSelección(imagen, textoSeleccionado);
    }

    private void cambiarEstadoSelección(ImageView imagen, boolean estado){
        if (estado){
            imagen.setVisibility(View.VISIBLE);
        }
        else{
            imagen.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void onBackPressed() {
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);
    }

    class GetPreferencias extends AsyncTask<String, String, JSONObject> {
        private final static String URL= "http://10.0.2.2/DS_P3/php/getRuletaActual.php";
        private JSONParser jsonParser = new JSONParser();

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        protected JSONObject doInBackground(String... args) {

            try{
                HashMap<String, String> params = new HashMap<>();

                params.put("username", usuario);

                Log.d("request", "starting");

                jsonPreferencias = jsonParser.makeHttpRequest(URL, "POST", params, "");

                if (jsonPreferencias != null) {
                    Log.d("JSON result:   ", jsonPreferencias.toString());

                    return jsonPreferencias;
                }
            } catch (Exception e) {

                e.printStackTrace();
            }
            return null;
        }
    }
}
