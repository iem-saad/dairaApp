package com.example.dairaapp.ParticipantPanel;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dairaapp.EventNews;
import com.example.dairaapp.R;

import java.util.ArrayList;

public class SimpleNewsAdapter extends RecyclerView.Adapter<SimpleNewsAdapter.ViewHolder>{
    ArrayList<EventNews> newsArrayList = new ArrayList<>();

    public SimpleNewsAdapter(ArrayList<EventNews> newsArrayList) {
        this.newsArrayList = newsArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.news_recycler_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        EventNews model = newsArrayList.get(position);
        holder.sub_event.setText(model.getSb_ev_name());
        if (model.getAdder_username().length() > 0){
            holder.adder.setText(model.getAdder_username());
        }
        holder.msg.setText(model.getMsg());
    }

    @Override
    public int getItemCount() {
        return newsArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView adder,sub_event,msg;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            adder = itemView.findViewById(R.id.newsaddershow);
            sub_event = itemView.findViewById(R.id.newseventshow);
            msg = itemView.findViewById(R.id.newsmsgshow);
        }
    }
}
