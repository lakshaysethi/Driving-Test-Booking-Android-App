package com.lakshaysethi.driverlicencetestbooking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static ArrayList<Pojoclasses.Slot> slotsList= new ArrayList<Pojoclasses.Slot>();
    public static ArrayList<Pojoclasses.User> usersStaticList = new ArrayList<Pojoclasses.User>();

    public static Pojoclasses.User currentUser;//Need to populate on click of main activity

    private Button continueButton;
    private EditText licenceInputText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //read from data base and make static
        populateUsersArrayListFromDatabase();
        populateSlotsArrayListFromDatabase();

        //get reference to the widgets on the screen
        licenceInputText = findViewById(R.id.licenceInput);
        continueButton =(Button) findViewById(R.id.continueButton);



        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                authenticateUser();

                openBookingActivity();




            }
        });



    }
//Important Functions below
    private void authenticateUser() {
        for(Pojoclasses.User user : usersStaticList ){
            if (user.licenceNumber.equals(licenceInputText.getText().toString())){
                Toast.makeText(this,"Welcome Back! :)",Toast.LENGTH_LONG);

            }
        }
        Pojoclasses.User newUser = new Pojoclasses.User(licenceInputText.getText().toString());
        usersStaticList.add(newUser);
        saveUserStaticLisToDatabase();
        Toast.makeText(this,"Welcome New User! you have been added to the Database :)",Toast.LENGTH_LONG);
    }

    private void saveUserStaticLisToDatabase() {
        //TODO
    }

    public void openBookingActivity(){
        Intent intent = new Intent(this,BookSlot.class);
        //intent.putExtra("licenceNumber",licenceString);
        startActivity(intent);
    }


    private void populateSlotsArrayListFromDatabase() {
        //TODO
    }

    private void populateUsersArrayListFromDatabase() {
        //TODO
    }

//

}


