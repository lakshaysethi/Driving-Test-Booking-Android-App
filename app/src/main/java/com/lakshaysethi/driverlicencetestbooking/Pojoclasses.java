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
            return  dateString;
        }

        public Slot(Date date) {
            this.date = date;
            this.remainingTimes = 10;

        }

        public String getDate() {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            String dateString = sdf.format(date);
            return dateString;
        }
        public String getTime() {
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
            String dateString = sdf.format(date);
            return dateString;
        }
        public int getTimeInInt(){
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
            String dateString = sdf.format(date);
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
    }

    public static class User {
        public String licenceNumber;
        public ArrayList<Booking> bookingsList;

        public User(String licenceNumber) {
            this.licenceNumber = licenceNumber;
            this.bookingsList = new ArrayList<Booking>();

        }

    }

    public static class Booking {
        public Slot slot;
        public String refNo;

        public Booking(Slot slot) {
            this.slot = slot;
            this.refNo = "#" + UUID.randomUUID().toString();//+ " "+ this.slot.toString();
        }
    }

}
