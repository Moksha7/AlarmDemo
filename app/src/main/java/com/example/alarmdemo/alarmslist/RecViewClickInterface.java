package com.example.alarmdemo.alarmslist;


import androidx.cardview.widget.CardView;

import com.example.alarmdemo.data.Alarm;

public interface RecViewClickInterface {

    void setOnItemClick(int pos, Alarm alarm,CardView view);
}
