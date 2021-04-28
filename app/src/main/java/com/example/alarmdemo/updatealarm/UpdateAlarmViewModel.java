package com.example.alarmdemo.updatealarm;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.alarmdemo.data.Alarm;
import com.example.alarmdemo.data.AlarmRepository;


public class UpdateAlarmViewModel extends AndroidViewModel {
    private AlarmRepository alarmRepository;

    public UpdateAlarmViewModel(@NonNull Application application) {
        super(application);

        alarmRepository = new AlarmRepository(application);
    }

    public void update(Alarm alarm) {
        alarmRepository.update(alarm);
    }

    public void delete(Alarm alarm) {
        alarmRepository.delete(alarm);
    }

    public void deleteById(int id) {
        alarmRepository.deleteById(id);
    }
}
