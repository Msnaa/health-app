package com.example.healthtrack;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class VitalsActivity extends AppCompatActivity {
    private Button homeButton, symptomsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vitals);

        homeButton = findViewById(R.id.homeButton);
        symptomsButton = findViewById(R.id.symptomsButton);

        homeButton.setOnClickListener(v -> {
            Intent intent = new Intent(VitalsActivity.this, HomeActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK); // Prevent back button return
            startActivity(intent);
            finish();
        });

        symptomsButton.setOnClickListener(v -> {
            Intent intent = new Intent(VitalsActivity.this, SymptomsActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK); // Prevent back button return
            startActivity(intent);
            finish();
        });
    }
}

