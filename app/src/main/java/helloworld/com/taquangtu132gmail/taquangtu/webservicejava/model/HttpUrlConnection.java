package helloworld.com.taquangtu132gmail.taquangtu.webservicejava.model;

import android.util.Log;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpUrlConnection{
    public String getUsersJsonString(String desiredUrl) throws Exception
    {
        URL url = null;
        BufferedReader reader = null;
        StringBuilder stringBuilder;
        try
        {
            // create the HttpURLConnection
            url = new URL(desiredUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            // just want to do an HTTP GET here
            connection.setRequestMethod("GET");
            // give it 150 seconds to respond
            connection.setReadTimeout(150*1000);
            connection.connect();
            int responseCode = connection.getResponseCode();
            // read the output from the server
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            stringBuilder = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null)
            {
                stringBuilder.append(line + "\n");
            }
            return stringBuilder.toString();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw e;
        }
        finally
        {
            // close the reader; this can throw an exception too, so
            // wrap it in another try/catch block.
            if (reader != null)
            {
                try
                {
                    reader.close();
                }
                catch (IOException ioe)
                {
                    ioe.printStackTrace();
                }
            }
        }
    }
    public void sendPOST(String urlString, String endpoint) throws IOException {
        URL url = new URL(urlString+"/"+endpoint);

        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        conn.setRequestMethod("POST");
        conn.setDoOutput(true);
       // conn.connect();
        StringBuilder sb = new StringBuilder();
        Reader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
        for (int c; (c = in.read()) >= 0;)
            sb.append((char)c);
        String response = sb.toString();
        Log.d("RESPONSE POST: ", response);
    }
    public void sendPUT(String urlString, String endPoint) throws IOException
    {
        URL url = new URL(urlString+"/"+endPoint);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setDoOutput(true);
        connection.setRequestMethod("PUT");
        StringBuilder sb = new StringBuilder();
        Reader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
        for (int c; (c = in.read()) >= 0;)
            sb.append((char)c);
        String response = sb.toString();
        Log.d("RESPONSE PUT: ", response);
    }
    public void sendDELETE(String urlString, String endPoint) throws IOException
    {
        URL url = new URL(urlString+"/"+endPoint);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("DELETE");
        connection.connect();
        int responseCode = connection.getResponseCode();
        Log.d("RESPONSE", "sendDELETE: "+responseCode);
    }
    public String getById(String urlString, String endpoint) throws IOException, JSONException {
        URL url = new URL(urlString+"/"+endpoint);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String line="";
        StringBuilder jsonString= new StringBuilder();
        while((line = reader.readLine())!=null)
        {
            jsonString.append(line);
        }
        return jsonString.toString();
    }
}