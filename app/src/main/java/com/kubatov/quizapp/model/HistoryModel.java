package com.kubatov.quizapp.model;

import androidx.annotation.NonNull;

public class HistoryModel {

    private String category;
    private String difficulty;
    private String time;
    private int answers;

    public HistoryModel(String category, String difficulty, String time, int answers) {
        this.category = category;
        this.difficulty = difficulty;
        this.time = time;
        this.answers = answers;
    }

    public String getCategory() {
        return category;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public String getTime() {
        return time;
    }

    public int getAnswers() {
        return answers;
    }

    @NonNull
    @Override
    public String toString() {
        return category + " " + difficulty + " " + time + " " + answers;
    }
}
