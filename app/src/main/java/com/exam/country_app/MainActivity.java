package com.exam.country_app;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

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

        EditText search = findViewById(R.id.search);
        search.setOnKeyListener(new View.OnKeyListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    int position = getTargetPosition(search.getText().toString());
                    recyclerView.scrollToPosition(position);
                    return true;
                }
                return false;
            }
        });

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private int getTargetPosition(String target) {
        List<Country> countryList = Arrays.asList(adapter.countries);
        for (int i = 0; i < countryList.size(); i++) {
                if(countryList.get(i).getName().contains(target))
                    return i;
        }
        return 0;
    }

}