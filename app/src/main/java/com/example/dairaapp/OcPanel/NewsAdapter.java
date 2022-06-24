package com.example.dairaapp.OcPanel;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dairaapp.EventNews;
import com.example.dairaapp.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class NewsAdapter extends FirebaseRecyclerAdapter<EventNews, NewsAdapter.ViewHolder> {
    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public NewsAdapter(@NonNull FirebaseRecyclerOptions<EventNews> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull NewsAdapter.ViewHolder holder, int position, @NonNull EventNews model) {
        holder.sub_event.setText(model.getSb_ev_name());
        if (model.getAdder_username().length() > 0){
            holder.adder.setText(model.getAdder_username());
        }
        holder.msg.setText(model.getMsg());
    }

    @NonNull
    @Override
    public NewsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_recycler_row, parent, false);
        return new NewsAdapter.ViewHolder(view);
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
