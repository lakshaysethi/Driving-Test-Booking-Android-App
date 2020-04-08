package com.lakshaysethi.driverlicencetestbooking;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import com.lakshaysethi.driverlicencetestbooking.Pojoclasses.Booking;
import com.lakshaysethi.driverlicencetestbooking.Pojoclasses.User;
import com.lakshaysethi.driverlicencetestbooking.Pojoclasses.Slot;

import static com.lakshaysethi.driverlicencetestbooking.MainActivity.currentUser;
import static com.lakshaysethi.driverlicencetestbooking.MainActivity.slotsList;

public class BookSlot extends AppCompatActivity {//implements AdapterView.OnItemClickListener {


    private Button bookButton;
    private Spinner bookingDateSpinner;
    private Spinner bookingSlotSpinner;
    private TextView licenceNumberTextView;
    private static Slot selectedSlot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_slot_form);


        bookButton = (Button) findViewById(R.id.bookButton);
        bookingDateSpinner = (Spinner) findViewById(R.id.dateSpinner);
        bookingSlotSpinner = (Spinner) findViewById(R.id.slotSpinner);
        licenceNumberTextView = (TextView) findViewById(R.id.licenceTextView);


        //set the licence number on this screen:
        licenceNumberTextView.setText(MainActivity.currentUser.licenceNumber);
        bookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                User u1 = currentUser;
                String day =((Slot) bookingSlotSpinner.getSelectedItem()).getDate();
                int hour =((Slot) bookingSlotSpinner.getSelectedItem()).getTimeInInt();
                if(bookTimeSlot( u1,  day,  hour)){
                    Toast t1= Toast.makeText(BookSlot.this,"Booked Successfully",Toast.LENGTH_LONG);
                    t1.show();
                }else{
                    Toast t1= Toast.makeText(BookSlot.this,"Booking Failed Successfully",Toast.LENGTH_LONG);
                    t1.show();
                }
            }
        });

        //populate the spinners
        ArrayAdapter<Slot> slotArrayAdapter = new ArrayAdapter<Slot>(this, android.R.layout.simple_spinner_item, slotsList);
        slotArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        bookingSlotSpinner.setAdapter(slotArrayAdapter);



    }




    public boolean bookTimeSlot(User u1, String day, int hour) {
        try {

            if (userHasMoreThanTwoBookings(selectedSlot)) {
                Toast t1= Toast.makeText(BookSlot.this,"You can NOt have more thant 2 bookings for "+day,Toast.LENGTH_LONG);
                t1.show();
            } else {


                if (slot_available(day, hour) != null) {
                    selectedSlot = slot_available(day, hour);
                    selectedSlot.remainingTimes--;
                    Booking newBooking = new Booking(selectedSlot);
                    //CHECK LATER - need to chk if this user has already booked this slot then he can not book again
                    u1.bookingsList.add(newBooking);
                    return true;
                } else {
                    //Toast.makeText(this,"FAILED",Toast.LENGTH_LONG);
                    System.out.println("FAILED to book");
                    return false;
                }
                }
            }catch(Exception e){
            e.printStackTrace();


        }
        return false;
    }

    private static boolean userHasMoreThanTwoBookings(Slot selectedSlot) {
        int count=0;
        for (Booking booking : currentUser.bookingsList){
            if (booking.slot.getDate().equals(selectedSlot.getDate())){
                count++;
                if(count>=2){
                    return true;
                }
                count++;
            }
        }
        return false;

    }

    private static Slot slot_available(String day, int hour) {
        String hourString = Integer.toString(hour);

        if (hour < 959) {
            hourString = "0" + hourString;
            hourString = hourString.substring(0, 2) + ":" + hourString.substring(2, hourString.length());
            //SEE SYSO
            System.out.println(hourString);

        } else {
            hourString = hourString.substring(0, 2) + ":" + hourString.substring(2, hourString.length());
        }


        String correctString = day + " " + hourString;

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        Date dateToCheck = null;
        try {
            dateToCheck = sdf.parse(correctString);
        } catch (ParseException e) {
            e.printStackTrace();
        }


        for (int i = 0; i < slotsList.size(); i++) {
            Slot testSlot = slotsList.get(i);
            if (testSlot.date.equals(dateToCheck)) {
                if (testSlot.remainingTimes >= 1) {

                    return testSlot;
                }
            }
        }
        System.out.println("returning Null");
        return null;
    }

}




