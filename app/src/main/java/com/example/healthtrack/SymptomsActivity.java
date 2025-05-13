package com.example.healthtrack;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.HashMap;
import java.util.Map;
import androidx.appcompat.app.AppCompatActivity;

public class SymptomsActivity extends AppCompatActivity {
    private ImageButton homeButton;
    private CheckBox fever, cough, headache, fatigue, nausea, soreThroat;
    private SeekBar feverBar, coughBar, headacheBar, fatigueBar, nauseaBar, throatBar;
    private TextView feverLevels, coughLevels, headacheLevels, fatigueLevels, nauseaLevels, throatLevels;
    private TextView feverLabels, coughLabels, headacheLabels, fatigueLabels, nauseaLabels, throatLabels;
    private Button saveButton;
    private FirebaseUser currentUser;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_symptoms);

        homeButton = findViewById(R.id.homeButton);
        saveButton = findViewById(R.id.saveButton);

        homeButton.setOnClickListener(v -> {
            Intent intent = new Intent(SymptomsActivity.this, HomeActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        });

        fever = findViewById(R.id.fever);
        cough = findViewById(R.id.cough);
        headache = findViewById(R.id.headache);
        fatigue = findViewById(R.id.fatigue);
        nausea = findViewById(R.id.nausea);
        soreThroat = findViewById(R.id.soreThroat);

        feverBar = findViewById(R.id.feverSeverityBar);
        coughBar = findViewById(R.id.coughSeverityBar);
        headacheBar = findViewById(R.id.headacheSeverityBar);
        fatigueBar = findViewById(R.id.fatigueSeverityBar);
        nauseaBar = findViewById(R.id.nauseaSeverityBar);
        throatBar = findViewById(R.id.throatSeverityBar);

        feverLevels = findViewById(R.id.feverSeverityLevels);
        coughLevels = findViewById(R.id.coughSeverityLevels);
        headacheLevels = findViewById(R.id.headacheSeverityLevels);
        fatigueLevels = findViewById(R.id.fatigueSeverityLevels);
        nauseaLevels = findViewById(R.id.nauseaSeverityLevels);
        throatLevels = findViewById(R.id.throatSeverityLevels);

        feverLabels = findViewById(R.id.fseverityLabels);
        coughLabels = findViewById(R.id.cseverityLabels);
        headacheLabels = findViewById(R.id.hseverityLabels);
        fatigueLabels = findViewById(R.id.faseverityLabels);
        nauseaLabels = findViewById(R.id.nseverityLabels);
        throatLabels = findViewById(R.id.tseverityLabels);

        // hide symptom bar, levels, labels initially when no symptom is checked
        hideSeverity(feverBar, feverLevels, feverLabels);
        hideSeverity(coughBar, coughLevels, coughLabels);
        hideSeverity(headacheBar, headacheLevels, headacheLabels);
        hideSeverity(fatigueBar, fatigueLevels, fatigueLabels);
        hideSeverity(nauseaBar, nauseaLevels, nauseaLabels);
        hideSeverity(throatBar, throatLevels, throatLabels);

        // check if the symptom is checked and show the corresponding level bars
        fever.setOnCheckedChangeListener((buttonView, isChecked) ->
                showSeverity(isChecked, feverBar, feverLevels, feverLabels));

        cough.setOnCheckedChangeListener((buttonView, isChecked) ->
                showSeverity(isChecked, coughBar, coughLevels, coughLabels));

        headache.setOnCheckedChangeListener((buttonView, isChecked) ->
                showSeverity(isChecked, headacheBar, headacheLevels, headacheLabels));

        fatigue.setOnCheckedChangeListener((buttonView, isChecked) ->
                showSeverity(isChecked, fatigueBar, fatigueLevels, fatigueLabels));

        nausea.setOnCheckedChangeListener((buttonView, isChecked) ->
                showSeverity(isChecked, nauseaBar, nauseaLevels, nauseaLabels));

        soreThroat.setOnCheckedChangeListener((buttonView, isChecked) ->
                showSeverity(isChecked, throatBar, throatLevels, throatLabels));

        currentUser = FirebaseAuth.getInstance().getCurrentUser();
        databaseReference = FirebaseDatabase.getInstance().getReference("symptomReports");

        saveButton.setOnClickListener(v -> {
            if (currentUser != null) {
                String userId = currentUser.getUid();

                //map to hold symptom data
                Map<String, Object> symptomData = new HashMap<>();

                if (fever.isChecked()) {
                    symptomData.put("fever", feverBar.getProgress() + 1);
                } else if (cough.isChecked()) {
                    symptomData.put("cough", coughBar.getProgress() + 1);
                } else if (headache.isChecked()) {
                    symptomData.put("headache", headacheBar.getProgress() + 1);
                } else if (fatigue.isChecked()) {
                    symptomData.put("fatigue", fatigueBar.getProgress() + 1);
                } else if (nausea.isChecked()) {
                    symptomData.put("nausea", nauseaBar.getProgress() + 1);
                } else if (soreThroat.isChecked()) {
                    symptomData.put("soreThroat", throatBar.getProgress() + 1);
                }

                if (symptomData.isEmpty()){
                    symptomData.put("noSymptoms", true);
                }

                symptomData.put("timestamp", System.currentTimeMillis());

                databaseReference.child(userId).child("symptoms").child(String.valueOf(System.currentTimeMillis())).setValue(symptomData)
                        .addOnSuccessListener(aVoid -> Toast.makeText(SymptomsActivity.this, "Symptoms saved.", Toast.LENGTH_SHORT).show())
                        .addOnFailureListener(e -> Toast.makeText(SymptomsActivity.this, "Failed to save.", Toast.LENGTH_SHORT).show());
            } else {
                Toast.makeText(SymptomsActivity.this, "Error.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showSeverity(boolean show, SeekBar bar, TextView levels, TextView labels) {
        if (show) {
            bar.setVisibility(View.VISIBLE);
            levels.setVisibility(View.VISIBLE);
            labels.setVisibility(View.VISIBLE);
        } else {
            bar.setVisibility(View.GONE);
            levels.setVisibility(View.GONE);
            labels.setVisibility(View.GONE);
        }
    }

    private void hideSeverity(SeekBar bar, TextView levels, TextView labels) {
        bar.setVisibility(View.GONE);
        levels.setVisibility(View.GONE);
        labels.setVisibility(View.GONE);
    }
}
