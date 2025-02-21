package com.erezcd.cyberpulsenew;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class QuizAdapter extends RecyclerView.Adapter<QuizAdapter.QuizViewHolder> {
    private List<Question> questionList;

    // Constructor to pass the list of quiz questions
    public QuizAdapter(List<Question> questionList) {
        this.questionList = questionList;
    }

    @NonNull
    @Override
    public QuizViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the layout for each quiz question
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.quiz_question, parent, false);
        return new QuizViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QuizViewHolder holder, int position) {
        Question question = questionList.get(position);
        holder.questionText.setText(question.getQuestionText());

        // Set answer options
        holder.option1.setText(question.getOption1());
        holder.option2.setText(question.getOption2());
        holder.option3.setText(question.getOption3());
        holder.option4.setText(question.getOption4());

        // Handle "Next" button click to check the selected answer
        holder.nextButton.setOnClickListener(v -> {
            int selectedId = holder.answerGroup.getCheckedRadioButtonId();
            int correctAnswer = question.getCorrectAnswerIndex();

            // No selection
            if (selectedId == -1) {
                holder.feedback.setText("⚠️ Please select an answer.");
                holder.feedback.setTextColor(0xFFFFA500); // Orange
                return;
            }

            // Determine which answer was selected
            int selectedIndex = -1;
            if (selectedId == holder.option1.getId()) selectedIndex = 1;
            if (selectedId == holder.option2.getId()) selectedIndex = 2;
            if (selectedId == holder.option3.getId()) selectedIndex = 3;
            if (selectedId == holder.option4.getId()) selectedIndex = 4;

            // Check if the answer is correct
            if (selectedIndex == correctAnswer) {
                holder.feedback.setText("✅ Correct!");
                holder.feedback.setTextColor(0xFF4CAF50); // Green
            } else {
                holder.feedback.setText("❌ Incorrect!");
                holder.feedback.setTextColor(0xFFFF0000); // Red
            }
            holder.feedback.setVisibility(View.VISIBLE);
        });
    }

    @Override
    public int getItemCount() {
        return questionList.size();
    }

    // ViewHolder class to hold UI elements for each quiz question
    public static class QuizViewHolder extends RecyclerView.ViewHolder {
        TextView questionText, feedback;
        RadioGroup answerGroup;
        RadioButton option1, option2, option3, option4;
        Button nextButton;

        public QuizViewHolder(@NonNull View itemView) {
            super(itemView);

            // Initialize UI components
            questionText = itemView.findViewById(R.id.questionText);
            feedback = itemView.findViewById(R.id.feedback);
            answerGroup = itemView.findViewById(R.id.answerGroup);
            option1 = itemView.findViewById(R.id.option1);
            option2 = itemView.findViewById(R.id.option2);
            option3 = itemView.findViewById(R.id.option3);
            option4 = itemView.findViewById(R.id.option4);
            nextButton = itemView.findViewById(R.id.nextButton);
        }
    }
}
