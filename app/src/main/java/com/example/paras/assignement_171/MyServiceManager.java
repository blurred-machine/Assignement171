package com.example.paras.assignement_171;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;

import static com.example.paras.assignement_171.MyNotification.CHANNEL_ID;

public class MyServiceManager extends Service {

    // initializing the media player and some final integer values used for pending intent.
    private MediaPlayer musicPlayer;
    public final int REQUEST_CODE = 0;
    public final int FLAG = 0;
    Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        // start the song play with the creation of the media player by the resource of the song in the raw directory.
        musicPlayer = MediaPlayer.create(getApplicationContext(), R.raw.buzz_song);
        context = this;
    }


    // overridden method of the service class which we extend and is used for bound services
    // other components can communicate back and forth by binding to it.
    // but we don't need that here.
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    // method called when the service is started everytime.
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // start the music player.
        musicPlayer.start();
        // intent for the notification and takes command to the main activity.
        Intent notificationIntent = new Intent(this, MainActivity.class);
        // pending intent for the notification intent created above with the application context, request code and flag.
        PendingIntent pendingIntent = PendingIntent.getActivity(context, REQUEST_CODE, notificationIntent, FLAG);
        // creating the design of the notification with the same channel id we used before.
        // setting the title of notification, text, icon, and the intent associated with it is pending intent.
        Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle("Music Player")
                .setContentText("To play or stop my music click on this notification")
                .setSmallIcon(R.drawable.music_icon)
                .setContentIntent(pendingIntent)
                .build();

        // this doesn't let the service as only background service because the android OS will
        // automatically kill it after some time, if it's not started as a foreground service.
        startForeground(1, notification);

        // This mode makes sense for things that want to do some work as a
        // result of being started, but can be stopped when under memory pressure
        // and will explicit start themselves again later to do more work.
        return START_NOT_STICKY;
    }

    // this method releases the music player as soon as the service is destroyed when we click the stop button.
    @Override
    public void onDestroy() {
        super.onDestroy();
        musicPlayer.release();
    }
}
