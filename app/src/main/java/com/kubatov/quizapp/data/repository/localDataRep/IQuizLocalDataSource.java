package com.kubatov.quizapp.data.repository.localDataRep;

import com.kubatov.quizapp.data.repository.IQuizRepository;
import com.kubatov.quizapp.model.Questions;

import java.util.List;

public interface IQuizLocalDataSource {

    void getLocalData(IQuizRepository.OnQuizCallBack callBack);
    void setLocalData(List<Questions> questionsList);
}
