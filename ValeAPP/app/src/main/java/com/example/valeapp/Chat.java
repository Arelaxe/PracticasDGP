package com.example.valeapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.scaledrone.lib.HistoryRoomListener;
import com.scaledrone.lib.Listener;
import com.scaledrone.lib.Member;
import com.scaledrone.lib.Room;
import com.scaledrone.lib.RoomListener;
import com.scaledrone.lib.Scaledrone;
import com.scaledrone.lib.SubscribeOptions;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.ExecutionException;

public class Chat extends AppCompatActivity implements RoomListener {

    // replace this with a real channelID from Scaledrone dashboard
    private String channelID;
    private String roomName;
    private EditText editText;
    private Scaledrone scaledrone;
    private MessageAdapter messageAdapter;
    private ListView messagesView;
    private String separador = "&%:_:%&";
    private String usuario;
    private String creador;
    private String nombreTarea = "";
    private Room room;
    private JSONObject jsonChat;
    private JSONObject jsonFotoFacilitador;
    private Drawable fotoFacilitador;
    private String tipo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar2);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.barra_de_tareas);

        //Modicar Barra de Tareas para esta pantalla
        final ImageButton botonAtras = findViewById(R.id.flechaVolverMenuAnterior);
        botonAtras.setVisibility(View.VISIBLE);
        botonAtras.setContentDescription("VOLVER A LA TAREA");
        final TextView textoFlechaAtras = findViewById(R.id.textoVolverAMenuAnterior);
        textoFlechaAtras.setText("VOLVER A LA TAREA");
        textoFlechaAtras.setVisibility(View.VISIBLE);
        textoFlechaAtras.setContentDescription("VOLVER A LA TAREA TÍTULO");

        final ImageButton botonLogout = findViewById(R.id.botonLogout);

        Bundle bundle = getIntent().getExtras();
        usuario = bundle.getString("usuario");
        creador = bundle.getString("creador");
        tipo = bundle.getString("tipo");

        //Boton logout
        botonLogout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                irALogout();
            }
        });

        //Boton Atrás
        botonAtras.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (tipo.equals("tarea")){
                    volverTareaDetallada();
                }
                else if(tipo.equals("facilitador")){
                    volverFacilitadores();
                }
            }
        });

        if (tipo.equals("tarea")){
            nombreTarea = bundle.getString("nombreTarea");
        }

        channelID = bundle.getString("idChat");
        roomName = bundle.getString("nombreChat");

        getFotoFacilitador();
        try {
            procesarFotoFacilitador();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        editText = (EditText) findViewById(R.id.editText);

        messageAdapter = new MessageAdapter(this);
        messageAdapter.setFotoFacilitador(fotoFacilitador);
        messagesView = (ListView) findViewById(R.id.messages_view);
        messagesView.setAdapter(messageAdapter);

        MemberData data = new MemberData(usuario, getRandomColor());

        scaledrone = new Scaledrone(channelID, data);
        scaledrone.connect(new Listener() {
            @Override
            public void onOpen() {
                System.out.println("Scaledrone connection open");
                room = scaledrone.subscribe(roomName, Chat.this, new SubscribeOptions(20));
                room.listenToHistoryEvents(new HistoryRoomListener() {
                    @Override
                    public void onHistoryMessage(Room room, com.scaledrone.lib.Message message) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                String [] msg = message.getData().asText().split(separador, 2);
                                boolean belongsToCurrentUser = msg[0].equals(usuario);
                                final Message message2 = new Message(msg[1], data, belongsToCurrentUser, msg[0]);
                                messageAdapter.add(message2);
                            }
                        });
                    }
                });

            }

            @Override
            public void onOpenFailure(Exception ex) {
                System.err.println(ex);
            }

            @Override
            public void onFailure(Exception ex) {
                System.err.println(ex);
            }

            @Override
            public void onClosed(String reason) {
                System.err.println(reason);
            }
        });

        setChatVisto();
    }

    public void sendMessage(View view) {
        String message = editText.getText().toString();
        if (message.length() > 0) {
            scaledrone.publish(roomName,usuario + separador + message);
            editText.getText().clear();
        }
        nuevoMensajeSocio();
    }

    @Override
    public void onOpen(Room room) {
        System.out.println("Conneted to room");
    }

    @Override
    public void onOpenFailure(Room room, Exception ex) {
        System.err.println(ex);
    }

    @Override
    public void onMessage(Room room, com.scaledrone.lib.Message receivedMessage) {
        final ObjectMapper mapper = new ObjectMapper();
        try {
            final MemberData data = mapper.treeToValue(receivedMessage.getMember().getClientData(), MemberData.class);
            String mensajes [] = receivedMessage.getData().asText().split(separador, 2);
            boolean belongsToCurrentUser = mensajes[0].equals(usuario);
            String mensaje = mensajes[1];
            final Message message = new Message(mensaje, data, belongsToCurrentUser, mensajes[0]);
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    messageAdapter.add(message);
                    messagesView.setSelection(messagesView.getCount() - 1);
                }
            });
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    private String getRandomColor() {
        Random r = new Random();
        StringBuffer sb = new StringBuffer("#");
        while(sb.length() < 7){
            sb.append(Integer.toHexString(r.nextInt()));
        }
        return sb.toString().substring(0, 7);
    }

    public void volverTareaDetallada(){
        setChatVisto();
        Intent intent = new Intent(this, TareaDetallada.class);
        intent.putExtra("usuario", usuario);
        intent.putExtra("creador", creador);
        intent.putExtra("nombreTarea", nombreTarea);
        startActivity(intent);
    }

    public void volverFacilitadores(){
        setChatVisto();
        Intent intent = new Intent(this, TareaDetallada.class);
        intent.putExtra("usuario", usuario);
        startActivity(intent);
    }

    public void onBackPressed() {
        if (tipo.equals("tarea")){
            volverTareaDetallada();
        }
        else if(tipo.equals("facilitador")){
            volverFacilitadores();
        }
    }

    private void irALogout(){
        setChatVisto();
        Intent intent = new Intent(this, Logout.class);
        startActivity(intent);
    }

    class SetChatVisto extends AsyncTask<String, String, JSONObject> {
        private final static String URL= "chat-visto-tarea-socio";
        private JSONParser jsonParser = new JSONParser();

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        protected JSONObject doInBackground(String... args) {

            try{
                HashMap<String, String> params = new HashMap<>();

                params.put("username", usuario);
                params.put("creador", creador);
                if(tipo.equals("tarea")){
                    params.put("nombreTarea", nombreTarea);
                }
                params.put("tipo", tipo);
                Log.d("request", "starting");

                jsonChat = jsonParser.makeHttpRequest(URL, "POST", params);

                if (jsonChat != null) {
                    Log.d("JSON result:   ", jsonChat.toString());
                    return jsonChat;
                }
            } catch (Exception e) {

                e.printStackTrace();
            }
            return null;
        }
    }

    private void setChatVisto(){
        try {
            new SetChatVisto().execute().get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    class NuevoMensajeSocio extends AsyncTask<String, String, JSONObject> {
        private final static String URL= "nuevo-mensaje-socio";
        private JSONParser jsonParser = new JSONParser();

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        protected JSONObject doInBackground(String... args) {

            try{
                HashMap<String, String> params = new HashMap<>();

                params.put("username", usuario);
                params.put("creador", creador);
                if(tipo.equals("tarea")){
                    params.put("nombreTarea", nombreTarea);
                }
                params.put("tipo", tipo);
                Log.d("request", "starting");

                jsonChat = jsonParser.makeHttpRequest(URL, "POST", params);

                if (jsonChat != null) {
                    Log.d("JSON result:   ", jsonChat.toString());
                    return jsonChat;
                }
            } catch (Exception e) {

                e.printStackTrace();
            }
            return null;
        }
    }

    private void nuevoMensajeSocio(){
        try {
            new NuevoMensajeSocio().execute().get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    class FotoFacilitador extends AsyncTask<String, String, JSONObject> {
        private final static String URL= "foto-facilitador";
        private JSONParser jsonParser = new JSONParser();

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        protected JSONObject doInBackground(String... args) {

            try{
                HashMap<String, String> params = new HashMap<>();

                params.put("creador", creador);

                Log.d("request", "starting");

                jsonFotoFacilitador = jsonParser.makeHttpRequest(URL, "GET", params);

                if (jsonFotoFacilitador != null) {
                    Log.d("JSON result:   ", jsonFotoFacilitador.toString());
                    return jsonFotoFacilitador;
                }
            } catch (Exception e) {

                e.printStackTrace();
            }
            return null;
        }
    }

    private void getFotoFacilitador(){
        try {
            new FotoFacilitador().execute().get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void procesarFotoFacilitador() throws JSONException {
        int width = 170;
        int height = 170;

        //Foto del facilitador
        byte[] data = Base64.decode(jsonFotoFacilitador.getString("fotoFacilitador"), Base64.DEFAULT);
        Bitmap mapaImagen = BitmapFactory.decodeByteArray(data, 0, data.length);
        fotoFacilitador = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(mapaImagen, width, height, true));
    }
}


