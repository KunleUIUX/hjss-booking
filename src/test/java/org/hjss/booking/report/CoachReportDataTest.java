package org.hjss.booking.report;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CoachReportDataTest {

    @Test
    public void testAverageRatingWithNoRatings() {
        // Test average rating when there are no ratings
        CoachReportData coachReportData = new CoachReportData();
        assertEquals(0, coachReportData.getAverageRating(), 0.001);
    }

    @Test
    public void testAverageRatingWithRatings() {
        // Test average rating when there are ratings
        CoachReportData coachReportData = new CoachReportData();
        coachReportData.addRating(4);
        coachReportData.addRating(5);
        coachReportData.addRating(3);

        assertEquals(4.0, coachReportData.getAverageRating(), 0.001);
    }

    @Test
    public void testAddRating() {
        // Test adding a single rating
        CoachReportData coachReportData = new CoachReportData();
        coachReportData.addRating(3);

        assertEquals(3, coachReportData.getTotalRatings());
        assertEquals(1, coachReportData.getRatingCount());
    }


}