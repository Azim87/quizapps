package com.kubatov.quizapp.data.repository.remoteDataRep;

import android.util.Log;

import com.kubatov.quizapp.data.repository.IQuizRepository;
import com.kubatov.quizapp.data.repository.remoteDataRep.model.CategoryResponse;
import com.kubatov.quizapp.data.repository.remoteDataRep.model.QuestionResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class QuizRemoteDataSource implements IQuizRemoteDataSource {
    private final static String BASE_URL = "https://opentdb.com";

    private static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    private static IQuizService service = retrofit.create(IQuizService.class);

    @Override
    public void getQuestions(int amount, String category, String difficulty, IQuizRepository.OnQuizCallBack onQuizCallBack) {
        Call<QuestionResponse> callQuestions = service.getQuestions(amount, null, null);

        callQuestions.enqueue(new Callback<QuestionResponse>() {
            @Override
            public void onResponse(Call<QuestionResponse> call, Response<QuestionResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        onQuizCallBack.onSuccess(response.body().getResults());
                        Log.d("ololo", "onResponse: " + response.body().getResults());
                    } else {
                        onQuizCallBack.onFailure("Body is null");
                    }
                } else {
                    onQuizCallBack.onFailure("Response code  " + response.code());
                }
            }

            @Override
            public void onFailure(Call<QuestionResponse> call, Throwable t) {
                onQuizCallBack.onFailure(t.getMessage());
            }
        });
    }


    @Override
    public void getCategory(int id, String name, IQuizRepository.OnQuizCategoryCallBack categoryCallBack) {
        Call<CategoryResponse> callCategory = service.getCategory(id, name);

        callCategory.enqueue(new Callback<CategoryResponse>() {
            @Override
            public void onResponse(Call<CategoryResponse> call, Response<CategoryResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        categoryCallBack.onSuccesss(response.body().getCategories());
                        Log.d("ololo", "onResponse: " + response.body().getCategories());

                    } else {
                        categoryCallBack.onFailures("body is empty");
                    }
                } else {
                    categoryCallBack.onFailures("Response code  " + response.code());
                }
            }

            @Override
            public void onFailure(Call<CategoryResponse> call, Throwable t) {

            }
        });
    }


    public interface IQuizService {
        @GET("api.php")
        Call<QuestionResponse> getQuestions(
                @Query("amount") int amount,
                @Query("category") String category,
                @Query("difficulty") String difficulty);

        @GET("api.category.php")
        Call<CategoryResponse> getCategory(
                @Query("id") Integer amount,
                @Query("name") String category);

    }
}
