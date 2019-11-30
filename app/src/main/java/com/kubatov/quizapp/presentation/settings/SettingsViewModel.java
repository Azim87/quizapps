package com.kubatov.quizapp.presentation.settings;

import androidx.lifecycle.ViewModel;

import com.kubatov.quizapp.App;
import com.kubatov.quizapp.core.SingleLiveEvent;

public class SettingsViewModel extends ViewModel {

    SingleLiveEvent<Integer> deleteAllEvent = new SingleLiveEvent<>();

    public void clearHistory(){
        App.localDataSource.deleteAll();
    }
}