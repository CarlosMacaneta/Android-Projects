package com.management.agenda.controller;

import android.view.View;
import android.widget.Toast;
import com.management.agenda.models.Evento;

import java.util.ArrayList;
import java.util.List;

public class EventoController {

    private List<Evento> eventos = new ArrayList<>();

    public EventoController(List<Evento> eventos){
        eventos = eventos;
    }

    public EventoController(){
    }

    public int findId(int position){
        return eventos.get(position).getId();
    }

    public int findId(List<Evento> event, int pos){
        return event.get(pos).getId();
    }

    public Evento evento(List<Evento> event, int pos){
        return event.get(pos);
    }
}
