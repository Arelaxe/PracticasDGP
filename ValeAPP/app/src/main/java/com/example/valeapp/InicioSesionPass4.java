package com.example.valeapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class InicioSesionPass4 extends AppCompatActivity {
    private String passPaso1;
    private String passPaso2;
    private String passPaso3;
    private String passPaso4;
    private String usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_pass4);

        Bundle bundle = getIntent().getExtras();

        usuario = bundle.getString("usuario");
        passPaso1 = bundle.getString("pass1");
        passPaso2 = bundle.getString("pass2");
        passPaso3 = bundle.getString("pass3");

        ImageView cocheSeleccionado = findViewById(R.id.cocheSeleccionado);
        ImageView motoSeleccionada = findViewById(R.id.motoSeleccionada);
        ImageView bicicletaSeleccionada = findViewById(R.id.bicicletaSeleccionada);
        ImageView helicopteroSeleccionado = findViewById(R.id.helicopteroSeleccionado);

        cambiarEstadoSelección(cocheSeleccionado, false);
        cambiarEstadoSelección(motoSeleccionada, false);
        cambiarEstadoSelección(bicicletaSeleccionada, false);
        cambiarEstadoSelección(helicopteroSeleccionado, false);


        final ImageButton coche = findViewById(R.id.Coche);
        final ImageButton moto = findViewById(R.id.Moto);
        final ImageButton bicicleta = findViewById(R.id.Bicicleta);
        final ImageButton helicoptero = findViewById(R.id.Helicoptero);
        final ImageButton botonSiguiente = findViewById(R.id.irPass5);
        final ImageButton botonAnterior = findViewById(R.id.irPass3);

        botonSiguiente.setEnabled(false);
        coche.setEnabled(true);
        moto.setEnabled(true);
        bicicleta.setEnabled(true);
        helicoptero.setEnabled(true);

        coche.setContentDescription(getResources().getString(R.string.imagen_de_un_coche));
        moto.setContentDescription(getResources().getString(R.string.imagen_de_una_moto));
        bicicleta.setContentDescription(getResources().getString(R.string.imagen_de_una_bicicleta));
        helicoptero.setContentDescription(getResources().getString(R.string.imagen_de_un_helicoptero));

        //Boton coche
        coche.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                cambiarEstadoSelección(cocheSeleccionado, true);
                coche.setEnabled(false);
                coche.setContentDescription(getResources().getString(R.string.coche_seleccionado));//
                cambiarEstadoSelección(motoSeleccionada, false);
                moto.setEnabled(true);
                moto.setContentDescription(getResources().getString(R.string.imagen_de_una_moto));
                cambiarEstadoSelección(bicicletaSeleccionada, false);
                bicicleta.setEnabled(true);
                bicicleta.setContentDescription(getResources().getString(R.string.imagen_de_una_bicicleta));
                cambiarEstadoSelección(helicopteroSeleccionado, false);
                helicoptero.setEnabled(true);
                helicoptero.setContentDescription(getResources().getString(R.string.imagen_de_un_helicoptero));

                passPaso4 = "Coche";

                botonSiguiente.setEnabled(true);
            }
        });

        //Boton moto
        moto.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                cambiarEstadoSelección(cocheSeleccionado, false);
                coche.setEnabled(true);
                coche.setContentDescription(getResources().getString(R.string.imagen_de_un_coche));
                cambiarEstadoSelección(motoSeleccionada, true);
                moto.setEnabled(false);
                moto.setContentDescription(getResources().getString(R.string.moto_seleccionada));
                cambiarEstadoSelección(bicicletaSeleccionada, false);
                bicicleta.setEnabled(true);
                bicicleta.setContentDescription(getResources().getString(R.string.imagen_de_una_bicicleta));
                cambiarEstadoSelección(helicopteroSeleccionado, false);
                helicoptero.setEnabled(true);
                helicoptero.setContentDescription(getResources().getString(R.string.imagen_de_un_helicoptero));

                passPaso4 = "Moto";

                botonSiguiente.setEnabled(true);
            }
        });

        //Boton bicicleta
        bicicleta.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                cambiarEstadoSelección(cocheSeleccionado, false);
                coche.setEnabled(true);
                coche.setContentDescription(getResources().getString(R.string.imagen_de_un_coche));
                cambiarEstadoSelección(motoSeleccionada, false);
                moto.setEnabled(true);
                moto.setContentDescription(getResources().getString(R.string.imagen_de_una_moto));
                cambiarEstadoSelección(bicicletaSeleccionada, true);
                bicicleta.setEnabled(false);
                bicicleta.setContentDescription(getResources().getString(R.string.bicicleta_seleccionada));
                cambiarEstadoSelección(helicopteroSeleccionado, false);
                helicoptero.setEnabled(true);
                helicoptero.setContentDescription(getResources().getString(R.string.imagen_de_un_helicoptero));

                passPaso4 = "Bicicleta";

                botonSiguiente.setEnabled(true);
            }
        });

        //Boton helicoptero
        helicoptero.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                cambiarEstadoSelección(cocheSeleccionado, false);
                coche.setEnabled(true);
                coche.setContentDescription(getResources().getString(R.string.imagen_de_un_coche));
                cambiarEstadoSelección(motoSeleccionada, false);
                moto.setEnabled(true);
                moto.setContentDescription(getResources().getString(R.string.imagen_de_una_moto));
                cambiarEstadoSelección(bicicletaSeleccionada, false);
                bicicleta.setEnabled(true);
                bicicleta.setContentDescription(getResources().getString(R.string.imagen_de_una_bicicleta));
                cambiarEstadoSelección(helicopteroSeleccionado, true);
                helicoptero.setEnabled(false);
                helicoptero.setContentDescription(getResources().getString(R.string.helicoptero_seleccionado));

                passPaso4 = "Helicoptero";

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
        Intent intent = new Intent(this, InicioSesionPass5.class);
        intent.putExtra("usuario", usuario);
        intent.putExtra("pass1", passPaso1);
        intent.putExtra("pass2", passPaso2);
        intent.putExtra("pass3", passPaso3);
        intent.putExtra("pass4", passPaso4);
        startActivity(intent);
    }

    private void  anteriorPantallaPass(){
        Intent intent = new Intent(this, InicioSesionPass3.class);
        intent.putExtra("usuario", usuario);
        intent.putExtra("pass1", passPaso1);
        intent.putExtra("pass2", passPaso2);
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
