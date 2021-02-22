package com.example.homework2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {

    private Button button_home;
    private ImageView imageView_home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button_home = findViewById(R.id.button_home);
        imageView_home = findViewById(R.id.imageView_home);

        Picasso.get().load("file:///android_asset/beer.png").into(imageView_home);

        button_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchNextActivity(v);
            }
        });

    }

    public void launchNextActivity(View v){
        Intent intent = new Intent(this, SecondActivity.class);
        startActivityForResult(intent, 1);
    }
}