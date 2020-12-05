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
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.concurrent.ExecutionException;

public class MisFacilitadores extends AppCompatActivity {

    private String usuario;
    JSONObject jsonFacilitadores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_facilitadores);

        Bundle bundle = getIntent().getExtras();
        usuario = bundle.getString("usuario");

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.barra_de_tareas);

        //Modicar Barra de Tareas para esta pantalla
        final ImageButton botonAtras = findViewById(R.id.flechaVolverMenuAnterior);
        botonAtras.setVisibility(View.VISIBLE);
        botonAtras.setContentDescription(getResources().getString(R.string.bot_n_para_volver_al_men_principal));
        final TextView textoFlechaAtras = findViewById(R.id.textoVolverAMenuAnterior);
        textoFlechaAtras.setText(getResources().getString(R.string.volver_al_men_principal));
        textoFlechaAtras.setVisibility(View.VISIBLE);
        textoFlechaAtras.setContentDescription("VOLVER AL MENÚ PRINCIPAL TÍTULO");
        final ImageButton botonLogout = findViewById(R.id.botonLogout);

        //Obtener de la base de datos las tareas de socio
        ////////////////////////////////////////////////////
        try {
            new GetTareas().execute().get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            creaListaFacilitadores();
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
                volverAMenuPrincipal();
            }
        });
    }

    private void irALogout(){
        Intent intent = new Intent(this, Logout.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        volverAMenuPrincipal();
    }

    public void volverAMenuPrincipal(){
        Intent intent = new Intent(this, MenuPrincipal.class);
        intent.putExtra("usuario", usuario);
        startActivity(intent);
    }

    @SuppressLint("ResourceType")
    private void creaListaFacilitadores() throws JSONException {
        LinearLayout layout = (LinearLayout)findViewById(R.id.LayoutTareas);
        JSONArray arrayFacilitadores = jsonFacilitadores.getJSONArray("arrayRespuesta");
        for (int i = 0; i < arrayFacilitadores.length(); i++) {

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
            Button facilitador = new Button(this);
            layout.addView(facilitador);
            facilitador.setText(((JSONObject) arrayFacilitadores.get(i)).getString("mote").toUpperCase());
            facilitador.setContentDescription(((JSONObject) arrayFacilitadores.get(i)).getString("mote"));
            facilitador.setBackgroundColor(getResources().getInteger(R.color.grey));
            facilitador.setTextColor(getResources().getInteger(R.color.black));
            facilitador.setTextSize(32);
            facilitador.setCompoundDrawablePadding(50);
            facilitador.setPadding(0, 0, 0, 0);
            facilitador.setLayoutParams(params);
            facilitador.setGravity(Gravity.CENTER_VERTICAL);
            byte[] data = Base64.decode(((JSONObject) arrayFacilitadores.get(i)).getString("fotoFacilitador"), Base64.DEFAULT);
            Bitmap mapaImagen = BitmapFactory.decodeByteArray(data, 0, data.length);
            Drawable imagenTarea = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(mapaImagen, width, height, true));
            final int facilitador_i = i;

            /*
            Drawable dEscalado = null;

            int alturaImagen = 110;
            int anchuraImagen = 65;

            //nuevo mensaje ///// Por ahora no
            if (((JSONObject) arrayTareas.get(i)).getBoolean("nuevoMensaje")){
                tarea.setContentDescription(((JSONObject) arrayTareas.get(i)).getString("nombreTarea") + ". Tienes un nuevo mensaje en el chat de la tarea.");
                Drawable dr = getResources().getDrawable(R.drawable.chat);
                Bitmap bitmap = ((BitmapDrawable) dr).getBitmap();
                // Escalar
                dEscalado= new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(bitmap, anchuraImagen, alturaImagen, true));
            }*/
            facilitador.setCompoundDrawablesWithIntrinsicBounds(imagenTarea, null, null, null);
            //Boton Atrás
            facilitador.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    try {
                        infoFacilitador(((JSONObject) arrayFacilitadores.get(facilitador_i)).getString("username"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    public void infoFacilitador(String facilitador){
        System.out.println(facilitador);
    }

    class GetTareas extends AsyncTask<String, String, JSONObject> {
        private final static String URL= "facilitadores-socio";
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

                jsonFacilitadores = jsonParser.makeHttpRequest(URL, "GET", params);

                if (jsonFacilitadores != null) {
                    Log.d("JSON result:   ", jsonFacilitadores.toString());

                    return jsonFacilitadores;
                }
            } catch (Exception e) {

                e.printStackTrace();
            }
            return null;
        }
    }
}
