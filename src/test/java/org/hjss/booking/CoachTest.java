package org.hjss.booking;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class CoachTest {

    @Test
    public void testCoachCreation() {
        // Test coach creation and getters
        UUID coachUuid = UUID.randomUUID();
        Coach coach = new Coach("Coach John",coachUuid);

        assertEquals(coachUuid, coach.getCoachId());
        assertEquals("Coach John", coach.getName());

    }

}