package com.kubatov.quizapp.data.QuizRepository.local;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;

import com.kubatov.quizapp.data.QuizRepository.local.model.QuizResult;
import com.kubatov.quizapp.data.QuizRepository.local.room.dao.HistoryDao;
import com.kubatov.quizapp.model.ShortQuizResult;

import java.util.ArrayList;
import java.util.List;

public class QuizLocalDataSource implements IQuizLocalDataSource {
    private HistoryDao mHistoryDao;

    public QuizLocalDataSource(HistoryDao historyDao) {
        mHistoryDao = historyDao;
    }

    @Override
    public QuizResult getQuizResult(int id) {
        return mHistoryDao.get(id);
    }

    @Override
    public int saveQuizResult(QuizResult quizResult) {
        return (int) mHistoryDao.insert(quizResult);
    }

    @Override
    public LiveData<List<QuizResult>> getAll() {
        return mHistoryDao.getAll();
    }

    @Override
    public LiveData<List<ShortQuizResult>> getAllShort() {
        return Transformations.map(getAll(), quizResults -> {
            ArrayList<ShortQuizResult> shortQuizResults = new ArrayList<>();

            for (QuizResult result : quizResults) {
                shortQuizResults.add(new ShortQuizResult(
                        result.getId(),
                        result.getQuestions().size(),
                        result.getCorrectAnswers(),
                        result.getCreatedAt()));
            }
            return shortQuizResults;
        });
    }
}
