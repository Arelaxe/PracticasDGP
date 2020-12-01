
package com.example.valeapp;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaRecorder;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

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
    String tipoRespuesta = null;
    TextView textoEscucharAudio;
    TextView textoVerVideo;

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
        tipoRespuesta = bundle.getString("tipoRespuesta");

        nombreAudio = "Music/" + "Respuesta_" + nombreTarea + "_"+ mote + "_"+ usuario +".3gp";
        nombreVideo = "Movies/" + "Respuesta_" + nombreTarea + "_"+ mote + "_"+ usuario +".mp4";

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.barra_de_tareas);

        //Modicar Barra de Tareas para esta pantalla
        final ImageButton flechaAtras = findViewById(R.id.flechaVolverMenuAnterior);
        flechaAtras.setVisibility(View.VISIBLE);
        flechaAtras.setContentDescription("VOLVER A LA TAREA");
        final TextView textoFlechaAtras = findViewById(R.id.textoVolverAMenuAnterior);
        textoFlechaAtras.setText("VOLVER A LA TAREA");
        textoFlechaAtras.setVisibility(View.VISIBLE);
        final ImageButton botonAtras = findViewById(R.id.flechaVolverMenuAnterior);

        final ImageButton botonLogout = findViewById(R.id.botonLogout);
        if (guardarRespuesta){

        }
        else{

        }
        if (true){
            //crearBotonGrabarAudio();
            crearBotonGrabarVideo();
        }
        else if (true){
            //texto
        }
        else if (true){
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

        if (false){
            crearBotonEscucharAudio();
        }
        else if (true){
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

        Drawable dGrabar = getResources().getDrawable(R.drawable.grabar);
        Bitmap bitmap = ((BitmapDrawable) dGrabar).getBitmap();
        // Escalar
        Drawable dEscaladoGrabar = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(bitmap, 120, 120, true));

        Drawable dDejarGrabar = getResources().getDrawable(R.drawable.dejar_grabar);
        Bitmap bitmap2 = ((BitmapDrawable) dDejarGrabar).getBitmap();
        // Escalar
        Drawable dEscaladoDejarGrabar = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(bitmap2, 120, 120, true));

        botonGrabar.setBackgroundDrawable(dEscaladoGrabar);

        TextView textoAudio = new TextView(this);
        textoAudio.setText("GRABAR AUDIO");
        textoAudio.setTextSize(25);
        textoAudio.setGravity(Gravity.CENTER);
        textoAudio.setTextColor(getResources().getColor(R.color.black));
        botonGrabar.setTextOff("");
        botonGrabar.setTextOn("");

        //Boton Grabar
        botonGrabar.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // The toggle is enabled
                    grabacionAudio();
                    botonGrabar.setBackgroundDrawable(dEscaladoDejarGrabar);
                    botonGrabar.setContentDescription("Pulsa para grabar audio");
                    textoAudio.setText("PARAR GRABACIÓN");
                } else {
                    // The toggle is disabled
                    pararGrabacionAudio();
                    botonGrabar.setBackgroundDrawable(dEscaladoGrabar);
                    botonGrabar.setContentDescription("Pulsa para dejar de grabar audio");
                    textoAudio.setText("GRABAR AUDIO");
                }
            }
        });
        layout.addView(botonGrabar);
        layout.addView(textoAudio);
        textoAudio.setPadding(0, 30, 0, 60);
    }

    private void crearBotonGrabarVideo(){
        LinearLayout layout = (LinearLayout)findViewById(R.id.layoutRespuesta);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
        );

        Button botonGrabar = new Button(this);

        Drawable dGrabar = getResources().getDrawable(R.drawable.grabar_video);
        Bitmap bitmap = ((BitmapDrawable) dGrabar).getBitmap();
        // Escalar
        Drawable dEscaladoGrabar = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(bitmap, 120, 120, true));
        botonGrabar.setBackgroundDrawable(dEscaladoGrabar);

        botonGrabar.setContentDescription("Pulsa para grabar un vídeo");

        TextView textoVideo = new TextView(this);
        textoVideo.setText("GRABAR VIDEO");
        textoVideo.setTextSize(25);
        textoVideo.setGravity(Gravity.CENTER);
        textoVideo.setTextColor(getResources().getColor(R.color.black));

        //Boton Grabar
        botonGrabar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                irAGrabarVideo();
            }
        });
        layout.addView(botonGrabar);
        layout.addView(textoVideo);
        textoVideo.setPadding(0, 30, 0, 60);
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
        intent.putExtra("mote", mote);
        if (guardarRespuesta){

        }
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

    private void irAGrabarVideo(){
        Intent intent = new Intent(this, GrabarVideo.class);
        intent.putExtra("usuario", usuario);
        intent.putExtra("creador", creador);
        intent.putExtra("nombreTarea", nombreTarea);
        intent.putExtra("guardarRespuesta", guardarRespuesta);
        intent.putExtra("mote", mote);
        intent.putExtra("tipoRespuesta",tipoRespuesta);
        startActivity(intent);
    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(this, new String[]{WRITE_EXTERNAL_STORAGE, RECORD_AUDIO}, RequestPermissionCode);
    }

    public void grabarAudio(){
        if (tieneAudio){
            audio.setVisibility(INVISIBLE);
            textoEscucharAudio.setVisibility(INVISIBLE);
        }

        mediaRecorder = new MediaRecorder();
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);;
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
        textoEscucharAudio.setVisibility(VISIBLE);
    }

    public void mostrarMultimedia(String nombreMultimedia, String tipo){
        Intent intent = new Intent(this, Multimedia.class);
        intent.putExtra("usuario", usuario);
        intent.putExtra("creador", creador);
        intent.putExtra("nombreTarea", nombreTarea);
        intent.putExtra("guardarRespuesta", guardarRespuesta);
        intent.putExtra("nombreMultimedia", nombreMultimedia);
        intent.putExtra("tipo", tipo);
        System.out.println(tipo);
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

        textoEscucharAudio = new TextView(this);
        textoEscucharAudio.setText("ESCUCHAR AUDIO");
        textoEscucharAudio.setTextSize(25);
        textoEscucharAudio.setGravity(Gravity.CENTER);
        textoEscucharAudio.setTextColor(getResources().getColor(R.color.black));

        layout.addView(textoEscucharAudio);
        textoEscucharAudio.setPadding(0, 30, 0, 0);
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

        textoVerVideo = new TextView(this);
        textoVerVideo.setText("VER VIDEO");
        textoVerVideo.setTextSize(25);
        textoVerVideo.setGravity(Gravity.CENTER);
        textoVerVideo.setTextColor(getResources().getColor(R.color.black));
        layout.addView(textoVerVideo);
        textoVerVideo.setPadding(0, 30, 0, 30);
    }

    private Boolean comprobarMultimediaRespuesta(String nombreMultimedia){
        File multimedia = new File(Environment.getExternalStorageDirectory() + File.separator + nombreMultimedia);
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
