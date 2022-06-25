package com.example.dairaapp.ParticipantPanel;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dairaapp.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class RegistrationAdapter extends FirebaseRecyclerAdapter<Registration, RegistrationAdapter.ViewHolder> {
    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     *
     */
    public RegistrationAdapter(@NonNull FirebaseRecyclerOptions<Registration> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull RegistrationAdapter.ViewHolder holder, int position, @NonNull Registration model) {
        holder.email.setText(model.getParticipant_mail());
        holder.event.setText(model.getSb_name());
    }

    @NonNull
    @Override
    public RegistrationAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.registration_recycler_row, parent, false);
        return new RegistrationAdapter.ViewHolder(view);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView event,email;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            email = itemView.findViewById(R.id.partitipant_email);
            event = itemView.findViewById(R.id.partitipant_event);
        }
    }
}
