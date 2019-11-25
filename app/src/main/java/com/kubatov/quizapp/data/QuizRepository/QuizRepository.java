package com.kubatov.quizapp.data.QuizRepository;


import androidx.annotation.Nullable;

import com.kubatov.quizapp.data.QuizRepository.local.QuizLocalDataSource;
import com.kubatov.quizapp.data.QuizRepository.remote.QuizRemoteDataSource;
import com.kubatov.quizapp.model.Questions;
import java.util.List;

public class QuizRepository implements IQuizRepository {

    @Nullable private QuizRemoteDataSource mRemoteDataSource;
    @Nullable private QuizLocalDataSource mLocalDataSource;

    public QuizRepository(
            @Nullable QuizRemoteDataSource remoteDataSource,
            @Nullable QuizLocalDataSource localDataSource) {

        mRemoteDataSource = remoteDataSource;
        mLocalDataSource = localDataSource;
    }

    @Override
    public void getQuizQuestions(
            int amount,
            Integer category,
            String difficulty,
            OnQuizCallBack questionCallBack) {

        if (mRemoteDataSource != null && mLocalDataSource != null) {
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
                    questionCallBack.onFailure(new Exception("not successful"));
                }
            });

        }
    }
}
