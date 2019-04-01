package com.example.ibookit.Functionality;

import android.os.AsyncTask;
import android.util.Log;

import com.example.ibookit.View.AddBookOwnerActivity;

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

public class FetchUrlData extends AsyncTask<String, Void, Void> {
    private String data = "";
    private String description = "";
    private String title = "";
    private String author = "";
    @Override
    protected Void doInBackground(String... urlString) {
        BufferedReader reader = null;
        HttpsURLConnection con = null;

        try {

            Log.d("myslelf-readUrl", "reading url started");
            URL url = new URL(urlString[0]);
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
                data = data + line;
            }

            JSONObject JO = new JSONObject(data);
            JSONArray items = JO.getJSONArray("items");
            JSONObject basicInfos = (JSONObject) items.get(0);
            JSONObject volumeInfo = basicInfos.getJSONObject("volumeInfo");
            JSONArray authors = volumeInfo.getJSONArray("authors");
            title = volumeInfo.getString("title");
            description = volumeInfo.getString("description");
            for (int i =0; i < authors.length();i++){
                author = author+authors.get(i);

            }


        } catch (MalformedURLException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
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
        return null;
    }


    @Override
    protected void onPostExecute(Void aVoid){
        super.onPostExecute(aVoid);
        if (this.description != "") {
            AddBookOwnerActivity.mDescription.setText(this.description);
        }else{
            AddBookOwnerActivity.mDescription.setText("Not Found");
        }
        if (this.title != "") {
            AddBookOwnerActivity.mTitle.setText(this.title);
        }else{
            AddBookOwnerActivity.mTitle.setText("Not Found");
        }if (this.author != "") {
            AddBookOwnerActivity.mAuthor.setText(this.author);
        }else{
            AddBookOwnerActivity.mAuthor.setText("Not Found");
        }

    }


}
