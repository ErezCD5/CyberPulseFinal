package com.erezcd.cyberpulsenew;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class PasswordCheckerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_checker);

        // UI elements
        EditText passwordInput = findViewById(R.id.passwordInput);
        Button checkButton = findViewById(R.id.checkButton);
        TextView passwordStrength = findViewById(R.id.passwordStrength);
        TextView passwordFeedback = findViewById(R.id.passwordFeedback);
        Button backToMain = findViewById(R.id.backToMainButton);

        // Check password strength when button is clicked
        checkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String password = passwordInput.getText().toString();
                int strengthScore = evaluatePassword(password);
                updateStrengthIndicator(strengthScore, passwordStrength, passwordFeedback);
            }
        });

        // Return to Main Menu
        backToMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PasswordCheckerActivity.this, MainActivity.class));
                finish(); // Closes this activity
            }
        });
    }

    // Evaluates password strength based on different criteria
    private int evaluatePassword(String password) {
        int score = 0;

        if (password.length() >= 6) score++; // Minimum Length
        if (password.length() >= 10) score++; // Bonus for longer passwords
        if (password.matches(".*[A-Z].*")) score++; // Contains uppercase letter
        if (password.matches(".*[a-z].*")) score++; // Contains lowercase letter
        if (password.matches(".*[0-9].*")) score++; // Contains a number
        if (password.matches(".*[!@#$%^&*(),.?\":{}|<>].*")) score++; // Contains a special character

        return score;
    }

    // Updates the UI with the password strength and feedback
    private void updateStrengthIndicator(int score, TextView passwordStrength, TextView passwordFeedback) {
        String feedback = "";

        if (score <= 2) {
            passwordStrength.setText("Strength: Weak");
            passwordStrength.setTextColor(Color.RED);
            feedback = "❌ Your password is too weak!\n✅ Try adding:\n• At least 10 characters\n• A mix of uppercase & lowercase\n• Numbers & special symbols";
        } else if (score <= 4) {
            passwordStrength.setText("Strength: Medium");
            passwordStrength.setTextColor(Color.rgb(255, 140, 0)); // Dark Orange
            feedback = "⚠️ Decent password, but could be stronger.\n✅ Improve by:\n• Adding more characters\n• Including numbers or symbols";
        } else {
            passwordStrength.setText("Strength: Strong");
            passwordStrength.setTextColor(Color.GREEN);
            feedback = "✅ Great password! Your security is strong.";
        }

        passwordFeedback.setText(feedback);
        passwordFeedback.setVisibility(View.VISIBLE);
    }
}
