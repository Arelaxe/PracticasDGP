package com.example.valeapp;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;

public class InicioSesionPass5 extends AppCompatActivity {
    private String passPaso1;
    private String passPaso2;
    private String passPaso3;
    private String passPaso4;
    private String passPaso5;
    private String passCodificada = "";
    private String usuario;
    private Boolean nombreDeUsuarioIncorrecto = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_pass5);

        Bundle bundle = getIntent().getExtras();

        usuario = bundle.getString("usuario");
        passPaso1 = bundle.getString("pass1");
        passPaso2 = bundle.getString("pass2");
        passPaso3 = bundle.getString("pass3");
        passPaso4 = bundle.getString("pass4");

        ImageView pelotaSeleccionada = findViewById(R.id.pelotaSeleccionada);
        ImageView yoyoSeleccionado = findViewById(R.id.yoyoSeleccionado);
        ImageView munecoSeleccionado = findViewById(R.id.munecoSeleccionado);
        ImageView bloquesSeleccionados = findViewById(R.id.bloquesSeleccionados);

        cambiarEstadoSelección(pelotaSeleccionada, false);
        cambiarEstadoSelección(yoyoSeleccionado, false);
        cambiarEstadoSelección(munecoSeleccionado, false);
        cambiarEstadoSelección(bloquesSeleccionados, false);


        final ImageButton pelota = findViewById(R.id.Pelota);
        final ImageButton yoyo = findViewById(R.id.Yoyo);
        final ImageButton muneco= findViewById(R.id.Muneco);
        final ImageButton bloques = findViewById(R.id.Bloques);
        final ImageButton botonSiguiente = findViewById(R.id.irPass5);
        final ImageButton botonAnterior = findViewById(R.id.irPass3);

        botonSiguiente.setEnabled(false);
        pelota.setEnabled(true);
        yoyo.setEnabled(true);
        muneco.setEnabled(true);
        bloques.setEnabled(true);

        pelota.setContentDescription(getResources().getString(R.string.imagen_de_una_pelota));
        yoyo.setContentDescription(getResources().getString(R.string.imagen_de_un_yoy));
        muneco.setContentDescription(getResources().getString(R.string.imagen_de_un_mu_eco));
        bloques.setContentDescription(getResources().getString(R.string.imagen_de_unos_bloques));

        //Boton pelota
        pelota.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                cambiarEstadoSelección(pelotaSeleccionada, true);
                pelota.setEnabled(false);
                pelota.setContentDescription(getResources().getString(R.string.pelota_seleccionada));//
                cambiarEstadoSelección(yoyoSeleccionado, false);
                yoyo.setEnabled(true);
                yoyo.setContentDescription(getResources().getString(R.string.imagen_de_un_yoy));
                cambiarEstadoSelección(munecoSeleccionado, false);
                muneco.setEnabled(true);
                muneco.setContentDescription(getResources().getString(R.string.imagen_de_un_mu_eco));
                cambiarEstadoSelección(bloquesSeleccionados, false);
                bloques.setEnabled(true);
                bloques.setContentDescription(getResources().getString(R.string.imagen_de_unos_bloques));

                passPaso5 = "Pelota";

                botonSiguiente.setEnabled(true);
            }
        });

        //Boton Yoyo
        yoyo.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                cambiarEstadoSelección(pelotaSeleccionada, false);
                pelota.setEnabled(true);
                pelota.setContentDescription(getResources().getString(R.string.imagen_de_una_pelota));
                cambiarEstadoSelección(yoyoSeleccionado, true);
                yoyo.setEnabled(false);
                yoyo.setContentDescription(getResources().getString(R.string.yoy_seleccionado));
                cambiarEstadoSelección(munecoSeleccionado, false);
                muneco.setEnabled(true);
                muneco.setContentDescription(getResources().getString(R.string.imagen_de_un_mu_eco));
                cambiarEstadoSelección(bloquesSeleccionados, false);
                bloques.setEnabled(true);
                bloques.setContentDescription(getResources().getString(R.string.imagen_de_unos_bloques));

                passPaso5 = "Yoyo";

                botonSiguiente.setEnabled(true);
            }
        });

        //Boton muneco
        muneco.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                cambiarEstadoSelección(pelotaSeleccionada, false);
                pelota.setEnabled(true);
                pelota.setContentDescription(getResources().getString(R.string.imagen_de_una_pelota));
                cambiarEstadoSelección(yoyoSeleccionado, false);
                yoyo.setEnabled(true);
                yoyo.setContentDescription(getResources().getString(R.string.imagen_de_un_yoy));
                cambiarEstadoSelección(munecoSeleccionado, true);
                muneco.setEnabled(false);
                muneco.setContentDescription(getResources().getString(R.string.mu_eco_seleccionado));
                cambiarEstadoSelección(bloquesSeleccionados, false);
                bloques.setEnabled(true);
                bloques.setContentDescription(getResources().getString(R.string.imagen_de_unos_bloques));

                passPaso5 = "Muneco";

                botonSiguiente.setEnabled(true);
            }
        });

        //Boton bloques
        bloques.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                cambiarEstadoSelección(pelotaSeleccionada, false);
                pelota.setEnabled(true);
                pelota.setContentDescription(getResources().getString(R.string.imagen_de_una_pelota));
                cambiarEstadoSelección(yoyoSeleccionado, false);
                yoyo.setEnabled(true);
                yoyo.setContentDescription(getResources().getString(R.string.imagen_de_un_yoy));
                cambiarEstadoSelección(munecoSeleccionado, false);
                muneco.setEnabled(true);
                muneco.setContentDescription(getResources().getString(R.string.imagen_de_un_mu_eco));
                cambiarEstadoSelección(bloquesSeleccionados, true);
                bloques.setEnabled(false);
                bloques.setContentDescription(getResources().getString(R.string.bloques_seleccionados));

                passPaso5 = "Bloques";

                botonSiguiente.setEnabled(true);
            }
        });

        botonSiguiente.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                siguientePantallaPass();
            }
        });

        botonAnterior.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                anteriorPantallaPass();
            }
        });
    }

    private void siguientePantallaPass() {
        codificarPassword();
        //Comprobar contraseña con servidor
        try {
            new IniciaSesionPass().execute().get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private void  anteriorPantallaPass(){
        Intent intent = new Intent(this, InicioSesionPass4.class);
        intent.putExtra("usuario", usuario);
        intent.putExtra("pass1", passPaso1);
        intent.putExtra("pass2", passPaso2);
        intent.putExtra("pass3", passPaso3);
        startActivity(intent);
    }

    private void cambiarEstadoSelección(ImageView imagen, boolean estado){
        if (estado){
            imagen.setVisibility(View.VISIBLE);
        }
        else{
            imagen.setVisibility(View.INVISIBLE);
        }
    }

    class IniciaSesionPass extends AsyncTask<String, String, JSONObject> {
        private final static String URL = "login-socio";
        private JSONParser jsonParser = new JSONParser();
        private Boolean errorInicio = true;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        protected JSONObject doInBackground(String... args) {

            try {
                HashMap<String, String> params = new HashMap<>();

                params.put("username", usuario);
                params.put("passwd", passCodificada);
                Log.d("request", "starting");

                JSONObject json = jsonParser.makeHttpRequest(URL, "POST", params, "");

                if (json != null) {
                    Log.d("JSON result:   ", json.toString());

                    if (json.getInt("exito") == 0)
                        errorInicio = true;
                    else if (json.getInt("exito") == 1) {
                        errorInicio = false;
                    }
                    else if (json.getInt("exito") == 2){
                        errorInicio = true;
                        nombreDeUsuarioIncorrecto = true;
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

            if(!errorInicio){
                //Pasar datos necesarios (usuario)
                //Ir a pantalla de éxito
                pantallaExitoPassword();
            }
            else {
                //Ir a pantalla de error
                if (nombreDeUsuarioIncorrecto){
                    pantallaErrorUsuario();
                }
                else {
                    pantallaErrorPassword();
                }
            }
        }
    }

    private void pantallaExitoPassword(){
        Intent intent = new Intent(this, ExitoPass.class);
        startActivity(intent);
    }

    private void pantallaErrorPassword(){
        Intent intent = new Intent(this, ErrorPass.class);
        intent.putExtra("usuario", usuario);
        startActivity(intent);
    }

    private void pantallaErrorUsuario(){
        Intent intent = new Intent(this, ErrorNombreUsuario.class);
        startActivity(intent);
    }

    private void codificarPassword(){
        String password = passPaso1+passPaso2+passPaso3+passPaso4+passPaso5;
        System.out.println(password);
        passCodificada = md5(password);
    }

    public static String md5(final String s) {
        final String MD5 = "MD5";
        try {
            // Create MD5 Hash
            MessageDigest digest = java.security.MessageDigest
                    .getInstance(MD5);
            digest.update(s.getBytes());
            byte messageDigest[] = digest.digest();

            // Create Hex String
            StringBuilder hexString = new StringBuilder();
            for (byte aMessageDigest : messageDigest) {
                String h = Integer.toHexString(0xFF & aMessageDigest);
                while (h.length() < 2)
                    h = "0" + h;
                hexString.append(h);
            }
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }
    /*
    public String md5(String input) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(input.getBytes());
        byte[] digest = md.digest();
        return DatatypeConverter.printHexBinary(digest).toUpperCase();
    }
     */

    @Override
    public void onBackPressed() {

    }
}
