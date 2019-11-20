package com.kubatov.quizapp.data.repository.remoteDataRep;

import android.util.Log;

import com.kubatov.quizapp.data.repository.IQuizRepository;
import com.kubatov.quizapp.data.repository.remoteDataRep.model.QuestionResponse;
import com.kubatov.quizapp.model.Questions;

import java.util.ArrayList;
import java.util.Collections;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class QuizRemoteDataSource implements IQuizRemoteDataSource {
    private final static String BASE_URL = "https://opentdb.com/";

    private static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    private static IQuizService service = retrofit.create(IQuizService.class);

    private Questions shuffleQuestions(Questions questions) {
        ArrayList<String> answers = new ArrayList<>();
        answers.add(questions.getCorrectAnswers());
        answers.addAll(questions.getIncorrectAnswers());

        Collections.shuffle(answers);
        questions.setAnswers(answers);
        return questions;
    }

    @Override
    public void getQuestions(int amount, Integer category, String difficulty, IQuizRepository.OnQuizCallBack onQuizCallBack) {

        Call<QuestionResponse> callQuestions = service.getQuestions(amount, category, null);

        callQuestions.enqueue(new Callback<QuestionResponse>() {
            @Override
            public void onResponse(Call<QuestionResponse> call, Response<QuestionResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {

                        for (int i = 0; i < response.body().getResults().size(); i++) {
                            Questions questions = response.body().getResults().get(i);
                            response.body().getResults().set(i, shuffleQuestions(questions));
                        }

                        onQuizCallBack.onSuccess(response.body().getResults());
                        Log.d("ololo", "onResponse: " + response.body().getResults());
                    } else {
                        onQuizCallBack.onFailure(new Exception());
                    }
                } else {
                    onQuizCallBack.onFailure(new Exception("Response code  " + response.code()));
                }
            }

            @Override
            public void onFailure(Call<QuestionResponse> call, Throwable t) {
                onQuizCallBack.onFailure(new Exception(t));
            }
        });
    }

    public interface IQuizService {
        @GET("/api.php")
        Call<QuestionResponse> getQuestions(
                @Query("amount") int amount,
                @Query("category") Integer category,
                @Query("difficulty") String difficulty
        );
    }
}
