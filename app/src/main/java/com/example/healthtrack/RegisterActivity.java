package com.example.healthtrack;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

    EditText firstName, lastName, email, password;
    Button registerBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        firstName = findViewById(R.id.firstName);
        lastName = findViewById(R.id.lastName);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        registerBtn = findViewById(R.id.registerBtn);

        registerBtn.setOnClickListener(v -> {
            // check if all fields are entered
            if (firstName.getText().toString().isEmpty() || lastName.getText().toString().isEmpty() || email.getText().toString().isEmpty() || password.getText().toString().isEmpty()) {
                Toast.makeText(this, "All fields are required.", Toast.LENGTH_SHORT).show();
            } else if (firstName.getText().toString().length() < 2) {
                Toast.makeText(this, "First name must be more than 1 character.", Toast.LENGTH_SHORT).show();
            } else if (lastName.getText().toString().length() < 2) {
                Toast.makeText(this, "Last name must be more than 1 character.", Toast.LENGTH_SHORT).show();
            } else if (password.getText().toString().length() < 6) {
                Toast.makeText(this, "Password must be more than 6 characters.", Toast.LENGTH_SHORT).show();
            } else if (!Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches()) {
                Toast.makeText(this, "Please enter a valid email address.", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Registered!", Toast.LENGTH_SHORT).show();
                // return to login screen
                Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
