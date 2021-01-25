package com.management.agenda.adapter;

import android.content.Context;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.management.agenda.R;
import com.management.agenda.models.Evento;

import java.util.List;

public class EventRecyclerViewAdapter extends RecyclerView.Adapter<EventRecyclerViewAdapter.RecyclerViewHolder> {

    private List<Evento> evtList;
    private int lastposition = -1;

    public static class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {

        private TextView title;
        private TextView date;
        private TextView desc;
        private CardView parent;

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);

            parent = itemView.findViewById(R.id.parent);
            title = itemView.findViewById(R.id.textViewTitle);
            date  = itemView.findViewById(R.id.textViewDate);
            desc  = itemView.findViewById(R.id.textViewDesc);
            itemView.setOnCreateContextMenuListener(this);
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            menu.add(0, getAdapterPosition(), 0, "Editar");
            menu.add(0, getAdapterPosition(), 0,"Apagar");
        }
    }

    public EventRecyclerViewAdapter(@NonNull List<Evento> evts) {
        evtList = evts;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.evento_item, parent, false);

        return new RecyclerViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerViewHolder holder, int position) {
        Evento currentEvt = evtList.get(position);

        holder.title.setText(currentEvt.getTitle());
        holder.date.setText(currentEvt.getDate());
        holder.desc.setText(currentEvt.getDescription());

        setAnimation(holder.parent, position);
    }

    private void setAnimation(View v, int position) {

        Animation animation = AnimationUtils.loadAnimation(v.getContext(), (position > lastposition) ? R.anim.item_scale: R.anim.item_scale);
        v.startAnimation(animation);
        lastposition = position;
        /*if (position > lastposition) {
            animate(v);
            lastposition = position;
        } else if (lastposition == position) {
            animate(v);
            lastposition = -1;
        }else {
            animate(v);
        }*/
    }

    public void removeItem(int position){
        evtList.remove(position);

        notifyItemRemoved(position);
        notifyItemRangeChanged(position, evtList.size());
    }

    public void updateItem(){
        notifyDataSetChanged();
    }

    public void restoreItem(Evento evento, int position){
        evtList.add(position, evento);
        notifyItemInserted(position);
    }

    public List<Evento> getEvtList() {
        return evtList;
    }

    @Override
    public long getItemId(int position) {
        return evtList.indexOf(evtList.get(position));
    }

    @Override
    public int getItemCount() {
        return evtList.size();
    }
}
