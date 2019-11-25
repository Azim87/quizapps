package com.kubatov.quizapp.data.QuizRepository.local.room.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.kubatov.quizapp.data.QuizRepository.local.model.QuizResult;
import com.kubatov.quizapp.data.QuizRepository.local.room.dao.HistoryDao;


@Database(entities = {QuizResult.class}, exportSchema = false, version = 1)
abstract public class QuizDataBase extends RoomDatabase {
    public abstract HistoryDao getHistoryDao();

}
