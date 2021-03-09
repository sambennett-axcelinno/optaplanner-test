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
                //teacherConflict(constraintFactory),
                periodConflict(constraintFactory),
                teacherConflictBennett(constraintFactory),
                teacherConflictHulk(constraintFactory),
                teacherPrefersSameRoom(constraintFactory),
                preferBackToBack(constraintFactory)
        };
    }

    Constraint roomConflict(ConstraintFactory constraintFactory) {
        return constraintFactory.fromUniquePair(Lecture.class,
                Joiners.equal(Lecture::getRoomNumber), Joiners.equal(Lecture::getPeriod))
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

    Constraint teacherConflictBennett(ConstraintFactory constraintFactory) {
        return constraintFactory.from(Lecture.class)
                .join(Lecture.class,
                    Joiners.equal(Lecture::getTeacher))
                .filter(((lecture, lecture2) -> {return (lecture.getTeacher().getName().equals("Bennett") && lecture.getRoomNumber().equals(123));}))
                .penalize("Does not want room 123", HardSoftScore.ONE_SOFT);
    }

    Constraint teacherConflictHulk(ConstraintFactory constraintFactory) {
        return constraintFactory.from(Lecture.class)
                .join(Lecture.class,
                        Joiners.equal(Lecture::getTeacher))
                .filter(((lecture, lecture2) -> {return (lecture.getTeacher().getName().equals("Hulk") && lecture.getPeriod().equals(1));}))
                .penalize("Does not want period 1", HardSoftScore.ONE_SOFT);
    }

    Constraint teacherPrefersSameRoom(ConstraintFactory constraintFactory) {
        return constraintFactory.from(Lecture.class)
                .join(Lecture.class, Joiners.equal(Lecture::getTeacher))
                .filter((lecture, lecture2) -> {return lecture.getTeacher().equals(lecture2.getTeacher()) && !lecture.getRoomNumber().equals(lecture2.getRoomNumber());})
                .penalize("Prefer same room", HardSoftScore.ONE_SOFT);
    }

    Constraint preferBackToBack(ConstraintFactory constraintFactory) {
        return constraintFactory.from(Lecture.class)
                .join(Lecture.class, Joiners.equal(Lecture::getTeacher), Joiners.equal(Lecture::getPeriod))
                .filter((lecture, lecture2) -> {return lecture.getTeacher().equals(lecture2.getTeacher()) && (Math.abs(lecture.getPeriod() - lecture2.getPeriod()) != 1);})
                .penalize("prefer back to back", HardSoftScore.ONE_SOFT);
    }
}
