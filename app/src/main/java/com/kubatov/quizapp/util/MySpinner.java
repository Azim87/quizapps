package com.kubatov.quizapp.util;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class MySpinner {
    public static void show(String[] spin, Spinner spinner, Context context) {
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, spin);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinner.setAdapter(arrayAdapter);
    }
}
