
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
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Base64;
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

import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;


public class RespuestaTareaTexto extends AppCompatActivity{
    String usuario;
    String creador;
    String nombreTarea;
    String mote;
    Boolean tieneTexto = false;
    String tipoRespuesta = null;
    String textoRespuesta;
    String nombreTexto;
    TextInputEditText textoTarea;
    ImageButton botonResponder;
    Boolean textoVacio;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.respuesta_tarea);

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
        final ImageButton botonAtras = findViewById(R.id.flechaVolverMenuAnterior);

        final ImageButton botonLogout = findViewById(R.id.botonLogout);

        botonResponder = findViewById(R.id.irEnviarRespuesta);

        nombreTexto = "Download/" + nombreTarea + "_"+ mote + "_"+ usuario +".txt";
        crearRespuestaTexto();
        tieneTexto = comprobarMultimediaRespuesta(nombreTexto);
        if (tieneTexto){
            try {
                inicializaTexto();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else{
            botonResponder.setEnabled(false);
            textoVacio = true;

        }

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

    private void crearRespuestaTexto(){
        LinearLayout layout = (LinearLayout)findViewById(R.id.layoutRespuesta);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
        );

        TextView textoInicial = new TextView(this);
        textoInicial.setText("ESCRIBE TU RESPUESTA");
        textoInicial.setTextSize(34);
        textoInicial.setContentDescription("ESCRIBE TU RESPUESTA");
        textoInicial.setPadding(0, 0, 0, 50);
        textoInicial.setTextColor(getResources().getColor(R.color.black));
        textoInicial.setGravity(Gravity.CENTER);
        textoTarea = new TextInputEditText(this);
        textoTarea.setBackgroundColor(getResources().getColor(R.color.grey));
        textoTarea.setTextSize(28);

        textoTarea.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                textoRespuesta = textoTarea.getText().toString();
                if (textoRespuesta.equals("")){
                    botonResponder.setEnabled(false);
                    textoVacio = true;
                }
                else if(textoVacio){
                    botonResponder.setEnabled(true);
                    textoVacio = false;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        layout.addView(textoInicial);
        layout.addView(textoTarea);
    }

    private void inicializaTexto() throws IOException {
        textoVacio = false;
        File texto = new File(Environment.getExternalStorageDirectory() + File.separator + nombreTexto);
        int longitud = (int) texto.length();
        byte[] bytes = new byte[longitud];
        FileInputStream in = new FileInputStream(texto);
        try {
            in.read(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        in.close();
        textoTarea.setText(new String (bytes));
    }

    private void almacenarTexto() throws IOException {
        File texto = new File(Environment.getExternalStorageDirectory() + File.separator + nombreTexto);
        texto.createNewFile();
        FileOutputStream fos = new FileOutputStream(texto);

        if (textoRespuesta == null){
            textoRespuesta = "";
        }
        byte[] data = textoRespuesta.getBytes();
        fos.write(data);
        fos.close();
    }

    private void irALogout() throws IOException {
        almacenarTexto();

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
        almacenarTexto();


        Intent intent = new Intent(this, TareaDetallada.class);
        intent.putExtra("usuario", usuario);
        intent.putExtra("creador", creador);
        intent.putExtra("nombreTarea", nombreTarea);
        intent.putExtra("mote", mote);
        startActivity(intent);
    }


    private Boolean comprobarMultimediaRespuesta(String nombreMultimedia){
        File multimedia = new File(Environment.getExternalStorageDirectory() + File.separator + nombreMultimedia);
        return multimedia.exists();
    }

    private void responder() throws IOException {
        if (!textoRespuesta.equals("")){
            almacenarTexto();
            boolean puedeResponder = comprobarMultimediaRespuesta(nombreTexto);
            
            if (puedeResponder){
                enviarRespuesta();
            }
        }

    }

    private void enviarRespuesta() throws IOException {
        Intent intent = new Intent(this, EnviarRespuesta.class);
        intent.putExtra("usuario", usuario);
        intent.putExtra("creador", creador);
        intent.putExtra("nombreTarea", nombreTarea);
        intent.putExtra("nombreArchivo", nombreTexto);
        intent.putExtra("formato", ".txt");

        startActivity(intent);
    }

}
