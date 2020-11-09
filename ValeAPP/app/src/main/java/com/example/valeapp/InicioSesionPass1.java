package com.example.valeapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONObject;

import java.util.HashMap;

public class InicioSesionPass1 extends AppCompatActivity {
    private EditText usuarioT;
    private String usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_pass1);

        //Boton circulo
        final ImageButton circulo = findViewById(R.id.circulo);

        circulo.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                System.out.println("circulo");
            }
        });

        //Boton estrella
        final ImageButton estrella = findViewById(R.id.estrella);

        estrella.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                System.out.println("estrella");
            }
        });

        //Boton cuadrado
        final ImageButton cuadrado = findViewById(R.id.cuadrado);

        cuadrado.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                System.out.println("cuadrado");
            }
        });

        //Boton triangulo
        final ImageButton triangulo = findViewById(R.id.triangulo);

        triangulo.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                System.out.println("triangulo");
            }
        });
    }

    private void siguientePantallaPass() {
        //Intent intent = new Intent(this, InicioSesionPass.class);
        //startActivity(intent);
    }
}
