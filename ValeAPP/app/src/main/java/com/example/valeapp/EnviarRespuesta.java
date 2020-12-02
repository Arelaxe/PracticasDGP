package com.example.valeapp;

import android.content.Intent;
import android.media.MediaRecorder;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.util.Base64;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;

public class EnviarRespuesta extends AppCompatActivity{
    JSONObject jsonRespuesta;
    String usuario;
    String creador;
    String nombreTarea;
    String nombreArchivo;
    String formato;
    String respuesta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.enviar_respuesta);

        Bundle bundle = getIntent().getExtras();
        usuario = bundle.getString("usuario");
        creador = bundle.getString("creador");
        nombreTarea = bundle.getString("nombreTarea");
        nombreArchivo = bundle.getString("nombreArchivo");
        formato = bundle.getString("formato");

        try {
            obtenerRespuesta();
        } catch (IOException e) {
            e.printStackTrace();
        }
        enviarRespuesta();
        Handler handler = new Handler();
        handler.postDelayed(task, 3000);
    }

    private Runnable task = new Runnable() {
        public void run() {
            volverATarea();
        }
    };

    private void obtenerRespuesta() throws IOException {
        File file = new File(Environment.getExternalStorageDirectory() + File.separator + nombreArchivo);
        int longitud = (int) file.length();
        byte[] bytes = new byte[longitud];
        FileInputStream in = new FileInputStream(file);
        try {
            in.read(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        in.close();

        if (formato.equals(".txt")){
            respuesta = new String (bytes);
        }
        else if (formato.equals(".3gp") || formato.equals(".mp4")){
            respuesta = Base64.encodeToString(bytes, Base64.DEFAULT);
        }
    }

    private void enviarRespuesta(){
        try {
            new EnviarRespuestaTarea().execute().get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void volverATarea(){
        Intent intent = new Intent(this, TareaDetallada.class);
        intent.putExtra("usuario", usuario);
        intent.putExtra("creador", creador);
        intent.putExtra("nombreTarea", nombreTarea);

        startActivity(intent);
    }

    class EnviarRespuestaTarea extends AsyncTask<String, String, JSONObject> {
        private final static String URL= "enviar-respuesta-tarea";
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
                params.put("formatoEntrega", formato);
                params.put("filedata", respuesta);
System.out.println(respuesta);
                Log.d("request", "starting");

                jsonRespuesta = jsonParser.makeHttpRequest(URL, "POST", params);

                if (jsonRespuesta != null) {
                    Log.d("JSON result:   ", jsonRespuesta.toString());

                    return jsonRespuesta;
                }
            } catch (Exception e) {

                e.printStackTrace();
            }
            return null;
        }
    }

    @Override
    public void onBackPressed() {
    }
}
