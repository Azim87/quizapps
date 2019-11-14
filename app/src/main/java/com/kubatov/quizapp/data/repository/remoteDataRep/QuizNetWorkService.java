package com.kubatov.quizapp.data.repository.remoteDataRep;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class QuizNetWorkService {
    private static IQuizService mInstance;

    private static IQuizService getInstance() {
        if (mInstance != null) {
            mInstance = buildRetroFit();
        }
        return mInstance;
    }

    private static IQuizService buildRetroFit() {
        return new Retrofit.Builder()
                .baseUrl("")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(IQuizService.class);
    }
}
