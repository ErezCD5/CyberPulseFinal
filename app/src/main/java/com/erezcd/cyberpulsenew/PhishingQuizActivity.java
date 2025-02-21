package com.erezcd.cyberpulsenew;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class PhishingQuizActivity extends AppCompatActivity {
    private RecyclerView quizRecyclerView;
    private QuizAdapter quizAdapter;
    private List<Question> questionList;
    private Button backToMainButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phishing_quiz);

        // Initialize UI elements
        quizRecyclerView = findViewById(R.id.quizRecyclerView);
        backToMainButton = findViewById(R.id.backToMainButton);

        // Load quiz questions
        loadQuestions();

        // Set up RecyclerView for one question at a time
        quizRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        quizAdapter = new QuizAdapter(questionList, quizRecyclerView);
        quizRecyclerView.setAdapter(quizAdapter);

        // Back to main menu button
        backToMainButton.setOnClickListener(v -> {
            startActivity(new Intent(PhishingQuizActivity.this, MainActivity.class));
            finish();
        });
    }

    private void loadQuestions() {
        questionList = new ArrayList<>();
        questionList.add(new Question("What is 2FA?", 4, "An Anti-Virus", "A Firewall", "A Phishing Link", "2-Factor Authentication"));
        questionList.add(new Question("What should you check before clicking a link?", 2, "The font of the email", "The URL domain", "The email subject", "The time it was sent"));
        questionList.add(new Question("Which is a sign of phishing?", 1, "Urgent request for personal info", "Email from a known friend", "Google search result", "None of the above"));
    }
}
