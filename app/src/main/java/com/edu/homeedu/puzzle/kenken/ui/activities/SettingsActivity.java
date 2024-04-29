package com.edu.homeedu.puzzle.kenken.ui.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

import com.edu.homeedu.puzzle.kenken.R;

public class SettingsActivity extends AppCompatActivity {
    private Button applyButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        initFields();
        setupListeners();
    }

    private void initFields() {
        applyButton = findViewById(R.id.settings_apply_button);
    }

    private void setupListeners() {
        applyButton.setOnClickListener(v -> {
            setResult(RESULT_OK);
            finish();
        });
    }
}