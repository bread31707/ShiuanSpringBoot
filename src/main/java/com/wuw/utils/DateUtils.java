package com.wuw.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class DateUtils {

    public final static String DefaultDatePattern = "yyyy/MM/dd";
    public final static String DefaultDateTimePattern = "yyyy/MM/dd HH:mm:ss";
    public final static String DefaultTimePattern = "HH:mm:ss";

    public static LocalDate getNowLocalDate(){
        return LocalDate.now();
    }
    public static LocalDateTime getNowLocalDateTime(){
        return LocalDateTime.now();
    }
    public static LocalTime getNowLocalTime(){
        return LocalTime.now();
    }

    public static String localDateFormat(LocalDate localDate, String pattern){
        return localDate.format(DateTimeFormatter.ofPattern(pattern));
    }
    public static String localDateTimeFormat(LocalDateTime localDateTime, String pattern){
        return localDateTime.format(DateTimeFormatter.ofPattern(pattern));
    }
    public static String localTimeFormat(LocalTime localTime, String pattern){
        return localTime.format(DateTimeFormatter.ofPattern(pattern));
    }

    public static long between(ChronoUnit chronoUnit, LocalDateTime temporal1Inclusive, LocalDateTime temporal2Exclusive) {
        return chronoUnit.between(temporal1Inclusive, temporal2Exclusive);
    }

}
