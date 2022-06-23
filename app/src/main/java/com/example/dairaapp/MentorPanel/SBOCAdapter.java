package com.example.dairaapp.MentorPanel;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dairaapp.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class SBOCAdapter extends FirebaseRecyclerAdapter<OCEV, SBOCAdapter.ViewHolder> {

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public SBOCAdapter(@NonNull FirebaseRecyclerOptions<OCEV> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull OCEV model) {
        holder.oc_name.setText(model.getOc_name());
        holder.oc_mail.setText(model.getOc_mail());
        holder.ev_name.setText(model.getSb_name());
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sboc_recycler_row, parent, false);
        return new SBOCAdapter.ViewHolder(view);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView oc_name, oc_mail, ev_name;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            oc_name = itemView.findViewById(R.id.oc_nameshow);
            oc_mail = itemView.findViewById(R.id.oc_mailshow);

            ev_name = itemView.findViewById(R.id.sb_nameshow);
        }
    }
}
