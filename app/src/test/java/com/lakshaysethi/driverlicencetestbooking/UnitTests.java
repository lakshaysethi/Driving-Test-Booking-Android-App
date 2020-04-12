package com.lakshaysethi.driverlicencetestbooking;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class UnitTests {

    Controller controller = new Controller();

    @Test
    public void testBookSlotsShouldPassOneBooking() {

//dd/MM/yyyy
        assertTrue(controller.bookTimeSlot("user1","15/04/2020",900));
    }

    @Test
    public void testBookSlotsShouldFailTwoOrMoreDifferentSlotsBookingsInADayBySameUser() {

//dd/MM/yyyy
        assertTrue(controller.bookTimeSlot("user1","17/04/2020",900));
        assertFalse(controller.bookTimeSlot("user1","17/04/2020",1000));
    }

    @Test
    public void testBookSlotsShouldFailSameSlotsBookingsInADayBySameUser() {

//dd/MM/yyyy
        assertTrue(controller.bookTimeSlot("user4","17/04/2020",900));
        assertFalse(controller.bookTimeSlot("user4","17/04/2020",900));

    }

    @Test
    public void testBookSlotsShouldFailforMorethanTwoDifferentBookingsInADayBySameUser() {

//dd/MM/yyyy
        assertTrue(controller.bookTimeSlot("user3","20/04/2020",900));
        assertFalse(controller.bookTimeSlot("user3","20/04/2020",1000));
        assertFalse(controller.bookTimeSlot("user3","20/04/2020",1100));

    }
    @Test
    public void testBookSlotsShouldFailWithTenBookings() {


        assertTrue(controller.bookTimeSlot("user1","16/04/2020",900));
        assertTrue(controller.bookTimeSlot("user2","16/04/2020",900));
        assertTrue(controller.bookTimeSlot("user3","16/04/2020",900));
        assertTrue(controller.bookTimeSlot("user4","16/04/2020",900));
        assertTrue(controller.bookTimeSlot("user5","16/04/2020",900));
        assertTrue(controller.bookTimeSlot("user6","16/04/2020",900));
        assertTrue(controller.bookTimeSlot("user7","16/04/2020",900));
        assertTrue(controller.bookTimeSlot("user8","16/04/2020",900));
        assertTrue(controller.bookTimeSlot("user9","16/04/2020",900));
        assertTrue(controller.bookTimeSlot("user10","16/04/2020",900));

        assertFalse ((controller.bookTimeSlot("user11","16/04/2020",900)));
    }


}