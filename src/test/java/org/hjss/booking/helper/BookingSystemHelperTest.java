package org.hjss.booking.helper;

import org.hjss.booking.Coach;
import org.hjss.booking.Learner;
import org.hjss.booking.Lesson;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class BookingSystemHelperTest {


    @Test
    public void testGetRandomTimeSlot() {
        String saturdayTimeSlot = BookingSystemHelper.getRandomTimeSlot("Saturday");
        assertNotNull(saturdayTimeSlot);

        String weekdayTimeSlot = BookingSystemHelper.getRandomTimeSlot("Monday");
        assertNotNull(weekdayTimeSlot);
    }

    @Test
    public void testGenerateRandomGrade() {
        int randomGrade = BookingSystemHelper.generateRandomGrade();
        assertTrue(randomGrade >= 1 && randomGrade <= 5);
    }

    @Test
    public void testIsLearnerAgeValid() {
        assertTrue(BookingSystemHelper.isLearnerAgeValid(5));
        assertFalse(BookingSystemHelper.isLearnerAgeValid(3));
        assertFalse(BookingSystemHelper.isLearnerAgeValid(12));
    }

    @Test
    public void testIsBookingEligible() {
        Learner learner = new Learner(UUID.randomUUID(), "John Doe", "Male", 8, "123456789", 3);
        Lesson lesson1 = new Lesson();
        lesson1.setGradeLevel(1);

        Lesson lesson2 = new Lesson();
        lesson2.setGradeLevel(4);

        assertFalse(BookingSystemHelper.isBookingEligible(learner, lesson1));
        assertTrue(BookingSystemHelper.isBookingEligible(learner, lesson2));
    }

    @Test
    public void testLearnerDetailsToString() {
        Learner learner = new Learner(UUID.randomUUID(), "Alice", "Female", 10, "987654321", 5);
        String expectedDetails = "Id: " + learner.getUuid() +
                ", Name: " + learner.getName() +
                ", Gender: " + learner.getGender() +
                ", Age: " + learner.getAge() +
                ", Emergency Contact: " + learner.getEmergencyContact() +
                ", Grade Level: " + learner.getGradeLevel();

        assertEquals(expectedDetails, BookingSystemHelper.learnerDetailsToString(learner));
    }

    @Test
    public void testLessonDetailsToString() {
        Coach coach = new Coach();
        coach.setName("Coach John");
        Lesson lesson = new Lesson();
        lesson.setUuid(UUID.randomUUID());
        lesson.setTimeSlot("4-5pm");
        lesson.setCurrentCapacity(10);
        lesson.setGradeLevel(3);
        lesson.setDay("Monday");
        lesson.setCoach(coach);

        String expectedDetails = "Id: " + lesson.getUuid() +
                ", Time: " + lesson.getTimeSlot() +
                ", Current Capacity: " + lesson.getCurrentCapacity() +
                ", Grade Level: " + lesson.getGradeLevel() +
                ", Day: " + lesson.getDay() +
                ", Coach: " + lesson.getCoach().getName();

        assertEquals(expectedDetails, BookingSystemHelper.lessonDetailsToString(lesson));
    }

}