package com.kubatov.quizapp.presentation.result;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.kubatov.quizapp.App;
import com.kubatov.quizapp.data.QuizRepository.local.model.QuizResult;

public class ResultViewModel extends ViewModel {

    public MutableLiveData<QuizResult> quizResultMutableLiveData = new MutableLiveData<>();

    public void getQuizResults(int id) {
        QuizResult quizResult = App.localDataSource.getQuizResult(id);
        quizResultMutableLiveData.setValue(quizResult);
    }
}
