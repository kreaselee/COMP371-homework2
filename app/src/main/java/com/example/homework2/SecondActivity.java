package com.example.homework2;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

public class SecondActivity extends AppCompatActivity {

    private EditText editText_name;
    private EditText editText_to;
    private EditText editText_from;
    private Switch switch_highPoint;
    private Button button_search;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        editText_name = findViewById(R.id.editText_name);
        editText_to = findViewById(R.id.editText_to);
        editText_from = findViewById(R.id.editText_from);
        switch_highPoint = findViewById(R.id.switch_highPoint);
        button_search = findViewById(R.id.button_search);

    }
}
