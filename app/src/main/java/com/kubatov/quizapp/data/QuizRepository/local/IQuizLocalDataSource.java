package com.kubatov.quizapp.data.QuizRepository.local;

import androidx.lifecycle.LiveData;

import com.kubatov.quizapp.data.QuizRepository.local.model.QuizResult;
import com.kubatov.quizapp.model.Questions;
import com.kubatov.quizapp.model.ShortQuizResult;

import java.util.List;

public interface IQuizLocalDataSource {

    QuizResult getQuizResult();

    long saveQuizResult(QuizResult quizResult);

    LiveData<List<Questions>> getAll(List<Questions> questions);

    LiveData<List<ShortQuizResult>> getAllShort();
}
