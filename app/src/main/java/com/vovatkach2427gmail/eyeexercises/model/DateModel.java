package com.vovatkach2427gmail.eyeexercises.model;

import android.content.Context;

import com.vovatkach2427gmail.eyeexercises.R;
import com.vovatkach2427gmail.eyeexercises.TimeWritter;

import java.util.Calendar;

/**
 * Created by vovat on 03.06.2017.
 */

public class DateModel {
    private int year;
    private int mounth;
    private int day;

    private int hour;
    private int minute;

    public DateModel(int year, int mounth, int day, int hour, int minute) {
        this.year = year;
        this.mounth = mounth;
        this.day = day;
        this.hour = hour;
        this.minute = minute;
    }

    public int getYear() {
        return year;
    }

    public int getMounth() {
        return mounth;
    }

    public int getDay() {
        return day;
    }

    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }


    @Override
    public String toString() {
        String monS;
        String dayS;
        String hourS;
        String minuteS;
        if (mounth < 10) {
            monS = "0" + String.valueOf(mounth);
        } else monS = String.valueOf(mounth);

        if (day < 10) {
            dayS = "0" + String.valueOf(day);
        } else dayS = String.valueOf(day);

        if (hour < 10) {
            hourS = "0" + String.valueOf(hour);
        } else hourS = String.valueOf(hour);

        if (minute < 10) {
            minuteS = "0" + String.valueOf(minute);
        } else minuteS = String.valueOf(minute);

        String date = "" + year + "." + monS + "." + dayS + "  " + hourS + ":" + minuteS + "";
        return date;
    }

    //--------------------------------------
    public static String getAverageTimeToTraining(DateModel dateStart, int countTraining, Context context) {
        if (countTraining == 0) {
            return "";
        }
        Calendar dStart = Calendar.getInstance();
        dStart.set(dateStart.getYear(), dateStart.getMounth(), dateStart.getDay(), dateStart.getHour(), dateStart.getMinute());
        Calendar calendar = Calendar.getInstance();
        DateModel dateModel = new DateModel(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DAY_OF_MONTH), calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE));
        Calendar dEnd = Calendar.getInstance();
        dEnd.set(dateModel.getYear(), dateModel.getMounth(), dateModel.getDay(), dateModel.getHour(), dateModel.getMinute());
        long timeInMilis = dEnd.getTimeInMillis() - dStart.getTimeInMillis();
        timeInMilis /= countTraining;
        long timeInSec = timeInMilis / 1000;
        float timeInHour = timeInSec / 3600;
        int res = Math.round(timeInHour);
        //-----------------------------------------
        if (res < 0) res *= (-1);
        if (res < 1) {
            if (countTraining <= 1) return context.getString(R.string.trainingOnce);
            else
                return context.getString(R.string.more_than_once);
        }
        return context.getString(R.string.once) + " " + TimeWritter.Time(res, context);
    }

    public static String lastTrainingTime(DateModel dateLastTraining, Context context) {
        Calendar dStart = Calendar.getInstance();
        dStart.set(dateLastTraining.getYear(), dateLastTraining.getMounth(), dateLastTraining.getDay(), dateLastTraining.getHour(), dateLastTraining.getMinute());
        Calendar calendar = Calendar.getInstance();
        DateModel dateModel = new DateModel(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DAY_OF_MONTH), calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE));
        Calendar dEnd = Calendar.getInstance();
        dEnd.set(dateModel.getYear(), dateModel.getMounth(), dateModel.getDay(), dateModel.getHour(), dateModel.getMinute());
        long timeInMilis = dEnd.getTimeInMillis() - dStart.getTimeInMillis();
        long timeInSec = timeInMilis / 1000;
        float timeInHour = timeInSec / 3600;
        int res = Math.round(timeInHour);
        //-----------------------------------------
        if (res < 0) res *= (-1);
        if (res < 1) {
            return context.getString(R.string.training_1);
        }
        String advice;
        if (res < 4) advice = context.getString(R.string.we_done);
        else advice = context.getString(R.string.go_training);
        return context.getString(R.string.we_training) + " " + TimeWritter.Time(res, context) + " " + context.getString(R.string.because) + " " + advice;
    }
}
