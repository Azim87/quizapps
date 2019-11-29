package com.kubatov.quizapp.presentation.history;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.kubatov.quizapp.App;
import com.kubatov.quizapp.model.ShortQuizResult;

import java.util.List;

public class HistoryViewModel extends ViewModel {

    LiveData<List<ShortQuizResult>> shortQuizResult = App.localDataSource.getAllShort();

}
