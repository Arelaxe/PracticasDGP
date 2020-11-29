package com.example.valeapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;

import static android.Manifest.permission.CAMERA;
import static android.Manifest.permission.RECORD_AUDIO;

public class MainActivity extends AppCompatActivity {

    String nombreUsuario;

    // Guardar permisos
    private final int REQUEST_EXTERNAL_STORAGE = 1;
    private String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.RECORD_AUDIO,
            Manifest.permission.CAMERA
    };

    private Runnable taskNombreUsuario = new Runnable() {
        public void run() {
            inicioSesionNombreUsuario();
        }
    };

    private Runnable taskContraseña = new Runnable() {
        public void run() {
            inicioSesionPass(nombreUsuario);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AlmacenamientoInformacion infoUsuario = new AlmacenamientoInformacion();
        Context mContext = getApplicationContext();

        nombreUsuario = infoUsuario.getData(mContext);
        verifyStoragePermissions(this);
    }

    private void inicioSesionNombreUsuario() {
        Intent intent = new Intent(this, InicioSesionNombreUsuario.class);
        startActivity(intent);
    }

    private void inicioSesionPass(String usuario) {
        Intent intent = new Intent(this, InicioSesionPass1.class);
        intent.putExtra("usuario", usuario);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);

    }

    /**
     * Checks if the app has permission to write to device storage
     *
     * If the app does not has permission then the user will be prompted to grant permissions
     *
     * @param activity
     */
    public void verifyStoragePermissions(Activity activity) {
        // Check if we have write permission
        int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int permisoGrabar = ActivityCompat.checkSelfPermission(getApplicationContext(),
                RECORD_AUDIO);
        if (permission != PackageManager.PERMISSION_GRANTED && permisoGrabar != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                    activity,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
        }

        else{
            comprobarUsuario();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        requestCode = ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int permisoAudio = ActivityCompat.checkSelfPermission(this, RECORD_AUDIO);
        int permisoVideo = ActivityCompat.checkSelfPermission(this, CAMERA);
       if (requestCode != PackageManager.PERMISSION_GRANTED || permisoAudio != PackageManager.PERMISSION_GRANTED ||
        permisoVideo != PackageManager.PERMISSION_GRANTED){
           closeNow();
       }
       else{
           comprobarUsuario();
       }
    }
    private void comprobarUsuario(){
        Handler handler = new Handler();
        if(nombreUsuario == null) {
            //Login Usuario
            handler.postDelayed(taskNombreUsuario, 3000);
        }
        else {
            // Login contraseña
            handler.postDelayed(taskContraseña, 3000);
        }
    }
    private void closeNow() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            finishAffinity();
        } else {
            finish();
        }
    }
}