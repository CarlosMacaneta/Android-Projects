package com.management.agenda.controller;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.Build;

import androidx.core.app.NotificationCompat;

import com.management.agenda.R;
import com.management.agenda.models.Evento;

import java.util.Calendar;

public class NotificationHelper extends ContextWrapper {

    private DataBaseHelper dbh;
    private final CharSequence name = getString(R.string.channel_name);
    private final String description = getString(R.string.channel_description);
    private NotificationManager notificationManager;

    public NotificationHelper(Context base) {
        super(base);

        dbh = new DataBaseHelper(this);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createChanel();
        }
    }

    @TargetApi(Build.VERSION_CODES.O)
    private void createChanel(){
        NotificationChannel channel = new NotificationChannel("0", name, NotificationManager.IMPORTANCE_DEFAULT);
        channel.setDescription(description);
        getManager().createNotificationChannel(channel);
    }

    public NotificationManager getManager(){
        if (notificationManager == null) {
            notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        }
        return notificationManager;
    }

    private int countEvent() {
        Calendar c = Calendar.getInstance();
        @SuppressLint("DefaultLocale")
        String date = String.format("%02d/%02d/%d", c.get(Calendar.DATE), c.get(Calendar.MONTH)+1, c.get(Calendar.YEAR));
        int count = 0;

        for (Evento evento: dbh.getAllData()) {
            if (evento.getDate().equals(date)) {
                count++;
            }
        }
        return count;
    }

    public NotificationCompat.Builder getChannelNotification() {
        return  new NotificationCompat.Builder(this, "0")
                .setSmallIcon(R.mipmap.note_round_icon)
                .setContentTitle("Eventos para este dia.")
                .setContentText("Você tem "+countEvent()+" marcados para o dia de hoje.")
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setAutoCancel(true);
    }
}
