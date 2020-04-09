package com.lakshaysethi.driverlicencetestbooking;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import com.lakshaysethi.driverlicencetestbooking.Pojoclasses.User;
import com.lakshaysethi.driverlicencetestbooking.Pojoclasses.Slot;



public class BookSlotActivity extends AppCompatActivity {

    private Button bookButton;
    private Spinner bookingSlotSpinner;
    private TextView licenceNumberTextView;
    private Spinner bookingDateSpinner;
    private Controller controller;
    private Button viewMyBookingsButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_slot);

        controller = new Controller();

        bookButton = (Button) findViewById(R.id.bookButton);
       // bookingDateSpinner = (Spinner) findViewById(R.id.dateSpinner);
        bookingSlotSpinner = (Spinner) findViewById(R.id.slotSpinner);
        licenceNumberTextView = (TextView) findViewById(R.id.licenceTextView);


        //set the licence number on this screen:
        licenceNumberTextView.setText("Licence # " +Controller.currentUser.licenceNumber);


        bookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });

        //populate the spinners
        ArrayAdapter<Slot> slotArrayAdapter = new ArrayAdapter<Slot>(this, android.R.layout.simple_spinner_item, Controller.slotsList);
        slotArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        bookingSlotSpinner.setAdapter(slotArrayAdapter);

        viewMyBookingsButton =(Button) findViewById(R.id.viewMyBookingsButton);
        viewMyBookingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { {openViewMyBookingsActivity();} }
        });


    }


    private void book(){
        User u1 = Controller.currentUser;
        String day =((Slot) bookingSlotSpinner.getSelectedItem()).getDate();
        int hour =((Slot) bookingSlotSpinner.getSelectedItem()).getTimeInInt();
        controller.showToast(this,"No logic for booking implemented");
        System.out.println(
        "Booked Successfully"+
        "Booking Failed"+
        "You can NOt have more thant 2 bookings for "+day);
    }
    private void openViewMyBookingsActivity() {
        Intent intent = new Intent(this,ViewMyBookingsActivity.class);
        //intent.putExtra("licenceNumber",licenceString);
        startActivity(intent);
    }
}







