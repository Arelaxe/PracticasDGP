package com.example.valeapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class InicioSesionPass2 extends AppCompatActivity {
    private String passPaso1;
    private String passPaso2;
    private String usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_pass2);

        Bundle bundle = getIntent().getExtras();

        usuario = bundle.getString("usuario");
        passPaso1 = bundle.getString("pass1");

        ImageView fresaSeleccionado = findViewById(R.id.fresaSeleccionado);
        ImageView platanoSeleccionado = findViewById(R.id.platanoSeleccionado);
        ImageView naranjaSeleccionado = findViewById(R.id.naranjaSeleccionado);
        ImageView manzanaSeleccionado = findViewById(R.id.manzanaSeleccionado);

        cambiarEstadoSelección(fresaSeleccionado, false);
        cambiarEstadoSelección(platanoSeleccionado, false);
        cambiarEstadoSelección(naranjaSeleccionado, false);
        cambiarEstadoSelección(manzanaSeleccionado, false);


        final ImageButton fresa = findViewById(R.id.Fresa);
        final ImageButton platano = findViewById(R.id.Platano);
        final ImageButton naranja = findViewById(R.id.Naranja);
        final ImageButton manzana = findViewById(R.id.Manzana);
        final ImageButton botonSiguiente = findViewById(R.id.irPass3);
        final ImageButton botonAnterior = findViewById(R.id.irPass1);

        botonSiguiente.setEnabled(false);
        fresa.setEnabled(true);
        platano.setEnabled(true);
        naranja.setEnabled(true);
        manzana.setEnabled(true);

        fresa.setContentDescription(getResources().getString(R.string.imagen_de_una_fresa));
        platano.setContentDescription(getResources().getString(R.string.imagen_de_un_platano));
        naranja.setContentDescription(getResources().getString(R.string.imagen_de_una_naranja));
        manzana.setContentDescription(getResources().getString(R.string.imagen_de_una_manzana));

        //Boton fresa
        fresa.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                cambiarEstadoSelección(fresaSeleccionado, true);
                fresa.setEnabled(false);
                fresa.setContentDescription(getResources().getString(R.string.fresa_seleccionada));//
                cambiarEstadoSelección(platanoSeleccionado, false);
                platano.setEnabled(true);
                platano.setContentDescription(getResources().getString(R.string.imagen_de_un_platano));
                cambiarEstadoSelección(naranjaSeleccionado, false);
                naranja.setEnabled(true);
                naranja.setContentDescription(getResources().getString(R.string.imagen_de_una_naranja));
                cambiarEstadoSelección(manzanaSeleccionado, false);
                manzana.setEnabled(true);
                manzana.setContentDescription(getResources().getString(R.string.imagen_de_una_manzana));

                passPaso2 = "Fresa";

                botonSiguiente.setEnabled(true);
            }
        });

        //Boton platano
        platano.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                cambiarEstadoSelección(fresaSeleccionado, false);
                fresa.setEnabled(true);
                fresa.setContentDescription(getResources().getString(R.string.imagen_de_una_fresa));
                cambiarEstadoSelección(platanoSeleccionado, true);
                platano.setEnabled(false);
                platano.setContentDescription(getResources().getString(R.string.platano_seleccionado));
                cambiarEstadoSelección(naranjaSeleccionado, false);
                naranja.setEnabled(true);
                naranja.setContentDescription(getResources().getString(R.string.imagen_de_una_naranja));
                cambiarEstadoSelección(manzanaSeleccionado, false);
                manzana.setEnabled(true);
                manzana.setContentDescription(getResources().getString(R.string.imagen_de_una_manzana));

                passPaso2 = "Platano";

                botonSiguiente.setEnabled(true);
            }
        });

        //Boton naranja
        naranja.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                cambiarEstadoSelección(fresaSeleccionado, false);
                fresa.setEnabled(true);
                fresa.setContentDescription(getResources().getString(R.string.imagen_de_una_fresa));
                cambiarEstadoSelección(platanoSeleccionado, false);
                platano.setEnabled(true);
                platano.setContentDescription(getResources().getString(R.string.imagen_de_un_platano));
                cambiarEstadoSelección(naranjaSeleccionado, true);
                naranja.setEnabled(false);
                naranja.setContentDescription(getResources().getString(R.string.naranja_seleccionada));
                cambiarEstadoSelección(manzanaSeleccionado, false);
                manzana.setEnabled(true);
                manzana.setContentDescription(getResources().getString(R.string.imagen_de_una_manzana));

                passPaso2 = "Naranja";

                botonSiguiente.setEnabled(true);
            }
        });

        //Boton manzana
        manzana.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                cambiarEstadoSelección(fresaSeleccionado, false);
                fresa.setEnabled(true);
                fresa.setContentDescription(getResources().getString(R.string.imagen_de_una_fresa));
                cambiarEstadoSelección(platanoSeleccionado, false);
                platano.setEnabled(true);
                platano.setContentDescription(getResources().getString(R.string.imagen_de_un_platano));
                cambiarEstadoSelección(naranjaSeleccionado, false);
                naranja.setEnabled(true);
                naranja.setContentDescription(getResources().getString(R.string.imagen_de_una_naranja));
                cambiarEstadoSelección(manzanaSeleccionado, true);
                manzana.setEnabled(false);
                manzana.setContentDescription(getResources().getString(R.string.boton_tri_ngulo_seleccionado));

                passPaso1 = "Manzana";

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
        /*Intent intent = new Intent(this, InicioSesionPass3.class);
        intent.putExtra("usuario", usuario);
        intent.putExtra("pass1", passPaso1);
        intent.putExtra("pass2", passPaso2);
        startActivity(intent);*/
    }

    private void  anteriorPantallaPass(){
        Intent intent = new Intent(this, InicioSesionPass1.class);
        intent.putExtra("usuario", usuario);
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
