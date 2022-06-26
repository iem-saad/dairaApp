package com.example.dairaapp.ParticipantPanel;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dairaapp.R;
import com.example.dairaapp.Scoreboard;

import java.util.ArrayList;

public class SimpleScoreAdapter  extends RecyclerView.Adapter<SimpleScoreAdapter.ViewHolder>{
    ArrayList<Scoreboard> newsArrayList = new ArrayList<>();

    public SimpleScoreAdapter(ArrayList<Scoreboard> newsArrayList) {
        this.newsArrayList = newsArrayList;
    }

    @NonNull
    @Override
    public SimpleScoreAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.scoreboard_recycler_row, parent, false);
        return new SimpleScoreAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SimpleScoreAdapter.ViewHolder holder, int position) {
        Scoreboard model = newsArrayList.get(position);
        holder.event.setText(model.getEvent_name());
        holder.player1.setText(model.getPlayer1());
        holder.player2.setText(model.getPlayer2());
        holder.player3.setText(model.getPlayer3());
    }

    @Override
    public int getItemCount() {
        return newsArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView event, player1,player2,player3;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            event = itemView.findViewById(R.id.scoreboard_ev_name);
            player1 = itemView.findViewById(R.id.player1sc);
            player2 = itemView.findViewById(R.id.player2sc);
            player3 = itemView.findViewById(R.id.player3sc);
        }
    }
}