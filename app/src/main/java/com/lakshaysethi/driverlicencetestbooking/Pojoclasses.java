package com.lakshaysethi.driverlicencetestbooking;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

public class Pojoclasses {


    public static class Slot {
        public int remainingTimes;
        public Date date;

        @Override
        public String toString() {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            String dateString = sdf.format(date);
            return  dateString + this.remainingTimes +" left";
        }
        public Slot(Date date) {
            this.date = date;
            this.remainingTimes = 10;

        }
        public String getDateString() {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            String dateString = sdf.format(this.date);
            return dateString;
        }
        public String getTimeString() {
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
            String timeString = sdf.format(this.date);
            return timeString;
        }
        public int getTimeInInt(){
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
            String dateString = sdf.format(this.date);
            String[] arr =  dateString.split(":");

            int timeInt=900;
            try{
                dateString = arr[0]+arr[1];
                timeInt = Integer.parseInt(dateString);
            }catch (Exception e){
                e.printStackTrace();
            }
            return timeInt;
        }
        public Boolean isAvailable() {
            if (this.remainingTimes >= 1) {
                return true;
            }else{
                return false;
            }
        }

    }

    public static class User {
        public String licenceNumber;
        public ArrayList<Booking> bookingsList;

        public User(String licenceNumber) {
            this.licenceNumber = licenceNumber;
            this.bookingsList = new ArrayList<Booking>();
        }
        public boolean hasMoreThanTwoBookings(Slot s1) {
            int count=0;
            for (Pojoclasses.Booking booking : this.bookingsList){
                    if(s1.equals(booking.slot)){
                        count++;
                    }
            }
            if(count>=2){
                return true;
            }
            return false;

        }

        @Override
        public String toString(){
            String bookingsString ="";
            for(Booking bookingObj:this.bookingsList){
                bookingsString += bookingObj.toString() +"\n";
            }
            return "Bookings for #"+ this.licenceNumber + "\n\n\n" + bookingsString;
        }
    }

    public static class Booking {
        public Slot slot;
        public String refNo;

        public Booking(Slot slot) {
            this.slot = slot;
            this.refNo = "#" + UUID.randomUUID().toString();//+ " "+ this.slot.toString();
        }
        @Override
        public String toString(){
            return "Booking "+this.refNo+ "\n"+ this.slot.getDateString()+ " "+this.slot.getTimeString();
        }
    }

}
