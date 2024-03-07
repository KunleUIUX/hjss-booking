package org.hjss.booking.report;

public class CoachReportData {
    private int totalRatings;
    private int ratingCount;

    public CoachReportData() {
        this.totalRatings = 0;
        this.ratingCount = 0;
    }

    public double getAverageRating() {
        return (ratingCount == 0) ? 0 : ((double) totalRatings / ratingCount);
    }

    public void addRating(int rating) {
        totalRatings += rating;
        ratingCount++;
    }
}
}
