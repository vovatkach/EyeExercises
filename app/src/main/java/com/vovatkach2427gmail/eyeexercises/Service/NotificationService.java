package com.vovatkach2427gmail.eyeexercises.Service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.IntDef;

import com.vovatkach2427gmail.eyeexercises.Act.MainMenu;
import com.vovatkach2427gmail.eyeexercises.R;

public class NotificationService extends Service {
    NotificationManager notificationManager;
    int number;
    public NotificationService() {
        number=1;
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        notificationManager=(NotificationManager) getSystemService(NOTIFICATION_SERVICE);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Thread thread=new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    try {
                        Thread.sleep(3600000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    sentNotif(number);
                    number++;
                }
            }
        });
        thread.start();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
    public void sentNotif(int number)
    {
        Notification notification=new Notification(R.drawable.logo_eye,"Час тренувати очі",System.currentTimeMillis());

        Intent intent=new Intent(this, MainMenu.class);
        PendingIntent pendingIntent=PendingIntent.getActivity(this,0,intent,0);

        Notification.Builder builder=new Notification.Builder(this);
        builder.setAutoCancel(true);
        builder.setTicker("this is ticker text");
        builder.setContentTitle("Час тренувати очі");
        builder.setContentText("You have a new message");
        builder.setOngoing(true);
        builder.setSmallIcon(R.drawable.logo_eye);
        builder.setContentIntent(pendingIntent);
        builder.setNumber(number);
        builder.build();

        notification=builder.getNotification();
        notificationManager.notify(1,notification);
    }
}
