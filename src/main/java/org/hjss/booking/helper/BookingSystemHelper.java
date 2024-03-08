package org.hjss.booking.helper;

import org.hjss.booking.Booking;
import org.hjss.booking.Learner;
import org.hjss.booking.Lesson;
import org.hjss.booking.report.CoachReportData;
import org.hjss.booking.report.LearnerReportData;

import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.UUID;

public class BookingSystemHelper {


    public static String getRandomTimeSlot(String day) {
        if (day.equals("Saturday")) {
            return (Math.random() < 0.5) ? "2-3pm" : "3-4pm";
        } else {
            String[] timeSlots = {"4-5pm", "5-6pm", "6-7pm"};
            return timeSlots[(int) (Math.random() * 3)];
        }
    }

    public static int generateRandomGrade() {
        // For simplicity, generate a random grade level between 1 and 5
        return (int) (Math.random() * 5) + 1;
    }

    public static boolean isLearnerAgeValid(int age) {
        // Check if the learner's age is between 4 and 11 (inclusive)
        return age >= 4 && age <= 11;
    }

    public static boolean isBookingEligible(Learner learner, Lesson lesson) {
        // Implement eligibility criteria (e.g., grade level restrictions)
        // Return true if the learner is eligible, false otherwise
        int learnerGrade = learner.getGradeLevel();
        int lessonGrade = lesson.getGradeLevel();

        return learnerGrade == lessonGrade || lessonGrade == learnerGrade + 1;

    }

    // Generate detailed string representation of lesson details
    public static String learnerDetailsToString(Learner learner) {
        return
                "Id: " + learner.getUuid() +
                        ", Name: " + learner.getName() +
                        ", Gender: " + learner.getGender() +
                        ", Age: " + learner.getAge() +
                        ", Emergency Contact: " + learner.getEmergencyContact() +
                        ", Grade Level: " + learner.getGradeLevel();
    }

    // Check if a lesson is in the specified month
    public static boolean lessonIsInMonth(Lesson lesson, int monthNumber) {
        return lesson.getDateTime().getMonthValue() == monthNumber;
    }

    // Generate detailed string representation of lesson details
    public static String lessonDetailsToString(Lesson lesson) {
        return
                "Id: " + lesson.getUuid() +
                        ", Time: " + lesson.getTimeSlot() +
                        ", Current Capacity: " + lesson.getCurrentCapacity() +
                        ", Grade Level: " + lesson.getGradeLevel() +
                        ", Day: " + lesson.getDay() +
                        ", Coach: " + lesson.getCoach().getName();
    }

    public static String getTimeSlotByChoice(int choice, int dayChoice) {
        // Validate the day choice
        if (dayChoice < 1 || dayChoice > 4) {
            System.out.println("Invalid day choice. Returning to the main menu.");
            return null;
        }

        String selectedSlot = null;

        if (dayChoice == 4) {

            if (choice < 1 || choice > 2) {
                System.out.println("Invalid Time Slot choice. Returning to the main menu.");
                return null;
            }

            // Map time choices to actual time slots
            String[] timeSlots = {"2-3pm", "3-4pm"};
            selectedSlot = timeSlots[choice - 1];

        } else {
            if (choice < 1 || choice > 3) {
                System.out.println("Invalid Time Slot choice. Returning to the main menu.");
                return null;
            }

            // Map time choices to actual time slots
            String[] timeSlots = {"4-5pm", "5-6pm", "6-7pm"};
            selectedSlot = timeSlots[choice - 1];
        }

        return selectedSlot;
    }

    public static boolean checkNullOrEmpty(String ex) {
        return ex == null || ex.equalsIgnoreCase("");
    }

    public static void displayLessons(List<Lesson> lessons) {
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

    public static void displayBookings(List<Booking> bookings) {
        System.out.println("\n========Available Bookings for Learner=========");

        if (bookings.isEmpty()) {
            System.out.println("No bookings available.");
            return;
        }

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

    public static void displayLearnersReport(int monthNumber, Map<UUID, LearnerReportData> learnerReportMap) {
        for (LearnerReportData learnerReportData : learnerReportMap.values()) {
            System.out.println("\nLearner: " + learnerReportData.getLearnerName());
            System.out.println("Booked Lessons: " + learnerReportData.getBookedCount());
            System.out.println("Cancelled Lessons: " + learnerReportData.getCancelledCount());
            System.out.println("Attended Lessons: " + learnerReportData.getAttendedCount());
            System.out.println("Detailed Lesson Information for the month " + monthNumber + ":");

            // Print detailed lesson information
            List<Lesson> bookedLessons = learnerReportData.getBookedLessons();

            System.out.println("\n---------Booked Lessons--------");
            for (Lesson lesson : bookedLessons) {
                // Check if the lesson is in the specified month
                if (lessonIsInMonth(lesson, monthNumber)) {
                    System.out.println("- " + lessonDetailsToString(lesson));
                }
            }
            System.out.println("-----Booked Lessons (end)------");

            System.out.println("\n--------Cancelled Lessons------");

            List<Lesson> cancelledLessons = learnerReportData.getCancelledLessons();
            for (Lesson lesson : cancelledLessons) {
                // Check if the lesson is in the specified month
                if (lessonIsInMonth(lesson, monthNumber)) {
                    System.out.println("- " + lessonDetailsToString(lesson));
                }
            }
            System.out.println("\n------Cancelled Lessons (end)------");


            System.out.println("\n--------- Attended Lessons---------");

            List<Lesson> attendedLessons = learnerReportData.getAttendedLessons();
            for (Lesson lesson : attendedLessons) {
                // Check if the lesson is in the specified month
                if (lessonIsInMonth(lesson, monthNumber)) {
                    System.out.println("- " + lessonDetailsToString(lesson));
                }
            }
            System.out.println("---------Attended Lessons (end)------");

        }
    }

    public static void displayCoachReport(int monthNumber, Map<String, CoachReportData> coachReportMap) {
        // Print the report for each coach
        for (Map.Entry<String, CoachReportData> entry : coachReportMap.entrySet()) {
            String coachName = entry.getKey();
            CoachReportData coachReportData = entry.getValue();

            System.out.println("\nCoach: " + coachName);
            System.out.println("Average Rating for the month " + monthNumber + ": " + coachReportData.getAverageRating());
        }
    }

    public static String getDayByChoice(int dayChoice) {
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

    public static String getGenderByChoice(int genderChoice) {
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

    public static String promptUserForInput(Scanner scanner, String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }
}
