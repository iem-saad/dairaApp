package com.example.dairaapp.OcPanel;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dairaapp.EvVenue;
import com.example.dairaapp.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class EvVenueAdapter extends FirebaseRecyclerAdapter<EvVenue, EvVenueAdapter.ViewHolder> {
    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public EvVenueAdapter(@NonNull FirebaseRecyclerOptions<EvVenue> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull EvVenueAdapter.ViewHolder holder, int position, @NonNull EvVenue model) {
        holder.event.setText(model.getSb_name());
        holder.venue.setText(model.getVenue_name());
    }

    @NonNull
    @Override
    public EvVenueAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.evvenue_recycler_row, parent, false);
        return new EvVenueAdapter.ViewHolder(view);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView event,venue;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            event = itemView.findViewById(R.id.senameshow);
            venue = itemView.findViewById(R.id.sevenueshow);
        }
    }
}
