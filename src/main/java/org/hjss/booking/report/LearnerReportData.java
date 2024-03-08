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
        private final List<Lesson> cancelledLessons;
        private final List<Lesson> attendedLessons;

        public LearnerReportData(String learnerName) {
            this.learnerName = learnerName;
            this.cancelledLessons = new ArrayList<>();
            this.attendedLessons = new ArrayList<>();
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

    public List<Lesson> getAttendedLessons() {
        return attendedLessons;
    }

    public List<Lesson> getCancelledLessons() {
        return cancelledLessons;
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

        public void addAttendedLesson(Lesson lesson) {
            attendedLessons.add(lesson);
        }


    public void addCancelledLesson(Lesson lesson) {
        cancelledLessons.add(lesson);
    }

}


