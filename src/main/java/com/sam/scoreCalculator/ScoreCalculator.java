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
                    String roomInUse = lecture.getPeriod().toString() + ":" + lecture.getRoomNumber().toString();
                    if (occupiedRooms.contains(roomInUse)) {
                        hardScore += -1;
                    } else {
                        occupiedRooms.add(roomInUse);
                    }

                    if (lecture.getTeacher().getName().equals("Bennett") && lecture.getRoomNumber().equals(123)) {
                        softScore += -1;
                    }

                    if (lecture.getTeacher().getName().equals("Thor") && lecture.getPeriod().equals(2)) {
                        softScore += -1;
                    }
                }
            }
        }

        return HardSoftScore.of(hardScore, softScore);
    }

}
