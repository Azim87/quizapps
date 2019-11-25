package com.kubatov.quizapp.data.QuizRepository.local.model;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.kubatov.quizapp.data.QuizRepository.local.room.converters.QuestionConverter;
import com.kubatov.quizapp.data.QuizRepository.local.room.converters.TimestampConverter;
import com.kubatov.quizapp.model.Questions;

import java.util.Date;
import java.util.List;

@Entity(tableName = "quiz_result")
public class QuizResult {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "questions")
    @TypeConverters({QuestionConverter.class})
    private List<Questions> questions;

    @ColumnInfo(name = "correct_answers")
    private int correctAnswers;

    @ColumnInfo(name = "created_at")
    @TypeConverters({TimestampConverter.class})
    private Date createdAt;

    public QuizResult(int id, List<Questions> questions, int correctAnswers, Date createdAt) {
        this.id = id;
        this.questions = questions;
        this.correctAnswers = correctAnswers;
        this.createdAt = createdAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Questions> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Questions> questions) {
        this.questions = questions;
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
