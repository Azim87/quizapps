package com.kubatov.quizapp.data.QuizRepository.local.room.converters;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kubatov.quizapp.model.Questions;

import java.lang.reflect.Type;
import java.util.List;

public class QuestionConverter {

    @TypeConverter
    public static List<Questions> fromRaw(String raw) {
        if (raw == null) {
            return null;
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<Questions>>() {
        }.getType();
        return gson.fromJson(raw, type);
    }


    @TypeConverter
    public static String toRaw(List<Questions> questions) {
        if (questions == null) {
            return null;
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<Questions>>() {
        }.getType();
        return gson.toJson(questions, type);
    }
}
