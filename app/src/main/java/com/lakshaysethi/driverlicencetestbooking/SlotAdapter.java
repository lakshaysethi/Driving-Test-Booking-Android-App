package com.lakshaysethi.driverlicencetestbooking;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SlotAdapter extends RecyclerView.Adapter<SlotAdapter.SlotViewHolder> {

    ArrayList<Pojoclasses.Slot> slotAdapterSlotArrayList;
    Context context;
    BookSlot newBSObject = new BookSlot();

    public SlotAdapter(ArrayList<Pojoclasses.Slot> slotAdapterSlotArrayList, Context context) {
        this.slotAdapterSlotArrayList = slotAdapterSlotArrayList;
        this.context = context;
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
    public void onBindViewHolder(@NonNull final SlotViewHolder holder, final int position) {
        final TextView availableInstructorsTextView= holder.availableInstructorsTextView;

        final TextView timeTextView= holder.timeTextView;



        holder.availableInstructorsTextView.setText("Available Instructors: "+Integer.toString(slotAdapterSlotArrayList.get(position).remainingTimes));
        holder.timeTextView.setText("Time: " + slotAdapterSlotArrayList.get(position).getTime() + " 1 Hr");
        holder.selectedSlot =slotAdapterSlotArrayList.get(position);
        holder.matrixBookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Pojoclasses.User u1 = Controller.currentUser;
                String day = holder.selectedSlot.getDate();
                int hour = holder.selectedSlot.getTimeInInt();
                //newBSObject.bookTimeSlot( u1,  day,  hour);
                onBindViewHolder( holder,  position);
            }
        });
    }

    @Override
    public int getItemCount() {

        return slotAdapterSlotArrayList.size();
    }

    public static class SlotViewHolder extends RecyclerView.ViewHolder{
        TextView timeTextView;
        TextView availableInstructorsTextView;
        Button matrixBookButton;
        Pojoclasses.Slot selectedSlot;

        public SlotViewHolder(@NonNull View itemView) {
            super(itemView);
            timeTextView = (TextView) itemView.findViewById(R.id.slotTimeTextView);
            availableInstructorsTextView = (TextView) itemView.findViewById(R.id.slotRemainingTextView);
            matrixBookButton = (Button) itemView.findViewById(R.id.matrixBookButton);
            selectedSlot = null;
        }
    }
}
