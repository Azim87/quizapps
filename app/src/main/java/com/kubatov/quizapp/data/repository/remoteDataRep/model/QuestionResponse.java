package com.kubatov.quizapp.data.repository.remoteDataRep.model;

import com.google.gson.annotations.SerializedName;
import com.kubatov.quizapp.model.Questions;

import java.util.List;

public class QuestionResponse {

    @SerializedName("response_code")
    private int responseCode;

    private List<Questions> results;

    private List<String> answers;

    public QuestionResponse(int responseCode, List<Questions> results) {
        this.responseCode = responseCode;
        this.results = results;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    public List<Questions> getResults() {
        return results;
    }

    public void setResults(List<Questions> results) {
        this.results = results;
    }
}
