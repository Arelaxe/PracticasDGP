package com.example.valeapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.SupportActionModeWrapper;
import androidx.appcompat.widget.Toolbar;

import com.universalvideoview.UniversalMediaController;
import com.universalvideoview.UniversalVideoView;

import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;

public class Multimedia  extends AppCompatActivity{

    private EditText usuarioT;
    private String usuario;
    private String creador;
    private String nombreTarea;
    private Boolean guardarRespuesta;
    private String nombreMultimadia;
    private Toolbar myToolbar;
    private String tipo;
    private JSONObject jsonMultimedia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.multimedia);

        Bundle bundle = getIntent().getExtras();
        usuario = bundle.getString("usuario");
        creador = bundle.getString("creador");
        nombreTarea = bundle.getString("nombreTarea");
        guardarRespuesta = bundle.getBoolean("guardarRespuesta");
        nombreMultimadia = bundle.getString("nombreMultimedia");
        tipo = bundle.getString("tipo");

        comprobarMultimediaDescargado();

        if (guardarRespuesta){

        }

        myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.barra_de_tareas);

        //Modicar Barra de Tareas para esta pantalla
        final ImageButton flechaAtras = findViewById(R.id.flechaVolverMenuAnterior);
        flechaAtras.setVisibility(View.VISIBLE);
        flechaAtras.setContentDescription("Volver a la tarea");
        final TextView textoFlechaAtras = findViewById(R.id.textoVolverAMenuAnterior);
        textoFlechaAtras.setText("Volver a la tarea");
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
        reproducirMultimedia();
    }

    private void reproducirMultimedia(){

        UniversalVideoView multimediaView = findViewById(R.id.multimediaView);
        Uri uri = Uri.parse(Environment.getExternalStorageDirectory() + File.separator + nombreMultimadia);
        multimediaView.setVideoURI(uri);
        UniversalMediaController mMediaController = findViewById(R.id.media_controller);
        multimediaView.setMediaController(mMediaController);
        multimediaView.start();
    }

    private void comprobarMultimediaDescargado(){
        File multimedia = new File(Environment.getExternalStorageDirectory() + File.separator + nombreMultimadia);
        System.out.println(Environment.getExternalStorageDirectory() + File.separator + nombreMultimadia);
        if (!multimedia.exists()){
            descargarMultimedia(multimedia);
        }
    }

    private void descargarMultimedia(File multimedia){
        try {
            //Descargar el archivo multimedia
            try {
                new GetMultimediaTarea().execute().get();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            multimedia.createNewFile();
            FileOutputStream fos = new FileOutputStream(multimedia);
            byte[] data = Base64.decode(jsonMultimedia.getString("multimedia"), Base64.DEFAULT);
            fos.write(data);
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onBackPressed() {
        volverATarea();
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

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
           getSupportActionBar().hide();
        } else {
            getSupportActionBar().show();
        }
    }

    private void irALogout(){
        Intent intent = new Intent(this, Logout.class);
        startActivity(intent);
    }

    class GetMultimediaTarea extends AsyncTask<String, String, JSONObject> {
        private final static String URL= "obtener-multimedia-tarea-socio";
        private JSONParser jsonParser = new JSONParser();

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }
        protected JSONObject doInBackground(String... args) {

            try{
                HashMap<String, String> params = new HashMap<>();

                params.put("username", usuario);
                params.put("creador", creador);
                params.put("nombreTarea", nombreTarea);
                params.put("tipo", tipo);

                Log.d("request", "starting");

                jsonMultimedia = jsonParser.makeHttpRequest(URL, "GET", params, "");

                if (jsonMultimedia != null) {
                    Log.d("JSON result:   ", jsonMultimedia.toString());

                    return jsonMultimedia;
                }
            } catch (Exception e) {

                e.printStackTrace();
            }
            return null;
        }
    }
}
