package com.kubatov.quizapp.core;

public interface CoreCallBack<T> {

    void onSuccess(T result);

    void onFailure(Exception e);
}
