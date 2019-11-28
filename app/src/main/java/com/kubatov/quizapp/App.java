package com.kubatov.quizapp;

import android.app.Application;

import androidx.room.Room;

import com.kubatov.quizapp.data.QuizRepository.IQuizRepository;
import com.kubatov.quizapp.data.QuizRepository.QuizRepository;
import com.kubatov.quizapp.data.QuizRepository.local.QuizLocalDataSource;
import com.kubatov.quizapp.data.QuizRepository.local.room.database.QuizDataBase;
import com.kubatov.quizapp.data.QuizRepository.remote.QuizRemoteDataSource;


public class App extends Application {
    private final static String QUIZ = "quiz";
    public static App instance;
    public static IQuizRepository quizRepository;
    public static QuizDataBase dataBase;
    public static QuizLocalDataSource localDataSource;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        dataBase = Room.databaseBuilder(this, QuizDataBase.class, QUIZ)
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();

        quizRepository = new QuizRepository(new QuizRemoteDataSource());
        localDataSource = new QuizLocalDataSource(dataBase.getHistoryDao());
    }
}
