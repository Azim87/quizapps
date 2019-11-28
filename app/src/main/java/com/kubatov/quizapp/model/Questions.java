package com.kubatov.quizapp.model;

import androidx.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Questions {

    private String category;

    private String type;

    private String difficulty;

    private String question;

    @SerializedName("correct_answer")
    private String correctAnswers;

    @SerializedName("incorrect_answers")
    private List<String> incorrectAnswers;

    private List<String> answers;

    @Nullable
    private Integer selectedAnswerPosition = null;

    public Questions(String category, String type, String difficulty, String question, String correctAnswers, List<String> incorrectAnswers, int selectedAnswerPosition) {
        this.category = category;
        this.type = type;
        this.difficulty = difficulty;
        this.question = question;
        this.correctAnswers = correctAnswers;
        this.incorrectAnswers = incorrectAnswers;
        this.selectedAnswerPosition = selectedAnswerPosition;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public String getCategory() {
        return category;
    }

    public String getType() {
        return type;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public String getQuestion() {
        return question;
    }

    public String getCorrectAnswers() {
        return correctAnswers;
    }

    public List<String> getIncorrectAnswers() {
        return incorrectAnswers;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setCorrectAnswers(String correctAnswers) {
        this.correctAnswers = correctAnswers;
    }

    public void setIncorrectAnswers(List<String> incorrectAnswers) {
        this.incorrectAnswers = incorrectAnswers;
    }

    public void setAnswers(List<String> answers) {
        this.answers = answers;
    }

    @Nullable
    public Integer getSelectedAnswerPosition() {
        return selectedAnswerPosition;
    }

    public void setSelectedAnswerPosition(Integer selectedAnswerPosition) {
        this.selectedAnswerPosition = selectedAnswerPosition;
    }

    @Override
    public String toString() {
        return category + " " + question + " " + type + " " + difficulty;
    }
}
