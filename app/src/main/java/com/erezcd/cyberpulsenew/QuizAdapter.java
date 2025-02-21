package com.erezcd.cyberpulsenew;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class QuizAdapter extends RecyclerView.Adapter<QuizAdapter.QuizViewHolder> {
    private List<Question> questionList;
    private final Handler handler = new Handler();
    private RecyclerView recyclerView;

    public QuizAdapter(List<Question> questionList, RecyclerView recyclerView) {
        this.questionList = questionList;
        this.recyclerView = recyclerView;
    }

    @NonNull
    @Override
    public QuizViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
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

        // Clear previous selections and hide feedback
        holder.answerGroup.clearCheck();
        if (holder.feedback != null) {
            holder.feedback.setVisibility(View.GONE);
        }

        // Handle answer selection and next button click
        holder.nextButton.setOnClickListener(v -> {
            int selectedId = holder.answerGroup.getCheckedRadioButtonId();
            if (selectedId == -1) {
                if (holder.feedback != null) {
                    holder.feedback.setText("⚠️ Please select an answer.");
                    holder.feedback.setTextColor(0xFFFFA500);
                    holder.feedback.setVisibility(View.VISIBLE);
                }
                return;
            }

            int selectedIndex = -1;
            if (selectedId == holder.option1.getId()) selectedIndex = 1;
            if (selectedId == holder.option2.getId()) selectedIndex = 2;
            if (selectedId == holder.option3.getId()) selectedIndex = 3;
            if (selectedId == holder.option4.getId()) selectedIndex = 4;

            if (selectedIndex == question.getCorrectAnswerIndex()) {
                if (holder.feedback != null) {
                    holder.feedback.setText("✅ Correct!");
                    holder.feedback.setTextColor(0xFF4CAF50);
                    holder.feedback.setVisibility(View.VISIBLE);
                }
            } else {
                if (holder.feedback != null) {
                    holder.feedback.setText("❌ Incorrect!");
                    holder.feedback.setTextColor(0xFFFF0000);
                    holder.feedback.setVisibility(View.VISIBLE);
                }
            }

            // Delay before moving to the next question
            handler.postDelayed(() -> moveToNext(holder.getAdapterPosition()), 1500);
        });
    }

    private void moveToNext(int position) {
        if (position < questionList.size() - 1) {
            recyclerView.smoothScrollToPosition(position + 1);
        } else {
            Toast.makeText(recyclerView.getContext(), "Quiz Completed!", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public int getItemCount() {
        return questionList.size();
    }

    public static class QuizViewHolder extends RecyclerView.ViewHolder {
        TextView questionText, feedback;
        RadioGroup answerGroup;
        RadioButton option1, option2, option3, option4;
        Button nextButton;

        public QuizViewHolder(@NonNull View itemView) {
            super(itemView);
            questionText = itemView.findViewById(R.id.questionText);
            feedback = itemView.findViewById(R.id.feedback); // Ensure this exists in the XML
            answerGroup = itemView.findViewById(R.id.answerGroup);
            option1 = itemView.findViewById(R.id.option1);
            option2 = itemView.findViewById(R.id.option2);
            option3 = itemView.findViewById(R.id.option3);
            option4 = itemView.findViewById(R.id.option4);
            nextButton = itemView.findViewById(R.id.nextButton);
        }
    }
}
