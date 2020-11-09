package com.example.valeapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class InicioSesionPass3 extends AppCompatActivity {
    private String passPaso1;
    private String passPaso2;
    private String passPaso3;
    private String usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_pass3);

        Bundle bundle = getIntent().getExtras();

        usuario = bundle.getString("usuario");
        passPaso1 = bundle.getString("pass1");
        passPaso2 = bundle.getString("pass2");

        ImageView perroSeleccionado = findViewById(R.id.perroSeleccionado);
        ImageView gatoSeleccionado = findViewById(R.id.gatoSeleccionado);
        ImageView caballoSeleccionado = findViewById(R.id.caballoSeleccionado);
        ImageView pajaroSeleccionado = findViewById(R.id.pajaroSeleccionado);

        cambiarEstadoSelección(perroSeleccionado, false);
        cambiarEstadoSelección(gatoSeleccionado, false);
        cambiarEstadoSelección(caballoSeleccionado, false);
        cambiarEstadoSelección(pajaroSeleccionado, false);


        final ImageButton perro = findViewById(R.id.Perro);
        final ImageButton gato = findViewById(R.id.Gato);
        final ImageButton caballo = findViewById(R.id.Caballo);
        final ImageButton pajaro = findViewById(R.id.Pajaro);
        final ImageButton botonSiguiente = findViewById(R.id.irPass4);
        final ImageButton botonAnterior = findViewById(R.id.irPass2);

        botonSiguiente.setEnabled(false);
        perro.setEnabled(true);
        gato.setEnabled(true);
        caballo.setEnabled(true);
        pajaro.setEnabled(true);

        perro.setContentDescription(getResources().getString(R.string.imagen_de_un_perro));
        gato.setContentDescription(getResources().getString(R.string.imagen_de_un_gato));
        caballo.setContentDescription(getResources().getString(R.string.imagen_de_un_caballo));
        pajaro.setContentDescription(getResources().getString(R.string.imagen_de_un_pajaro));

        //Boton perro
        perro.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                cambiarEstadoSelección(perroSeleccionado, true);
                perro.setEnabled(false);
                perro.setContentDescription(getResources().getString(R.string.perro_seleccionado));//
                cambiarEstadoSelección(gatoSeleccionado, false);
                gato.setEnabled(true);
                gato.setContentDescription(getResources().getString(R.string.imagen_de_un_gato));
                cambiarEstadoSelección(caballoSeleccionado, false);
                caballo.setEnabled(true);
                caballo.setContentDescription(getResources().getString(R.string.imagen_de_un_caballo));
                cambiarEstadoSelección(pajaroSeleccionado, false);
                pajaro.setEnabled(true);
                pajaro.setContentDescription(getResources().getString(R.string.imagen_de_un_pajaro));

                passPaso3 = "Perro";

                botonSiguiente.setEnabled(true);
            }
        });

        //Boton gato
        gato.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                cambiarEstadoSelección(perroSeleccionado, false);
                perro.setEnabled(true);
                perro.setContentDescription(getResources().getString(R.string.imagen_de_un_perro));
                cambiarEstadoSelección(gatoSeleccionado, true);
                gato.setEnabled(false);
                gato.setContentDescription(getResources().getString(R.string.gato_seleccionado));
                cambiarEstadoSelección(caballoSeleccionado, false);
                caballo.setEnabled(true);
                caballo.setContentDescription(getResources().getString(R.string.imagen_de_un_caballo));
                cambiarEstadoSelección(pajaroSeleccionado, false);
                pajaro.setEnabled(true);
                pajaro.setContentDescription(getResources().getString(R.string.imagen_de_un_pajaro));

                passPaso3 = "Gato";

                botonSiguiente.setEnabled(true);
            }
        });

        //Boton caballo
        caballo.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                cambiarEstadoSelección(perroSeleccionado, false);
                perro.setEnabled(true);
                perro.setContentDescription(getResources().getString(R.string.imagen_de_un_perro));
                cambiarEstadoSelección(gatoSeleccionado, false);
                gato.setEnabled(true);
                gato.setContentDescription(getResources().getString(R.string.imagen_de_un_gato));
                cambiarEstadoSelección(caballoSeleccionado, true);
                caballo.setEnabled(false);
                caballo.setContentDescription(getResources().getString(R.string.caballo_seleccionado));
                cambiarEstadoSelección(pajaroSeleccionado, false);
                pajaro.setEnabled(true);
                pajaro.setContentDescription(getResources().getString(R.string.imagen_de_un_pajaro));

                passPaso3 = "Caballo";

                botonSiguiente.setEnabled(true);
            }
        });

        //Boton pajaro
        pajaro.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                cambiarEstadoSelección(perroSeleccionado, false);
                perro.setEnabled(true);
                perro.setContentDescription(getResources().getString(R.string.imagen_de_un_perro));
                cambiarEstadoSelección(gatoSeleccionado, false);
                gato.setEnabled(true);
                gato.setContentDescription(getResources().getString(R.string.imagen_de_un_gato));
                cambiarEstadoSelección(caballoSeleccionado, false);
                caballo.setEnabled(true);
                caballo.setContentDescription(getResources().getString(R.string.imagen_de_un_caballo));
                cambiarEstadoSelección(pajaroSeleccionado, true);
                pajaro.setEnabled(false);
                pajaro.setContentDescription(getResources().getString(R.string.pajaro_seleccionado));

                passPaso3 = "Pajaro";

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
        Intent intent = new Intent(this, InicioSesionPass4.class);
        intent.putExtra("usuario", usuario);
        intent.putExtra("pass1", passPaso1);
        intent.putExtra("pass2", passPaso2);
        intent.putExtra("pass3", passPaso3);
        startActivity(intent);
    }

    private void  anteriorPantallaPass(){
        Intent intent = new Intent(this, InicioSesionPass2.class);
        intent.putExtra("usuario", usuario);
        intent.putExtra("pass1", passPaso1);
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

    @Override
    public void onBackPressed() {

    }
}
