package com.kubatov.quizapp.data.QuizRepository.local.room.converters;

import androidx.room.TypeConverter;

import java.util.Date;

public class TimestampConverter {

    @TypeConverter
    public static Date fromTimeStamp(Long time) {

        return time == null ? null : new Date(time);
    }

    @TypeConverter
    public static Long dateToTimeStamp(Date date) {
        return date == null ? 0L : date.getTime();
    }
}
