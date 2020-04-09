package com.lakshaysethi.driverlicencetestbooking;


import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.lakshaysethi.driverlicencetestbooking.Pojoclasses.User;
import com.lakshaysethi.driverlicencetestbooking.Pojoclasses.Slot;



public class BookSlot extends AppCompatActivity {//implements AdapterView.OnItemClickListener {

    private Button bookButton;
    private Spinner bookingSlotSpinner;
    private TextView licenceNumberTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_slot_form);


        bookButton = (Button) findViewById(R.id.bookButton);
        //bookingDateSpinner = (Spinner) findViewById(R.id.dateSpinner);
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



    }


    private void book(){
        User u1 = Controller.currentUser;
        String day =((Slot) bookingSlotSpinner.getSelectedItem()).getDate();
        int hour =((Slot) bookingSlotSpinner.getSelectedItem()).getTimeInInt();
//        if(Controller.bookTimeSlot( u1,  day,  hour)){
//            Toast t1= Toast.makeText(BookSlot.this,"Booked Successfully",Toast.LENGTH_LONG);
//            t1.show();
//        }else{
//            Toast t1= Toast.makeText(BookSlot.this,"Booking Failed",Toast.LENGTH_LONG);
//            t1.show();
//        }
    }
}




