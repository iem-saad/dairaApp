package com.example.dairaapp.AdminPanel;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dairaapp.Event;
import com.example.dairaapp.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;

import java.util.HashMap;
import java.util.Map;

public class EventsAdapter extends FirebaseRecyclerAdapter<Event, EventsAdapter.ViewHolder> {
    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public EventsAdapter(@NonNull FirebaseRecyclerOptions<Event> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position, @NonNull Event model) {

        holder.name.setText(model.getName());
        Log.d("TAG", "NAME IS "+ model.getName());
        Log.d("TAG", "DESC IS "+ model.getDescription());
        holder.desc.setText(model.getDescription());
        if(model.getMentor_id().length() > 0 ){
            holder.mentor.setText(model.getMentor_id());
        }

        holder.edit.setOnClickListener((view)-> {
            final DialogPlus dialogPlus = DialogPlus.newDialog(holder.name.getContext())
            .setContentHolder(new com.orhanobut.dialogplus.ViewHolder(R.layout.event_dialog))
            .setExpanded(true, 1100)
            .create();
            View myView = dialogPlus.getHolderView();
            final EditText name = myView.findViewById(R.id.up_ev_name);
            final EditText desc = myView.findViewById(R.id.up_ev_desc);
            final Button submit = myView.findViewById(R.id.button5);
            name.setText(model.getName());
            desc.setText(model.getDescription());
            dialogPlus.show();

            submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Map<String, Object> map = new HashMap<>();
                    map.put("name", name.getText().toString());
                    map.put("description", desc.getText().toString());
                    map.put("mentor_id", model.getMentor_id());
                    FirebaseDatabase.getInstance().getReference().child("events").child(getRef(position).getKey()).updateChildren(map).
                            addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    dialogPlus.dismiss();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            dialogPlus.dismiss();
                        }
                    });
                }
            });
        });

        holder.delete.setOnClickListener((view) ->{
            AlertDialog.Builder builder = new AlertDialog.Builder(holder.name.getContext());
            builder.setTitle("DELETE PANEL");
            builder.setMessage("Are You Sure?");
            builder.setPositiveButton("YES", ((dialogInterface, i) -> {
                FirebaseDatabase.getInstance().getReference().child("events").child(getRef(position).getKey()).removeValue();
            }));

            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                }
            });
            builder.show();
        });

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.events_recycler_row, parent, false);
        return new ViewHolder(view);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView edit, delete;
        TextView name, desc, mentor;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.eventnameshow);
            desc = itemView.findViewById(R.id.eventdescshow);

            edit = itemView.findViewById(R.id.editeventicon);
            delete = itemView.findViewById(R.id.deleventicon);
            mentor = itemView.findViewById(R.id.eventmentorshow);
        }
    }
}
