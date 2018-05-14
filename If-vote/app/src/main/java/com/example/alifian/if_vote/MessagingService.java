package com.example.alifian.if_vote;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Vibrator;
import android.support.v4.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by ALIFIAN on 11/05/2018.
 */

public class MessagingService extends FirebaseMessagingService {

    Vibrator vibrator;

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
//        super.onMessageReceived(remoteMessage);
        showNotifications(remoteMessage.getNotification().getBody());
    }

    public void showNotifications(String message){
        PendingIntent pi = PendingIntent.getActivity(this, 0, new Intent(this, LoginActivity.class), 0);
        Notification notification = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.ic_stat_name)
                .setContentTitle("HMIF - UNIKOM")
                .setContentText(message)
                .setContentIntent(pi)
                .setAutoCancel(true)
                .build();

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(0, notification);

        vibrator =(Vibrator)getSystemService(VIBRATOR_SERVICE);
        vibrator.vibrate(50);
    }
}
