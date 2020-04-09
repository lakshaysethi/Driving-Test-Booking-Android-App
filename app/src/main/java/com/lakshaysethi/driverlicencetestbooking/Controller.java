package com.lakshaysethi.driverlicencetestbooking;

import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Controller {
    //available everywhere
    public static ArrayList<Pojoclasses.Slot> slotsList= new ArrayList<Pojoclasses.Slot>();//TODO make = function
    public static ArrayList<Pojoclasses.User> usersStaticList = new ArrayList<Pojoclasses.User>();//TODO make = function
    public static Pojoclasses.User currentUser;//Need to populate on click of main activity

    public Controller(Date startDate) {

    }
    public Controller() {

    }




    //Important Functions below

    public void onMyAppStart(){
        //populateSlotsArrayListFromDatabase();
        populateUsersArrayListFromDatabase();
    }

    public  Pojoclasses.User getCurrentUser(String licenceNumber) {

        if(!licenceNumber.equals("")) {
            for(Pojoclasses.User user : usersStaticList ){
                if (user.licenceNumber.equals(licenceNumber)){

                    currentUser = user;
                    return user;

                }

            }
            Pojoclasses.User newUser = new Pojoclasses.User(licenceNumber);
            usersStaticList.add(newUser);
            currentUser = newUser;
            saveUserStaticLisToDatabase();

            return newUser;
        }


        return null;
    }

//    public ArrayList<Pojoclasses.Slot> getTimeslotBooking(String licenceNumber){
//        Pojoclasses.User u1 = getCurrentUser();
//        if (u1!=null && u1.licenceNumber.equals(licenceNumber) && !u1.bookingsList.isEmpty()){
//            ArrayList<Pojoclasses.Slot> sl = new ArrayList<Pojoclasses.Slot>();
//            for(Pojoclasses.Booking booking:u1.bookingsList){
//                sl.add(booking.slot);
//            }
//            return sl;
//
//        }
//
//        return null;
//    }
//
//    public  boolean bookTimeSlot(Pojoclasses.User u1, String day, int hour) {
//        try {
//
//            if (userHasMoreThanTwoBookings(selectedSlot)) {
//                Toast t1= Toast.makeText(BookSlot.this,"You can NOt have more thant 2 bookings for "+day,Toast.LENGTH_LONG);
//                t1.show();
//            } else {
//
//
//                if (slot_available(day, hour) != null) {
//                    selectedSlot = slot_available(day, hour);
//                    selectedSlot.remainingTimes--;
//                    Pojoclasses.Booking newBooking = new Pojoclasses.Booking(selectedSlot);
//                    //CHECK LATER - need to chk if this user has already booked this slot then he can not book again
//                    u1.bookingsList.add(newBooking);
//                    return true;
//                } else {
//                    //Toast.makeText(this,"FAILED",Toast.LENGTH_LONG);
//                    System.out.println("FAILED to book");
//                    return false;
//                }
//            }
//        }catch(Exception e){
//            e.printStackTrace();
//
//
//        }
//        return false;
//    }



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
