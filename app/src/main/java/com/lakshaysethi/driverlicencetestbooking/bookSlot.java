package com.lakshaysethi.driverlicencetestbooking;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class bookSlot extends AppCompatActivity {


    private ArrayList<TimeSlot> timeSlotArrayList2;
    private RecyclerView recyclerview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_slot);
        recyclerview = findViewById(R.id.recyclerView);
        serTimeSlotInfo();
        setAdapter();


    }

    private void setAdapter() {
        recyclerAdapter adapter = new recyclerAdapter(timeSlotArrayList2);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerview.setLayoutManager(layoutManager);
        recyclerview.setItemAnimator(new DefaultItemAnimator());
        recyclerview.setAdapter(adapter);
    }

    private void serTimeSlotInfo() {
        int time = 900;
        for (int i=0;i<8;i++){

            timeSlotArrayList2.add(new TimeSlot(time,1));
            time+=100;
        }
    }


}
