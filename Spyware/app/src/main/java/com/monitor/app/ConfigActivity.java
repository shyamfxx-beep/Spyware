package com.monitor.app;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class ConfigActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        SharedPreferences prefs = getSharedPreferences("AppPrefs", MODE_PRIVATE);
        if (prefs.getBoolean("is_registered", false)) {
            startActivity(new Intent(this, MainActivity.class));
            finish();
            return;
        }

        setContentView(R.layout.activity_config);

        EditText nameInput = findViewById(R.id.nameInput);
        Button saveBtn = findViewById(R.id.saveBtn);

        saveBtn.setOnClickListener(v -> {
            String targetName = nameInput.getText().toString().trim();
            if (!targetName.isEmpty()) {
                prefs.edit().putString("target_name", targetName)
                            .putBoolean("is_registered", true)
                            .apply();
                startActivity(new Intent(ConfigActivity.this, MainActivity.class));
                finish();
            }
        });
    }
}