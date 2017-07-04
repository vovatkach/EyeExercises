package com.vovatkach2427gmail.eyeexercises;

import android.content.Context;

/**
 * Created by vovat on 04.06.2017.
 */

public class TimeWritter {
    public static String Time(int hours, Context context) {

        String T = null;
        T = "" + Years(hours, context);
        return T;
    }

    private static String Hours(int hours, Context context) {
        String T = "";
        if (hours == 1 || hours == 21) {
            T = hours + " " + context.getString(R.string.hour_1);
        }
        if (hours >= 2 && hours <= 4 || hours >= 22 && hours <= 24) {
            T = hours + " " + context.getString(R.string.hour_2);
        }
        if (hours >= 5 && hours <= 20) {
            T = hours + " " + context.getString(R.string.hour_5);
        }
        return T;
    }

    private static String Days(int hours, Context context) {

        int days, hour = 0;
        days = hours / 24;
        if (hours > 24) {
            hour = hours % 24;
        } else if (hours < 24) {
            hour = hours;
        }
        if (days > 30) {

        }
        String T = "";
        if (days == 0) {
            T = "" + Hours(hour, context);
        }
        if (days == 1 || days == 21) {
            T = days + " " + context.getString(R.string.day_1) + " " + Hours(hour, context);
        }
        if (days >= 5 && days <= 20 || days >= 25 && days <= 30) {
            T = days + " " + context.getString(R.string.day_2) + " " + Hours(hour, context);
        }
        if (days > 1 && days < 5 || days >= 22 && days <= 24) {
            T = days + " " + context.getString(R.string.day_3) + " " + Hours(hour, context);
        }
        return T;
    }

    private static String Weeks(int hours, Context context) {
        int weeks = hours / 168;
        int daysH = 0;
        if (hours > 168) {
            daysH = hours % 168;
        } else if (hours < 168) {
            daysH = hours;
        }
        if (weeks > 4) {
        }
        String T = "";
        if (weeks == 0) {
            T = "" + Days(daysH, context);
        }
        if (weeks == 1) {
            T = weeks + " " + context.getString(R.string.week_1) + " " + Days(daysH, context);
        }
        if (weeks >= 2 && weeks <= 4) {
            T = weeks + " " + context.getString(R.string.week_2) + " " + Days(daysH, context);
        }

        return T;
    }

    private static String Months(int hours, Context context) {
        int months = hours / 720;
        int weeksH = 0;
        if (hours > 720) {
            weeksH = hours % 720;
        } else if (hours < 720) {
            weeksH = hours;
        }
        if (months > 12) {
        }
        String T = "";
        if (months == 0) {
            T = "" + Weeks(weeksH, context);
        }
        if (months == 1) {
            T = months + " " + context.getString(R.string.mounth_1) + " " + Weeks(weeksH, context);
        }
        if (months >= 2 && months <= 4) {
            T = months + " " + context.getString(R.string.mounth_2) + " " + Weeks(weeksH, context);
        }
        if (months >= 5 && months <= 12) {
            T = months + " " + context.getString(R.string.mounth_3) + " " + Weeks(weeksH, context);
        }

        return T;
    }

    private static String Years(int hours, Context context) {
        int years = hours / 8640;
        int monthsH = 0;
        if (hours > 8640) {
            monthsH = hours % 8640;
        } else if (hours < 8640) {
            monthsH = hours;
        }

        String T = "";
        if (years == 0) {
            T = "" + Months(monthsH, context);
        }
        if (years > 60) {
            T = "Years Error, maximum 60 years!";
        }
        if (years == 1 || years == 21 || years == 31 || years == 41 || years == 41 || years == 51) {
            T = years + " " + context.getString(R.string.year_1) + " " + Months(monthsH, context);
        }
        if (years >= 2 && years <= 4 || years >= 22 && years <= 24 || years >= 32 && years <= 34
                || years >= 42 && years <= 44 || years >= 52 && years <= 54) {
            T = years + " " + context.getString(R.string.year_2) + " " + Months(monthsH, context);
        }
        if (years >= 5 && years <= 20 || years >= 25 && years <= 30 || years >= 35 && years <= 40
                || years >= 45 && years <= 50 || years >= 55 && years <= 60) {
            T = years + " " + context.getString(R.string.year_3) + " " + Months(monthsH, context);
        }

        return T;
    }
}
