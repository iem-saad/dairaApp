package com.example.dairaapp.MentorPanel;

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

import com.bumptech.glide.Glide;
import com.example.dairaapp.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;

import java.util.HashMap;
import java.util.Map;

public class SubEventAdapter extends FirebaseRecyclerAdapter<SubEvent, SubEventAdapter.ViewHolder> {

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public SubEventAdapter(@NonNull FirebaseRecyclerOptions<SubEvent> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position, @NonNull SubEvent model) {
        holder.name.setText(model.getName());
        Log.d("TAG", "NAME IS "+ model.getName());
        Log.d("TAG", "DESC IS "+ model.getDescription());
        holder.desc.setText(model.getDescription());
        holder.desc.setText(model.getDescription());
        Glide.with(holder.desc.getContext())
                .load(model.getUrl())
                .into(holder.url);


        holder.edit.setOnClickListener((view)-> {
            final DialogPlus dialogPlus = DialogPlus.newDialog(holder.name.getContext())
                    .setContentHolder(new com.orhanobut.dialogplus.ViewHolder(R.layout.subevent_dialog))
                    .setExpanded(true, 1100)
                    .create();
            View myView = dialogPlus.getHolderView();
            final EditText name = myView.findViewById(R.id.up_sbev_name);
            final EditText desc = myView.findViewById(R.id.up_sbev_desc);
            final EditText img = myView.findViewById(R.id.up_sbev_img);
            final Button submit = myView.findViewById(R.id.upsbsubmit);
            name.setText(model.getName());
            desc.setText(model.getDescription());
            img.setText(model.getUrl());
            dialogPlus.show();

            submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Map<String, Object> map = new HashMap<>();
                    map.put("name", name.getText().toString());
                    map.put("description", desc.getText().toString());
                    map.put("url", img.getText().toString());
                    map.put("event_id", model.getEvent_id());
                    map.put("event_name", model.getEvent_name());
                    FirebaseDatabase.getInstance().getReference().child("subevents").child(getRef(position).getKey()).updateChildren(map).
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
                FirebaseDatabase.getInstance().getReference().child("subevents").child(getRef(position).getKey()).removeValue();
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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.subevent_recycler_row, parent, false);
        return new SubEventAdapter.ViewHolder(view);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView edit, delete, url;
        TextView name, desc;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.subeventnameshow);
            desc = itemView.findViewById(R.id.subeventdescshow);

            edit = itemView.findViewById(R.id.editsubeventicon);
            delete = itemView.findViewById(R.id.delsubeventicon);
            url = itemView.findViewById(R.id.subeventimgshow);
        }
    }
}
