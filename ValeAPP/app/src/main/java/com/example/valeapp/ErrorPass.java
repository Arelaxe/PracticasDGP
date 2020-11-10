package com.example.valeapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class ErrorPass extends AppCompatActivity {
    private String usuario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.error_pass);

        Bundle bundle = getIntent().getExtras();

        usuario = bundle.getString("usuario");

        //Boton Atras
        final ImageButton volverInicioSesionPass = findViewById(R.id.idBotonVolverAPass);

        volverInicioSesionPass.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                inicioSesionPass();
            }
        });
    }

    private void inicioSesionPass() {
        Intent intent = new Intent(this, InicioSesionPass1.class);
        intent.putExtra("usuario", usuario);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
    }
}
