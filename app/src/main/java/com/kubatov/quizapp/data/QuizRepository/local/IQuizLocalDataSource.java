package com.kubatov.quizapp.data.QuizRepository.local;

import androidx.lifecycle.LiveData;

import com.kubatov.quizapp.data.QuizRepository.local.model.QuizResult;
import com.kubatov.quizapp.model.ShortQuizResult;

import java.util.List;

public interface IQuizLocalDataSource {

    public QuizResult getQuizResult(int id);

    public int saveQuizResult(QuizResult quizResult);

    public LiveData<List<QuizResult>> getAll();

    public LiveData<List<ShortQuizResult>> getAllShort();
}
