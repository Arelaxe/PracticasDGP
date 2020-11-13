package com.example.valeapp;
import android.util.Log;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

public class JSONParser {

    private final String CHARSET = "UTF-8";
    //private final String URL = "http://10.0.2.2:5000/";
    private final String URL = "http://192.168.1.45:5000/";
    private HttpURLConnection conn;
    private DataOutputStream wr;
    private StringBuilder result;
    private URL urlObj;
    private JSONObject jObj = null;
    private StringBuilder sbParams;
    private String paramsString;

    private boolean isNumeric(String s) {
        return s != null && s.matches("[-+]?\\d*\\.?\\d+");
    }

    public JSONObject makeHttpRequest(String url, String method,
                                      HashMap<String, String> params, String tipo) {
        url = URL + url;
        sbParams = new StringBuilder();

        int i = 0;

        for (String key : params.keySet()) {
          if (i != 0) {
              sbParams.append("&");
          }
          sbParams.append(key).append("=");
          if (!isNumeric(params.get(key))) {
              sbParams.append(params.get(key));
          } else {
              sbParams.append(params.get(key));
          }

          i++;
        }

        System.out.println((sbParams));
        if (method.equals("POST")) {
            // request method is POST
            try {
                urlObj = new URL(url);

                conn = (HttpURLConnection) urlObj.openConnection();

                conn.setDoOutput(true);

                conn.setRequestMethod("POST");

                conn.setRequestProperty("Accept-Charset", CHARSET);

                conn.setReadTimeout(10000);
                conn.setConnectTimeout(15000);

                conn.connect();

                paramsString = sbParams.toString();
                wr = new DataOutputStream(conn.getOutputStream());
                wr.writeBytes(paramsString);
                wr.flush();
                wr.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(method.equals("GET")){
            // request method is GET

            if (sbParams.length() != 0) {
                url += "?" + sbParams.toString();
            }

            try {
                urlObj = new URL(url);

                conn = (HttpURLConnection) urlObj.openConnection();

                conn.setDoOutput(false);

                conn.setRequestMethod("GET");

                conn.setRequestProperty("Accept-Charset", CHARSET);

                conn.setConnectTimeout(15000);

                conn.connect();

            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        try {

            int responseCode = conn.getResponseCode();

            //Receive the response from the server
            InputStream in = new BufferedInputStream(conn.getInputStream());
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            result = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }

            Log.d("JSON Parser", ":result" + result.toString());

        } catch (IOException e) {
            e.printStackTrace();
        }

        conn.disconnect();

        // try parse the string to a JSON object
        try {
            jObj = new JSONObject(result.toString());
        } catch (JSONException e) {
            Log.e("JSON Parser", "Error parsing data " + e.toString());
        }

        // return JSON Object
        return jObj;
    }
}