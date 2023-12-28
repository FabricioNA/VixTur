package com.example.pei2022;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private void Activity_cartoes_postais(){
        Intent intent = new Intent(MainActivity.this, Cartoes_postais.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Objects.requireNonNull(getSupportActionBar()).hide();
        getWindow().setStatusBarColor(Color.BLACK);


        Button cartoes_postais = findViewById(R.id.cartoes_postais);

        cartoes_postais.setBackgroundResource(R.color.black);

        cartoes_postais.setOnClickListener(view -> new Handler().postDelayed(this::Activity_cartoes_postais, 100));

    }
}