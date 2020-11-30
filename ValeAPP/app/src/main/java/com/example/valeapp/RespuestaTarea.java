package com.example.valeapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.hardware.Camera;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.universalvideoview.UniversalMediaController;
import com.universalvideoview.UniversalVideoView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;

import static android.Manifest.permission.RECORD_AUDIO;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;

public class RespuestaTarea extends AppCompatActivity{
    private String usuario;
    String creador;
    String nombreTarea;
    String mote;
    JSONObject jsonTareas;
    boolean guardarRespuesta;
    String AudioSavePathInDevice = null;
    String VideoSavePathInDevice = null;
    MediaRecorder mediaRecorder;
    String nombreAudio = null;
    String nombreVideo = null;
    Boolean tieneAudio = false;
    Boolean tieneVideo = false;
    ImageButton audio = null;
    ImageButton video = null;
    String tipo = null;

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
        mote = bundle.getString("mote");
        tipo = bundle.getString("tipo");

        nombreAudio = "Music/" + "Respuesta_" + nombreTarea + "_"+ mote +".3gp";
        nombreVideo = "Movies/" + "Respuesta_" + nombreTarea + "_"+ mote +".mp4";

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
        final ImageButton botonAtras = findViewById(R.id.flechaVolverMenuAnterior);

        final ImageButton botonLogout = findViewById(R.id.botonLogout);
        if (guardarRespuesta){

        }
        else{

        }

        if (tipo.equals("audio")){
            crearBotonGrabarAudio();
        }
        else if (tipo.equals("texto")){
            //crearBotonGrabarAudio();
        }
        else if (tipo.equals("video")){
            crearBotonGrabarVideo();
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

        tieneAudio = comprobarMultimediaRespuesta(nombreAudio);
        tieneVideo = comprobarMultimediaRespuesta(nombreVideo);

        if (tieneAudio){
            crearBotonEscucharAudio();
        }
        else if (tieneVideo){
            crearBotonVerVideo();
        }

    }

    private void crearBotonGrabarAudio(){
        LinearLayout layout = (LinearLayout)findViewById(R.id.layoutRespuesta);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
        );

        ToggleButton botonGrabar = new ToggleButton(this);

        //Boton Grabar
        botonGrabar.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // The toggle is enabled
                    grabacionAudio();
                } else {
                    // The toggle is disabled
                    pararGrabacionAudio();
                }
            }
        });
        layout.addView(botonGrabar);
    }

    private void crearBotonGrabarVideo(){
        LinearLayout layout = (LinearLayout)findViewById(R.id.layoutRespuesta);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
        );

        ToggleButton botonGrabar = new ToggleButton(this);

        //Boton Grabar
        botonGrabar.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // The toggle is enabled
                    grabacionVideo();
                } else {
                    // The toggle is disabled
                    pararGrabacionVideo();
                }
            }
        });
        layout.addView(botonGrabar);
    }

    private void irALogout(){
        Intent intent = new Intent(this, Logout.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed(){
        volverATareaDetallada();
    }

    public void volverATareaDetallada(){
        Intent intent = new Intent(this, TareaDetallada.class);
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

    public void grabacionAudio(){
        if(checkPermission()) {
            AudioSavePathInDevice = Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + nombreAudio;

            grabarAudio();

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

    public void grabacionVideo(){
        if(checkPermission()) {
            VideoSavePathInDevice = Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + nombreVideo;
            Camera.open();
            Camera.setPreviewDisplay();
            Camera.startPreview();
            Camera.unlock();
            grabarVideo();

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

    public void grabarAudio(){
        if (tieneAudio){
            audio.setVisibility(INVISIBLE);
        }

        mediaRecorder=new MediaRecorder();
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        //mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        //mediaRecorder.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mediaRecorder.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);
        mediaRecorder.setAudioEncodingBitRate(16*44100);
        mediaRecorder.setAudioSamplingRate(44100);

        mediaRecorder.setOutputFile(AudioSavePathInDevice);
    }

    public void grabarVideo(){
        if (tieneVideo){
            video.setVisibility(INVISIBLE);
        }

        mediaRecorder=new MediaRecorder();
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        //mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        //mediaRecorder.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mediaRecorder.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);
        mediaRecorder.setAudioEncodingBitRate(16*44100);
        mediaRecorder.setAudioSamplingRate(44100);

        mediaRecorder.setOutputFile(AudioSavePathInDevice);
    }


    public void pararGrabacionAudio(){
        mediaRecorder.stop();
        mediaRecorder.release();
        if (!tieneAudio){
            crearBotonEscucharAudio();
            tieneAudio = true;
        }
        audio.setVisibility(VISIBLE);
    }

    public void mostrarMultimedia(String nombreMultimedia, String tipo){
        Intent intent = new Intent(this, Multimedia.class);
        intent.putExtra("usuario", usuario);
        intent.putExtra("creador", creador);
        intent.putExtra("nombreTarea", nombreTarea);
        intent.putExtra("guardarRespuesta", guardarRespuesta);
        intent.putExtra("nombreMultimedia", nombreMultimedia);
        intent.putExtra("tipo", tipo);
        intent.putExtra("tareaDetallada", false);
        intent.putExtra("mote", mote);
        if (guardarRespuesta){

        }
        startActivity(intent);
    }

    private void crearBotonEscucharAudio () {
        audio = new ImageButton(this);
        audio.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mostrarMultimedia(nombreAudio, "audio");
            }
        });
        Drawable drAudio = getResources().getDrawable(R.drawable.audio);
        Bitmap bitmap = ((BitmapDrawable) drAudio).getBitmap();
        // Escalar
        Drawable dEscaladoAudio = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(bitmap, 120, 120, true));
        audio.setImageDrawable(dEscaladoAudio);
        audio.setContentDescription("Escuchar audio");
        LinearLayout layout = (LinearLayout) findViewById(R.id.layoutRespuesta);
        layout.addView(audio);
    }

    private void crearBotonVerVideo () {
        video = new ImageButton(this);
        video.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mostrarMultimedia(nombreVideo, "video");
            }
        });
        Drawable drAudio = getResources().getDrawable(R.drawable.video);
        Bitmap bitmap = ((BitmapDrawable) drAudio).getBitmap();
        // Escalar
        Drawable dEscaladoAudio = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(bitmap, 120, 120, true));
        video.setImageDrawable(dEscaladoAudio);
        video.setContentDescription("Ver video");
        LinearLayout layout = (LinearLayout) findViewById(R.id.layoutRespuesta);
        layout.addView(video);
    }

    private Boolean comprobarMultimediaRespuesta(String nombreMultimadia){
        File multimedia = new File(Environment.getExternalStorageDirectory() + File.separator + nombreMultimadia);
        return multimedia.exists();
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
