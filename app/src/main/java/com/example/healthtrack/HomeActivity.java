package com.example.healthtrack;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class HomeActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    private Button logoutButton, vitalsButton, symptomsButton, reportButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        firebaseAuth = FirebaseAuth.getInstance();

        vitalsButton = findViewById(R.id.vitalsButton);
        symptomsButton = findViewById(R.id.symptomsButton);
        reportButton = findViewById(R.id.reportButton);
        logoutButton = findViewById(R.id.logoutButton);

        vitalsButton.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, VitalsActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK); // Prevent back button return
            startActivity(intent);
            finish();
        });

        symptomsButton.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, SymptomsActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK); // Prevent back button return
            startActivity(intent);
            finish();
        });

        reportButton.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, ReportActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK); // Prevent back button return
            startActivity(intent);
            finish();
        });

        logoutButton.setOnClickListener(v -> {
            firebaseAuth.signOut();
            Toast.makeText(HomeActivity.this, "Logged out", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(HomeActivity.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK); // Prevent back button return
            startActivity(intent);
            finish();
        });
    }
}
