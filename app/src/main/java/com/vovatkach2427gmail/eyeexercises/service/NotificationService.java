package com.vovatkach2427gmail.eyeexercises.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.vovatkach2427gmail.eyeexercises.R;
import com.vovatkach2427gmail.eyeexercises.activity.ActivityMainMenu;

import java.util.concurrent.TimeUnit;

public class NotificationService extends Service {
    NotificationManager notificationManager;

    public NotificationService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        sendNotification();
        stopSelf();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    private void sendNotification() {
        Intent intent = new Intent(this, ActivityMainMenu.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

        Notification.Builder builder=new Notification.Builder(this)
                .setContentTitle("Вправи для очей 1")
                .setContentText("Вправи для очей 2")
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .setSmallIcon(R.drawable.logo_eye);

        notificationManager.notify(1,builder.build());
    }
}
