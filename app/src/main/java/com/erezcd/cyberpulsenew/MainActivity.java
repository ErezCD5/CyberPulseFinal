package com.erezcd.cyberpulsenew;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Buttons for navigation
        Button passwordCheckerButton = findViewById(R.id.passwordCheckerButton);
        Button phishingQuizButton = findViewById(R.id.phishingQuizButton);
        Button exitButton = findViewById(R.id.exitButton);

        // Open Password Checker
        passwordCheckerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, PasswordCheckerActivity.class));
            }
        });

        // Open Phishing Quiz
        phishingQuizButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, PhishingQuizActivity.class));
            }
        });

        // Exit the App
        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishAffinity(); // Closes all activities and exits app
            }
        });
    }
}
