package com.kubatov.quizapp.data.QuizRepository.remote.model;

import com.google.gson.annotations.SerializedName;
import com.kubatov.quizapp.model.Category;

import java.util.List;

public class CategoryResponse {

    @SerializedName("trivia_categories")
    private List<Category> categories;

    public CategoryResponse(List<Category> categories) {
        this.categories = categories;
    }

    public List<Category> getCategories() {
        return categories;
    }
}
