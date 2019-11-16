package com.kubatov.quizapp.data.repository.remoteDataRep;

import com.kubatov.quizapp.data.repository.IQuizRepository;
import com.kubatov.quizapp.data.repository.remoteDataRep.model.QuestionResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class QuizRemoteDataSource implements IQuizRemoteDataSource {
    private static String BASE_URL = "https://opentdb.com";

    private static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public static IQuizService service = retrofit.create(IQuizService.class);

    @Override
    public void getQuestions(IQuizRepository.OnQuizCallBack onQuizCallBack) {
        Call<QuestionResponse> call = service.getQuestions(
                10, null, null
        );

        call.enqueue(new Callback<QuestionResponse>() {
            @Override
            public void onResponse(Call<QuestionResponse> call, Response<QuestionResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        onQuizCallBack.onSuccess(response.body().getResults());
                    }else {}
                }else {}
            }

            @Override
            public void onFailure(Call<QuestionResponse> call, Throwable t) {
                onQuizCallBack.onFailure(t.getMessage());
            }
        });
    }


    public interface IQuizService {
        @GET("api.php")
        Call<QuestionResponse> getQuestions(
                @Query("amount") int amount,
                @Query("category") Integer category,
                @Query("difficulty") String difficulty

        );
    }

}
