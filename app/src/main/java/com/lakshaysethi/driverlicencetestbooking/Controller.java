package com.lakshaysethi.driverlicencetestbooking;

import android.content.Context;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import com.lakshaysethi.driverlicencetestbooking.Pojoclasses.User;
import com.lakshaysethi.driverlicencetestbooking.Pojoclasses.Slot;
import java.util.Calendar;
import java.util.Date;

public class Controller {
    //available everywhere
    public final static ArrayList<Slot> slotsList = new ArrayList<Slot>();//this class populates and keeps all the slots
    public final static ArrayList<User> usersList = new ArrayList<User>();
    public static String licenceNumber;


    //constructor
    public Controller() { }




//Important Functions below



    public ArrayList<Slot> getTimeslotBooking(String licenceNumber){

        return null;
    }

    public  boolean bookTimeSlot(String licenceNumber, String day, int hour) {//TODO
        /*
        * what this function does:
        * 1.checks if the licence number given to this fn is not "" if it is then return false
        * 2.it gets the user from licenceNumber and
        *   2.5. sets this user as the currentUser        *
        * 3.it finds the Slot from the slots list using the day and the time;
        * 4.checks if user should be allowed to book
        * 5.it creates a new booking Object using the Slot and adds this booking object to User
        * 6.it returns true then
        * */
        //check licence
        if(!licenceNumber.equals("")) {
            User u1 = getOrCreateUser(licenceNumber);

            Slot s1 = getSlot(day,hour);

        }
        return false;

    }

    private Slot getSlot(String day, int hour) {

        Date d1 = parseInputDateAndTime( day, hour);
        Slot s1 = new Slot(d1);

        for(Slot s2: slotsList){
            if(s2.equals(s1)){
                return s1;
            }
        }


        return null;
    }

    public Date parseInputDateAndTime(String day,int hour){
        String hourString = getStringHourFromInt(hour);
        String correctString = day + " " + hourString;

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        Date d1 = null;
        try {
            d1 = sdf.parse(correctString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return d1;

    }

    public String getStringHourFromInt(int hour) {
        String hourString = Integer.toString(hour);

        if (hour < 959) {
            hourString = "0" + hourString;
            hourString = hourString.substring(0, 2) + ":" + hourString.substring(2, hourString.length());
        } else {
            hourString = hourString.substring(0, 2) + ":" + hourString.substring(2, hourString.length());
        }
        return hourString;
    }

    public User getOrCreateUser(String licenceNumber){
        for(User user: usersList) {
            if (user.licenceNumber.equals(licenceNumber)) {
                return user;
            }
        }
        User u1 = new User(licenceNumber);
        usersList.add(u1);
        return u1;

    }

    public void showToast(Context c, String s) {
        Toast t1 = Toast.makeText(c,s,Toast.LENGTH_SHORT);
        t1.show();
    }


    public void populateSlotsArrayList() {


        String startDateString = "15/04/2020 09:00";

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
            if (cal.get(Calendar.DAY_OF_WEEK) == 1 || cal.get(Calendar.DAY_OF_WEEK) == 7) {
                cal.add(Calendar.DAY_OF_WEEK, 1); // adds day
                dateForSlot = cal.getTime();
            } else {
                dateForSlot = cal.getTime();
                for (int j = 0; j < 8; j++) {//8 slots each day
                    slotsList.add(new Slot(dateForSlot));
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