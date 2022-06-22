package com.example.dairaapp;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class UsersAdapter extends FirebaseRecyclerAdapter<User, UsersAdapter.ViewHolder> {

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public UsersAdapter(@NonNull FirebaseRecyclerOptions<User> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull User model) {
        holder.name.setText(model.getName());
        Log.d("TAG", "NAME IS "+ model.getName());
        holder.u_name.setText(model.getUsername());
        holder.pass.setText(model.getPassword());
        holder.email.setText(model.getEmail());
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.mentor_row, parent, false);
        return new UsersAdapter.ViewHolder(view);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, u_name, pass,email;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.mentornameshow);
            u_name = itemView.findViewById(R.id.mentorusernameshow);
            pass = itemView.findViewById(R.id.mentorpassshow);
            email = itemView.findViewById(R.id.mentoremailshow);

        }
    }
}
