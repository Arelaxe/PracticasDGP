package com.example.valeapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONObject;

import java.util.HashMap;

public class InicioSesionPass extends AppCompatActivity {
    private EditText usuarioT;
    private String usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_pass);

        //Boton Inicio
        final Button siguienteNombreUsuario = findViewById(R.id.siguienteLoginNombreUsuario);

        usuarioT = (EditText) findViewById(R.id.usuario);

        siguienteNombreUsuario.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                usuario = usuarioT.getText().toString();
                new IniciaSesion().execute();
            }
        });
    }
    class IniciaSesion extends AsyncTask<String, String, JSONObject> {
        private final static String URL = "existe-usuario";
        private JSONParser jsonParser = new JSONParser();
        private Boolean errorInicio = false;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        protected JSONObject doInBackground(String... args) {

            try {
                HashMap<String, String> params = new HashMap<>();

                params.put("usuario", usuario);

                Log.d("request", "starting");

                JSONObject json = jsonParser.makeHttpRequest(URL, "POST", params, "");

                if (json != null) {
                    Log.d("JSON result:   ", json.toString());

                    if (json.getInt("exito") == 0)
                        errorInicio = true;
                    else if (json.getInt("exito") == 1) {
                        errorInicio = false;
                    }

                    return json;
                }
            } catch (Exception e) {

                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(JSONObject result){
            super.onPostExecute(result);

            if(errorInicio){
                //Ir a pantalla de error
                pantallaErrorNombreUsuario();
            }
            else {
                //IR A PANTAllA DE EXITO
            }
        }
    }
    private void pantallaErrorNombreUsuario() {
        Intent intent = new Intent(this, ErrorNombreUsuario.class);
        startActivity(intent);
    }

    private void pantallaExitoNombreUsuario() {
        //Intent intent = new Intent(this, InicioSesionPass.class);
        //startActivity(intent);
    }
}
