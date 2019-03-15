package com.example.ibookit.Functionality;

import android.os.AsyncTask;
import android.util.Log;

import com.example.ibookit.Model.Page;
import com.example.ibookit.View.AddBookAsOwnerActivity;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

//https://www.youtube.com/watch?v=Vcn4OuV4Ixg

public class FetchUrlData extends AsyncTask<Void, Void, Void> {
    private String data = "";
    private String dataParsed = "";
    private String singleParsed = "";
    @Override
    protected Void doInBackground(Void... voids) {
        BufferedReader reader = null;
        HttpsURLConnection con = null;

        try {
            Log.d("myslelf-readUrl", "reading url started");
            URL url = new URL("https://www.googleapis.com/books/v1/volumes?q=isbn:0735619670");
            //https://stackoverflow.com/questions/43079460/how-to-read-json-data-from-url-in-android
            con = (HttpsURLConnection) url.openConnection();
            Log.d("myslelf-readUrl", "connection created");
            con.connect();
            Log.d("myshelf-readUrl", "connection established");
            InputStream inputStream = con.getInputStream();
            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line = "";
            StringBuffer buffer = new StringBuffer();


            while(line != null){
                line = reader.readLine();
//                buffer.append(chars, 0, read);
                data = data + line;
            }

            JSONArray JA = new JSONArray(data);
            JSONObject JO = (JSONObject) JA.get(0);
            singleParsed = "title:"+JO.get("kind");
//            dataParsed += singleParsed;






//            reader = new BufferedReader(new InputStreamReader(url.openStream()));
//            Log.d("myshelf-readUrl", "connection established");
//            StringBuffer buffer = new StringBuffer();
//            int read;
//            char[] chars = new char[1024];
//            while ((read = reader.read(chars)) != -1)
//                buffer.append(chars, 0, read);
//
////            return buffer.toString();
        } catch (MalformedURLException ex) {
//            Log.d("myshelf-readUrl", "MalformedURLException");
            ex.printStackTrace();
        } catch (IOException ex) {
//            Log.d("myshelf-readUrl", "IOException");
            ex.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } finally {
            if (con != null) {
                try {
                    con.disconnect();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
//        return null;
        return null;
    }


    @Override
    protected void onPostExecute(Void aVoid){
        super.onPostExecute(aVoid);
//        try {
////            Gson gson = new Gson();
////            Page bookPage = gson.fromJson(data, Page.class);
////            AddBookAsOwnerActivity.mDescription.setText(bookPage.getTitle());
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        AddBookAsOwnerActivity.mDescription.setText(this.data);
        AddBookAsOwnerActivity.mTitle.setText(this.singleParsed);

    }


}
