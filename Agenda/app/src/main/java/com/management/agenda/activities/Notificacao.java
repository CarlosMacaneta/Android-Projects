package com.management.agenda.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;

import com.management.agenda.R;
import com.management.agenda.controller.DataBaseHelper;
import com.management.agenda.controller.NotificationHelper;
import com.management.agenda.models.Evento;

import java.util.Calendar;
import java.util.Locale;

public class Notificacao extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationHelper notificationHelper = new NotificationHelper(context);
        NotificationCompat.Builder nb = notificationHelper.getChannelNotification();

        notificationHelper.getManager().notify(1001, nb.build());

        nb.setVibrate(new long[]{1000, 1000, 1000, 1000, 1000});
        nb.setLights(Color.BLUE, 3000, 3000);
        nb.setSound(Settings.System.DEFAULT_NOTIFICATION_URI);

        Notification notification = nb.build();

        notification.defaults |= Notification.DEFAULT_VIBRATE;
        notification.defaults |= Notification.DEFAULT_SOUND;
    }
}