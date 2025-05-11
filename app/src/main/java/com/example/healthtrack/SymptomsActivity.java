package com.example.healthtrack;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class SymptomsActivity extends AppCompatActivity {
    private Button homeButton, reportButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_symptoms);

        homeButton = findViewById(R.id.homeButton);
        reportButton = findViewById(R.id.reportButton);

        homeButton.setOnClickListener(v -> {
            Intent intent = new Intent(SymptomsActivity.this, HomeActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK); // Prevent back button return
            startActivity(intent);
            finish();
        });

        reportButton.setOnClickListener(v -> {
            Intent intent = new Intent(SymptomsActivity.this, ReportActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK); // Prevent back button return
            startActivity(intent);
            finish();
        });

    }
}