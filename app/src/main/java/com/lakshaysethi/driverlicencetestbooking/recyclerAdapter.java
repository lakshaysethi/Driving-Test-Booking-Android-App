package com.lakshaysethi.driverlicencetestbooking;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class recyclerAdapter extends RecyclerView.Adapter<recyclerAdapter.MyViewHolder> {

    private ArrayList<TimeSlot> timeSlotArrayList;

    public recyclerAdapter(ArrayList<TimeSlot> timeSlotArrayList){
        this.timeSlotArrayList = timeSlotArrayList;
    }


    public class MyViewholder extends RecyclerView.ViewHolder{
        
    }

    @NonNull
    @Override
    public recyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull recyclerAdapter.MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
