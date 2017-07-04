package com.vovatkach2427gmail.eyeexercises.notification;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by tkach on 04.07.17.
 */

public class BootBrodcastReciever extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("notification", MODE_PRIVATE);
        int repeat_time = sharedPreferences.getInt("repeat_time", 1);

        MyNotification myNotification=new MyNotification(context);
        myNotification.start(repeat_time);
    }
}
