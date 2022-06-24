package com.example.dairaapp.MentorPanel;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dairaapp.R;
import com.example.dairaapp.Venue;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class VenuesAdapter extends FirebaseRecyclerAdapter<Venue, VenuesAdapter.ViewHolder> {
    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public VenuesAdapter(@NonNull FirebaseRecyclerOptions<Venue> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull VenuesAdapter.ViewHolder holder, int position, @NonNull Venue model) {
        holder.name.setText(model.getName());
        Log.d("TAG", "NAME IS "+ model.getName());
    }

    @NonNull
    @Override
    public VenuesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.venue_recycler_row, parent, false);
        return new VenuesAdapter.ViewHolder(view);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.venuenameshow);
        }
    }
}
