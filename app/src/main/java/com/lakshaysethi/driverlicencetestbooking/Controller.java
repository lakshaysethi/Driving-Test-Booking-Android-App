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
    public  ArrayList<Pojoclasses.Slot> slotsList;
    public  ArrayList<Pojoclasses.User> usersList;
    public  Pojoclasses.User currentUser;

    //constructor
    public Controller() {
        this.slotsList = new ArrayList<Pojoclasses.Slot>();
        this.usersList = new ArrayList<Pojoclasses.User>();;
        this.currentUser = new Pojoclasses.User("123");
    }


//Important Functions below

    public void onMyAppStart(){
        //populateSlotsArrayListFromDatabase();
      //   populateUsersArrayListFromDatabase();
    }

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

    public  boolean bookTimeSlot(String licenceNumber, String day, int hour) {

        return false;
    }



    private  Pojoclasses.Slot slot_available(String day, int hour) {
        String hourString = Integer.toString(hour);

        if (hour < 959) {
            hourString = "0" + hourString;
            hourString = hourString.substring(0, 2) + ":" + hourString.substring(2, hourString.length());
            //SEE SYSO
            System.out.println(hourString);

        } else {
            hourString = hourString.substring(0, 2) + ":" + hourString.substring(2, hourString.length());
        }


        String correctString = day + " " + hourString;

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        Date dateToCheck = null;
        try {
            dateToCheck = sdf.parse(correctString);
        } catch (ParseException e) {
            e.printStackTrace();
        }


        for (int i = 0; i < slotsList.size(); i++) {
            Pojoclasses.Slot testSlot = slotsList.get(i);
            if (testSlot.date.equals(dateToCheck)) {
                if (testSlot.remainingTimes >= 1) {

                    return testSlot;
                }
            }
        }
        System.out.println("returning Null");
        //means slot is not available for the specific day 
        return null;
    }



    private  boolean userHasMoreThanTwoBookings(Pojoclasses.Slot selectedSlot) {
        int count=0;
        for (Pojoclasses.Booking booking : currentUser.bookingsList){
            if (booking.slot.getDate().equals(selectedSlot.getDate())){
                count++;
                if(count>=2){
                    return true;
                }
                count++;
            }
        }
        return false;

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
        if(currentUser.equals(null)){
            return false;
        }else{
            return true;
        }
    }
}
