package com.example.swarangigaurkar.quizzup;

import android.app.Application;

public class GlobalClass extends Application {
    private String Category1;
    private String Difficulty1;
    private int imagev;
    private int QuestionCount1;

    public void setCategory(String category) {
        Category1 = category;
    }

    public void setDifficulty(String difficulty) {
        Difficulty1 = difficulty;
    }

    public void setQuestionCount(int questionCount) {
        QuestionCount1 = questionCount;
    }

    public String getCategory() {
        return Category1;
    }

    public String getDifficulty() {
        return Difficulty1;
    }
}

