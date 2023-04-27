package com.example.proiectandroid;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        //facem legatura cu layout
        setContentView(R.layout.activity_second);
        Intent intent = getIntent();

        Bundle bundle = intent.getExtras();

        //String message = intent.getStringExtra("MESSAGE");
        String message = bundle.getString("MESSAGE");

        TextView text = findViewById(R.id.mesaj2);
        text.setText(getString(R.string.hi) + " "+ message);
    }
}
