package com.kubatov.quizapp.presentation.settings;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.kubatov.quizapp.App;
import com.kubatov.quizapp.core.SingleLiveEvent;
import com.kubatov.quizapp.model.ShortQuizResult;

import java.util.List;

public class SettingsViewModel extends ViewModel {

    SingleLiveEvent<Integer> deleteAllEvent = new SingleLiveEvent<>();

    public void clearHistory(){
        App.localDataSource.deleteAll();
    }
}