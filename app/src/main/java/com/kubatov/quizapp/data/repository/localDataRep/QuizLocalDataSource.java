package com.kubatov.quizapp.data.repository.localDataRep;

import com.kubatov.quizapp.data.repository.IQuizRepository;
import com.kubatov.quizapp.model.Questions;

import java.util.ArrayList;
import java.util.List;

public class QuizLocalDataSource implements IQuizLocalDataSource{


    @Override
    public void getLocalData(IQuizRepository.OnQuizCallBack callBack) {
        List<Questions> responses = new ArrayList<>();
        callBack.onSuccess(responses);
    }


    @Override
    public void setLocalData(List<Questions> questionsList) {

    }
}
