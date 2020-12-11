
package com.example.valeapp;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
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

import com.google.android.material.textfield.TextInputEditText;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import static android.Manifest.permission.RECORD_AUDIO;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;

public class GrabarAudio extends AppCompatActivity{
    String usuario;
    String creador;
    String nombreTarea;
    String mote;
    String AudioSavePathInDevice = null;
    MediaRecorder mediaRecorder;
    String nombreAudio = null;
    Boolean tieneAudio = false;
    String tipoRespuesta = null;
    TextView textoEscucharAudio;
    ImageButton botonResponder;
    ImageButton escucharAudiob;
    ImageButton botonAtras;
    ImageButton botonLogout;

    public static final int RequestPermissionCode = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.grabar_audio);

        Bundle bundle = getIntent().getExtras();
        usuario = bundle.getString("usuario");
        creador = bundle.getString("creador");
        nombreTarea = bundle.getString("nombreTarea");
        mote = bundle.getString("mote");
        tipoRespuesta = bundle.getString("tipoRespuesta");

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
        textoFlechaAtras.setContentDescription("VOLVER A LA TAREA TÍTULO");
        botonAtras = findViewById(R.id.flechaVolverMenuAnterior);

        botonLogout = findViewById(R.id.botonLogout);

        nombreAudio = "Music/" + "Respuesta_" + nombreTarea + "_"+ mote + "_"+ usuario +".3gp";

        botonResponder = findViewById(R.id.enviarAudio);
        escucharAudiob = findViewById(R.id.imageButtonEscucharAudio);

        escucharAudiob.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mostrarMultimedia();
            }
        });

        crearBotonGrabarAudio();

        //Boton logout
        botonLogout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    irALogout();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        //Boton Atrás
        botonAtras.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    volverATareaDetallada();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        botonResponder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    responder();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }


    private void crearBotonGrabarAudio(){
        ToggleButton botonGrabar = findViewById(R.id.botonGrabar);
        Drawable dGrabar = getResources().getDrawable(R.drawable.grabar_video_mini);
        Bitmap bitmap = ((BitmapDrawable) dGrabar).getBitmap();
        // Escalar
        Drawable dEscaladoGrabar = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(bitmap, 120, 120, true));

        Drawable dDejarGrabar = getResources().getDrawable(R.drawable.detener);
        Bitmap bitmap2 = ((BitmapDrawable) dDejarGrabar).getBitmap();
        // Escalar
        Drawable dEscaladoDejarGrabar = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(bitmap2, 120, 120, true));

        botonGrabar.setBackgroundDrawable(dEscaladoGrabar);

        botonGrabar.setContentDescription("Grabar");
        botonGrabar.setTextOff(null);
        botonGrabar.setText(null);
        botonGrabar.setTextOn(null);

        //Boton Grabar
        botonGrabar.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // The toggle is enabled
                    grabacionAudio();
                    botonGrabar.setContentDescription("Detener grabación");
                    botonGrabar.setBackgroundDrawable(dEscaladoDejarGrabar);
                    botonAtras.setEnabled(false);
                    botonLogout.setEnabled(false);

                    cambiarBotonesExtra(false);

                } else {
                    // The toggle is disabled
                    pararGrabacionAudio();
                    botonGrabar.setContentDescription("Grabar");
                    botonGrabar.setBackgroundDrawable(dEscaladoGrabar);
                    botonAtras.setEnabled(true);
                    botonLogout.setEnabled(true);

                    cambiarBotonesExtra(true);
                }
            }
        });

        if (comprobarMultimediaRespuesta(nombreAudio)){
            cambiarBotonesExtra(true);
        }
    }


    private void irALogout() throws IOException {
        Intent intent = new Intent(this, Logout.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed(){
        try {
            volverATareaDetallada();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void volverATareaDetallada() throws IOException {
        Intent intent = new Intent(this, TareaDetallada.class);
        intent.putExtra("usuario", usuario);
        intent.putExtra("creador", creador);
        intent.putExtra("nombreTarea", nombreTarea);
        intent.putExtra("mote", mote);
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

    private void requestPermission() {
        ActivityCompat.requestPermissions(this, new String[]{WRITE_EXTERNAL_STORAGE, RECORD_AUDIO}, RequestPermissionCode);
    }

    public void grabarAudio(){
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
    }

    public void mostrarMultimedia(){
        Intent intent = new Intent(this, Multimedia.class);
        intent.putExtra("usuario", usuario);
        intent.putExtra("creador", creador);
        intent.putExtra("nombreTarea", nombreTarea);
        intent.putExtra("nombreMultimedia", nombreAudio);
        intent.putExtra("tipo", "audio");
        intent.putExtra("tareaDetallada", false);
        intent.putExtra("mote", mote);

        startActivity(intent);
    }

    private Boolean comprobarMultimediaRespuesta(String nombreMultimedia){
        File multimedia = new File(Environment.getExternalStorageDirectory() + File.separator + nombreMultimedia);
        return multimedia.exists();
    }

    private void responder() throws IOException {
        boolean puedeResponder = comprobarMultimediaRespuesta(nombreAudio);

        if (puedeResponder){
            enviarRespuesta();
        }
    }

    private void enviarRespuesta() throws IOException {
        Intent intent = new Intent(this, EnviarRespuesta.class);
        intent.putExtra("usuario", usuario);
        intent.putExtra("creador", creador);
        intent.putExtra("nombreTarea", nombreTarea);
        intent.putExtra("nombreArchivo", nombreAudio);
        intent.putExtra("formato", ".3gp");
        startActivity(intent);
    }

    private void cambiarBotonesExtra(Boolean estado){
        if (estado){
            escucharAudiob.setEnabled(true);

            botonResponder.setEnabled(true);
        }
        else{
            escucharAudiob.setEnabled(false);

            botonResponder.setEnabled(false);
        }
    }
}
