package org.hjss.booking;

import org.hjss.util.NameGenerator;

import java.util.*;

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
    }

    public void generateSampleData() {

        // Generate coaches
        for (int i = 1; i <= 4; i++) {
            Coach coach = new Coach();
            coach.setCoachId(UUID.randomUUID().toString());
            coach.setName(NameGenerator.generateRandomCoachName());
            System.out.println("Coach: " + coach.getName());
            addCoach(coach);
        }

        // Generate learners
        for (int i = 1; i <= 15; i++) {
            Learner learner = new Learner();
            learner.setName(NameGenerator.generateRandomLearnerName());
            System.out.println("Learner: " + learner.getName());
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

    public void addCoach(Coach coach) {
        coaches.add(coach);
    }
    public void addLesson(Lesson lesson) {
        lessons.add(lesson);
    }
    private Coach getRandomCoach() {
        // Get a random coach from the existing coaches
        return coaches.get(new Random().nextInt(coaches.size()));
    }


    // Function 6: Register a new learner
    public void registerNewLearner(Learner newLearner) {
        // Implement learner registration logic

// Check if the learner meets the age requirement
        if (isLearnerAgeValid(newLearner.getAge())) {
            // Check if the learner is not already registered
            if (!isLearnerAlreadyRegistered(newLearner.getName())) {
                // Add the new learner to the system
                learners.add(newLearner);
                System.out.println("New learner registered successfully.");
            } else {
                System.out.println("This learner is already registered in the system.");
            }
        } else {
            System.out.println("Invalid learner age. The learner must be between 4 and 11 years old.");
        }
    }

    private boolean isLearnerAgeValid(int age) {
        // Check if the learner's age is between 4 and 11 (inclusive)
        return age >= 4 && age <= 11;
    }


    private boolean isLearnerAlreadyRegistered(String learnerName) {
        // Check if the learner is already registered in the system
        for (Learner existingLearner : learners) {
            if (existingLearner.getName().equalsIgnoreCase(learnerName)) {
                return true;
            }
        }
        return false;
    }

    public void runCommandInterface() {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("\nMain Menu:");
            System.out.println("1. Book a swimming lesson");
            System.out.println("2. Change/Cancel a booking");
            System.out.println("3. Attend a swimming lesson");
            System.out.println("4. Monthly learner report");
            System.out.println("5. Monthly coach report");
            System.out.println("6. Register a new learner");

            System.out.println("7. Exit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    bookSwimmingLesson();
                    break;
                case 2:
//                    changeCancelBooking();
                    break;
                case 3:
//                    attendSwimmingLesson();
                    break;
                case 7:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        System.out.println("Exiting the program. Thank you!");
    }

    private void bookSwimmingLesson() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter learner name: ");
        String learnerName = scanner.nextLine();
        Learner learner = findLearnerByName(learnerName);

        if (learner != null) {
            System.out.print("Enter lesson day: ");
            String day = scanner.nextLine();
            System.out.print("Enter lesson time slot: ");
            String timeSlot = scanner.nextLine();
            Lesson lesson = findLessonByDayAndTime(day, timeSlot);

            if (lesson != null) {
                // Check if the lesson is eligible and has available space
                if (isBookingEligible(learner, lesson) && lesson.getCurrentCapacity() < lesson.getMaxCapacity()) {
                    // Check if the learner has already booked this lesson
                    if (!isDuplicateBooking(learner, lesson)) {

                        // Create a new booking

                        Booking newBooking = new Booking();
                        newBooking.setLearner(learner);
                        newBooking.setLesson(lesson);
                        newBooking.setStatus("booked");
                        // Add the new booking to the system
                        bookings.add(newBooking);
                        // Update the current capacity of the lesson
                        lesson.setCurrentCapacity(lesson.getCurrentCapacity() + 1);

                        System.out.println("Booking successful!");
                    }
                    else {
                        System.out.println("You have already booked this lesson. Duplicate booking is not allowed.");
                    }
                } else {
                    System.out.println("The lesson is not eligible or it is already full. Booking failed.");
                }
            } else {
                System.out.println("Lesson not found for the specified day and time slot.");
            }
        } else {
            System.out.println("Learner not found for the specified name.");
        }
    }

    private boolean isDuplicateBooking(Learner learner, Lesson lesson) {
        for (Booking booking : bookings) {
            if (booking.getLearner().equals(learner) && booking.getLesson().equals(lesson) && booking.getStatus().equals("booked")) {
                return true;
            }
        }
        return false;
    }


    private Learner findLearnerByName(String learnerName) {
        for (Learner learner : learners) {
            if (learner.getName().equalsIgnoreCase(learnerName)) {
                return learner;
            }
        }
        return null;
    }

    private Lesson findLessonByDayAndTime(String day, String timeSlot) {
        for (Lesson lesson : lessons) {
            if (lesson.getDay().equalsIgnoreCase(day) && lesson.getTimeSlot().equalsIgnoreCase(timeSlot)) {
                return lesson;
            }
        }
        return null;
    }

    private boolean isBookingEligible(Learner learner, Lesson lesson) {
        // Implement eligibility criteria (e.g., grade level restrictions)
        // Return true if the learner is eligible, false otherwise
        // You can add more conditions based on your specific requirements
        return learner.getGradeLevel() >= lesson.getGradeLevel() && learner.getGradeLevel() <= lesson.getGradeLevel() + 1;
    }

}
