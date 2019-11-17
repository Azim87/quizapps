package com.kubatov.quizapp.data.repository;


import androidx.annotation.Nullable;

import com.kubatov.quizapp.data.repository.localDataRep.QuizLocalDataSource;
import com.kubatov.quizapp.data.repository.remoteDataRep.QuizRemoteDataSource;
import com.kubatov.quizapp.model.Questions;

import java.util.List;

public class QuizRepository implements IQuizRepository {

    @Nullable
    private QuizRemoteDataSource mRemoteDataSource;
    @Nullable
    private QuizLocalDataSource mLocalDataSource;

    public QuizRepository(@Nullable QuizRemoteDataSource remoteDataSource, @Nullable QuizLocalDataSource localDataSource) {
        mRemoteDataSource = remoteDataSource;

        mLocalDataSource = localDataSource;
    }

    @Override
    public void getQuizData(int amount, String category, String difficulty,
                            OnQuizCallBack onQuizCallBack) {

        if (mLocalDataSource != null) {
            mLocalDataSource.getLocalData(onQuizCallBack);
        }

        if (mRemoteDataSource != null) {
            mRemoteDataSource.getQuestions(amount, category, difficulty,
                    new OnQuizCallBack() {
                        @Override
                        public void onSuccess(List<Questions> quizQuestions) {
                            onQuizCallBack.onSuccess(quizQuestions);
                            mLocalDataSource.setLocalData(quizQuestions);
                        }

                        @Override
                        public void onFailure(String message) {
                            onQuizCallBack.onFailure(message);
                        }
                    });
        }
    }
}
