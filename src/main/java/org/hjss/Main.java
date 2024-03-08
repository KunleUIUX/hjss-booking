package org.hjss;

import org.hjss.booking.BookingManagementService;
import org.hjss.booking.BookingManagementSystem;

import java.util.InputMismatchException;

public class Main {
    public static void main(String[] args) {

        BookingManagementSystem bookingSystem = new BookingManagementSystem();
        bookingSystem.runCommandInterface();
        bookingSystem.generateSampleData();

    }
}