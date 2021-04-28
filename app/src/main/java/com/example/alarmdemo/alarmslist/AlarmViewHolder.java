package com.example.alarmdemo.alarmslist;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.alarmdemo.R;
import com.example.alarmdemo.activities.UpdateActivity;
import com.example.alarmdemo.data.Alarm;


public class AlarmViewHolder extends RecyclerView.ViewHolder {
    private CardView cardView;
    private TextView alarmTime;
    private ImageView alarmRecurring;
    private TextView alarmRecurringDays;
    private TextView alarmTitle;

    Switch alarmStarted;

    private OnToggleAlarmListener listener;
    private RecViewClickInterface clickInterface;

    public AlarmViewHolder(@NonNull View itemView, OnToggleAlarmListener listener,RecViewClickInterface clickInterface) {
        super(itemView);
        cardView = itemView.findViewById(R.id.card_alarms);
        alarmTime = itemView.findViewById(R.id.item_alarm_time);
        alarmStarted = itemView.findViewById(R.id.item_alarm_started);
        alarmRecurring = itemView.findViewById(R.id.item_alarm_recurring);
        alarmRecurringDays = itemView.findViewById(R.id.item_alarm_recurringDays);
        alarmTitle = itemView.findViewById(R.id.item_alarm_title);
        this.listener = listener;
        this.clickInterface = clickInterface;


    }

    public void bind(Alarm alarm, Context ctx,int pos) {
        String alarmText = String.format("%02d:%02d", alarm.getHour(), alarm.getMinute());

        alarmTime.setText(alarmText);
        alarmStarted.setChecked(alarm.isStarted());

        if (alarm.isRecurring()) {
            alarmRecurring.setImageResource(R.drawable.ic_repeat_black_24dp);
            alarmRecurringDays.setText(alarm.getRecurringDaysText());
        } else {
            alarmRecurring.setImageResource(R.drawable.ic_looks_one_black_24dp);
            alarmRecurringDays.setText("Once Off");
        }

        if (alarm.getTitle().length() != 0) {
            alarmTitle.setText(String.format("%s | %s", alarm.getTitle(), alarm.getDescription()));
        } else {
            alarmTitle.setText(String.format("%s | %s ", "Alarm", "Description"));
        }

        alarmStarted.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                listener.onToggle(alarm);
            }
        });

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(itemView.getContext(),"ccccc",Toast.LENGTH_LONG).show();
                Intent i = new Intent(itemView.getContext(), UpdateActivity.class);
                i.putExtra("POS",pos);
                i.putExtra("ID",alarm.getAlarmId());
                i.putExtra("ALARM",alarm);
                Log.d("Update Activity","ID"+String.valueOf(alarm.getAlarmId()));
                Log.d("Update Activity","POS"+String.valueOf(getAdapterPosition()));
                itemView.getContext().startActivity(i);
            }
        });


    }
}
