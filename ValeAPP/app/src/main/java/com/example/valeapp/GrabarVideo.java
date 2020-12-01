package com.example.valeapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.hardware.Camera;
import android.media.CamcorderProfile;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import org.json.JSONObject;

import java.io.IOException;

import static android.view.View.INVISIBLE;

public class GrabarVideo extends AppCompatActivity {
    private String usuario;
    String creador;
    String nombreTarea;
    String mote;
    MediaRecorder mediaRecorder;
    String nombreVideo = null;
    String tipoRespuesta = null;
    Camera camara;
    SurfaceView surfaceViewCamara;
    SurfaceHolder holder;
    boolean inPreview = false;
    Boolean camaraConfigurada = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.grabar_video);


        Bundle bundle = getIntent().getExtras();
        usuario = bundle.getString("usuario");
        creador = bundle.getString("creador");
        nombreTarea = bundle.getString("nombreTarea");
        mote = bundle.getString("mote");
        System.out.println(mote);
        tipoRespuesta = bundle.getString("tipoRespuesta");

        nombreVideo = "Movies/" + "Respuesta_" + nombreTarea + "_"+ mote + "_"+ usuario +".mp4";

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.barra_de_tareas);

        //Modicar Barra de Tareas para esta pantalla
        final ImageButton botonAtras = findViewById(R.id.flechaVolverMenuAnterior);
        botonAtras.setVisibility(View.VISIBLE);
        botonAtras.setContentDescription("VOLVER A LA RESPUESTA");
        final TextView textoFlechaAtras = findViewById(R.id.textoVolverAMenuAnterior);
        textoFlechaAtras.setText("VOLVER A LA RESPUESTA");
        textoFlechaAtras.setVisibility(View.VISIBLE);

        final ImageButton botonLogout = findViewById(R.id.botonLogout);


        //Boton logout
        botonLogout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                irALogout();
            }
        });

        //Boton Atrás
        botonAtras.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                volverARespuesta();
            }
        });

        final ToggleButton botonGrabar = findViewById(R.id.botonGrabar);
        camara = Camera.open();
        camara.getParameters();

        try {
            camara.stopPreview();
            surfaceViewCamara = findViewById(R.id.grabarVideoSurface);
            holder = surfaceViewCamara.getHolder();
            holder.addCallback(surfaceCallback);
            camara.setPreviewDisplay(surfaceViewCamara.getHolder());
            camara.startPreview();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Drawable dGrabar = getResources().getDrawable(R.drawable.grabar_video_mini);
        Bitmap bitmap = ((BitmapDrawable) dGrabar).getBitmap();
        // Escalar
        Drawable dEscaladoGrabar = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(bitmap, 120, 120, true));

        Drawable dDejarGrabar = getResources().getDrawable(R.drawable.detener);
        Bitmap bitmap2 = ((BitmapDrawable) dDejarGrabar).getBitmap();
        // Escalar
        Drawable dEscaladoDejarGrabar = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(bitmap2, 120, 120, true));

        botonGrabar.setBackgroundDrawable(dEscaladoGrabar);

        botonGrabar.setContentDescription("Grabar");

        //Boton Grabar
        botonGrabar.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // The toggle is enabled
                    try {
                        grabacionVideo();
                        botonGrabar.setContentDescription("Detener grabación");
                        botonGrabar.setBackgroundDrawable(dEscaladoDejarGrabar);
                        botonAtras.setEnabled(false);
                        botonLogout.setEnabled(false);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    // The toggle is disabled
                    pararGrabacionVideo();
                    botonGrabar.setContentDescription("Grabar");
                    botonGrabar.setBackgroundDrawable(dEscaladoGrabar);
                    botonAtras.setEnabled(true);
                    botonLogout.setEnabled(true);
                }
            }
        });
    }

    private void startPreview() {
        if (camaraConfigurada && camara!=null) {
            camara.startPreview();
            inPreview=true;
        }
    }

    @Override
    public void onResume() {
        super.onResume();

        camara=Camera.open();
        startPreview();
    }

    @Override
    public void onPause() {
        if (inPreview) {
            camara.stopPreview();
        }

        camara.release();
        camara=null;
        inPreview=false;

        super.onPause();
    }

    private Camera.Size getBestPreviewSize(int width, int height,
                                           Camera.Parameters parameters) {
        Camera.Size result=null;

        for (Camera.Size size : parameters.getSupportedPreviewSizes()) {
            if (size.width<=width && size.height<=height) {
                if (result==null) {
                    result=size;
                }
                else {
                    int resultArea=result.width*result.height;
                    int newArea=size.width*size.height;

                    if (newArea>resultArea) {
                        result=size;
                    }
                }
            }
        }

        return(result);
    }

    @SuppressLint("LongLogTag")
    private void initPreview(int width, int height) {
        if (camara!=null && holder.getSurface()!=null) {
            try {
                camara.setPreviewDisplay(holder);
            }
            catch (Throwable t) {
                Log.e("PreviewDemo-surfaceCallback",
                        "Exception in setPreviewDisplay()", t);
                Toast
                        .makeText(GrabarVideo.this, t.getMessage(), Toast.LENGTH_LONG)
                        .show();
            }

            if (!camaraConfigurada) {
                Camera.Parameters parameters=camara.getParameters();
                Camera.Size size = getBestPreviewSize(height, width,
                        parameters);

                if (size!=null) {
                    parameters.setPreviewSize(size.width, size.height);
                    camara.setParameters(parameters);
                    camaraConfigurada = true;
                }

                if (this.getResources().getConfiguration().orientation != Configuration.ORIENTATION_LANDSCAPE) {
                    camara.setDisplayOrientation(90);
                    //lp.height = surfaceViewCamara.hight;
                    //lp.width = (int) (previewSurfaceHeight / aspect);
                } else {
                    camara.setDisplayOrientation(0);
                    //lp.width = previewSurfaceWidth;
                    //lp.height = (int) (previewSurfaceWidth / aspect);
                }
            }
        }
    }
    SurfaceHolder.Callback surfaceCallback=new SurfaceHolder.Callback() {
        public void surfaceCreated(SurfaceHolder holder) {
            // no-op -- wait until surfaceChanged()
        }

        public void surfaceChanged(SurfaceHolder holder,
                                   int format, int width,
                                   int height) {
            initPreview(width, height);
            startPreview();
        }

        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {

        }
    };

    public void grabacionVideo() throws IOException {
        camara.unlock();
        mediaRecorder = new MediaRecorder();
        mediaRecorder.setCamera(camara);
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.CAMCORDER);
        mediaRecorder.setVideoSource(MediaRecorder.VideoSource.CAMERA);
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
        mediaRecorder.setAudioEncoder(MediaRecorder.OutputFormat.THREE_GPP);
        mediaRecorder.setVideoEncoder(MediaRecorder.OutputFormat.MPEG_4);
        mediaRecorder.setAudioEncodingBitRate(196608);
        mediaRecorder.setVideoSize(1280, 720);
        mediaRecorder.setVideoEncodingBitRate(15000000);
        mediaRecorder.setOutputFile(Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + nombreVideo);
        mediaRecorder.setOrientationHint(90);

        try {
            camara.stopPreview();
            surfaceViewCamara = findViewById(R.id.grabarVideoSurface);
            holder = surfaceViewCamara.getHolder();
            holder.addCallback(surfaceCallback);
            camara.setPreviewDisplay(surfaceViewCamara.getHolder());
            camara.startPreview();
        } catch (IOException e) {
            e.printStackTrace();
        }

        mediaRecorder.prepare();
        mediaRecorder.start();
    }

    public void pararGrabacionVideo(){
        mediaRecorder.stop();
        mediaRecorder.release();
        camara.lock();
    }

    private void irALogout(){
        Intent intent = new Intent(this, Logout.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed(){
        volverARespuesta();
    }

    public void volverARespuesta(){

        Intent intent = new Intent(this, RespuestaTarea.class);
        intent.putExtra("usuario", usuario);
        intent.putExtra("creador", creador);
        intent.putExtra("nombreTarea", nombreTarea);
        intent.putExtra("tipoRespuesta", "video");
        intent.putExtra("mote", mote);
        startActivity(intent);
    }
}
