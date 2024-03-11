package org.hjss.booking;

import java.time.LocalDateTime;
import java.util.UUID;

public class Booking {

    private UUID uuid;

    private Learner learner;
    private Lesson lesson;
    private String status; // "booked", "attended", "cancelled"
    private String review;
    private String bookingId;
    private LocalDateTime timestamp;


    public Booking(UUID uuid, Learner learner, Lesson lesson, String status, String review, String bookingId, int rating) {
        this.uuid = uuid;
        this.learner = learner;
        this.lesson = lesson;
        this.status = status;
        this.review = review;
        this.bookingId = bookingId;
        this.rating = rating;
        this.timestamp = timestamp;
    }

    public Booking() {
    }

    public UUID getUuid() {
        return uuid;
    }


    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }



    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    private int rating;

    public Learner getLearner() {
        return learner;
    }

    public void setLearner(Learner learner) {
        this.learner = learner;
    }

    public Lesson getLesson() {
        return lesson;
    }

    public void setLesson(Lesson lesson) {
        this.lesson = lesson;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }


    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
