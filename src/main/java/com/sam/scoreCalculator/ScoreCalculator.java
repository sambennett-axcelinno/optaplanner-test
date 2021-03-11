package com.sam.scoreCalculator;

import com.sam.courseSchedule.CourseSchedule;
import com.sam.lecture.Lecture;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;
import org.optaplanner.core.api.score.calculator.EasyScoreCalculator;

import java.util.HashSet;
import java.util.Set;

public class ScoreCalculator implements EasyScoreCalculator<CourseSchedule, HardSoftScore> {

    @Override
    public HardSoftScore calculateScore(CourseSchedule courseSchedule) {
        int hardScore = 0;
        int softScore = 0;


        Set<String> occupiedRooms = new HashSet<>();
        for (Lecture lecture : courseSchedule.getLectureList()) {
                if (lecture != null) {
                    if (lecture.getPeriod() != null && lecture.getRoomNumber() != null) {
                        String roomInUse = lecture.getPeriod().toString() + ":" + lecture.getRoomNumber().getRoomNumber().toString();
                        if (occupiedRooms.contains(roomInUse)) {
                            hardScore += -1;
                        } else {
                            occupiedRooms.add(roomInUse);
                        }

                        if (lecture.getTeacher().getName().equals("Bennett") && lecture.getRoomNumber().getRoomNumber().equals(123)) {
                            softScore += -1;
                        }

                        if (lecture.getTeacher().getName().equals("Thor") && lecture.getPeriod().equals(2)) {
                            softScore += -1;
                        }

                        if (lecture.getTeacher().getName().equals("Bennett") && lecture.getPeriod() <= 1) {
                            softScore--;
                        }

                        for (Lecture lecture1 : courseSchedule.getLectureList()) {
                            if (lecture.getTeacher().getName().equals(lecture1.getTeacher().getName()) && lecture1.getPeriod() != null && lecture1.getRoomNumber() != null) {
                                if (!lecture.getRoomNumber().getRoomNumber().equals(lecture1.getRoomNumber().getRoomNumber())) {
                                    softScore--;
                                }
                                if (Math.abs(lecture.getPeriod() - lecture1.getPeriod()) == 1) {
                                    softScore++;
                                }
                            }
                        }
                    }
                }
        }
        /*for (Lecture lecture1 : courseSchedule.getLectureList()) {
            for (Lecture lecture2 : courseSchedule.getLectureList()) {
                if (lecture1 != null && lecture2 != null && lecture1.getPeriod() != null && lecture1.getRoomNumber() != null && lecture2.getPeriod() != null && lecture2.getRoomNumber() != null) {
                    if (!(lecture1.getTeacher().equals(lecture2.getTeacher()))) {
                        if (lecture1.getPeriod().equals(lecture2.getPeriod()) && lecture1.getRoomNumber().equals(lecture2.getRoomNumber())) {
                            hardScore--;
                        }
                        if (lecture1.getTeacher().getName().equals("Bennett") && lecture1.getRoomNumber().equals(123)) {
                            softScore--;
                        }
                        if (lecture1.getTeacher().getName().equals("IronMan") && lecture1.getRoomNumber().equals(123)) {
                            softScore++;
                        }
                    }
                }
            }*/
            /*if (lecture1 != null && lecture1.getTeacher() != null && lecture1.getRoomNumber() != null && lecture1.getPeriod() != null) {
                if (lecture1.getTeacher().getName().equals("Bennett") && lecture1.getRoomNumber().equals(123)) {
                    softScore--;
                }
            }
        }*/

        return HardSoftScore.of(hardScore, softScore);
    }

}
