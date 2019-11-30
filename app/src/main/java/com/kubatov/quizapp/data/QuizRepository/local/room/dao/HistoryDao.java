package com.kubatov.quizapp.data.QuizRepository.local.room.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.kubatov.quizapp.data.QuizRepository.local.model.QuizResult;

import java.util.List;

@Dao
public interface HistoryDao {
    @Insert
    long insert(QuizResult result);

    @Query("SELECT * FROM quiz_result WHERE id=:id")
    QuizResult get(int id);

    @Query("DELETE FROM quiz_result ")
    int deleteAll();

    @Query("SELECT * FROM quiz_result ORDER BY ID DESC")
    LiveData<List<QuizResult>> getAll();
}
