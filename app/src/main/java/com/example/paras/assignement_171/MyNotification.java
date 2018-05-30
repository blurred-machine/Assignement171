package com.example.paras.assignement_171;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;


// a public class that extends the Application class.
public class MyNotification extends Application {

    // a constant fixed string channel id used for notification.
public static final String CHANNEL_ID = "my_music_player";

    @Override
    public void onCreate() {
        super.onCreate();
        // calling the method that creates the notification.
        createMyNotification();
    }

    private void createMyNotification() {
        // if the current android sdk version is equal to or grater than the Oreo version then,
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            // create the notification channel object with the defined channel id and a name and a notification importance value
            // that is shown everywhere and makes a sound.
            NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID, "MyMusicApp", NotificationManager.IMPORTANCE_DEFAULT);
            // use notification manager to get the system's service
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            // create the notification channel with the so created notification manager.
            notificationManager.createNotificationChannel(notificationChannel);
        }
    }
}
