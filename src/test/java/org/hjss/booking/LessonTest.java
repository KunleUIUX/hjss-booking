package org.hjss.booking;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class LessonTest {

    @Test
    public void testLessonCreation() {
        // Test lesson creation and getters
        Coach coach = new Coach("Timalex", UUID.randomUUID());

        Lesson lesson = new Lesson();
        lesson.setUuid(UUID.randomUUID());
        lesson.setLessonId("123456");
        lesson.setDay("Monday");
        lesson.setTimeSlot("10:00 AM - 11:00 AM");
        lesson.setMaxCapacity(10);
        lesson.setCurrentCapacity(5);
        lesson.setCoach(coach);
        lesson.setGradeLevel(3);
        lesson.setDateTime(LocalDateTime.now());

        assertNotNull(lesson.getUuid());
        assertEquals("123456", lesson.getLessonId());
        assertEquals("Monday", lesson.getDay());
        assertEquals("10:00 AM - 11:00 AM", lesson.getTimeSlot());
        assertEquals(10, lesson.getMaxCapacity());
        assertEquals(5, lesson.getCurrentCapacity());
        assertEquals(coach, lesson.getCoach());
        assertEquals(3, lesson.getGradeLevel());
        assertNotNull(lesson.getDateTime());
    }

    @Test
    public void testLessonCapacityUpdate() {
        // Test lesson capacity update
        Lesson lesson = new Lesson();

        assertEquals(0, lesson.getCurrentCapacity());

        lesson.setMaxCapacity(5);
        lesson.setCurrentCapacity(3);

        assertEquals(3, lesson.getCurrentCapacity());

        lesson.setCurrentCapacity(5);
        assertEquals(5, lesson.getCurrentCapacity());
    }

}