package com.example.healthtrack;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.app.AlertDialog;

import androidx.appcompat.app.AppCompatActivity;

public class VitalsActivity extends AppCompatActivity {
    private Button saveButton;
    private ImageButton homeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vitals);

        homeButton = findViewById(R.id.homeButton);
        saveButton = findViewById(R.id.saveButton);

        homeButton.setOnClickListener(v -> {
            Intent intent = new Intent(VitalsActivity.this, HomeActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        });

        saveButton.setOnClickListener(v -> {
            new AlertDialog.Builder(VitalsActivity.this)
                    .setTitle("Log Your Symptoms")
                    .setMessage("Would you like to log symptoms?")
                    .setPositiveButton("Yes", (dialog, which) -> {
                        startActivity(new Intent(this, SymptomsActivity.class));
                    })
                    .setNegativeButton("No", (dialog, which) -> {
                        startActivity(new Intent(this, HomeActivity.class));
                    })
                    .setCancelable(true)
                    .show();
        });
    }
}

