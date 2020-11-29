package com.example.valeapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaRecorder;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;

import static android.Manifest.permission.RECORD_AUDIO;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class RespuestaTarea extends AppCompatActivity{
    private String usuario;
    String creador;
    String nombreTarea;
    JSONObject jsonTareas;
    boolean guardarRespuesta;
    String AudioSavePathInDevice = null;
    MediaRecorder mediaRecorder ;
    public static final int RequestPermissionCode = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.respuesta_tarea);


        Bundle bundle = getIntent().getExtras();
        usuario = bundle.getString("usuario");
        creador = bundle.getString("creador");
        nombreTarea = bundle.getString("nombreTarea");
        guardarRespuesta = bundle.getBoolean("guardarRespuesta");

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.barra_de_tareas);

        //Modicar Barra de Tareas para esta pantalla
        final ImageButton flechaAtras = findViewById(R.id.flechaVolverMenuAnterior);
        flechaAtras.setVisibility(View.VISIBLE);
        flechaAtras.setContentDescription(getResources().getString(R.string.mis_tareas2));
        final TextView textoFlechaAtras = findViewById(R.id.textoVolverAMenuAnterior);
        textoFlechaAtras.setText(getResources().getString(R.string.mis_tareas3));
        textoFlechaAtras.setVisibility(View.VISIBLE);

        final ImageButton botonLogout = findViewById(R.id.botonLogout);
        final Button botonGrabar = findViewById(R.id.grabar);
        final ImageButton botonAtras = findViewById(R.id.flechaVolverMenuAnterior);

        if (guardarRespuesta){

        }
        else{

        }

        //Boton logout
        botonLogout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                irALogout();
            }
        });

        //Boton Atrás
        botonAtras.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                volverATareaDetallada();
            }
        });

        //Boton Grabar
        botonGrabar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                grabar();
            }
        });


    }

    private void irALogout(){
        Intent intent = new Intent(this, Logout.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        volverATareaDetallada();
    }

    public void volverATareaDetallada(){
        Intent intent = new Intent(this, RespuestaTarea.class);
        intent.putExtra("usuario", usuario);
        intent.putExtra("creador", creador);
        intent.putExtra("nombreTarea", nombreTarea);
        intent.putExtra("guardarRespuesta", guardarRespuesta);
        startActivity(intent);
    }

    public boolean checkPermission() {
        int result = ContextCompat.checkSelfPermission(getApplicationContext(),
                WRITE_EXTERNAL_STORAGE);
        int result1 = ContextCompat.checkSelfPermission(getApplicationContext(),
                RECORD_AUDIO);
        return result == PackageManager.PERMISSION_GRANTED &&
                result1 == PackageManager.PERMISSION_GRANTED;
    }

    public void grabar(){
        if(checkPermission()) {

            AudioSavePathInDevice = Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + "audio" + "AudioRecording.3gp";

            MediaRecorderReady();

            try {
                mediaRecorder.prepare();
                mediaRecorder.start();
            } catch (IllegalStateException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }


        } else {
            requestPermission();
        }
    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(this, new String[]{WRITE_EXTERNAL_STORAGE, RECORD_AUDIO}, RequestPermissionCode);
    }

    public void MediaRecorderReady(){
        mediaRecorder=new MediaRecorder();
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mediaRecorder.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);
        mediaRecorder.setOutputFile(AudioSavePathInDevice);
    }


    class EnviarRespuestaTarea extends AsyncTask<String, String, JSONObject> {
        private final static String URL= "obtener-tarea-socio";
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

                Log.d("request", "starting");

                jsonTareas = jsonParser.makeHttpRequest(URL, "GET", params, "");

                if (jsonTareas != null) {
                    Log.d("JSON result:   ", jsonTareas.toString());

                    return jsonTareas;
                }
            } catch (Exception e) {

                e.printStackTrace();
            }
            return null;
        }
    }
}
