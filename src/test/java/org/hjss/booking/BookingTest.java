package org.hjss.booking;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class BookingTest {

    @Test
    public void testBookingCreation() {
        // Test booking creation and getters
        Learner learner = new Learner(UUID.randomUUID(), "John Doe", "Male", 10, "123-456-7890", 3);
        Lesson lesson = new Lesson(/* initialize lesson parameters */);

        Booking booking = new Booking();
        booking.setUuid(UUID.randomUUID());
        booking.setLearner(learner);
        booking.setLesson(lesson);
        booking.setStatus("booked");
        booking.setReview("Good lesson");
        booking.setBookingId("123456");
        booking.setTimestamp(LocalDateTime.now());

        assertEquals(learner, booking.getLearner());
        assertEquals(lesson, booking.getLesson());
        assertNotNull(booking.getUuid());
        assertEquals("booked", booking.getStatus());
        assertEquals("Good lesson", booking.getReview());
        assertEquals("123456", booking.getBookingId());
        assertNotNull(booking.getTimestamp());
    }

    @Test
    public void testBookingStatusChange() {
        // Test booking status change
        Booking booking = new Booking();
        booking.setStatus("booked");
        booking.setReview("Good lesson");
        booking.setBookingId("123456");

        assertEquals("booked", booking.getStatus());

        booking.setStatus("cancelled");
        assertEquals("cancelled", booking.getStatus());

        booking.setStatus("attended");
        assertEquals("attended", booking.getStatus());
    }

}