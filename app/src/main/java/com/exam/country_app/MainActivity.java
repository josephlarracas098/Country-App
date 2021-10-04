package com.exam.country_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

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
            public void onLoadCountryAPI(Country[] countries) {
                recyclerView = findViewById(R.id.rv_country);
                recyclerView.setNestedScrollingEnabled(false);
                recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                adapter = new CountryRecyclerAdapter(MainActivity.this, countries);
                recyclerView.setAdapter(adapter);
            }
        });
    }

}