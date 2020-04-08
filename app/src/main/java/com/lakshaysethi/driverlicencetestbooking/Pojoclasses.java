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
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
            String dateString = sdf.format(date);
            return "date and time: " + dateString;
        }

        public Slot(Date date) {
            this.date = date;
            this.remainingTimes = 10;

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
