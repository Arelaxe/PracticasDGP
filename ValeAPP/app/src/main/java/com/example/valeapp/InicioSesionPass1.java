package com.example.valeapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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

        Bundle bundle = getIntent().getExtras();

        usuario = bundle.getString("usuario");

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
        final ImageButton botonSiguiente =findViewById(R.id.irPass2);

        botonSiguiente.setEnabled(false);
        circulo.setEnabled(true);
        estrella.setEnabled(true);
        cuadrado.setEnabled(true);
        triangulo.setEnabled(true);

        circulo.setContentDescription(getResources().getString(R.string.imagen_de_un_c_rculo));
        estrella.setContentDescription(getResources().getString(R.string.imagen_de_una_estrella));
        cuadrado.setContentDescription(getResources().getString(R.string.imagen_de_un_cuadrado));
        triangulo.setContentDescription(getResources().getString(R.string.imagen_de_tri_ngulo));

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

                botonSiguiente.setEnabled(true);
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

                botonSiguiente.setEnabled(true);
            }
        });

        //Boton Cuadrado
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

                botonSiguiente.setEnabled(true);
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

                botonSiguiente.setEnabled(true);
            }
        });

        botonSiguiente.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                siguientePantallaPass();
            }
        });
    }

    private void siguientePantallaPass() {
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
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);

    }
}
