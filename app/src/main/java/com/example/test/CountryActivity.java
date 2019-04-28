package com.example.test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;


import com.example.test.Data.Country;
import com.example.test.R;
import com.google.firebase.database.FirebaseDatabase;
import com.google.gson.Gson;

public class CountryActivity extends AppCompatActivity {
    TextView name;
    TextView topLevelDomain;
    TextView alpha2Code;
    TextView alpha3Code;
    TextView capital;
    TextView altSpellings;
    TextView region;
    TextView subregion;
    TextView population;
    TextView timezones;
    TextView currencies;
    TextView languages;
    FirebaseDatabase database;
    Country country;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country);

        Bundle bundle = getIntent().getExtras();

       country = (Country) bundle.getSerializable("country");
        database = FirebaseDatabase.getInstance();
        name = findViewById(R.id.name);
        topLevelDomain = findViewById(R.id.domain);
        alpha2Code = findViewById(R.id.code2);
        alpha3Code = findViewById(R.id.code3);
        capital = findViewById(R.id.capital);
        altSpellings = findViewById(R.id.altspellings);
        region = findViewById(R.id.region);
        subregion = findViewById(R.id.subregion);
        population = findViewById(R.id.population);
        timezones = findViewById(R.id.timezones);
        currencies = findViewById(R.id.currencies);
        languages = findViewById(R.id.languages);


        name.setText(country.getName());
        topLevelDomain.setText(country.getTopLevelDomains());
        alpha2Code.setText(country.getAlpha2Code());
        alpha3Code.setText(country.getAlpha3Code());
        capital.setText(country.getCapital());
        altSpellings.setText(country.getSpellings());
        region.setText(country.getRegion());
        subregion.setText(country.getSubregion());
        population.setText(country.getPopulation());
        timezones.setText(country.getTimezonesString());
        currencies.setText(country.getCurrenciesString());
        languages.setText(country.getTimezonesString());


    }

    public void toWishlist(View view) {

        database.getReference("wishlist").push().setValue(country.getName());
    }

    public void visited(View view) {
        Gson gson = new Gson();
        String s = gson.toJson(country);
        database.getReference("visited").push().setValue(country.getName());
    }
}
