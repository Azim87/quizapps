package com.kubatov.quizapp.data.QuizRepository;

import com.kubatov.quizapp.core.CoreCallBack;
import com.kubatov.quizapp.model.Questions;

import java.util.List;

public interface IQuizRepository {

    void getQuizQuestions(int amount, Integer category, String difficult,
                          OnQuizCallBack questionCallBack);

    interface OnQuizCallBack
            extends CoreCallBack<List<Questions>> {
    }
}
