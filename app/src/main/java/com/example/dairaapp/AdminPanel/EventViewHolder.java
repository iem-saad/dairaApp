package com.example.dairaapp.AdminPanel;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.dairaapp.R;

public class EventViewHolder  extends RecyclerView.ViewHolder {
    View view;

    public EventViewHolder(View itemView) {
        super(itemView);
        view = itemView;
    }

    public void setName(String title) {
        TextView tvTitle = (TextView) view.findViewById(R.id.eventnameshow);
        tvTitle.setText(title);
    }

    public void setDesc(String desc) {
        TextView tvDesc = (TextView) view.findViewById(R.id.eventdescshow);
        tvDesc.setText(desc);
    }

}
