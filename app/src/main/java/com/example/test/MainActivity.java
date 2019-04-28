package com.example.test;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.widget.EditText;


import com.example.test.Data.Country;
import com.example.test.Data.DataAdapter;
import com.example.test.Data.OnListItemClickListener;
import com.google.gson.Gson;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, OnListItemClickListener {
    ArrayList<Country> countries;
    RecyclerView countryList;
    DataAdapter countryAdapter;
    Toolbar toolbar;
    DrawerLayout drawerLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawer_layout);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerLayout = findViewById(R.id.drawer_layoutas);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle
                (this, drawerLayout, toolbar, R.string.all_countries, R.string.visited_countries);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        countryList = findViewById(R.id.recyclerView);
        countryList.hasFixedSize();
        countryList.setLayoutManager(new LinearLayoutManager(this));
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        try {
            HttpResponse<JsonNode> response = Unirest.get("https://restcountries-v1.p.rapidapi.com/region/europe")
                    .header("X-RapidAPI-Host", "restcountries-v1.p.rapidapi.com")
                    .header("X-RapidAPI-Key", "4ca0bd6408mshe32110c34e6da9cp11c795jsna4dc6ed2fd36")
                    .asJson();
            Gson gson = new Gson();
            Country[] countriesArray = (Country[]) gson.fromJson(String.valueOf(response.getBody()), Country[].class);
            countries = new ArrayList<>(Arrays.asList(countriesArray));
            countryAdapter = new DataAdapter(countries, this);
            countryList.setAdapter(countryAdapter);


        } catch (UnirestException e) {
            e.printStackTrace();
        }
        EditText editText = findViewById(R.id.searchText);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());
            }
        });
    }

    private void filter(String text) {
        ArrayList<Country> filteredList = new ArrayList<>();

        for (Country country : countries) {
            if (country.getName().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(country);

            }
        }

        countryAdapter.filterList(filteredList);
    }

    @Override
    public void onListItemClick(int clickedItemIndex) {
        Intent intent = new Intent(MainActivity.this, CountryActivity.class);
        intent.putExtra("country", (Serializable) countryAdapter.getCountries().get(clickedItemIndex));
        startActivity(intent);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.visitedCountries:
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new VisitedCountriesFragment()).commit();
                break;
            case R.id.wishlist:
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new WishlistedCountriesFragment()).commit();
                break;
            default:


        }
        DrawerLayout drawer = findViewById(R.id.drawer_layoutas);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}
