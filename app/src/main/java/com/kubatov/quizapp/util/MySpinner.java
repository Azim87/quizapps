package com.kubatov.quizapp.util;

import android.widget.ArrayAdapter;

import com.kubatov.quizapp.App;

public class Spinner {

    public static void show(String[] spinner) {
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(App.context, android.R.layout.simple_spinner_item, spinner);
    }
}
