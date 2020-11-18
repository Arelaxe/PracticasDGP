package com.example.valeapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ScaleDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.concurrent.ExecutionException;

public class MisTareas extends AppCompatActivity {

    private String usuario;
    JSONObject jsonTareas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_tareas);

        Bundle bundle = getIntent().getExtras();
        usuario = bundle.getString("usuario");

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.barra_de_tareas);

        //Modicar Barra de Tareas para esta pantalla
        final ImageButton flechaAtras = findViewById(R.id.flechaVolverMenuAnterior);
        flechaAtras.setVisibility(View.VISIBLE);
        flechaAtras.setContentDescription(getResources().getString(R.string.bot_n_para_volver_al_men_principal));
        final TextView textoFlechaAtras = findViewById(R.id.textoVolverAMenuAnterior);
        textoFlechaAtras.setText(getResources().getString(R.string.volver_al_men_principal));
        textoFlechaAtras.setVisibility(View.VISIBLE);

        final ImageButton botonLogout = findViewById(R.id.botonLogout);
        final ImageButton botonAtras = findViewById(R.id.flechaVolverMenuAnterior);

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
            creaListaTareas();
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
        intent.putExtra("usuario", usuario);
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
    private void creaListaTareas() throws JSONException {
        String[] names = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"}; // 20 names
        Button[] buttons = new Button[20];
        for (int i = 0; i < 20; i++) {
            Button button = new Button(this);
            button.setText(names[i]);
            buttons[i] = button;
        }
        LinearLayout layout = (LinearLayout)findViewById(R.id.LayoutTareas);

        for (int i = 0; i < 20; i++) {

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT
            );
            int left = 10;
            int top = 0;
            int right = 10;
            int bottom = 30;
            int height =  75;
            int width =  75;

            params.setMargins(left, top, right, bottom);
            layout.addView(buttons[i]);
            buttons[i].setContentDescription(names[i]);
            buttons[i].setBackgroundColor(getResources().getInteger(R.color.grey));
            buttons[i].setTextColor(getResources().getInteger(R.color.black));
            buttons[i].setTextSize(25);
            buttons[i].setGravity(View.TEXT_ALIGNMENT_TEXT_START);
            buttons[i].setLayoutParams(params);
            byte[] data = Base64.decode(jsonTareas.getString("fotoTarea"), Base64.DEFAULT);
            Bitmap mapaImagen = BitmapFactory.decodeByteArray(data, 0, data.length);
            Drawable imagenTarea = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(mapaImagen, width, height, true));

            buttons[i].setCompoundDrawablesWithIntrinsicBounds(null, null, imagenTarea, null);
        }
    }

    class GetTareas extends AsyncTask<String, String, JSONObject> {
        private final static String URL= "tareas-socio";
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
