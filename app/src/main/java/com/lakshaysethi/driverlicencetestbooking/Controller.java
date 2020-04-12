package com.lakshaysethi.driverlicencetestbooking;

import android.content.Context;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import com.lakshaysethi.driverlicencetestbooking.Pojoclasses.User;
import com.lakshaysethi.driverlicencetestbooking.Pojoclasses.Slot;
import com.lakshaysethi.driverlicencetestbooking.Pojoclasses.Booking;
import java.util.Calendar;
import java.util.Date;

public class Controller {
    //available everywhere
    public final static ArrayList<Slot> slotsList = new ArrayList<Slot>();
    public final static ArrayList<User> usersList = new ArrayList<User>();
    public static String licenceNumber;


    //constructor
    public Controller() {

        populateSlotsArrayList();
    }




    //Important Assignment Functions below


    //===============================================================================

    public ArrayList<Slot> getTimeslotBooking(String licenceNumber){
        User u1 = getOrCreateUser(licenceNumber);
        ArrayList<Slot> al = u1.getAllSlots();

        return al;
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
        * 6. modifies the slot object's remainings
        * 7.it returns true then
        * */
        //check licence
        if(!licenceNumber.equals("")) {

            User u1 = getOrCreateUser(licenceNumber);
            Slot s1 = getSlot(day,hour);
            Booking b1 = new Booking(s1);
            if(u1.licenceNumber.equals("911")){
                if(s1.remainingTimes>=1){
                    u1.bookingsList.add(b1);
                    s1.remainingTimes--;
                    return true;

                }
            }

            if(!u1.hasMoreThanOneBookingsForAday(s1)){
                if(!getTimeslotBooking(licenceNumber).contains(s1)){// do not book the dame slot
                    if(s1.remainingTimes>=1){
                        u1.bookingsList.add(b1);
                        s1.remainingTimes--;
                        return true;

                    }
                }

            }
        }
        return false;

    }


    public ArrayList<Slot> getSlots(String day){
        /*
        * Causion while useing this - stick to the good date format ok?
        * ie dd/MM/yyyy
        * */
        ArrayList<Slot> als = new ArrayList<Slot>();
        for (Slot slot: slotsList ){
            if(slot.getDateString().equals(day)){
                als.add(slot);
            }
        }
        return als;

    }

    //================================================================================



    private Slot getSlot(String day, int hour) {

        Date d1 = parseInputDateAndTime( day, hour);
        Slot s1 = new Slot(d1);

        for(Slot s2: slotsList){
            if(s2.date.equals(s1.date)){
                s1= s2;
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


    public ArrayList<Pojoclasses.Slot> getArrayListOfUniqueDates(ArrayList<Pojoclasses.Slot> slotsList) {
        ArrayList<Pojoclasses.Slot> al = new ArrayList<Pojoclasses.Slot>();
        int count=0;
        for(int i=0;i<5;i++){
            al.add(slotsList.get(count));
            count+=8;
        }
        return  al;

    }
}