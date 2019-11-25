package com.kubatov.quizapp.model;

import java.util.Date;

public class ShortQuizResult {

    private int id;
    private int questionsAmount;
    private int correctAnswers;
    private Date createdAt;

    public ShortQuizResult(int id, int questionsAmount, int correctAnswers, Date createdAt) {
        this.id = id;
        this.questionsAmount = questionsAmount;
        this.correctAnswers = correctAnswers;
        this.createdAt = createdAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuestionsAmount() {
        return questionsAmount;
    }

    public void setQuestionsAmount(int questionsAmount) {
        this.questionsAmount = questionsAmount;
    }

    public int getCorrectAnswers() {
        return correctAnswers;
    }

    public void setCorrectAnswers(int correctAnswers) {
        this.correctAnswers = correctAnswers;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
