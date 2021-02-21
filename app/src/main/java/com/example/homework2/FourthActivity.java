package com.example.homework2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;

public class FourthActivity extends AppCompatActivity {

    private TextView textView_name;
    private ImageView imageView_imageUrl;
    private TextView textView_abv;
    private TextView textView_firstBrewed;
    private TextView textView_description;
    private TextView textView_foodPairings;
    private TextView textView_tips;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth);

        textView_name = findViewById(R.id.textView_name);
        imageView_imageUrl = findViewById(R.id.imageView_imageUrl);
        textView_abv = findViewById(R.id.textView_abv);
        textView_firstBrewed = findViewById(R.id.textView_firstBrewed);
        textView_description = findViewById(R.id.textView_description);
        textView_foodPairings = findViewById(R.id.textView_foodPairings);
        textView_tips = findViewById(R.id.textView_tips);

        // unpack the intent
        Intent intent = getIntent();
        textView_name.setText(intent.getStringExtra("name"));
        textView_abv.setText("ABV: " + intent.getStringExtra("abv"));
        textView_firstBrewed.setText("First Brewed: " + intent.getStringExtra("firstBrewed"));
        textView_description.setText("Description: " + intent.getStringExtra("description"));

        JSONArray foodPairings = null;
        String constructed = "";
        try {
            foodPairings = new JSONArray(intent.getStringExtra("foodPairings"));
            for (int i = 0; i < foodPairings.length(); i++){
                if (i < foodPairings.length()-1) {
                    constructed+=foodPairings.getString(i)+", ";
                }
                else {
                    constructed+=foodPairings.getString(i);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        textView_foodPairings.setText("Food Pairings: " + constructed);
        textView_tips.setText("Brewer's Tips: " + intent.getStringExtra("tips"));
        Picasso.get().load(intent.getStringExtra("imageUrl")).into(imageView_imageUrl);
    }
}
