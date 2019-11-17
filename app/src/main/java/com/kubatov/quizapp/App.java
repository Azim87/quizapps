package com.kubatov.quizapp;

import android.app.Application;
import com.kubatov.quizapp.data.repository.IQuizRepository;
import com.kubatov.quizapp.data.repository.QuizRepository;
import com.kubatov.quizapp.data.repository.localDataRep.QuizLocalDataSource;
import com.kubatov.quizapp.data.repository.remoteDataRep.QuizRemoteDataSource;


public class App extends Application {
    public static App instance;
    public static IQuizRepository quizRepository;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        quizRepository = new QuizRepository(new QuizRemoteDataSource(), new QuizLocalDataSource());
    }
}
