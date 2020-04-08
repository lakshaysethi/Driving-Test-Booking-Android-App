package com.lakshaysethi.driverlicencetestbooking;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

public class BookSlot extends Activity implements AdapterView.OnItemClickListener {
    private Button bookButton;
    private Date bookingDate;
    private Spinner bookingDateSpinner;
    private String bookingSlot;
    private Spinner bookingSlotSpinner;
    public static ArrayList<Slot> slotsList;
    public static ArrayList<User> users = new ArrayList<User>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_slot_form);
        bookButton = findViewById(R.id.book);



        bookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean is_success = doTheseThings();
            }

            private boolean doTheseThings() {
                String licenceNumber = "123";
                User u1 = createNewUser(licenceNumber);
                users.add(u1);
                String day = "11/04/2020";
                int hour = 900;
                String startDateString = "11/04/2020 09:00";
                ArrayList<Slot> slotsList = openShop(startDateString);

                if (slot_available(day, hour) != null) {
                    bookTimeSlot(u1, day, hour);
                } else {
                    showNotBookedMessage();
                }

                printAllBookings(users);
                return true;
            }

            private void showNotBookedMessage() {
                System.out.println("Not Booked");
            }

            private User createNewUser(String licenceNumber) {
                return new User(licenceNumber);
            }

            private Slot slot_available(String day, int hour) {
                String hourString = Integer.toString(hour);

                if (hour < 959) {
                    hourString = "0" + hourString;
                    hourString = hourString.substring(0, 2) + ":" + hourString.substring(2, hourString.length());
                    System.out.println(hourString);

                } else {
                    hourString = hourString.substring(0, 2) + ":" + hourString.substring(2, hourString.length());
                }


                String correctString = day + " " + hourString;

                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/YYYY HH:mm");
                Date dateToCheck = null;
                try {
                    dateToCheck = sdf.parse(correctString);
                } catch (ParseException e) {
                    e.printStackTrace();
                }


                for (int i = 0; i < slotsList.size(); i++) {
                    Slot testSlot = slotsList.get(i);
                    if (testSlot.date.equals(dateToCheck)) {
                        if (testSlot.remainingTimes >= 1) {
                            testSlot.remainingTimes--;
                            return testSlot;
                        }
                    }
                }

                return null;
            }

            private void bookTimeSlot(User u1, String day, int hour) {
                Slot selectedSlot = slot_available(day, hour);
                Booking newBooking = new Booking(selectedSlot);
                //CHECK LATER - need to chk if this user has already booked this slot then he can not book again
                u1.bookingsList.add(newBooking);

            }

            private ArrayList<Slot> openShop(String startDateString) {

                slotsList = new ArrayList<Slot>();

                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/YYYY HH:mm");
                Date startDate = null;
                try {
                    startDate = sdf.parse(startDateString);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                Date dateForSlot = startDate;

                for (int i = 0; i < 40; i++) {

                    slotsList.add(new Slot(dateForSlot));
                    // long millis = date.getTime();

                    Calendar cal = Calendar.getInstance(); // creates calendar
                    cal.setTime(dateForSlot); // sets calendar time/date
                    cal.add(Calendar.HOUR_OF_DAY, 1); // adds one hour
                    dateForSlot = cal.getTime();


                }


                return slotsList;
            }

            private void printAllBookings(ArrayList<User> users) {
                System.out.println("List of all bookings");
                for (int i = 0; i < users.size(); i++) {
                    System.out.println("for " + users.get(i).licenceNumber);
                    for (int j = 0; j < users.get(i).bookingsList.size(); j++) {
                        System.out.println("      " + users.get(i).bookingsList.get(j).refNo);
                        System.out.println("      " + users.get(i).bookingsList.get(j).slot.toString());
                    }
                }

            }


        });


        ////////////////////////////////////////Load Screen//////////////////////////////////////////
        //1. In Drop Down There has to be a list of date
        //2. A list of slots
        //3. Click A button, based on selected item, dothesethings() would be called
        ////////////////////////////////////////////////////////////////////////////////////////////


        Spinner bookingDateSpinner = findViewById(R.id.spinner_date_selector);
        Spinner bookingSlotSpinner = findViewById(R.id.spinner);
        //TODO:bookingDateSpinner
        //Array of dates
        //1-8 Date dd-MM-yyyy + Slot number [1,2,3,4,5,6,7,8]
        DateFormat sdf = new SimpleDateFormat("dd/MM/YYYY");
        Calendar c1 = Calendar.getInstance();
        Date today = Calendar.getInstance().getTime();
        //yesterday
        c1.add(Calendar.DATE, -1);

        ArrayList<String> nextFiveWorkingDays = new ArrayList<String>();

        while (nextFiveWorkingDays.size() <= 5) {
            if (c1.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || c1.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {

            } else {
                nextFiveWorkingDays.add(sdf.format(today));
            }

        }
        ///Dates

        ArrayAdapter<String> dateAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,nextFiveWorkingDays); //selected item will look like a spinner set from XML
        dateAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        bookingSlotSpinner.setAdapter(dateAdapter);
        bookingSlotSpinner.setOnItemClickListener(this);

        //Populates Slots in dropdown spinner menu
        ArrayAdapter<CharSequence> slotAdapter = ArrayAdapter.createFromResource(this, R.array.SlotNames, android.R.layout.simple_spinner_item);
        slotAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        bookingSlotSpinner.setAdapter(slotAdapter);
        bookingSlotSpinner.setOnItemClickListener(this);



    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String selectedSlot = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), selectedSlot, Toast.LENGTH_SHORT).show();

        //At this time, just store the value in a selected_slot_string

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

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
        private String licenceNumber;
        private ArrayList<Booking> bookingsList;

        public User(String licenceNumber) {
            this.licenceNumber = licenceNumber;
            this.bookingsList = new ArrayList<Booking>();

        }

        public String getLicenceNumber() {
            return licenceNumber;
        }

        public void setLicenceNumber(String licenceNumber) {
            this.licenceNumber = licenceNumber;
        }

        public ArrayList<Booking> getBookingsList() {
            return bookingsList;
        }

        public void setBookingsList(ArrayList<Booking> bookingsList) {
            this.bookingsList = bookingsList;
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
