package org.hjss;

import org.hjss.booking.BookingManagementService;

public class Main {
    public static void main(String[] args) {

        BookingManagementService bookingSystem = new BookingManagementService();
        bookingSystem.generateSampleData();
        bookingSystem.runCommandInterface();
    }

}