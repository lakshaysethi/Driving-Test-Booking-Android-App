package com.lakshaysethi.driverlicencetestbooking;

import android.content.Context;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import java.util.Calendar;
import java.util.Date;

public class Controller {
    //available everywhere
    public final static ArrayList<Pojoclasses.Slot> slotsList = new ArrayList<Pojoclasses.Slot>();
    public final static ArrayList<Pojoclasses.User> usersList = new ArrayList<Pojoclasses.User>();
    public static Pojoclasses.User currentUser;


    //constructor
    public Controller() { }


//Important Functions below



    public  Pojoclasses.User getCurrentUser(String licenceNumber) {
        if(!licenceNumber.equals("")) {
            for(Pojoclasses.User user : usersList){
                if (user.licenceNumber.equals(licenceNumber)){
                    currentUser = user;
                    return user;
                }
            }
        }
        return currentUser;
    }

    public  Pojoclasses.User addNewUser(String licenceNumber) {

        if(!licenceNumber.equals("")) {
            Pojoclasses.User newUser = new Pojoclasses.User(licenceNumber);
            usersList.add(newUser);
            currentUser = newUser;
            //saveUserStaticLisToDatabase();
            System.out.println("Added user with Licence #"+ newUser.licenceNumber);
            return newUser;

            }
        System.out.println("add user failed - is the licenceNumber entered?");
        return null;
    }

    public ArrayList<Pojoclasses.Slot> getTimeslotBooking(String licenceNumber){

        return null;
    }

    public  boolean bookTimeSlot(String licenceNumber, String day, int hour) {//TODO
        /*
        * what this function does:
        * 1.checks if the licence number given to this fn is not "" if it is then return false
        * 2.it finds the user from the licenceNumber and
        *   2.5. sets this user as the currentUser        *
        * 3.it finds the Slot from the slots list using the day and the time;
        * 4.it creates a new booking Object using the Slot and adds this booking object to User
        * 5. it returns true then
        * */
        //check licence
        if(!licenceNumber.equals("")) {
            Pojoclasses.User u1 = getUserFromList(licenceNumber);
            currentUser = u1;
            Pojoclasses.Slot s1 = getSlot(day,hour);
        }
        return false;

    }

    private Pojoclasses.Slot getSlot(String day, int hour) {

        Date d1 = parseInputDateAndTime( day, hour);
        Pojoclasses.Slot s1 = new Pojoclasses.Slot(d1);

        for(Pojoclasses.Slot s2: slotsList){
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

    public Pojoclasses.User getUserFromList(String licenceNumber){
        for(Pojoclasses.User user: usersList) {
            if (user.licenceNumber.equals(licenceNumber)) {
                return user;
            }
        }
        //returns null if user not found in the UserStatic Array List
        return null;
    }

    public void showToast(Context c, String s) {
        Toast t1 = Toast.makeText(c,s,Toast.LENGTH_SHORT);
        t1.show();
    }

    public boolean isOldUser(String licenceNumber) {
        if(getUserFromList(licenceNumber)!=null){
            return true;
        }
        return false;
    }

    public boolean setCurrentUser(String licenceNumber) {
        currentUser = getUserFromList(licenceNumber);
        return currentUser != null;
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