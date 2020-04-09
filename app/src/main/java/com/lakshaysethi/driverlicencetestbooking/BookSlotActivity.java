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
        bookingSlotSpinner = (Spinner) findViewById(R.id.slotSpinner);
        licenceNumberTextView = (TextView) findViewById(R.id.licenceTextView);
        viewMyBookingsButton =(Button) findViewById(R.id.viewMyBookingsButton);
        // bookingDateSpinner = (Spinner) findViewById(R.id.dateSpinner);



        licenceNumberTextView.setText("Licence # " +Controller.currentUser.licenceNumber);

        viewMyBookingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { {openViewMyBookingsActivity();} }
        });

        ArrayAdapter<Slot> saa = new ArrayAdapter<Slot>(this, android.R.layout.simple_spinner_item, Controller.slotsList);
        saa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        bookingSlotSpinner.setAdapter(saa);



        bookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Slot s1 =(Slot) bookingSlotSpinner.getSelectedItem();
                if(controller.bookTimeSlot(licenceNumberTextView.getText().toString(),s1.getDateString(),s1.getTimeInInt())){
                    controller.showToast(BookSlotActivity.this,"Successfully Booked "+s1.toString());
                    controller.showToast(BookSlotActivity.this," You can also check your bookings in the My Bookings Section");

                }else {
                    controller.showToast(BookSlotActivity.this,"Failed to book: "+s1.toString());
                }
            }
        });





    }



    private void openViewMyBookingsActivity() {
        Intent intent = new Intent(this,ViewMyBookingsActivity.class);
        //intent.putExtra("licenceNumber",licenceString);
        startActivity(intent);
    }
}







