package org.hjss.booking.report;

import org.hjss.booking.Lesson;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LearnerReportDataTest {

    @Test
    public void testIncrementBookedCount() {
        // Test incrementing booked count
        LearnerReportData learnerReportData = new LearnerReportData("John Doe");
        learnerReportData.incrementBookedCount();
        assertEquals(1, learnerReportData.getBookedCount());
    }

    @Test
    public void testIncrementCancelledCount() {
        // Test incrementing cancelled count
        LearnerReportData learnerReportData = new LearnerReportData("Jane Doe");
        learnerReportData.incrementCancelledCount();
        assertEquals(1, learnerReportData.getCancelledCount());
    }

    @Test
    public void testIncrementAttendedCount() {
        // Test incrementing attended count
        LearnerReportData learnerReportData = new LearnerReportData("Alice");
        learnerReportData.incrementAttendedCount();
        assertEquals(1, learnerReportData.getAttendedCount());
    }

    @Test
    public void testAddLesson() {
        // Test adding a lesson to booked lessons
        LearnerReportData learnerReportData = new LearnerReportData("Bob");
        Lesson lesson = new Lesson();
        learnerReportData.addLesson(lesson);
        assertTrue(learnerReportData.getBookedLessons().contains(lesson));
    }

}