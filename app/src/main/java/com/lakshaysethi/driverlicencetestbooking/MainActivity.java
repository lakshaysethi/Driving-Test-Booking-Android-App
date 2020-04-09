package com.lakshaysethi.driverlicencetestbooking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    public static ArrayList<Pojoclasses.Slot> slotsList= new ArrayList<Pojoclasses.Slot>();
    public static ArrayList<Pojoclasses.User> usersStaticList = new ArrayList<Pojoclasses.User>();

    private Button continueButton;
    private Button showMatrixButton;
    private Button viewMyBookingsButton;
    private Button adminViewButton;
    private EditText licenceInputText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //read from data base and make static
        populateUsersArrayListFromDatabase();
        populateSlotsArrayListFromDatabase(null);

        //get reference to the widgets on the screen
        licenceInputText = findViewById(R.id.licenceInput);
        continueButton =(Button) findViewById(R.id.continueButton);
        showMatrixButton = (Button) findViewById(R.id.showMatrixButton);
        viewMyBookingsButton =(Button) findViewById(R.id.viewMyBookingsButton);
        adminViewButton = (Button) findViewById(R.id.adminViewButton);


        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { if( authenticateUser()!=null){
                       openBookingActivity();

                   } }
        });
        showMatrixButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if( authenticateUser()!=null){openMatrixActivity();

            }

        adminViewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { if( authenticateUser()!=null){
                openBookingActivity();

            } }
        });
        viewMyBookingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { if( authenticateUser()!=null){
                openViewMyBookingsActivity();

            } }
        });
        adminViewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { if( authenticateUser()!=null){
                openAdminActivity();

            } }
        });

    }

    private void openAdminActivity() {

    }

    private void openViewMyBookingsActivity() {
        Intent intent = new Intent(this,ViewMyBookingsActivity.class);
        //intent.putExtra("licenceNumber",licenceString);
        startActivity(intent);
    }

    private void openMatrixActivity() {
        Intent intent = new Intent(this,MatrixActivity.class);
        //intent.putExtra("licenceNumber",licenceString);
        startActivity(intent);
    }

    public void openBookingActivity(){
        Intent intent = new Intent(this,BookSlot.class);
        //intent.putExtra("licenceNumber",licenceString);
        startActivity(intent);
    }


}


