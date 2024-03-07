package org.hjss.booking.report;

import org.hjss.booking.Lesson;

import java.util.ArrayList;
import java.util.List;

public class LearnerReportData {
        private final String learnerName;
        private int bookedCount;
        private int cancelledCount;
        private int attendedCount;
        private final List<Lesson> bookedLessons;

        public LearnerReportData(String learnerName) {
            this.learnerName = learnerName;
            this.bookedCount = 0;
            this.cancelledCount = 0;
            this.attendedCount = 0;
            this.bookedLessons = new ArrayList<>();
        }

        public String getLearnerName() {
            return learnerName;
        }

        public int getBookedCount() {
            return bookedCount;
        }

        public int getCancelledCount() {
            return cancelledCount;
        }

        public int getAttendedCount() {
            return attendedCount;
        }

        public List<Lesson> getBookedLessons() {
            return bookedLessons;
        }

        public void incrementBookedCount() {
            bookedCount++;
        }

        public void incrementCancelledCount() {
            cancelledCount++;
        }

        public void incrementAttendedCount() {
            attendedCount++;
        }

        public void addLesson(Lesson lesson) {
            bookedLessons.add(lesson);
        }
    }
