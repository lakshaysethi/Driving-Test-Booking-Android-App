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

    public static Pojoclasses.User currentUser;//Need to populate on click of main activity

    private Button continueButton;
    private Button showMatrixButton;
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


        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                authenticateUser();
                openBookingActivity();
            }
        });
        showMatrixButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMatrixActivity();
            }
        });


    }


    //Important Functions below
    private void authenticateUser() {
        for(Pojoclasses.User user : usersStaticList ){
            if (user.licenceNumber.equals(licenceInputText.getText().toString())){
                Toast.makeText(this,"Welcome Back! :)",Toast.LENGTH_LONG);
                currentUser = user;
            }
        }
        Pojoclasses.User newUser = new Pojoclasses.User(licenceInputText.getText().toString());
        usersStaticList.add(newUser);
        currentUser = newUser;
        saveUserStaticLisToDatabase();
        Toast.makeText(this,"Welcome New User! you have been added to the Database :)",Toast.LENGTH_LONG);
    }

    private void saveUserStaticLisToDatabase() {
        //TODO
    }




    private void populateSlotsArrayListFromDatabase(Date startdate) {
        //TODO
       if(startdate!=null){




       }else{
           String startDateString = "11/04/2020 09:00";

           SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/YYYY HH:mm");
           Date startDate = null;
           try {
               startDate = sdf.parse(startDateString);
           } catch (ParseException e) {
               e.printStackTrace();
           }
           Date dateForSlot = startDate;

           for (int i = 0; i < 40; i++) {
            //TODO make sure weekends are ignored
               slotsList.add(new Pojoclasses.Slot(dateForSlot));
               // long millis = date.getTime();

               Calendar cal = Calendar.getInstance(); // creates calendar
               cal.setTime(dateForSlot); // sets calendar time/date
               cal.add(Calendar.HOUR_OF_DAY, 1); // adds one hour
               dateForSlot = cal.getTime();


           }
       }


    }

    private void populateUsersArrayListFromDatabase() {
        //TODO
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
//

}


