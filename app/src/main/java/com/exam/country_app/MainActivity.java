package com.exam.country_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    CountryRecyclerAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RestAPI restAPI = new RestAPI(MainActivity.this);
        restAPI.loadCountryAPI(new APIListener() {
            @Override
            public void onLoadCountryAPI(String result) {
                Log.d("Result", result);
            }
        });

//        recyclerView = findViewById(R.id.rv_country);
//        recyclerView.setNestedScrollingEnabled(false);
//        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
//        adapter = new CountryRecyclerAdapter(MainActivity.this, announcementDataArr);
//        recyclerView.setAdapter(adapter);


    }

}