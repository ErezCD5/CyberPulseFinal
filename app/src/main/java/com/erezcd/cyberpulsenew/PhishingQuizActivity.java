package com.erezcd.cyberpulsenew;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;

public class PhishingQuizActivity extends AppCompatActivity {
    private TextView questionText, feedback;
    private RadioGroup answerGroup;
    private RadioButton option1, option2, option3, option4;
    private Button nextButton, backToMain;
    private int currentQuestionIndex = 0;
    private List<Question> questionList;
    private final Handler handler = new Handler(); // Handler for delay

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phishing_quiz);

        // Initialize UI elements
        questionText = findViewById(R.id.questionText);
        feedback = findViewById(R.id.feedback);
        answerGroup = findViewById(R.id.answerGroup);
        option1 = findViewById(R.id.option1);
        option2 = findViewById(R.id.option2);
        option3 = findViewById(R.id.option3);
        option4 = findViewById(R.id.option4);
        nextButton = findViewById(R.id.nextButton);
        backToMain = findViewById(R.id.backToMainButton);

        // Load the quiz questions
        loadQuestions();

        // Prevents crash if there are no questions
        if (questionList == null || questionList.isEmpty()) {
            questionText.setText("No questions available.");
            nextButton.setEnabled(false);
        } else {
            displayQuestion();
        }

        // Button for moving to the next question
        nextButton.setOnClickListener(v -> {
            if (answerGroup.getCheckedRadioButtonId() == -1) {
                feedback.setText("⚠️ Please select an answer.");
                feedback.setTextColor(0xFFFFA500); // Orange
                feedback.setVisibility(View.VISIBLE);
                return;
            }

            // Check if the selected answer is correct
            int selectedIndex = getSelectedAnswerIndex();
            if (selectedIndex == questionList.get(currentQuestionIndex).getCorrectAnswerIndex()) {
                feedback.setText("✅ Correct!");
                feedback.setTextColor(0xFF4CAF50); // Green
                feedback.setVisibility(View.VISIBLE);

                // Delay before moving to next question
                handler.postDelayed(this::moveToNextQuestion, 1500); // 1.5s delay
            } else {
                feedback.setText("❌ Incorrect!");
                feedback.setTextColor(0xFFFF0000); // Red
                feedback.setVisibility(View.VISIBLE);
                moveToNextQuestion(); // Move immediately on incorrect answer
            }
        });

        // Button to return to the main menu
        backToMain.setOnClickListener(v -> {
            startActivity(new Intent(PhishingQuizActivity.this, MainActivity.class));
            finish();
        });
    }

    // Loads predefined quiz questions into the list
    private void loadQuestions() {
        questionList = new ArrayList<>();
        questionList.add(new Question("What is 2FA?", 4, "An Anti-Virus", "A Firewall", "A Phishing Link", "2-Factor Authentication"));
        questionList.add(new Question("What should you check before clicking a link?", 2, "The font of the email", "The URL domain", "The email subject", "The time it was sent"));
        questionList.add(new Question("Which is a sign of phishing?", 1, "Urgent request for personal info", "Email from a known friend", "Google search result", "None of the above"));
    }

    // Displays the current question and resets UI elements
    private void displayQuestion() {
        Question currentQuestion = questionList.get(currentQuestionIndex);
        questionText.setText(currentQuestion.getQuestionText());
        option1.setText(currentQuestion.getOption1());
        option2.setText(currentQuestion.getOption2());
        option3.setText(currentQuestion.getOption3());
        option4.setText(currentQuestion.getOption4());
        answerGroup.clearCheck();
        feedback.setVisibility(View.GONE);
    }

    // Retrieves the selected answer's index
    private int getSelectedAnswerIndex() {
        int selectedId = answerGroup.getCheckedRadioButtonId();
        if (selectedId == option1.getId()) return 1;
        if (selectedId == option2.getId()) return 2;
        if (selectedId == option3.getId()) return 3;
        if (selectedId == option4.getId()) return 4;
        return -1; // No selection
    }

    // Moves to the next question or finishes the quiz
    private void moveToNextQuestion() {
        if (currentQuestionIndex < questionList.size() - 1) {
            currentQuestionIndex++;
            displayQuestion();
        } else {
            nextButton.setText("Finish");
            nextButton.setOnClickListener(v1 -> finish());
        }
    }
}
