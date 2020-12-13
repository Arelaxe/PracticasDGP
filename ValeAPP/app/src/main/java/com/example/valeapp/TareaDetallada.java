package com.example.valeapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Base64;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;

public class TareaDetallada extends AppCompatActivity{
    private String usuario;
    String creador;
    String nombreTarea;
    String mote;
    JSONObject jsonTareas;
    String nombreVideo = "";
    String nombreAudio = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.tarea_detallada);
        Bundle bundle = getIntent().getExtras();
        usuario = bundle.getString("usuario");
        creador = bundle.getString("creador");
        nombreTarea = bundle.getString("nombreTarea");

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.barra_de_tareas);

        //Modicar Barra de Tareas para esta pantalla
        final ImageButton botonAtras = findViewById(R.id.flechaVolverMenuAnterior);
        botonAtras.setVisibility(View.VISIBLE);
        botonAtras.setContentDescription(getResources().getString(R.string.mis_tareas2));
        final TextView textoFlechaAtras = findViewById(R.id.textoVolverAMenuAnterior);
        textoFlechaAtras.setText(getResources().getString(R.string.mis_tareas3));
        textoFlechaAtras.setVisibility(View.VISIBLE);
        textoFlechaAtras.setContentDescription("VOLVER A MIS TAREAS TÍTULO");

        final ImageButton botonLogout = findViewById(R.id.botonLogout);
        final ImageButton botonSiguiente = findViewById(R.id.responderPregunta);

        //Obtener de la base de datos las tareas de socio
        ////////////////////////////////////////////////////
        try {
            new GetTareaDetallada().execute().get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //Modicar Pantalla para mostrar información de la tarea
        final TextView nombreTareaDetallada = findViewById(R.id.textoTareaDetallada);
        nombreTareaDetallada.setText(nombreTarea.toUpperCase());
        nombreTareaDetallada.setContentDescription(nombreTarea);

        //Imagen de la tarea
        try {
            setImagenTarea();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        //Información de la tarea
        try {
            setInfoTarea();
        } catch (JSONException e) {
            e.printStackTrace();
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
                volverAMisTareas();
            }
        });

        //Boton Siguiente
        botonSiguiente.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    if (jsonTareas.getBoolean("permiteVideo")){
                        irARespuestaVideo();
                    }
                    else if (jsonTareas.getBoolean("permiteAudio")){
                        irARespuestaAudio();
                    }
                    else if (jsonTareas.getBoolean("permiteTexto")){
                        irARespuestaTexto();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void irALogout(){
        Intent intent = new Intent(this, Logout.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        volverAMisTareas();
    }

    public void volverAMisTareas(){
        Intent intent = new Intent(this, MisTareas.class);
        intent.putExtra("usuario", usuario);
        startActivity(intent);
    }

    public void irARespuestaTexto() throws JSONException {
        Intent intent = new Intent(this, RespuestaTareaTexto.class);
        intent.putExtra("usuario", usuario);
        intent.putExtra("creador", creador);
        intent.putExtra("nombreTarea", nombreTarea);
        intent.putExtra("mote", mote);
        startActivity(intent);
    }

    public void irARespuestaVideo() throws JSONException {
        Intent intent = new Intent(this, GrabarVideo.class);
        intent.putExtra("usuario", usuario);
        intent.putExtra("creador", creador);
        intent.putExtra("nombreTarea", nombreTarea);
        intent.putExtra("mote", mote);
        startActivity(intent);
    }

    public void irARespuestaAudio() throws JSONException {
        Intent intent = new Intent(this, GrabarAudio.class);
        intent.putExtra("usuario", usuario);
        intent.putExtra("creador", creador);
        intent.putExtra("nombreTarea", nombreTarea);
        intent.putExtra("mote", mote);
        startActivity(intent);
    }

    @SuppressLint("ResourceType")
    private void setImagenTarea() throws JSONException {
        LinearLayout layout = (LinearLayout)findViewById(R.id.layoutFotoYFacilitador);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
        );

        int height =  300;
        int width =  300;

        //Foto de la tarea
        if (!jsonTareas.getString("fotoTarea").equals("")) {
            byte[] data = Base64.decode(jsonTareas.getString("fotoTarea"), Base64.DEFAULT);
            Bitmap mapaImagen = BitmapFactory.decodeByteArray(data, 0, data.length);
            Drawable fotoTarea = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(mapaImagen, 500, 500, true));
            ImageView fotoTareaImageView = new ImageView(this);
            fotoTareaImageView.setImageDrawable(fotoTarea);
            layout.addView(fotoTareaImageView);
            fotoTareaImageView.setPadding(20, 0, 50, 0);
        }

        LinearLayout layoutFacilitador = new LinearLayout(this);

        params.setMargins(0, 20, 0, 20);
        layoutFacilitador.setLayoutParams(params);
        layoutFacilitador.setPadding(0, 0, 0, 0);

        //Foto del facilitador
        byte[] data = Base64.decode(jsonTareas.getString("fotoFacilitador"), Base64.DEFAULT);
        Bitmap mapaImagen = BitmapFactory.decodeByteArray(data, 0, data.length);
        Drawable fotoFacilitador = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(mapaImagen, width, height, true));
        ImageView fotoFacilitadorImageView = new ImageView(this);
        fotoFacilitadorImageView.setImageDrawable(fotoFacilitador);
        fotoFacilitadorImageView.setPadding(0, 0, 30, 0);
        layoutFacilitador.addView(fotoFacilitadorImageView);

        //Mote del facilitador
        TextView moteFacilitador = new TextView(this);
        layoutFacilitador.addView(moteFacilitador);
        moteFacilitador.setText(jsonTareas.getString("mote").toUpperCase());
        mote = jsonTareas.getString("mote");
        moteFacilitador.setContentDescription("Facilitador encargado: " + jsonTareas.getString("mote").toUpperCase());
        moteFacilitador.setTextColor(getResources().getInteger(R.color.black));
        moteFacilitador.setTextSize(30);
        
        layoutFacilitador.setGravity(Gravity.CENTER_VERTICAL);

        layout.addView(layoutFacilitador);

    }

    @SuppressLint("ResourceType")
    private void setInfoTarea() throws JSONException {
        LinearLayout layout = (LinearLayout) findViewById(R.id.layoutInfoTarea);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
        );
        layout.setBackgroundColor(getResources().getInteger(R.color.white));
        int left = 20;
        int top = 20;
        int right = 20;
        int bottom = 25;
        int height =  300;
        int width =  300;

        params.setMargins(left, top, right, bottom);

        LinearLayout layoutBotonesTarea = new LinearLayout(this);
        layoutBotonesTarea.setOrientation(0); //Horizontal
        layoutBotonesTarea.setGravity(Gravity.CENTER);
        layout.addView(layoutBotonesTarea);
        //Descipción de la tarea
        if (!jsonTareas.getString("descripcion").equals("")) {
            ImageButton texto = new ImageButton(this);
            texto.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    try {
                        mostrarTexto();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
            Drawable drTexto = getResources().getDrawable(R.drawable.texto);
            Bitmap bitmap = ((BitmapDrawable) drTexto).getBitmap();
            // Escalar
            Drawable dEscaladoAudio = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(bitmap, 120, 120, true));
            texto.setImageDrawable(dEscaladoAudio);
            texto.setContentDescription("Ver descripción");
            layoutBotonesTarea.addView(texto);
        }

        //Audio de la tarea
        if(jsonTareas.getBoolean("tieneAudio")){
            nombreAudio = "Music/" + nombreTarea + "_" + jsonTareas.getString("mote") + ".mp3";
            ImageButton audio = new ImageButton(this);
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
            layoutBotonesTarea.addView(audio);
        }

        //Video de la tarea
        if(jsonTareas.getBoolean("tieneVideo")){
            //if (!jsonTareas.getString("videoTarea").equals("")) {
            nombreVideo = "Movies/" + nombreTarea + "_" + jsonTareas.getString("mote") + ".mp4";
            //descargarVideoTarea();
            ImageButton video = new ImageButton(this);
            video.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    mostrarMultimedia(nombreVideo, "video");
                }
            });
            Drawable drVideo = getResources().getDrawable(R.drawable.video);
            Bitmap bitmap = ((BitmapDrawable) drVideo).getBitmap();
            // Escalar
            Drawable dEscaladoVideo = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(bitmap, 120, 120, true));
            video.setImageDrawable(dEscaladoVideo);
            video.setContentDescription("Ver vídeo");
            layoutBotonesTarea.addView(video);
        }


        //Botón del chat con el facilitador sobre la tarea
        Drawable dr = getResources().getDrawable(R.drawable.chat_cuadrado);
        Bitmap bitmap = ((BitmapDrawable) dr).getBitmap();
        // Escalar
        Drawable dEscalado = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(bitmap, 100, 100, true));
        ImageButton botonChat = new ImageButton(this);
        botonChat.setImageDrawable(dEscalado);
        //botonChat.setBackgroundColor(getResources().getInteger(R.color.white));
        botonChat.setContentDescription(getResources().getString(R.string.ir_a_chat));

        botonChat.setPadding(0, 0, 0, 0);
        layout.addView(botonChat);

        botonChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    iniciarChat();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void mostrarTexto() throws JSONException {
        Intent intent = new Intent(this, Texto.class);
        intent.putExtra("usuario", usuario);
        intent.putExtra("creador", creador);
        intent.putExtra("nombreTarea", nombreTarea);
        intent.putExtra("texto", jsonTareas.getString("descripcion"));

        startActivity(intent);
    }

    public void mostrarMultimedia(String nombreMultimedia, String tipo){
        Intent intent = new Intent(this, Multimedia.class);
        intent.putExtra("usuario", usuario);
        intent.putExtra("creador", creador);
        intent.putExtra("nombreTarea", nombreTarea);
        intent.putExtra("nombreMultimedia", nombreMultimedia);
        intent.putExtra("tipo", tipo);
        intent.putExtra("tareaDetallada", true);

        startActivity(intent);
    }

    class GetTareaDetallada extends AsyncTask<String, String, JSONObject> {
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

                jsonTareas = jsonParser.makeHttpRequest(URL, "GET", params);

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
    private void iniciarChat() throws JSONException {
        Intent intent = new Intent(this, Chat.class);
        intent.putExtra("usuario", usuario);
        intent.putExtra("creador", creador);
        intent.putExtra("tipo", "tarea");
        intent.putExtra("nombreTarea", nombreTarea);
        intent.putExtra("idChat", jsonTareas.getString("idChat"));
        intent.putExtra("nombreChat", jsonTareas.getString("nombreChat"));
        startActivity(intent);
    }
}
