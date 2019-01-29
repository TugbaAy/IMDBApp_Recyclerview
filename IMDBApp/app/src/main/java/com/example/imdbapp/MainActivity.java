package com.example.imdbapp;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recyclerView;
    static View.OnClickListener myOnClickListener;
    static ArrayList<String> filmName = new ArrayList<>();
    static ArrayList<String> year = new ArrayList<>();
    static ArrayList<String> rate = new ArrayList<>();
    static ArrayList<String> urlMov = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        get_json();
        adapter = new CustomAdapter(MainActivity.this);
        recyclerView.setAdapter(adapter);
    }

    public void get_json(){
        String json;
        try{
            InputStream is =getAssets().open("imdb.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();

            json = new String(buffer,"UTF-8");
            Log.d("esat",json);
            JSONArray array = new JSONArray(json);
            for (int i = 0; i<array.length();i++){
                JSONObject obj = array.getJSONObject(i);
                filmName.add(obj.getString("name"));
                year.add(obj.getString("year"));
                rate.add(obj.getString("rating"));
                urlMov.add(obj.getString("poster"));
            }
        }
        catch(IOException e){
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
