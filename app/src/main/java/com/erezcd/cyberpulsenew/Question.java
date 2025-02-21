package com.erezcd.cyberpulsenew;

// Represents a quiz question with four options and a correct answer index
public class Question {
    private String questionText;
    private int correctAnswerIndex;
    private String option1, option2, option3, option4;

    // Constructor for initializing a question
    public Question(String questionText, int correctAnswerIndex, String option1, String option2, String option3, String option4) {
        this.questionText = questionText;
        this.correctAnswerIndex = correctAnswerIndex;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
    }

    // Returns the question text
    public String getQuestionText() {
        return questionText;
    }

    // Returns the index of the correct answer (1-based index)
    public int getCorrectAnswerIndex() {
        return correctAnswerIndex;
    }

    // Returns option 1
    public String getOption1() {
        return option1;
    }

    // Returns option 2
    public String getOption2() {
        return option2;
    }

    // Returns option 3
    public String getOption3() {
        return option3;
    }

    // Returns option 4
    public String getOption4() {
        return option4;
    }
}
