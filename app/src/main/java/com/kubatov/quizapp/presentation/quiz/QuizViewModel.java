package com.kubatov.quizapp.presentation.quiz;

import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.kubatov.quizapp.App;
import com.kubatov.quizapp.core.SingleLiveEvent;
import com.kubatov.quizapp.data.QuizRepository.IQuizRepository;
import com.kubatov.quizapp.data.QuizRepository.local.QuizLocalDataSource;
import com.kubatov.quizapp.data.QuizRepository.local.model.QuizResult;
import com.kubatov.quizapp.model.Questions;

import java.util.Date;
import java.util.List;

public class QuizViewModel extends ViewModel {

    private List<Questions> questionsList;
    private IQuizRepository quizRepository = App.quizRepository;
    private QuizLocalDataSource localDataSource = App.localDataSource;
    MutableLiveData<List<Questions>> mQuestions = new MutableLiveData<>();
    MutableLiveData<Integer> currentQuestionPosition = new MutableLiveData<>();
    SingleLiveEvent<Integer> finishEvent = new SingleLiveEvent<>();
    SingleLiveEvent<Integer> openResultEvent = new SingleLiveEvent<>();

    public void initViews(Integer amount, Integer category, String difficulty) {
        quizRepository.getQuizQuestions(amount, category, difficulty, new IQuizRepository.OnQuizCallBack() {
            @Override
            public void onSuccess(List<Questions> quizResponse) {
                questionsList = quizResponse;
                mQuestions.setValue(quizResponse);
                currentQuestionPosition.setValue(0);
            }

            @Override
            public void onFailure(Exception e) {
                Toast.makeText(App.instance, "No internet", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private int getCorrectAnswersAmount() {
        int correctAnswers = 0;

        for (Questions question : questionsList) {
            Integer selectedAnswerPosition = question.getSelectedAnswerPosition();

            if (selectedAnswerPosition != null && selectedAnswerPosition >= 0 &&
                    question.getAnswers().get(selectedAnswerPosition).equals(question.getCorrectAnswers())) {
                correctAnswers++;
            }
        }

        return correctAnswers;
    }

    void quizFinish() {

        QuizResult quizResult = new QuizResult(
                0,
                questionsList,
                getCorrectAnswersAmount(),
                new Date()
        );

        int resultId = localDataSource.saveQuizResult(quizResult);
        finishEvent.call();
        openResultEvent.setValue(resultId);
    }


    void onAnswerClick(int questionPosition, int answerPosition) {
        if (currentQuestionPosition.getValue() == null || mQuestions == null) {
            return;
        }

        Questions questions = questionsList.get(questionPosition);
        questions.setSelectedAnswerPosition(answerPosition);
        questionsList.set(questionPosition, questions);
        mQuestions.setValue(questionsList);

        if (questionPosition == questionsList.size() - 1) {
            quizFinish();
        } else {
            currentQuestionPosition.setValue(questionPosition + 1);
        }
    }

    void onSkipButtonClick() {
        Integer currentPosition = currentQuestionPosition.getValue();
        if (currentPosition != null) {
            onAnswerClick(currentPosition, -1);
        }
    }

    void onBackPressed() {
        Integer currentPosition = currentQuestionPosition.getValue();
        if (currentPosition != null) {
            if (currentPosition == 0) {
                finishEvent.call();
            } else {
                currentQuestionPosition.setValue(currentPosition - 1);
            }
        }
    }
}
