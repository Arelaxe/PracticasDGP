package com.example.valeapp;

import android.content.Intent;
import android.hardware.Camera;
import android.media.CamcorderProfile;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
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

import org.json.JSONObject;

import java.io.IOException;

import static android.view.View.INVISIBLE;

public class GrabarVideo extends AppCompatActivity {
    private String usuario;
    String creador;
    String nombreTarea;
    String mote;
    JSONObject jsonTareas;
    boolean guardarRespuesta;
    String AudioSavePathInDevice = null;
    String VideoSavePathInDevice = null;
    MediaRecorder mediaRecorder;
    String nombreVideo = null;
    Boolean tieneAudio = false;
    Boolean tieneVideo = false;
    ImageButton audio = null;
    ImageButton video = null;
    String tipoRespuesta = null;
    Camera camara;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.grabar_video);


        Bundle bundle = getIntent().getExtras();
        usuario = bundle.getString("usuario");
        creador = bundle.getString("creador");
        nombreTarea = bundle.getString("nombreTarea");
        guardarRespuesta = bundle.getBoolean("guardarRespuesta");
        mote = bundle.getString("mote");
        tipoRespuesta = bundle.getString("tipoRespuesta");

        nombreVideo = "Movies/" + "Respuesta_" + nombreTarea + "_"+ mote +".mp4";

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.barra_de_tareas);

        //Modicar Barra de Tareas para esta pantalla
        final ImageButton flechaAtras = findViewById(R.id.flechaVolverMenuAnterior);
        flechaAtras.setVisibility(View.VISIBLE);
        flechaAtras.setContentDescription("VOLVER A LA RESPUESTA");
        final TextView textoFlechaAtras = findViewById(R.id.textoVolverAMenuAnterior);
        textoFlechaAtras.setText("VOLVER A LA RESPUESTA");
        textoFlechaAtras.setVisibility(View.VISIBLE);
        final ImageButton botonAtras = findViewById(R.id.flechaVolverMenuAnterior);

        final ImageButton botonLogout = findViewById(R.id.botonLogout);


        //Boton logout
        botonLogout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                irALogout();
            }
        });

        //Boton Atrás
        botonAtras.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                volverARespuesta();
            }
        });

        final ToggleButton botonGrabar = findViewById(R.id.botonGrabar);

        Camera camara = Camera.open();
        camara.getParameters();
        try {
            camara.setPreviewDisplay(findViewById(R.id.grabarVideoSurface));
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Boton Grabar
        botonGrabar.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // The toggle is enabled
                    try {
                        grabacionVideo();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    // The toggle is disabled
                    pararGrabacionVideo();
                }
            }
        });
    }

    public void grabacionVideo() throws IOException {
        camara.unlock();
        mediaRecorder = new MediaRecorder();
        mediaRecorder.setCamera(camara);
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.CAMCORDER);
        mediaRecorder.setVideoSource(MediaRecorder.VideoSource.CAMERA);
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
        mediaRecorder.setAudioEncoder(MediaRecorder.OutputFormat.THREE_GPP);
        mediaRecorder.setVideoEncoder(MediaRecorder.OutputFormat.MPEG_4);
        mediaRecorder.setOutputFile(Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + nombreVideo);
        camara.setPreviewDisplay(findViewById(R.id.grabarVideoSurface));
        mediaRecorder.prepare();
        mediaRecorder.start();
    }

    public void pararGrabacionVideo(){
        mediaRecorder.stop();
        mediaRecorder.release();
        camara.lock();
    }

    private void irALogout(){
        Intent intent = new Intent(this, Logout.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed(){
        volverARespuesta();
    }

    public void volverARespuesta(){
        camara.stopPreview();
        camara.release();

        Intent intent = new Intent(this, TareaDetallada.class);
        intent.putExtra("usuario", usuario);
        intent.putExtra("creador", creador);
        intent.putExtra("nombreTarea", nombreTarea);
        intent.putExtra("guardarRespuesta", guardarRespuesta);
        startActivity(intent);
    }
}
