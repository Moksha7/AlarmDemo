package com.example.alarmdemo.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.alarmdemo.R;
import com.example.alarmdemo.data.Alarm;
import com.example.alarmdemo.updatealarm.TimePickerUtil;
import com.example.alarmdemo.updatealarm.UpdateAlarmViewModel;

import java.util.Random;

public class UpdateActivity extends AppCompatActivity {
    int id,pos;
    TimePicker timePicker;
    EditText title,description;
    Button scheduleAlarm;
    CheckBox recurring;
    CheckBox mon;
    CheckBox tue;
    CheckBox wed,thu,fri,sat,sun;
    private UpdateAlarmViewModel updateAlarmViewModel;
    LinearLayout recurringOptions;
    Toolbar toolbar;
    Alarm alarm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        id = getIntent().getIntExtra("ID",-99);
        pos = getIntent().getIntExtra("POS",-99);
        alarm = (Alarm) getIntent().getSerializableExtra("ALARM");
        Log.d("Update Activity","ID"+String.valueOf(id));
        Log.d("Update Activity","POS"+String.valueOf(pos));
        updateAlarmViewModel = ViewModelProviders.of(this).get(UpdateAlarmViewModel.class);

        timePicker = findViewById(R.id.fragment_createalarm_timePicker);
        title = findViewById(R.id.fragment_createalarm_title);
        description = findViewById(R.id.fragment_createalarm_description);
        scheduleAlarm = findViewById(R.id.fragment_createalarm_scheduleAlarm);
        recurring = findViewById(R.id.fragment_createalarm_recurring);
        mon = findViewById(R.id.fragment_createalarm_checkMon);
        tue = findViewById(R.id.fragment_createalarm_checkTue);
        wed = findViewById(R.id.fragment_createalarm_checkWed);
        thu = findViewById(R.id.fragment_createalarm_checkThu);
        fri = findViewById(R.id.fragment_createalarm_checkFri);
        sat = findViewById(R.id.fragment_createalarm_checkSat);
        sun = findViewById(R.id.fragment_createalarm_checkSun);
        recurringOptions = findViewById(R.id.fragment_createalarm_recurring_options) ;

        //ButterKnife.bind(this, view);

        recurring.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    recurringOptions.setVisibility(View.VISIBLE);
                } else {
                    recurringOptions.setVisibility(View.GONE);
                }
            }
        });

        scheduleAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scheduleAlarm();
                    Intent i = new Intent(UpdateActivity.this,MainActivity.class);
                    startActivity(i);
                //Navigation.findNavController(v).navigate(R.id.action_createAlarmFragment_to_alarmsListFragment);
            }
        });

    }


    private void scheduleAlarm() {
       // int alarmId = new Random().nextInt(Integer.MAX_VALUE);
        alarm = new Alarm(
                id,
                TimePickerUtil.getTimePickerHour(timePicker),
                TimePickerUtil.getTimePickerMinute(timePicker),
                title.getText().toString(),
                description.getText().toString(),
                System.currentTimeMillis(),
                true,
                recurring.isChecked(),
                mon.isChecked(),
                tue.isChecked(),
                wed.isChecked(),
                thu.isChecked(),
                fri.isChecked(),
                sat.isChecked(),
                sun.isChecked()
        );

        updateAlarmViewModel.update(alarm);
        alarm.schedule(this);
    }


   @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.mdelete:
                Toast.makeText(getApplicationContext(),"Deleted",Toast.LENGTH_LONG).show();
                updateAlarmViewModel.delete(alarm);
                Intent i = new Intent(UpdateActivity.this,MainActivity.class);
                startActivity(i);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}