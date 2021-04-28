package com.example.alarmdemo.alarmslist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.alarmdemo.R;
import com.example.alarmdemo.data.Alarm;

import java.util.ArrayList;
import java.util.List;

public class AlarmRecyclerViewAdapter extends RecyclerView.Adapter<AlarmViewHolder> {
    private List<Alarm> alarms;
    private OnToggleAlarmListener listener;
    private RecViewClickInterface clickInterface;
    private Context ctx;

    public AlarmRecyclerViewAdapter(OnToggleAlarmListener listener,RecViewClickInterface clickInterface,Context ctx) {
        this.alarms = new ArrayList<Alarm>();
        this.listener = listener;
        this.clickInterface = clickInterface;
        this.ctx = ctx;
    }

    @NonNull
    @Override
    public AlarmViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_alarm, parent, false);
        return new AlarmViewHolder(itemView, listener,clickInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull AlarmViewHolder holder, int position) {
        Alarm alarm = alarms.get(position);
        holder.bind(alarm,ctx,position);
    }

    public Alarm getData(int pos) {
        return alarms.get(pos);
    }

    public void removeData(int pos) {
        alarms.remove(pos);
        notifyDataSetChanged();
    }
    @Override
    public int getItemCount() {
        return alarms.size();
    }

    public void setAlarms(List<Alarm> alarms) {
        this.alarms = alarms;
        notifyDataSetChanged();
    }

    @Override
    public void onViewRecycled(@NonNull AlarmViewHolder holder) {
        super.onViewRecycled(holder);
        holder.alarmStarted.setOnCheckedChangeListener(null);
    }


}

