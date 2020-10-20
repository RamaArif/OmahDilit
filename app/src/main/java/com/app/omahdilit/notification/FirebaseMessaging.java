package com.app.omahdilit.notification;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.Settings;
import android.util.Log;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.allyants.notifyme.NotifyMe;
import com.app.omahdilit.MainActivity;
import com.app.omahdilit.Promo;
import com.app.omahdilit.R;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class FirebaseMessaging extends FirebaseMessagingService {
    public FirebaseMessaging() {
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        if (remoteMessage.getData().size() > 0) {

// setup intent
//            Intent intent = new Intent(this, Promo.class);
//            intent.putExtras(bundle);
//            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent,
//                    PendingIntent.FLAG_ONE_SHOT);
//
//            NotificationCompat.Builder notifBuilder = new NotificationCompat.Builder(this, "NotifApps")
//                    .setContentTitle(remoteMessage.getNotification().getTitle())
//                    .setContentText(remoteMessage.getNotification().getBody())
//                    .setSmallIcon(R.drawable.ic_launcher_foreground) // icon.setAutoCancel(true) // menghapus notif ketika user melakukan tap pada notif
//                    .setLights(200,200,200) // light button
//                    .setSound(Settings.System.DEFAULT_NOTIFICATION_URI) // set sound
//                    .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
//                    .setStyle(new NotificationCompat.BigTextStyle().bigText(remoteMessage.getNotification().getBody()))
//                    .setPriority(NotificationCompat.PRIORITY_HIGH)
//                    .setContentIntent(pendingIntent); // action notif ketika di tap
//
//            NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//                NotificationChannel channel = new NotificationChannel("default", "Default channel", NotificationManager.IMPORTANCE_DEFAULT);
//                notificationManager.createNotificationChannel(channel);
//            }
//            notificationManager.notify(0, notifBuilder.build());

        }

        if (remoteMessage.getNotification() != null){

            Bundle bundle = new Bundle();
            bundle.putString("title", remoteMessage.getNotification().getTitle());
            bundle.putString("message", remoteMessage.getNotification().getBody());

            Intent intent = new Intent(this, Promo.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);
            String channelId = "Default";

            NotificationCompat.Builder builder = new  NotificationCompat.Builder(this, channelId)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContentTitle(remoteMessage.getNotification().getTitle())
                    .setContentText(remoteMessage.getNotification().getBody()).setAutoCancel(true).setContentIntent(pendingIntent);;
            NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                NotificationChannel channel = new NotificationChannel(channelId, "Default channel", NotificationManager.IMPORTANCE_DEFAULT);
                manager.createNotificationChannel(channel);
            }
            manager.notify(0, builder.build());
            
        }
    }

    @Override
    public void onNewToken(String s) {
        super.onNewToken(s);
    }
}
