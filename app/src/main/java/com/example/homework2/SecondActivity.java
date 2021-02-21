package com.example.homework2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class SecondActivity extends AppCompatActivity {

    private static final String api_url="https://api.punkapi.com/v2/beers?";
    private String constructed_url;
    private static AsyncHttpClient client = new AsyncHttpClient();

    private EditText editText_name;
    private EditText editText_from;
    private EditText editText_to;
    private Switch switch_highPoint;
    private Boolean highPointCheck = false;
    private Button button_search;

    private ArrayList<String> names;
    private ArrayList<String> descriptions;
    private ArrayList<String> images;
    private ArrayList<String> abvs;
    private ArrayList<String> brewedDates;
    private ArrayList<String> foodPairingsList;
    private ArrayList<String> tipsList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        // getActionBar().setDisplayHomeAsUpEnabled(false);

        editText_name = findViewById(R.id.editText_name);
        editText_from = findViewById(R.id.editText_from);
        editText_to = findViewById(R.id.editText_to);
        switch_highPoint = findViewById(R.id.switch_highPoint);
        button_search = findViewById(R.id.button_search);

        if (switch_highPoint != null) {
            switch_highPoint.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        highPointCheck = true;
                    }
                    else {
                        highPointCheck = false;
                    }
                }
            });
        }

        button_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchNextActivity(v);
            }
        });

    }

    public void launchNextActivity(View v){
        // get text input and construct url
        // ADD VALIDATIONS/CHECK FOR ERRORS
        constructed_url = api_url;
        // if user has entered a name
        if (!editText_name.getText().toString().trim().matches("")) {
            String name = editText_name.getText().toString().trim();
            constructed_url = constructed_url+"beer_name="+name;
        }
        // if user has entered a start date
        if (!editText_from.getText().toString().trim().matches("")) {
            String from = editText_from.getText().toString().trim();
            constructed_url = constructed_url+"brewed_after="+from;
        }
        // if user has entered an end date
        if (!editText_to.getText().toString().trim().matches("")) {
            String from = editText_to.getText().toString().trim();
            constructed_url = constructed_url+"brewed_before="+from;
        }
        // if high point is checked
        if (highPointCheck) {
            constructed_url = constructed_url+"abv_gt=3.9";
        }

        // set the header because of the api endpoint
        client.addHeader("Accept", "application/json");
        // send a get request to the api url
        client.get(constructed_url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                Log.d("api response", new String(responseBody));
                // System.out.println(constructed_url);

                try {
                    JSONArray jsonArray = new JSONArray(new String(responseBody));

                    names = new ArrayList<>();
                    descriptions = new ArrayList<>();
                    images = new ArrayList<>();
                    abvs = new ArrayList<>();
                    brewedDates = new ArrayList<>();
                    foodPairingsList = new ArrayList<>();
                    tipsList = new ArrayList<>();

                    // for each beer,
                    // add its name, description, and imageUrl into each respective list
                    // to add to intent
                    for (int i = 0; i < jsonArray.length(); i++) {
                        names.add(jsonArray.getJSONObject(i).getString("name"));
                        descriptions.add(jsonArray.getJSONObject(i).getString("description"));
                        images.add(jsonArray.getJSONObject(i).getString("image_url"));
                        abvs.add(jsonArray.getJSONObject(i).getString("abv"));
                        brewedDates.add(jsonArray.getJSONObject(i).getString("first_brewed"));
                        foodPairingsList.add(jsonArray.getJSONObject(i).getJSONArray("food_pairing").toString());
                        tipsList.add(jsonArray.getJSONObject(i).getString("brewers_tips"));
                    }

                    Intent intent = new Intent(SecondActivity.this, ThirdActivity.class);
                    // add info of beers into intent
                    intent.putExtra("numResults", jsonArray.length());
                    intent.putExtra("names", names);
                    intent.putExtra("descriptions", descriptions);
                    intent.putExtra("images", images);
                    intent.putExtra("abvs", abvs);
                    intent.putExtra("brewedDates", brewedDates);
                    intent.putExtra("foodPairingsList", foodPairingsList);
                    intent.putExtra("tipsList", tipsList);
                    startActivity(intent);
                }
                catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.e("api error", new String(responseBody));
            }
        });
    }
}
