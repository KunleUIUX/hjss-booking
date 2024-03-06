package org.hjss.booking;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BookingManagementSystem {

    private List<Learner> learners;
    private List<Coach> coaches;
    private List<Lesson> lessons;
    private List<Booking> bookings;

    public BookingManagementSystem() {
        learners = new ArrayList<>();
        coaches = new ArrayList<>();
        lessons = new ArrayList<>();
        bookings = new ArrayList<>();
        // Initialize coaches, lessons, etc. (you can do this in a constructor or a separate method)
    }

    // Add methods for adding coaches, learners, lessons, etc.

    // Function 1: Book a swimming lesson
    public void bookLesson(Learner learner, Lesson lesson) {
        // Implement booking logic
    }

    // Function 2: Change/Cancel a booking
    public void changeCancelBooking(Learner learner, Lesson oldLesson, Lesson newLesson) {
        // Implement change/cancel booking logic
    }

    // Function 3: Attend a swimming lesson
    public void attendLesson(Learner learner, Lesson lesson, int rating) {
        // Implement attending lesson logic
    }

    // Function 4: Monthly learner report
    public void generateMonthlyLearnerReport() {
        // Implement monthly learner report logic
    }

    // Function 5: Monthly coach report
    public void generateMonthlyCoachReport() {
        // Implement monthly coach report logic
    }

    // Function 6: Register a new learner
    public void registerNewLearner(Learner newLearner) {
        // Implement learner registration logic
    }

    public void generateSampleData() {

    // Generate coaches
        for (int i = 1; i <= 4; i++) {
        Coach coach = new Coach();
        coach.setName("Coach" + i);
        addCoach(coach);
    }

    // Generate learners
        for (int i = 1; i <= 15; i++) {
        Learner learner = new Learner();
        learner.setName("Learner" + i);
        learner.setGender((i % 2 == 0) ? "Male" : "Female");
        learner.setAge(new Random().nextInt(8) + 4); // Random age between 4 and 11
        learner.setEmergencyContact("123-456-789" + i);
        learner.setGradeLevel(new Random().nextInt(5) + 1); // Random grade level between 1 and 5
        registerNewLearner(learner);
    }

        // Generate lessons for 4 weeks (44 lessons) following the specified schedule
        String[] days = {"Monday", "Wednesday", "Friday", "Saturday"};
        String[] timeSlots = {"4-5pm", "5-6pm", "6-7pm", "2-3pm", "3-4pm"};

        for (int week = 1; week <= 4; week++) {
            for (String day : days) {
                int lessonsPerDay = (day.equals("Saturday")) ? 2 : 3;

                for (int i = 0; i < lessonsPerDay; i++) {
                    String timeSlot = timeSlots[i];
                    Lesson lesson = new Lesson();
                    lesson.setDay(day);
                    lesson.setTimeSlot(timeSlot);
                    lesson.setMaxCapacity(4);
                    lesson.setCoach(getRandomCoach());
                    addLesson(lesson);
                }
            }
        }

    }

    private Coach getRandomCoach() {
        // Get a random coach from the existing coaches
        return coaches.get(new Random().nextInt(coaches.size()));
    }

    public void addLesson(Lesson lesson) {
        lessons.add(lesson);
    }

    public void addCoach(Coach coach) {
        coaches.add(coach);
    }


}
