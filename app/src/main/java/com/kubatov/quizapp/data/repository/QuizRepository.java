package com.kubatov.quizapp.data.repository;


import androidx.annotation.Nullable;
import com.kubatov.quizapp.data.repository.localDataRep.QuizLocalDataSource;
import com.kubatov.quizapp.data.repository.remoteDataRep.QuizRemoteDataSource;
import com.kubatov.quizapp.data.repository.remoteDataRep.model.QuestionResponse;
import com.kubatov.quizapp.model.Questions;

import java.util.List;

public class QuizRepository implements IQuizRepository {

    @Nullable
    private QuizRemoteDataSource mRemoteDataSource;
    @Nullable
    private QuizLocalDataSource mLocalDataSource;

    public QuizRepository(
            @Nullable QuizRemoteDataSource remoteDataSource,
            @Nullable QuizLocalDataSource localDataSource) {
        mRemoteDataSource = remoteDataSource;
        mLocalDataSource = localDataSource;
    }

    @Override
    public void getQuizQuestions(
            int amount, String category, String difficulty, OnQuizCallBack questionCallBack) {

        if (mLocalDataSource != null) {
            mLocalDataSource.getLocalData(questionCallBack);
        }

        if (mRemoteDataSource != null) {
            mRemoteDataSource.getQuestions(amount, category, difficulty,
                    new OnQuizCallBack() {
                        @Override
                        public void onSuccess(List quizResponse) {
                            questionCallBack.onSuccess(quizResponse);
                            mLocalDataSource.setLocalData(quizResponse);
                        }


                        @Override
                        public void onFailure(String message) {
                            questionCallBack.onFailure(message);
                        }
                    });
        }
    }

    @Override
    public void getQuizCategory(int id, String name, OnQuizCallBack categoryCallBack) {

    }


}
