package com.management.agenda.activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;
import com.management.agenda.R;
import com.management.agenda.adapter.EventRecyclerViewAdapter;
import com.management.agenda.controller.DataBaseHelper;
import com.management.agenda.controller.EventoController;
import com.management.agenda.controller.SwipeToDeleteData;
import com.management.agenda.models.Evento;

public class ListarEvento extends AppCompatActivity {

    private RecyclerView recyclerView;
    private EventRecyclerViewAdapter rvAdapter;
    private CoordinatorLayout coordinatorLayout;
    private DataBaseHelper dbh;
    private EventoController ec;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listar_evento);

        dbh = new DataBaseHelper(this);
        ec  = new EventoController(dbh.getAllData());

        recyclerView = findViewById(R.id.recycleView);
        coordinatorLayout = findViewById(R.id.coordinatorLayout);

        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager rvLayout = new LinearLayoutManager(this);

        rvAdapter = new EventRecyclerViewAdapter(dbh.getAllData());

        recyclerView.setLayoutManager(rvLayout);
        recyclerView.setAdapter(rvAdapter);

        /*recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {

                Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.item_scale);
                recyclerView.startAnimation(animation);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                //Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.item_anim02);
                //recyclerView.setAnimation(animation);
            }
        });*/

        enableSwipeToDelete();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        update();
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {

        switch (item.getTitle().toString()){
            case "Editar":
                ActualizarEvento.catchEvent(ec.evento(dbh.getAllData(), item.getItemId()));

                Intent intent = new Intent(this, ActualizarEvento.class);
                startActivity(intent);
                break;
            case "Apagar":
                int id = ec.findId(dbh.getAllData(), item.getItemId());

                if (dbh.delete(id)){
                    rvAdapter.removeItem(item.getItemId());
                    Toast.makeText(getApplicationContext(), "Evento removido com sucesso.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Erro ao remover evento.", Toast.LENGTH_SHORT).show();
                }
                break;
        }
        return true;
    }

    private void enableSwipeToDelete(){
        SwipeToDeleteData swipeToDeleteData = new SwipeToDeleteData(this){
            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction){
                final int position = viewHolder.getAdapterPosition();

                final Evento item = rvAdapter.getEvtList().get(position);
                rvAdapter.removeItem(position);

                Snackbar snackbar = Snackbar.make(coordinatorLayout, "Evento removido", Snackbar.LENGTH_LONG);

                snackbar.setAction("UNDO", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        rvAdapter.restoreItem(item, position);
                        recyclerView.scrollToPosition(position);
                    }
                }).addCallback(new Snackbar.Callback(){
                    @Override
                    public void onDismissed(Snackbar transientBottomBar, int event) {
                        if (event == Snackbar.Callback.DISMISS_EVENT_TIMEOUT) {
                            int id = ec.findId(dbh.getAllData(), position);
                            dbh.delete(id);
                        }
                    }
                });
                snackbar.setActionTextColor(Color.parseColor("#0165C7"));
                snackbar.show();
            }
        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(swipeToDeleteData);
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }

    private void update(){
        rvAdapter.updateItem();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK){
            Intent intent = new Intent(this, TelaPrincipal.class);
            setIntent(intent);
        }
        return super.onKeyDown(keyCode, event);
    }
}
