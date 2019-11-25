package com.kubatov.quizapp.data.QuizRepository.local;

import androidx.lifecycle.LiveData;

import com.kubatov.quizapp.data.QuizRepository.local.model.QuizResult;
import com.kubatov.quizapp.data.QuizRepository.local.room.dao.HistoryDao;
import com.kubatov.quizapp.model.Questions;
import com.kubatov.quizapp.model.ShortQuizResult;

import java.util.List;

public class QuizLocalDataSource implements IQuizLocalDataSource{
    private HistoryDao mHistoryDao;

    public QuizLocalDataSource(HistoryDao historyDao){
        mHistoryDao = historyDao;
    }

    @Override
    public QuizResult getQuizResult() {
        return null;
    }

    @Override
    public long saveQuizResult(QuizResult quizResult) {
        return 0;
    }

    @Override
    public LiveData<List<Questions>> getAll(List<Questions> questions) {
        return null;
    }

    @Override
    public LiveData<List<ShortQuizResult>> getAllShort() {
        return null;
    }
}
