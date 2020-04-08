package com.lakshaysethi.driverlicencetestbooking;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SlotAdapter extends RecyclerView.Adapter<SlotAdapter.SlotViewHolder> {

    ArrayList<Pojoclasses.Slot> slotAdapterSlotArrayList;
    Context contect;

    public SlotAdapter(ArrayList<Pojoclasses.Slot> slotAdapterSlotArrayList, Context contect) {
        this.slotAdapterSlotArrayList = slotAdapterSlotArrayList;
        this.contect = contect;
    }

    @NonNull
    @Override
    public SlotViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater li = LayoutInflater.from(parent.getContext());
        View view = li.inflate(R.layout.slot_button,parent,false);
        SlotViewHolder newSlotViewHolder = new SlotViewHolder(view);


        return newSlotViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SlotViewHolder holder, int position) {
        holder.availableInstructorsTextView.setText(Integer.toString(slotAdapterSlotArrayList.get(position).remainingTimes));
        holder.timeTextView.setText(slotAdapterSlotArrayList.get(position).getTime());
    }

    @Override
    public int getItemCount() {

        return slotAdapterSlotArrayList.size();
    }

    public static class SlotViewHolder extends RecyclerView.ViewHolder{
        TextView timeTextView;
        TextView availableInstructorsTextView;

        public SlotViewHolder(@NonNull View itemView) {
            super(itemView);
            timeTextView = (TextView) itemView.findViewById(R.id.slotTimeTextView);
            availableInstructorsTextView = (TextView) itemView.findViewById(R.id.slotRemainingTextView);

        }
    }
}
