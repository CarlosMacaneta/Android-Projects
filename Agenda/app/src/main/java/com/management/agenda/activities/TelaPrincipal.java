package com.management.agenda.activities;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.management.agenda.R;
import com.management.agenda.controller.DataBaseHelper;
import com.management.agenda.models.Evento;

import java.util.Calendar;
import java.util.Locale;
import java.util.Objects;

public class TelaPrincipal extends AppCompatActivity {

    private DataBaseHelper dbh;
    FloatingActionButton btnMenu;
    FloatingActionButton btnAddEvt;
    FloatingActionButton btnListEvt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_principal);

        dbh = new DataBaseHelper(this);
        btnMenu    = findViewById(R.id.bt_menu);
        btnAddEvt  = findViewById(R.id.btnAddEvent);
        btnListEvt = findViewById(R.id.btnListEvent);

        btnAddEvt.hide();
        btnListEvt.hide();

        btnMenu.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                menuConfig();
            }
        });

        btnAddEvt.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View v){
               gotoTelaRegistarEvento();
            }
        });

        btnListEvt.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View v){
                gotoTelaListarEvento();
            }
        });

        fireAlert();
    }

    private void menuConfig() {
        if (!btnAddEvt.isShown() && !btnListEvt.isShown()) {
            Animation fabShow = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_show);

            btnAddEvt.show();
            btnListEvt.show();

            btnAddEvt.setClickable(true);
            btnListEvt.setClickable(true);

            btnAddEvt.startAnimation(fabShow);
            btnListEvt.startAnimation(fabShow);
        }else {
            Animation fabHide = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_scale);

            btnAddEvt.hide();
            btnListEvt.hide();

            btnAddEvt.setClickable(false);
            btnListEvt.setClickable(false);

            btnAddEvt.startAnimation(fabHide);
            btnListEvt.startAnimation(fabHide);
        }
    }

    private void gotoTelaRegistarEvento(){
        Intent intent = new Intent(this, RegistarEvento.class);
        startActivity(intent);
    }

    private void gotoTelaListarEvento(){
        Intent intent = new Intent(this, ListarEvento.class);
        startActivity(intent);
    }

    private void fireAlert(){
        Calendar c = Calendar.getInstance(Locale.getDefault());
        @SuppressLint("DefaultLocale")
        String date = String.format("%02d/%02d/%d", c.get(Calendar.DATE), c.get(Calendar.MONTH)+1, c.get(Calendar.YEAR));
        boolean eventoDia = false;

        for (Evento evento: dbh.getAllData()) {
            if (evento.getDate().equals(date)) {
                eventoDia = true;
                break;
            }
        }

        if (eventoDia) {

            AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

            Intent intent = new Intent(getApplicationContext(), Notificacao.class);
            PendingIntent pi = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), (long) (1000 * 60 * 60 * 24 * 7), pi);
            Objects.requireNonNull(alarmManager).setExact(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pi);
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

}
