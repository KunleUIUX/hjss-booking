package org.hjss.booking;

import org.hjss.booking.helper.BookingSystemHelper;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class LearnerTest {

    @Test
    public void testLearnerCreation() {
        // Test learner creation and getters
        UUID learnerUuid = UUID.randomUUID();
        Learner learner = new Learner(learnerUuid, "John Doe", "Male", 10, "123456789", 5);

        assertEquals(learnerUuid, learner.getUuid());
        assertEquals("John Doe", learner.getName());
        assertEquals("Male", learner.getGender());
        assertEquals(10, learner.getAge());
        assertEquals("123456789", learner.getEmergencyContact());
        assertEquals(5, learner.getGradeLevel());
    }

    @Test
    public void testLearnerAgeValidation() {
        // Test learner age validation
        assertTrue(BookingSystemHelper.isLearnerAgeValid(7));  // Valid age
        assertFalse(BookingSystemHelper.isLearnerAgeValid(3)); // Invalid age
        assertFalse(BookingSystemHelper.isLearnerAgeValid(12)); // Invalid age
    }

}