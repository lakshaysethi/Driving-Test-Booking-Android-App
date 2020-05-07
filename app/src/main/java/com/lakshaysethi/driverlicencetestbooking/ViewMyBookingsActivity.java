package com.lakshaysethi.driverlicencetestbooking;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class ViewMyBookingsActivity extends AppCompatActivity {
    TextView myBookingsTextView;

    Controller controller =new Controller();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_my_bookings);

        TextView myBookingsTextView = (TextView)findViewById(R.id.myBookingsTextView);
        Pojoclasses.User u1 = controller.getOrCreateUser(controller.licenceNumber) ;
        String myBookingsString=  u1.toString();

        myBookingsTextView.setText(myBookingsString);



    }
}
