package com.vovatkach2427gmail.eyeexercises.notification;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import com.vovatkach2427gmail.eyeexercises.activity.ActivityNotification;

import java.util.Calendar;
import java.util.concurrent.TimeUnit;

/**
 * Created by tkach on 26.06.17.
 */

public class MyNotification {

    private AlarmManager alarmManager;
    private PendingIntent pendingIntent;

    private Context context;

    public MyNotification(Context context) {
        this.context = context;

        Intent intent = new Intent(context, NotificationService.class);
        pendingIntent = PendingIntent.getService(context, 0, intent, 0);

        alarmManager = (AlarmManager) context.getSystemService(context.ALARM_SERVICE);
    }

    public void start(int repeatHour) {
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, Calendar.getInstance().getTimeInMillis(), TimeUnit.MINUTES.toMillis(repeatHour), pendingIntent);
    }

    public void changeRepeatTime(int repeatHour) {
        alarmManager.cancel(pendingIntent);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,Calendar.getInstance().getTimeInMillis(),TimeUnit.MINUTES.toMillis(repeatHour),pendingIntent);
    }

}
