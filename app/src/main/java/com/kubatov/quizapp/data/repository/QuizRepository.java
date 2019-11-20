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
    public void getQuizQuestions(int amount, Integer category, String difficulty, OnQuizCallBack questionCallBack) {
        if (mRemoteDataSource != null &&
                mLocalDataSource != null) {

            mRemoteDataSource.getQuestions(amount, category, difficulty, new OnQuizCallBack() {
                @Override
                public void onSuccess(List<Questions> quizResponse) {
                    questionCallBack.onSuccess(quizResponse);
                    mLocalDataSource.setLocalData(quizResponse);
                }

                @Override
                public void onFailure(Exception e) {
                    questionCallBack.onFailure(new Exception("ololo"));
                }
            });

            mLocalDataSource.getLocalData(questionCallBack);
        }
    }
}
