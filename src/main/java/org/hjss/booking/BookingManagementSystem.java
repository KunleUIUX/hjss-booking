package org.hjss.booking;

import org.hjss.booking.report.CoachReportData;
import org.hjss.booking.report.LearnerReportData;
import org.hjss.util.NameGenerator;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

        generateSampleData();
    }

    public final void generateSampleData() {

        System.out.println("Initialization started..");
        System.out.println("\nGenerating dummy Coaches Data..");
        // Generate coaches
        for (int i = 1; i <= 4; i++) {
            Coach coach = new Coach();
            coach.setCoachId(UUID.randomUUID());
            coach.setName(NameGenerator.generateRandomCoachName());
            System.out.println("Coach: " + coach.getName());
            addCoach(coach);
        }

        System.out.println("\nCoaches initialized successfully.");

        System.out.println("\nGenerating dummy Timetable Data..");

        //ensuring there are no duplicates
        Set<String> uniqueLessons = new HashSet<>();

        // Generate lessons for 4 weeks (44 lessons) following the specified schedule

            String[] days = {"Monday", "Wednesday", "Friday", "Saturday"};

            for (int week = 1; week <= 4; week++) {
                for (String day : days) {

                        int lessonsPerDay = (day.equals("Saturday")) ? 2 : 3;


                        for (int i = 0; i < lessonsPerDay; i++) {

                            String timeSlot = getRandomTimeSlot(day);

                            Lesson lesson = new Lesson();
                            lesson.setUuid(UUID.randomUUID());
                            lesson.setDay(day);
                            lesson.setTimeSlot(timeSlot);
                            lesson.setCurrentCapacity(0);
                            lesson.setMaxCapacity(4);
                            lesson.setGradeLevel(generateRandomGrade());
                            lesson.setCoach(getRandomCoach());
                            lesson.setDateTime(LocalDateTime.now());

                            // Check uniqueness before adding the lesson
                            String lessonKey = lesson.getDay() + lesson.getTimeSlot() + lesson.getGradeLevel();
                            if (uniqueLessons.add(lessonKey)) {
                                addLesson(lesson);
                                System.out.println("\n" + lessonDetailsToString(lesson));
                            } else {
                                // Duplicate lesson, generate a new one
                                i--;
                            }

                            System.out.println("\n" + lessonDetailsToString(lesson));
                        }
                }
        }
        System.out.println("\nTimetable initialized successfully.");


        System.out.println("\nGenerating dummy Learners Data..");

        // Generate learners
        for (int i = 1; i <= 15; i++) {
            Learner learner = new Learner();
            learner.setUuid(UUID.randomUUID());
            learner.setName(NameGenerator.generateRandomLearnerName());
            System.out.println("Learner: " + learner.getName());
            learner.setGender((i % 2 == 0) ? "Male" : "Female");
            learner.setAge(new Random().nextInt(8) + 4); // Random age between 4 and 11
            learner.setEmergencyContact("123-456-789" + i);
            learner.setGradeLevel(new Random().nextInt(5) + 1); // Random grade level between 1 and 5
            registerNewLearner(learner);
        }
        System.out.println("\nLearners initialized successfully.");

    }


    private static String getRandomTimeSlot(String day) {
        if (day.equals("Saturday")) {
            return (Math.random() < 0.5) ? "2-3pm" : "3-4pm";
        } else {
            String[] timeSlots = {"4-5pm", "5-6pm", "6-7pm"};
            return timeSlots[(int) (Math.random() * 3)];
        }
    }

    private static int generateRandomGrade() {
        // For simplicity, generate a random grade level between 1 and 5
        return (int) (Math.random() * 5) + 1;
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

        try {

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
                        changeCancelBooking();
                        break;
                    case 3:
                        attendSwimmingLesson();
                        break;
                    case 4:
                        generateMonthlyLearnerReport();
                        break;
                    case 5:
                        generateMonthlyCoachReport();
                        break;
                    case 6:
                        registerNewLearner();
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
        catch (InputMismatchException ex){
            System.out.println("Invalid input. Please enter a valid input.");
            runCommandInterface();
        }
        finally {
            // Close the scanner to avoid resource leaks
            if (scanner != null) {
                scanner.close();
            }
        }
        }


    private void registerNewLearner() {

        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Register a New Learner ===");

        // Gather information about the new learner
        System.out.print("Enter learner name: ");
        String name = scanner.nextLine();
        System.out.print("Enter learner gender: \n");
        System.out.println("1. Male");
        System.out.println("2. Female");

        int genderChoice = scanner.nextInt();

        String gender = getGenderByChoice(genderChoice);

        int age = 0;

        do {
            System.out.print("Enter learner's age (between 4 and 11): ");
            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a valid integer.");
                scanner.next(); // Consume invalid input
            }
            age = scanner.nextInt();

            if (age < 4 || age > 11) {
                System.out.println("Invalid age. Please enter an age between 4 and 11.");
            }
        } while (age < 4 || age > 11);

        scanner.nextLine(); // Consume the newline character
        System.out.print("Enter emergency contact phone number: ");
        String emergencyContactNumber = scanner.nextLine();
        System.out.print("Enter learner grade level: ");
        int gradeLevel = scanner.nextInt();

        // Create a new learner with a UUID
        Learner newLearner = new Learner(UUID.randomUUID(), name, gender, age, emergencyContactNumber, gradeLevel);

        // Add the new learner to the system
        registerNewLearner(newLearner);

    }

        private void bookSwimmingLesson() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter learner name: ");
        String learnerName = scanner.nextLine();
        Learner learner = findLearnerByName(learnerName);

        if (learner != null) {

            System.out.println("----Learner Info----\n" +
                    "Name: " + learnerName +
                    ", Grade Level: " + learner.getGradeLevel());

            System.out.println("\n=== Book a Swimming Lesson ===");

            // Prompt the learner to select the way to view the timetable
            System.out.println("Select the way to view the timetable:");
            System.out.println("1. Specify the day");
            System.out.println("2. Specify the grade level");
            System.out.println("3. Specify the coach's name");
            System.out.print("Enter your choice: ");
            int viewOption = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            // Implement logic based on the selected view option
            switch (viewOption) {
                case 1:
                    // View by day logic
                    System.out.println("Select the day:");
                    System.out.println("1. Monday");
                    System.out.println("2. Wednesday");
                    System.out.println("3. Friday");
                    System.out.println("4. Saturday");
                    System.out.print("Enter your choice (1-4): ");
                    int dayChoice = scanner.nextInt();
                    viewLessonsByDay(dayChoice);
                    scanner.nextLine(); // Consume the newline character
                    break;
                case 2:
                    //View by Grade level logic
                    System.out.print("Enter the grade level (1 to 5): ");
                    int gradeLevelChoice = scanner.nextInt();
                    viewLessonsByGrade(gradeLevelChoice);
                    scanner.nextLine(); // Consume the newline character
                    break;
                case 3:
                    //View by Coach level logic
                    System.out.print("Enter the coach's name: ");
                    String coachNameChoice = scanner.nextLine();
                    viewLessonsByCoach(coachNameChoice);
                    scanner.nextLine(); // Consume the newline character
                    break;
                default:
                    System.out.println("Invalid choice. Returning to the main menu.");
                    return;
            }


            // Proceed with booking logic based on the selected view option
            System.out.print("Enter the lesson ID to book: ");
            String lessonIdToBook = scanner.nextLine();
            Lesson lesson = getLessonById(lessonIdToBook);

            if (lesson != null) {

                System.out.println("Learner with the name: " + learnerName
                                + " and Grade Level: " + learner.getGradeLevel()
                                + "\nTo Book Lesson: \n" + lessonDetailsToString(lesson)
                + ", at \n" + LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE)
                );
                // Check if the lesson is eligible and has available space
                if (isBookingEligible(learner, lesson) && lesson.getCurrentCapacity() < lesson.getMaxCapacity()) {
                    // Check if the learner has already booked this lesson
                    if (!isDuplicateBooking(learner, lesson)) {

                        // Create a new booking

                        Booking newBooking = new Booking();
                        newBooking.setLearner(learner);
                        newBooking.setLesson(lesson);
                        newBooking.setStatus("booked");
                        newBooking.setUuid(UUID.randomUUID());
                        newBooking.setTimestamp(LocalDateTime.now());
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
                System.out.println("Lesson not found for the ID inputted.");
            }
        } else {
            System.out.println("Learner not found for the specified name.");
        }
    }

    private void viewLessonsByCoach(String coachName) {
        List<Lesson> lessonsByCoach = getLessonsByCoach(coachName);

        if (lessonsByCoach.isEmpty()) {
            System.out.println("No lessons available for the specified coach.");
        } else {
            displayLessons(lessonsByCoach);
            // Add logic to proceed with booking based on the displayed lessons
        }
    }

    public List<Lesson> getLessonsByCoach(String coachName) {
        List<Lesson> lessonsByCoach = new ArrayList<>();

        for (Lesson lesson : lessons) {
            if (lesson.getCoach().getName().equalsIgnoreCase(coachName)) {
                lessonsByCoach.add(lesson);
            }
        }

        return lessonsByCoach;
    }

    public Lesson getLessonById(String lessonUuid) {
        for (Lesson lesson : lessons) {
            if (lesson.getUuid().toString().equals(lessonUuid)) {
                return lesson;
            }
        }
        return null; // Return null if the lesson with the specified ID is not found
    }

    private void viewLessonsByGrade(int gradeLevelChoice) {
        if (gradeLevelChoice < 1 || gradeLevelChoice > 5) {
            System.out.println("Invalid grade level choice. Grade level must be between 1 and 5.");
            return;
        }

        List<Lesson> lessonsByGrade = getLessonsByGrade(gradeLevelChoice);

        if (lessonsByGrade.isEmpty()) {
            System.out.println("No lessons available for the specified grade level.");
        } else {
            // Display lessons on the specified day
            displayLessons(lessonsByGrade);
    }
    }

    public List<Lesson> getLessonsByGrade(int gradeLevelChoice) {
        if (gradeLevelChoice < 1 || gradeLevelChoice > 5) {
            System.out.println("Invalid grade level choice. Grade level must be between 1 and 5.");
            return Collections.emptyList(); // Return an empty list for invalid grade level
        }

        List<Lesson> lessonsByGrade = new ArrayList<>();

        for (Lesson lesson : lessons) {
            if (lesson.getGradeLevel() == gradeLevelChoice) {
                lessonsByGrade.add(lesson);
            }
        }

        return lessonsByGrade;
    }

    // Helper method to implement view by day logic
    private void viewLessonsByDay(int dayChoice) {
        // Validate the day choice
        if (dayChoice < 1 || dayChoice > 4) {
            System.out.println("Invalid day choice. Returning to the main menu.");
            return;
        }

        // Map day choices to actual day names
        String[] daysOfWeek = {"Monday", "Wednesday", "Friday", "Saturday"};
        String selectedDay = daysOfWeek[dayChoice - 1];

        System.out.println("\n=== View Lessons for " + selectedDay + " ===");

        // Display lessons on the specified day
        displayLessons(getLessonsByDay(selectedDay));

    }

    private String getDayByChoice(int dayChoice) {
        // Validate the day choice
        if (dayChoice < 1 || dayChoice > 4) {
            System.out.println("Invalid day choice. Returning to the main menu.");
            return null;
        }
        // Map day choices to actual day names
        String[] daysOfWeek = {"Monday", "Wednesday", "Friday", "Saturday"};
        String selectedDay = daysOfWeek[dayChoice - 1];

        return selectedDay;
    }

    private String getGenderByChoice(int genderChoice) {
        // Validate the day choice
        if (genderChoice < 1 || genderChoice > 2) {
            System.out.println("Invalid gender choice. Returning to the main menu.");
            return null;
        }
        // Map day choices to actual day names
        String[] genders = {"Male", "Female"};
        String selectedGender = genders[genderChoice - 1];

        return selectedGender;
    }


    private String getTimeSlotByChoice(int choice, int dayChoice) {
        // Validate the day choice
        if (dayChoice < 1 || dayChoice > 4) {
            System.out.println("Invalid day choice. Returning to the main menu.");
            return null;
        }

        String selectedSlot = null;

        if (dayChoice == 4){

            if (choice < 1 || choice > 2) {
                System.out.println("Invalid Time Slot choice. Returning to the main menu.");
                return null;
            }

            // Map time choices to actual time slots
            String[] timeSlots = {"2-3pm", "3-4pm"};
            selectedSlot = timeSlots[choice - 1];

        }
        else {
            if(choice < 1 || choice > 3) {
                System.out.println("Invalid Time Slot choice. Returning to the main menu.");
                return null;
            }

            // Map time choices to actual time slots
            String[] timeSlots = {"4-5pm", "5-6pm", "6-7pm"};
            selectedSlot = timeSlots[choice - 1];
        }

        return selectedSlot;
    }


    private List<Lesson> getLessonsByDay(String day) {
        List<Lesson> lessonsByDay = new ArrayList<>();

        for (Lesson lesson : lessons) {
            if (lesson.getDay().equalsIgnoreCase(day)) {
                lessonsByDay.add(lesson);
            }
        }

        return lessonsByDay;
    }

    private void displayLessons(List<Lesson> lessons) {
        System.out.println("\nAvailable Lessons:");

        for (Lesson lesson : lessons) {
            System.out.println("Lesson ID: " + lesson.getUuid());
            System.out.println("Day: " + lesson.getDay());
            System.out.println("Time Slot: " + lesson.getTimeSlot());
            System.out.println("Grade Level: " + lesson.getGradeLevel());
            System.out.println("Current Capacity: " + lesson.getCurrentCapacity());
            System.out.println("Coach: " + lesson.getCoach().getName());
            System.out.println("Max Capacity: " + lesson.getMaxCapacity());
            System.out.println("------------------------");
        }
    }

    private void displayBookings(List<Booking> bookings) {
        System.out.println("\n========Available Bookings for Learner=========");

        for (Booking booking : bookings) {
            System.out.println("Booking ID: " + booking.getUuid());
            System.out.println("Day: " + booking.getLesson().getDay());
            System.out.println("Time Slot: " + booking.getLesson().getTimeSlot());
            System.out.println("Grade Level: " + booking.getLesson().getGradeLevel());
            System.out.println("Current Capacity: " + booking.getLesson().getCurrentCapacity());
            System.out.println("Coach: " + booking.getLesson().getCoach().getName());
            System.out.println("------------------------");
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

    private Booking findBookingByUid(String uuid) {
        for (Booking booking : bookings) {
            if (booking.getUuid().toString().equalsIgnoreCase(uuid)) {
                return booking;
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

    private Lesson findLessonByDayAndTimeAndLevel(String day, String timeSlot, int level) {
        for (Lesson lesson : lessons) {
            if (lesson.getDay().equalsIgnoreCase(day) && lesson.getTimeSlot().equalsIgnoreCase(timeSlot)
                    && lesson.getGradeLevel() == level ) {
                return lesson;
            }
        }
        return null;
    }

    private List<Booking> findBookedLessonByLearner(String learnerName) {

        List<Booking> bookingListFound = new ArrayList<>();
        for (Booking booking : bookings) {
            if (booking.getLearner().getName().equalsIgnoreCase(learnerName) && booking.getStatus().equalsIgnoreCase("booked")) {
                bookingListFound.add(booking);
            }
        }
        return bookingListFound;
    }

    private boolean isBookingEligible(Learner learner, Lesson lesson) {
        // Implement eligibility criteria (e.g., grade level restrictions)
        // Return true if the learner is eligible, false otherwise
        int learnerGrade = learner.getGradeLevel();
        int lessonGrade = lesson.getGradeLevel();

        return learnerGrade == lessonGrade || lessonGrade == learnerGrade + 1;

    }

    /*
    // Helper method to check if the learner is eligible for booking the lesson
    private boolean isBookingEligible(Lesson lesson) {
        // Check grade level constraint
        if (bookedLearner.getGradeLevel() != lesson.getGradeLevel() &&
                bookedLearner.getGradeLevel() != lesson.getGradeLevel() + 1) {
            System.out.println("Grade level constraint not met.");
            return false;
        }

        // Check lesson capacity constraint
        if (lesson.getCurrentCapacity() >= lesson.getMaxCapacity()) {
            System.out.println("Lesson is already fully booked.");
            return false;
        }

        // Check for duplicate booking
        if (bookedLearner.hasBookedLesson(lesson)) {
            System.out.println("You have already booked this lesson.");
            return false;
        }

        return true;
    }

*/
    private void changeCancelBooking() {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter learner name: ");
        String learnerName = scanner.nextLine();
        Learner learner = findLearnerByName(learnerName);

        if (learner != null) {
            System.out.print("Enter lesson day: ");
            String day = scanner.nextLine();
            System.out.print("Enter lesson time slot: ");
            String timeSlot = scanner.nextLine();
            Lesson oldLesson = findLessonByDayAndTime(day, timeSlot);

            if (oldLesson != null) {
                // Find the booking associated with the learner and old lesson
                Booking bookingToRemove = findBookingByLearnerAndLesson(learner, oldLesson);

                if (bookingToRemove != null) {
                    // Prompt the user to select a new lesson
                    System.out.print("Enter new lesson day: ");
                    String newDay = scanner.nextLine();
                    System.out.print("Enter new lesson time slot: ");
                    String newTimeSlot = scanner.nextLine();
                    Lesson newLesson = findLessonByDayAndTime(newDay, newTimeSlot);

                    if (newLesson != null) {
                        // Check if the new lesson is eligible and has available space
                        if (isBookingEligible(learner, newLesson) && newLesson.getCurrentCapacity() < newLesson.getMaxCapacity()) {
                            // Remove the old booking
                            bookings.remove(bookingToRemove);
                            // Release one place from the previously booked lesson
                            oldLesson.setCurrentCapacity(oldLesson.getCurrentCapacity() - 1);

                            // Create a new booking for the new lesson
                            Booking newBooking = new Booking();
                            newBooking.setLearner(learner);
                            newBooking.setLesson(newLesson);
                            newBooking.setStatus("booked");
                            // Add the new booking to the system
                            bookings.add(newBooking);
                            // Update the current capacity of the new lesson
                            newLesson.setCurrentCapacity(newLesson.getCurrentCapacity() + 1);

                            System.out.println("Booking change successful!");
                        } else {
                            System.out.println("The new lesson is not eligible or it is already full. Booking change failed.");
                        }
                    } else {
                        System.out.println("New lesson not found for the specified day and time slot.");
                    }
                } else {
                    System.out.println("No booking found for the specified learner and old lesson. Booking change failed.");
                }
            } else {
                System.out.println("Old lesson not found for the specified day and time slot.");
            }
        } else {
            System.out.println("Learner not found for the specified name.");
        }

    }

    private Booking findBookingByLearnerAndLesson(Learner learner, Lesson lesson) {
        for (Booking booking : bookings) {
            if (booking.getLearner().getUuid().equals(learner.getUuid()) && booking.getLesson().getUuid().equals(lesson.getUuid())) {
                return booking;
            }
        }
        return null;
    }

    public void attendSwimmingLesson() {
        // Implementation for attending a swimming lesson and providing a review
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter learner name: ");
        String learnerName = scanner.nextLine();
        Learner learner = findLearnerByName(learnerName);

        if (learner != null) {

            List<Booking> bookingList = findBookedLessonByLearner(learnerName);
            displayBookings(bookingList);

            System.out.print("Enter Booking Id: ");
            String bookingId = scanner.nextLine();

            Booking bookingToAttend = findBookingByUid(bookingId);

            if (bookingToAttend != null) {

                System.out.print("\nEnter your review: ");
                String review = scanner.nextLine();
                System.out.print("Enter your rating (1-5): \n1: Very dissatisfied, \n2: Dissatisfied, \n3: Ok, \n4: Satisfied, " +
                        "\n5: " + "Very Satisfied \n");
                int rating = scanner.nextInt();

                // Mark the lesson as attended
                bookingToAttend.setStatus("attended");
                // Set the review and rating for the attended lesson
                bookingToAttend.setReview(review);
                bookingToAttend.setRating(rating);

                // Check for grade level update
                updateGradeLevelAfterAttending(learner, bookingToAttend.getLesson());

                System.out.println("Lesson attended successfully. Rating: " + rating);

            } else {
                System.out.println("No booking found for the specified learner and lesson. Attendance failed.");
            }

            /*
            System.out.print("Select lesson day: \n");

            System.out.println("1. Monday");
            System.out.println("2. Wednesday");
            System.out.println("3. Friday");
            System.out.println("4. Saturday");
            System.out.print("Enter your choice (1-4): ");
            int dayChoice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            String day = getDayByChoice(dayChoice);

            int slotChoice = 0;
            if (dayChoice == 4){
                //deal with Saturday alone and return

                System.out.print("Select time slot for Saturday: \n");
                System.out.println("1. 2-3pm");
                System.out.println("2. 3-4pm");

                slotChoice = scanner.nextInt();
                scanner.nextLine();

            }
            else {

                System.out.print("Select time slot: \n");
                System.out.println("1. 4-5pm");
                System.out.println("2. 5-6pm");
                System.out.println("3. 6-7pm");

                slotChoice = scanner.nextInt();
                scanner.nextLine();
            }

            String timeSlot = getTimeSlotByChoice(slotChoice, dayChoice);

            //Null or empty checker in place for the values returned for day and slot
            if (checkNullOrEmpty(day) || checkNullOrEmpty(timeSlot)){
                return;
            }

            Lesson lesson = findLessonByDayAndTime(day, timeSlot);

            if (lesson != null) {

                System.out.println("Lesson found : " + lessonDetailsToString(lesson));

                System.out.print("\nEnter your review: ");
                String review = scanner.nextLine();
                System.out.print("Enter your rating (1-5): \n1: Very dissatisfied, \n2: Dissatisfied, \n3: Ok, \n4: Satisfied, " +
                        "\n5: " + "Very Satisfied \n");
                int rating = scanner.nextInt();

                attendLesson(learner, lesson, review, rating);
            } else {
                System.out.println("Lesson not found for the specified day and time slot.");
            }
        } else {
            System.out.println("Learner not found for the specified name.");
        }

             */
        }
        else {
            System.out.println("Specified learner with Name : " + learnerName + " not found");
            return;
        }
    }

    private boolean checkNullOrEmpty(String ex){
        return ex == null || ex.equalsIgnoreCase("");
    }

    // Function 3: Attend a swimming lesson
    public void attendLesson(Learner learner, Lesson lesson, String review, int rating) {
        // Implement attending lesson logic

        // Find the booking associated with the learner and lesson
        Booking bookingToAttend = findBookingByLearnerAndLesson(learner, lesson);

        if (bookingToAttend != null) {
            // Check the status of the booking
            if ("booked".equals(bookingToAttend.getStatus())) {
                // Mark the lesson as attended
                bookingToAttend.setStatus("attended");
                // Set the review and rating for the attended lesson
                bookingToAttend.setReview(review);
                bookingToAttend.setRating(rating);

                // Check for grade level update
                updateGradeLevelAfterAttending(learner, lesson);

                System.out.println("Lesson attended successfully. Rating: " + rating);
            } else {
                System.out.println("This lesson has already been attended or cancelled.");
            }
        } else {
            System.out.println("No booking found for the specified learner and lesson. Attendance failed.");
        }
    }

    private void updateGradeLevelAfterAttending(Learner learner, Lesson lesson) {
        // Check if the learner's grade level is lower than the lesson's grade level
        int lessonGrade = lesson.getGradeLevel();
        int learnerGrade = learner.getGradeLevel();

        if (learnerGrade < lessonGrade) {
            // Update the learner's grade level
            learner.setGradeLevel(lessonGrade);
            System.out.println("Congratulations! Your grade level has been updated to " + lessonGrade);
        }
    }


    // Generate monthly learner report for a specified month
    private void generateMonthlyLearnerReport() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n=== Monthly Learner Report ===");

        // Prompt user to input a month number
        System.out.print("Enter the month number (e.g., 03 for March): ");
        int monthNumber = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        // Validate the month number (assuming valid range is 1-12)
        if (monthNumber < 1 || monthNumber > 12) {
            System.out.println("Invalid month number. Please enter a valid month number.");
            return;
        }

        // Create a map to store learner information
        Map<UUID, LearnerReportData> learnerReportMap = new HashMap<>();

        // Iterate through bookings made in the specified month
        for (Booking booking : getBookingsInMonth(monthNumber)) {
            Learner learner = booking.getLearner();

            // If learner is not in the report map, add them with initial data
            learnerReportMap.computeIfAbsent(learner.getUuid(), k -> new LearnerReportData(learner.getName()));

            // Update learner report data based on booking status
            LearnerReportData learnerReportData = learnerReportMap.get(learner.getUuid());
            switch (booking.getStatus()) {
                case "booked":
                    learnerReportData.incrementBookedCount();
                    learnerReportData.addLesson(booking.getLesson());
                    break;
                case "cancelled":
                    learnerReportData.incrementCancelledCount();
                    break;
                case "attended":
                    learnerReportData.incrementAttendedCount();
                    break;
            }
        }

        // Print the report for each learner
        for (LearnerReportData learnerReportData : learnerReportMap.values()) {
            System.out.println("\nLearner: " + learnerReportData.getLearnerName());
            System.out.println("Booked Lessons: " + learnerReportData.getBookedCount());
            System.out.println("Cancelled Lessons: " + learnerReportData.getCancelledCount());
            System.out.println("Attended Lessons: " + learnerReportData.getAttendedCount());
            System.out.println("Detailed Lesson Information for the month " + monthNumber + ":");

            // Print detailed lesson information
            List<Lesson> bookedLessons = learnerReportData.getBookedLessons();
            for (Lesson lesson : bookedLessons) {
                // Check if the lesson is in the specified month
                if (lessonIsInMonth(lesson, monthNumber)) {
                    System.out.println("- " + lessonDetailsToString(lesson));
                }
            }
        }
    }

    // Check if a lesson is in the specified month
    private boolean lessonIsInMonth(Lesson lesson, int monthNumber) {
        return lesson.getDateTime().getMonthValue() == monthNumber;
    }

    // Generate detailed string representation of lesson details
    private String lessonDetailsToString(Lesson lesson) {
        return
                "Id: " + lesson.getUuid() +
                ", Time: " + lesson.getTimeSlot() +
                ", Current Capacity: " + lesson.getCurrentCapacity() +
                ", Grade Level: " + lesson.getGradeLevel() +
                ", Day: " + lesson.getDay() +
                ", Coach: " + lesson.getCoach().getName();
    }


    // Generate monthly coach report
    private void generateMonthlyCoachReport() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n=== Monthly Coach Report ===");

        // Prompt user to input a month number
        System.out.print("Enter the month number (e.g., 03 for March): ");
        int monthNumber = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        // Validate the month number (assuming valid range is 1-12)
        if (monthNumber < 1 || monthNumber > 12) {
            System.out.println("Invalid month number. Please enter a valid month number.");
            return;
        }

        // Create a map to store coach information
        Map<String, CoachReportData> coachReportMap = new HashMap<>();

        // Iterate through bookings made in the specified month
        for (Booking booking : getBookingsInMonth(monthNumber)) {
            Coach coach = booking.getLesson().getCoach();

            // If coach is not in the report map, add them with initial data
            coachReportMap.computeIfAbsent(coach.getName(), k -> new CoachReportData());

            // Update coach report data with the rating received
            coachReportMap.get(coach.getName()).addRating(booking.getRating());
        }

        // Print the report for each coach
        for (Map.Entry<String, CoachReportData> entry : coachReportMap.entrySet()) {
            String coachName = entry.getKey();
            CoachReportData coachReportData = entry.getValue();

            System.out.println("\nCoach: " + coachName);
            System.out.println("Average Rating for the month " + monthNumber + ": " + coachReportData.getAverageRating());
        }
    }

    // Get bookings made in the specified month
    private List<Booking> getBookingsInMonth(int monthNumber) {
        List<Booking> bookingsInMonth = new ArrayList<>();

        for (Booking booking : bookings) {
            LocalDateTime bookingTimestamp = booking.getTimestamp();
            if (bookingTimestamp.getMonthValue() == monthNumber) {
                bookingsInMonth.add(booking);
            }
        }

        return bookingsInMonth;
    }
}
