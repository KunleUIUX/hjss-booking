package org.hjss.booking;

import java.util.UUID;

public class Coach {

    private String name;
    private UUID coachId;

    public Coach(String name, UUID coachId) {
        this.name = name;
        this.coachId = coachId;
    }

    public Coach() {
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public UUID getCoachId() {
        return coachId;
    }

    public void setCoachId(UUID coachId) {
        this.coachId = coachId;
    }
}
