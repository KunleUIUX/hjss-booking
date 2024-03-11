package org.hjss.booking;

import org.hjss.booking.helper.BookingSystemHelper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class BookingManagementSystemTest {

    private BookingManagementSystem bookingSystem;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();



    @Mock
    private Scanner scanner;

    @BeforeEach
    void setUp() {

        System.setOut(new PrintStream(outputStreamCaptor));


        // Initialize or instantiate your BookingManagementSystem
        bookingSystem = new BookingManagementSystem();
        // Other setup code as needed


        Learner learnerCreate = new Learner();
        learnerCreate.setName("John Doe");
        learnerCreate.setGradeLevel(3);
        learnerCreate.setGender("Female");
        learnerCreate.setAge(5);
        learnerCreate.setUuid(UUID.randomUUID());
        learnerCreate.setEmergencyContact("890-39480-223");
        bookingSystem.registerNewLearner(learnerCreate);

        Coach coach = new Coach();
        coach.setName("Coach Helen");
        bookingSystem.addCoach(coach);

        Lesson lesson = new Lesson();
        lesson.setCurrentCapacity(0);
        lesson.setDateTime(LocalDateTime.now());
        lesson.setUuid(UUID.fromString("550e8400-e29b-41d4-a716-446655440000"));
        lesson.setDay("Monday");
        lesson.setCoach(coach);
        lesson.setGradeLevel(4);
        lesson.setMaxCapacity(4);
        lesson.setTimeSlot("4-5pm");

        bookingSystem.addLesson(lesson);
    }


    @AfterEach
    public void tearDown() {
        System.setOut(System.out);
    }

    /*
    @Test
    void bookSwimmingLesson_SuccessfulBooking() {

        BookingManagementSystem bookingSystem = new BookingManagementSystem();
        Learner learner = new Learner();
        learner.setName("John Doe");
        learner.setGradeLevel(3);
        learner.setGender("Female");
        learner.setAge(5);
        learner.setUuid(UUID.randomUUID());
        learner.setEmergencyContact("890-39480-223");
        bookingSystem.registerNewLearner(learner);

        Coach coach = new Coach();
        coach.setName("Coach Helen");
        bookingSystem.addCoach(coach);

        Lesson lesson = new Lesson();
        lesson.setUuid(UUID.randomUUID());
        lesson.setDay("Monday");
        lesson.setTimeSlot("4-5pm");
        lesson.setGradeLevel(3);
        lesson.setCoach(coach);
        bookingSystem.addLesson(lesson);

        String input = "John Doe\n";
        input += "Monday\n";
        input += "4-5pm\n";
        input += "exit\n";

        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        bookingSystem.bookSwimmingLesson(new Scanner(System.in));

        assertEquals(1, bookingSystem.getBookings().size());
        assertEquals(1, lesson.getCurrentCapacity());
        assertTrue(bookingSystem.getBookings().get(0).getStatus().equalsIgnoreCase("booked"));

    }

     */

    @Test
    void bookSwimmingLesson_Success(){

        String input = "John Doe\n1\n1\n550e8400-e29b-41d4-a716-446655440000\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        bookingSystem.bookSwimmingLesson(new Scanner(System.in));

        // Replace the expected values with the expected output based on your logic
        Learner learner = bookingSystem.findLearnerByName("John Doe");
        Lesson bookedLesson = bookingSystem.getLessonById("550e8400-e29b-41d4-a716-446655440000");
        Booking booking = bookingSystem.findBookingByLearnerAndLesson(learner, bookedLesson);

        System.out.println(booking.getUuid());

        assertEquals(3, learner.getGradeLevel());
        assertTrue(booking != null && booking.getStatus().equals("booked"));
        assertTrue(bookedLesson.getCurrentCapacity() > 0);
    }

    @Test
    void testCancelBooking_Success() {
        // Mock user input
        String userInput = "John Doe\n550e8400-e29b-41d4-a716-446655440000\n1\n3\nCoach Helen\n2\n4\n";
        InputStream inputStream = new ByteArrayInputStream(userInput.getBytes(StandardCharsets.UTF_8));
        System.setIn(inputStream);


        Learner mockLearner = bookingSystem.findLearnerByName("John Doe");

        // Mock bookings

        Booking mockBooking = new Booking();
        mockBooking.setUuid(UUID.fromString("550e8400-e29b-41d4-a716-446655440000"));
        mockBooking.setLearner(mockLearner);

        Lesson mockLesson = bookingSystem.getLessonById("550e8400-e29b-41d4-a716-446655440000");


        mockLesson.setGradeLevel(3);
        mockLesson.setCurrentCapacity(3);
        mockBooking.setLesson(mockLesson);
        mockBooking.setTimestamp(LocalDateTime.now());
        mockBooking.setStatus("booked");

        bookingSystem.addBooking(mockBooking);

        // Override System.in
        System.setIn(inputStream);

        // Test the method
        bookingSystem.changeCancelBooking(new Scanner(System.in));

        // Add assertions based on the expected behavior of the method
        // For example, check that the booking status is updated to "cancelled"
        // and that the current capacity of the lesson is decremented.
        assertEquals("cancelled", mockBooking.getStatus());
        assertEquals(2, mockLesson.getCurrentCapacity());

        // Reset System.in
        System.setIn(System.in);
    }

    @Test
    void testAttendSwimmingLesson_Success() {
        // Mock user input
        String userInput = "John Doe\n550e8400-e29b-41d4-a716-446655440000\nThis is a review\n5\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(userInput.getBytes(StandardCharsets.UTF_8));

        // Mock learner
        Learner mockLearner = bookingSystem.findLearnerByName("John Doe");

        // Mock bookings

        Booking mockBooking = new Booking();
        mockBooking.setUuid(UUID.fromString("550e8400-e29b-41d4-a716-446655440000"));
        mockBooking.setLearner(mockLearner);

        Lesson mockLesson = bookingSystem.getLessonById("550e8400-e29b-41d4-a716-446655440000");

        mockBooking.setLesson(mockLesson);
        mockBooking.setTimestamp(LocalDateTime.now());
        mockBooking.setStatus("booked");

        bookingSystem.addBooking(mockBooking);

        // Override System.in
        System.setIn(inputStream);

        // Test the method
        bookingSystem.attendSwimmingLesson(new Scanner(System.in));

        // Add assertions based on the expected behavior of the method
        // For example, check that the booking status is updated to "attended"
        // and that the lesson's grade level is updated if needed.
        assertEquals("attended", mockBooking.getStatus());
        assertEquals("This is a review", mockBooking.getReview());
        assertEquals(5, mockBooking.getRating());

        //ensuring there is an upgrade for the learner's grade Level
        assertEquals(4, mockLearner.getGradeLevel());

        // Reset System.in
        System.setIn(System.in);
    }


    @Test
    void testRegisterNewLearner_ValidInput() {
        // Mock user input
        String input = "John\n1\n10\n123-456-789\n3\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        // Call the method
        bookingSystem.registerNewLearner(new Scanner(System.in));

        // Check if the learner is registered successfully
        assertEquals(2, bookingSystem.getLearners().size());
        assertEquals("John", bookingSystem.getLearners().get(1).getName());
    }


    //    @Test
    void testRegisterNewLearnerInvalidAge() {
        String input = "Alice\n2\n15\n987-654-321\n4\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        // Call the method
        bookingSystem.registerNewLearner(new Scanner(System.in));

        // Check if the learner is not registered due to invalid age
        assertEquals(1, bookingSystem.getLearners().size());
        assertTrue(outputStreamCaptor.toString().contains("Invalid learner age."));
    }

    @Test
    void testRegisterNewLearnerAlreadyRegistered() {
        // Prepare the system with an existing learner
        Learner existingLearner = bookingSystem.findLearnerByName("John Doe");

        // Provide input for a learner with the same name
        String input = "John Doe\n1\n7\n123-456-789\n4\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        // Call the method
        bookingSystem.registerNewLearner(new Scanner(System.in));

        // Check if the learner is not registered due to being already registered
        assertEquals(1, bookingSystem.getLearners().size());
        assertTrue(outputStreamCaptor.toString().contains("This learner is already registered in the system."));
    }

}