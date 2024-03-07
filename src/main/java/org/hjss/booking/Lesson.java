package org.hjss.booking;

import java.time.LocalDateTime;
import java.util.UUID;

public class Lesson {

    private UUID uuid;

    private String lessonId;
    private String day;
    private String timeSlot;
    private int maxCapacity;
    private int currentCapacity;
    private Coach coach;
    private int gradeLevel;
    private LocalDateTime dateTime;

    public Lesson(UUID uuid, String lessonId, String day, String timeSlot, int maxCapacity, int currentCapacity, Coach coach, int gradeLevel, LocalDateTime dateTime) {
        this.uuid = uuid;
        this.lessonId = lessonId;
        this.day = day;
        this.timeSlot = timeSlot;
        this.maxCapacity = maxCapacity;
        this.currentCapacity = currentCapacity;
        this.coach = coach;
        this.gradeLevel = gradeLevel;
        this.dateTime = dateTime;
    }

    public Lesson() {
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getLessonId() {
        return lessonId;
    }

    public void setLessonId(String lessonId) {
        this.lessonId = lessonId;
    }
    public int getGradeLevel() {
        return gradeLevel;
    }

    public void setGradeLevel(int gradeLevel) {
        this.gradeLevel = gradeLevel;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(String timeSlot) {
        this.timeSlot = timeSlot;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public int getCurrentCapacity() {
        return currentCapacity;
    }

    public void setCurrentCapacity(int currentCapacity) {
        this.currentCapacity = currentCapacity;
    }

    public Coach getCoach() {
        return coach;
    }

    public void setCoach(Coach coach) {
        this.coach = coach;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}
