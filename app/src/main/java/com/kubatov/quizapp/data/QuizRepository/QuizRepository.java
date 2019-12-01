package com.kubatov.quizapp.data.QuizRepository;


import android.util.Log;

import androidx.annotation.Nullable;

import com.kubatov.quizapp.data.QuizRepository.remote.QuizRemoteDataSource;
import com.kubatov.quizapp.model.Questions;

import java.util.List;

public class QuizRepository implements IQuizRepository {

    @Nullable
    private QuizRemoteDataSource mRemoteDataSource;

    public QuizRepository(@Nullable QuizRemoteDataSource remoteDataSource) {
        mRemoteDataSource = remoteDataSource;
    }

    @Override
    public void getQuizQuestions(
            int amount,
            Integer category,
            String difficulty,
            OnQuizCallBack questionCallBack) {

        if (mRemoteDataSource != null) {
            mRemoteDataSource
                    .getQuestions(
                            amount,
                            category,
                            difficulty,
                            new OnQuizCallBack() {
                                @Override
                                public void onSuccess(List<Questions> quizResponse) {
                                    questionCallBack.onSuccess(quizResponse);
                                }

                                @Override
                                public void onFailure(Exception e) {
                                    questionCallBack.onFailure(new Exception());
                                }
                            });

        }
    }
}
