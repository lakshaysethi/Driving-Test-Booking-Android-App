package com.lakshaysethi.driverlicencetestbooking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static ArrayList<Pojoclasses.Slot> slotsList= new ArrayList<Pojoclasses.Slot>();
    public static ArrayList<Pojoclasses.User> users = new ArrayList<Pojoclasses.User>();

    public static Pojoclasses.User currentUser;//Need to populate on click of main activity

    private Button continueButton;
    private EditText licenceInputText;
    private String licenceString;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //read from data base and make static
        populateUsersArrayListFromDatabase();
        populateSlotsArrayListFromDatabase();


        licenceInputText = findViewById(R.id.licenceInput);



        continueButton =(Button) findViewById(R.id.bookButton);
        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                licenceString =  licenceInputText.toString();
                currentUser = new Pojoclasses.User(licenceString);
                openBookingActivity();
            }
        });

    }

    private void populateSlotsArrayListFromDatabase() {
        //TODO
    }

    private void populateUsersArrayListFromDatabase() {
        //TODO
    }

//
    public void openBookingActivity(){
        Intent intent = new Intent(this,BookSlot.class);
        //intent.putExtra("licenceNumber",licenceString);
        startActivity(intent);
    }
}


