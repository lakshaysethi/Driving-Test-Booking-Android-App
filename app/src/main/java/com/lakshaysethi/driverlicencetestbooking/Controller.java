package com.lakshaysethi.driverlicencetestbooking;

import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Controller {
    //available everywhere

    public static ArrayList<Pojoclasses.Slot> slotsList= new ArrayList<Pojoclasses.Slot>();
    public static ArrayList<Pojoclasses.User> usersStaticList = new ArrayList<Pojoclasses.User>();
    public static Pojoclasses.User currentUser;//Need to populate on click of main activity

    public Controller(Date startDate) {
        populateUsersArrayListFromDatabase();
        populateSlotsArrayListFromDatabase(startDate,);
    }

    //Important Functions below
    public Pojoclasses.User authenticateUser(String licenceNumber) {

        if(!licenceNumber.equals("")) {
            for(Pojoclasses.User user : usersStaticList ){
                if (user.licenceNumber.equals(licenceNumber)){
                    Toast t2 = Toast.makeText(MainActivity.this,"Welcome Back! :)",Toast.LENGTH_LONG);
                    t2.show();
                    currentUser = user;
                    return user;

                }

            }
            Pojoclasses.User newUser = new Pojoclasses.User(licenceInputText.getText().toString());
            usersStaticList.add(newUser);
            currentUser = newUser;
            saveUserStaticLisToDatabase();
            Toast t1 = Toast.makeText(this,"Welcome New User! you have been added to the Database :)",Toast.LENGTH_LONG);
            t1.show();
            return newUser;
        }
        else{
            Toast t2 = Toast.makeText(MainActivity.this,"PLEASE enter your licence number before continueing :)",Toast.LENGTH_SHORT);
            t2.show();
        }

        return null;
    }



    public ArrayList<Pojoclasses.Slot> getTimeslotBooking(String licenceNumber){
        Pojoclasses.User u1 = authenticateUser();
        if (u1!=null && u1.licenceNumber.equals(licenceNumber) && !u1.bookingsList.isEmpty()){
            ArrayList<Pojoclasses.Slot> sl = new ArrayList<Pojoclasses.Slot>();
            for(Pojoclasses.Booking booking:u1.bookingsList){
                sl.add(booking.slot);
            }
            return sl;

        }

        return null;
    }

    public void populateSlotsArrayListFromDatabase(Date argStartDate) {
        //TODO
        if(argStartDate!=null){

        }else{

            String startDateString = "09/04/2020 09:00";

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            Date startDate = null;
            try {
                startDate = sdf.parse(startDateString);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Date dateForSlot = startDate;
            Calendar cal = Calendar.getInstance(); // creates calendar

            for (int i = 0; i < 7; i++) {//days
                cal.setTime(dateForSlot);
                // sets calendar time/date
                if(cal.get(Calendar.DAY_OF_WEEK)==1||cal.get(Calendar.DAY_OF_WEEK)==7){
                    cal.add(Calendar.DAY_OF_WEEK, 1); // adds day
                    dateForSlot = cal.getTime();
                }else{
                    dateForSlot = cal.getTime();
                    for (int j = 0; j < 8; j++) {//8 slots each day
                        slotsList.add(new Pojoclasses.Slot(dateForSlot));
                        // long millis = date.getTime();
//                   cal.setTime(dateForSlot); // sets calendar time/date
                        cal.add(Calendar.HOUR_OF_DAY, 1); // adds one hour
                        dateForSlot = cal.getTime();

                    }
                    cal.add(Calendar.HOUR_OF_DAY, 16);
                    dateForSlot = cal.getTime();
                }
            }
        }


    }

    public void populateUsersArrayListFromDatabase() {
        //TODO
    }

    public void saveUserStaticLisToDatabase() {
        //TODO
    }
}
