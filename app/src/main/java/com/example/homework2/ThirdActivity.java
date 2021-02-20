package com.example.homework2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ThirdActivity extends AppCompatActivity {

    private ArrayList<Beer> beers;
    private TextView textView_results;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        // look up views by id
        textView_results = findViewById(R.id.textView_results);
        recyclerView = findViewById(R.id.recyclerView_results);

        // implement ArrayList
        beers = new ArrayList<>();

        // unpack the intent
        Intent intent = getIntent();
        int numResults = intent.getIntExtra("numResults", 0);
        textView_results.setText("We found " + numResults + " results.");

        for (int i = 0; i < numResults; i++) {
            String name = intent.getStringArrayListExtra("names").get(i);
            String description = intent.getStringArrayListExtra("descriptions").get(i);
            String imageUrl = intent.getStringArrayListExtra("images").get(i);
            String abv = intent.getStringArrayListExtra("abvs").get(i);
            String firstBrewed = intent.getStringArrayListExtra("brewedDates").get(i);
            String foodPairings = intent.getStringArrayListExtra("foodPairingsList").get(i);
            String tips = intent.getStringArrayListExtra("tipsList").get(i);
            Beer beer = new Beer(name, description, imageUrl, abv, firstBrewed, foodPairings, tips);
            beers.add(beer);
        }

        // create adapter to pass in the data
        BeerAdapter adapter = new BeerAdapter(beers);
        // attach the adapter to the recycler view to populate
        recyclerView.setAdapter(adapter);
        // layout manager
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }
}
