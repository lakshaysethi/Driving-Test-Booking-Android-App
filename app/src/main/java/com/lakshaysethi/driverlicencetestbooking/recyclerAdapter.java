package com.lakshaysethi.driverlicencetestbooking;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class recyclerAdapter extends RecyclerView.Adapter<recyclerAdapter.MyViewHolder> {

    private ArrayList<TimeSlot> timeSlotArrayList;

    public recyclerAdapter(ArrayList<TimeSlot> timeSlotArrayList){
        this.timeSlotArrayList = timeSlotArrayList;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView timeSlotText;
        public MyViewHolder(final View view){
            super(view);
            timeSlotText = view.findViewById(R.id.timeSlotTextView);
        }
    }

    @NonNull
    @Override
    public recyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_items,parent,false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull recyclerAdapter.MyViewHolder holder, int position) {
        int time = timeSlotArrayList.get(position).getStartTime();
        int duration = timeSlotArrayList.get(position).getDuration();
        holder.timeSlotText.setText(time);

    }

    @Override
    public int getItemCount() {
        return timeSlotArrayList.size();

    }
}
