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
    Object layout;
    Controller controller = new Controller();

//constructor
    public SlotAdapter(ArrayList<Pojoclasses.Slot> slotAdapterSlotArrayList, Context context, Object layout) {
        this.slotAdapterSlotArrayList = slotAdapterSlotArrayList;
        this.context = context;
        this.layout = layout;
    }


// Layout setter/ view creater
    @NonNull
    @Override
    public SlotViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater li = LayoutInflater.from(parent.getContext());
        View view = li.inflate((Integer) layout,parent,false);
        SlotViewHolder newSlotViewHolder = new SlotViewHolder(view);


        return newSlotViewHolder;
    }
// main method
    @Override
    public void onBindViewHolder(@NonNull final SlotViewHolder holder, final int position) {
        if (holder.workingon==1){
            holder.dateAndDayTextView.setText("\n\nDate:   14/04/2020  \n\nDay:     Thursday");
        }else if(holder.workingon ==2){
            holder.availableInstructorsTextView.setText("Available Instructors: "+Integer.toString(slotAdapterSlotArrayList.get(position).remainingTimes));
            holder.timeTextView.setText("Time: " + slotAdapterSlotArrayList.get(position).getTimeString() + " 1 Hr");
            final Pojoclasses.Slot tempSlot = slotAdapterSlotArrayList.get(position);
            holder.matrixBookButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String u1Licence = Controller.licenceNumber;
                    String day = tempSlot.getDateString();
                    int hour = tempSlot.getTimeInInt();
                    if(controller.bookTimeSlot( u1Licence,  day,  hour)){

                        controller.showToast(v.getContext(),"Booked Successfully");
                    }else{
                        controller.showToast(v.getContext(),"Booking Failed- do you have more than 2 bookings for the same day?");
                    }
                    onBindViewHolder( holder,  position);

                }
            });
        }
    }

    public static class SlotViewHolder extends RecyclerView.ViewHolder{
        public final int workingon;
        TextView timeTextView;
        TextView availableInstructorsTextView;
        Button matrixBookButton;
        TextView dateAndDayTextView;

        public SlotViewHolder(@NonNull View itemView) {
            super(itemView);
            timeTextView = (TextView) itemView.findViewById(R.id.slotTimeTextView);
            if(timeTextView==null){
                workingon = 1;//working on date and Day
                dateAndDayTextView =(TextView)itemView.findViewById(R.id.dateAndTimeTextView);
            }else{
                workingon = 2;
                availableInstructorsTextView = (TextView) itemView.findViewById(R.id.slotRemainingTextView);
                matrixBookButton = (Button) itemView.findViewById(R.id.matrixBookButton);
            }

        }
    }

    @Override
    public int getItemCount() {

        return slotAdapterSlotArrayList.size();
    }


}
