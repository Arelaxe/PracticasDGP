package com.example.valeapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.concurrent.ExecutionException;

public class TareaDetallada extends AppCompatActivity{
    private String usuario;
    String creador;
    String nombreTarea;
    JSONObject jsonTareas;
    boolean guardarRespuesta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.tarea_detallada);

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
        final ImageButton botonAtras = findViewById(R.id.flechaVolverMenuAnterior);


        if (guardarRespuesta){

        }
        else{

        }

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

        //Informarmión del facilitador
        try {
            setInformacionProfesional();
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

    @SuppressLint("ResourceType")
    private void setInformacionProfesional() throws JSONException {
        LinearLayout layout = (LinearLayout)findViewById(R.id.LayoutFacilitador);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
        );
        int left = 20;
        int top = 20;
        int right = 20;
        int bottom = 25;
        int height =  240;
        int width =  240;

        params.setMargins(left, top, right, bottom);

        //Foto del facilitador
        byte[] data = Base64.decode(jsonTareas.getString("fotoFacilitador"), Base64.DEFAULT);
        Bitmap mapaImagen = BitmapFactory.decodeByteArray(data, 0, data.length);
        Drawable fotoFacilitador = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(mapaImagen, width, height, true));
        ImageView fotoFacilitadorImageView = new ImageView(this);
        fotoFacilitadorImageView.setImageDrawable(fotoFacilitador);
        layout.addView(fotoFacilitadorImageView);
        fotoFacilitadorImageView.setPadding(20, 0, 50, 0);

        //Mote del facilitador
        TextView moteFacilitador = new TextView(this);
        layout.addView(moteFacilitador);
        moteFacilitador.setText(jsonTareas.getString("mote").toUpperCase());
        moteFacilitador.setContentDescription(jsonTareas.getString("mote").toUpperCase());
        moteFacilitador.setTextColor(getResources().getInteger(R.color.black));
        moteFacilitador.setTextSize(30);

        //Botón del chat con el facilitador sobre la tarea
        Drawable dr = getResources().getDrawable(R.drawable.chat_cuadrado);
        Bitmap bitmap = ((BitmapDrawable) dr).getBitmap();
        // Escalar
        Drawable dEscalado = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(bitmap, 50, 50, true));
        ImageButton botonChat = new ImageButton(this);
        botonChat.setImageDrawable(dEscalado);
        botonChat.setBackgroundColor(getResources().getInteger(R.color.white));
        botonChat.setContentDescription(getResources().getString(R.string.ir_a_chat));
        botonChat.setTranslationX(85);
        botonChat.setPadding(0, 0, 0, 0);
        layout.addView(botonChat);
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
