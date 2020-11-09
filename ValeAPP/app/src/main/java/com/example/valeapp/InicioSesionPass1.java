package com.example.valeapp;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class InicioSesionPass1 extends AppCompatActivity {
    private String passPaso1;
    private String usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_pass1);
        ImageView circuloSeleccionado = findViewById(R.id.circuloSeleccionado);
        ImageView estrellaSeleccionado = findViewById(R.id.estrellaSeleccionado);
        ImageView cuadradoSeleccionado = findViewById(R.id.cuadradoSeleccionado);
        ImageView trianguloSeleccionado = findViewById(R.id.trianguloSeleccionado);

        cambiarEstadoSelección(circuloSeleccionado, false);
        cambiarEstadoSelección(estrellaSeleccionado, false);
        cambiarEstadoSelección(cuadradoSeleccionado, false);
        cambiarEstadoSelección(trianguloSeleccionado, false);


        final ImageButton circulo = findViewById(R.id.Circulo);
        final ImageButton estrella = findViewById(R.id.Estrella);
        final ImageButton cuadrado = findViewById(R.id.Cuadrado);
        final ImageButton triangulo = findViewById(R.id.Triangulo);

        //Boton circulo
        circulo.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                cambiarEstadoSelección(circuloSeleccionado, true);
                circulo.setEnabled(false);
                cambiarEstadoSelección(estrellaSeleccionado, false);
                estrella.setEnabled(true);
                cambiarEstadoSelección(cuadradoSeleccionado, false);
                cuadrado.setEnabled(true);
                cambiarEstadoSelección(trianguloSeleccionado, false);
                triangulo.setEnabled(true);

                passPaso1 = "Circulo";
            }
        });

        //Boton estrella
        estrella.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                cambiarEstadoSelección(circuloSeleccionado, false);
                circulo.setEnabled(true);
                cambiarEstadoSelección(estrellaSeleccionado, true);
                estrella.setEnabled(false);
                cambiarEstadoSelección(cuadradoSeleccionado, false);
                cuadrado.setEnabled(true);
                cambiarEstadoSelección(trianguloSeleccionado, false);
                triangulo.setEnabled(true);

                passPaso1 = "Estrella";
            }
        });

        //Boton cuadrado
        cuadrado.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                cambiarEstadoSelección(circuloSeleccionado, false);
                circulo.setEnabled(true);
                cambiarEstadoSelección(estrellaSeleccionado, false);
                estrella.setEnabled(true);
                cambiarEstadoSelección(cuadradoSeleccionado, true);
                cuadrado.setEnabled(false);
                cambiarEstadoSelección(trianguloSeleccionado, false);
                triangulo.setEnabled(true);

                passPaso1 = "Cuadrado";
            }
        });

        //Boton triangulo
        triangulo.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                cambiarEstadoSelección(circuloSeleccionado, false);
                circulo.setEnabled(true);
                cambiarEstadoSelección(estrellaSeleccionado, false);
                estrella.setEnabled(true);
                cambiarEstadoSelección(cuadradoSeleccionado, false);
                cuadrado.setEnabled(true);
                cambiarEstadoSelección(trianguloSeleccionado, true);
                triangulo.setEnabled(false);

                passPaso1 = "Triangulo";
            }
        });
    }

    private void siguientePantallaPass() {
        //Intent intent = new Intent(this, InicioSesionPass.class);
        //startActivity(intent);
    }

    private void cambiarEstadoSelección(ImageView imagen, boolean estado){
        if (estado){
            imagen.setVisibility(View.VISIBLE);
        }
        else{
            imagen.setVisibility(View.INVISIBLE);
        }
    }
}
