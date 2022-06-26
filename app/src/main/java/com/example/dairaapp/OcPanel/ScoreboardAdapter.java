package com.example.dairaapp.OcPanel;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dairaapp.R;
import com.example.dairaapp.Scoreboard;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class ScoreboardAdapter extends FirebaseRecyclerAdapter<Scoreboard, ScoreboardAdapter.ViewHolder> {
    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public ScoreboardAdapter(@NonNull FirebaseRecyclerOptions<Scoreboard> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull ScoreboardAdapter.ViewHolder holder, int position, @NonNull Scoreboard model) {
        holder.event.setText(model.getEvent_name());
        holder.player1.setText(model.getPlayer1());
        holder.player2.setText(model.getPlayer2());
        holder.player3.setText(model.getPlayer3());
    }

    @NonNull
    @Override
    public ScoreboardAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.scoreboard_recycler_row, parent, false);
        return new ScoreboardAdapter.ViewHolder(view);
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
