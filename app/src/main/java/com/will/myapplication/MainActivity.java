package com.will.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private WaterCupView waterCupView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        waterCupView = findViewById(R.id.water);
        waterCupView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                waterCupView.startAnimater();
            }
        });
    }
}
