package com.sam.constraintProvider;

import com.sam.courseSchedule.CourseSchedule;
import com.sam.lecture.Lecture;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;
import org.optaplanner.core.api.score.stream.Constraint;
import org.optaplanner.core.api.score.stream.ConstraintFactory;
import org.optaplanner.core.api.score.stream.ConstraintProvider;
import org.optaplanner.core.api.score.stream.Joiners;

public class LectureConstraintProvider implements ConstraintProvider {
    @Override
    public Constraint[] defineConstraints(ConstraintFactory constraintFactory) {
        return new Constraint[] {
                roomConflict(constraintFactory),
                //periodConflict(constraintFactory),
                //teacherConflict(constraintFactory),
                periodNotAvailableConflict(constraintFactory),
                teacherConflictBennett(constraintFactory),
                teacherConflictThor(constraintFactory),
                teacherPrefersSameRoom(constraintFactory),
                preferBackToBack(constraintFactory)
        };
    }

    Constraint roomConflict(ConstraintFactory constraintFactory) {
        return constraintFactory.fromUniquePair(Lecture.class,
                Joiners.equal(lecture -> lecture.getRoomNumber().getRoomNumber()), Joiners.equal(Lecture::getPeriod), Joiners.equal(lecture -> lecture.getRoomNumber().getBuilding()))
                .penalize("Room Conflict", HardSoftScore.ONE_HARD);
    }

    Constraint teacherConflict(ConstraintFactory constraintFactory) {
        return constraintFactory.fromUniquePair(Lecture.class,
                Joiners.equal(Lecture::getRoomNumber), Joiners.equal(Lecture::getTeacher))
                .penalize("Teacher Conflict", HardSoftScore.ONE_HARD);
    }

    Constraint periodConflict(ConstraintFactory constraintFactory) {
        return constraintFactory.fromUniquePair(Lecture.class,
                Joiners.equal(Lecture::getTeacher), Joiners.equal(Lecture::getPeriod))
                .penalize("Period Conflict", HardSoftScore.ONE_HARD);
    }

    Constraint periodNotAvailableConflict(ConstraintFactory constraintFactory) {
        return constraintFactory.from(Lecture.class)
                .filter(lecture -> {return lecture.getRoomNumber().getPeriodsNotAvailable() != null && lecture.getRoomNumber().getPeriodsNotAvailable().contains(lecture.getPeriod());})
                .penalize("Room not available during this period", HardSoftScore.ONE_HARD);
    }

    Constraint teacherConflictBennett(ConstraintFactory constraintFactory) {
        return constraintFactory.from(Lecture.class)
                .join(Lecture.class,
                    Joiners.equal(Lecture::getTeacher))
                .filter(((lecture, lecture2) -> {return ((lecture.getTeacher().getName().equals("Bennett") && lecture.getRoomNumber().getRoomNumber().equals(123)) || (lecture.getTeacher().getName().equals("Bennett") && lecture.getPeriod() <= 1));}))
                .penalize("Bennett conflicts", HardSoftScore.ONE_SOFT);
    }

    Constraint teacherConflictThor(ConstraintFactory constraintFactory) {
        return constraintFactory.from(Lecture.class)
                .join(Lecture.class,
                        Joiners.equal(Lecture::getTeacher))
                .filter(((lecture, lecture2) -> {return (lecture.getTeacher().getName().equals("Thor") && lecture.getPeriod().equals(2));}))
                .penalize("Does not want period 1", HardSoftScore.ONE_SOFT);
    }

    Constraint teacherPrefersSameRoom(ConstraintFactory constraintFactory) {
        return constraintFactory.from(Lecture.class)
                .join(Lecture.class, Joiners.equal(Lecture::getTeacher))
                .filter((lecture, lecture2) -> {return lecture.getTeacher().getName().equals(lecture2.getTeacher().getName()) && !lecture.getRoomNumber().getRoomNumber().equals(lecture2.getRoomNumber().getRoomNumber());})
                .penalize("Prefer same room", HardSoftScore.ONE_SOFT);
    }

    Constraint preferBackToBack(ConstraintFactory constraintFactory) {
        return constraintFactory.from(Lecture.class)
                .join(Lecture.class, Joiners.equal(Lecture::getTeacher), Joiners.equal(Lecture::getPeriod))
                .filter((lecture, lecture2) -> {return lecture.getTeacher().getName().equals(lecture2.getTeacher().getName()) && (Math.abs(lecture.getPeriod() - lecture2.getPeriod()) == 1);})
                .reward("prefer back to back", HardSoftScore.ONE_SOFT);
    }
    //test 7/12/21
}
