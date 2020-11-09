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
                circulo.setContentDescription(getResources().getString(R.string.boton_c_rculo_seleccionado));
                cambiarEstadoSelección(estrellaSeleccionado, false);
                estrella.setEnabled(true);
                estrella.setContentDescription(getResources().getString(R.string.imagen_de_una_estrella));
                cambiarEstadoSelección(cuadradoSeleccionado, false);
                cuadrado.setEnabled(true);
                cuadrado.setContentDescription(getResources().getString(R.string.imagen_de_un_cuadrado));
                cambiarEstadoSelección(trianguloSeleccionado, false);
                triangulo.setEnabled(true);
                triangulo.setContentDescription(getResources().getString(R.string.imagen_de_tri_ngulo));

                passPaso1 = "Circulo";
            }
        });

        //Boton estrella
        estrella.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                cambiarEstadoSelección(circuloSeleccionado, false);
                circulo.setEnabled(true);
                circulo.setContentDescription(getResources().getString(R.string.imagen_de_un_c_rculo));
                cambiarEstadoSelección(estrellaSeleccionado, true);
                estrella.setEnabled(false);
                estrella.setContentDescription(getResources().getString(R.string.boton_estrella_seleccionada));
                cambiarEstadoSelección(cuadradoSeleccionado, false);
                cuadrado.setEnabled(true);
                cuadrado.setContentDescription(getResources().getString(R.string.imagen_de_un_cuadrado));
                cambiarEstadoSelección(trianguloSeleccionado, false);
                triangulo.setEnabled(true);
                triangulo.setContentDescription(getResources().getString(R.string.imagen_de_tri_ngulo));

                passPaso1 = "Estrella";
            }
        });

        //Boton cuadrado
        cuadrado.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                cambiarEstadoSelección(circuloSeleccionado, false);
                circulo.setEnabled(true);
                circulo.setContentDescription(getResources().getString(R.string.imagen_de_un_c_rculo));
                cambiarEstadoSelección(estrellaSeleccionado, false);
                estrella.setEnabled(true);
                estrella.setContentDescription(getResources().getString(R.string.imagen_de_una_estrella));
                cambiarEstadoSelección(cuadradoSeleccionado, true);
                cuadrado.setEnabled(false);
                cuadrado.setContentDescription(getResources().getString(R.string.boton_cuadrado_seleccionado));
                cambiarEstadoSelección(trianguloSeleccionado, false);
                triangulo.setEnabled(true);
                triangulo.setContentDescription(getResources().getString(R.string.imagen_de_tri_ngulo));

                passPaso1 = "Cuadrado";
            }
        });

        //Boton triangulo
        triangulo.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                cambiarEstadoSelección(circuloSeleccionado, false);
                circulo.setEnabled(true);
                circulo.setContentDescription(getResources().getString(R.string.imagen_de_un_c_rculo));
                cambiarEstadoSelección(estrellaSeleccionado, false);
                estrella.setEnabled(true);
                estrella.setContentDescription(getResources().getString(R.string.imagen_de_una_estrella));
                cambiarEstadoSelección(cuadradoSeleccionado, false);
                cuadrado.setEnabled(true);
                cuadrado.setContentDescription(getResources().getString(R.string.imagen_de_un_cuadrado));
                cambiarEstadoSelección(trianguloSeleccionado, true);
                triangulo.setEnabled(false);
                triangulo.setContentDescription(getResources().getString(R.string.boton_tri_ngulo_seleccionado));

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
