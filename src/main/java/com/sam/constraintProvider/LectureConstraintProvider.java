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
                periodConflict(constraintFactory)
        };
    }

    Constraint roomConflict(ConstraintFactory constraintFactory) {
        return constraintFactory.fromUniquePair(Lecture.class,
                Joiners.equal(Lecture::getRoomNumber)).penalize("Room Conflict", HardSoftScore.ONE_HARD);
    }

    Constraint periodConflict(ConstraintFactory constraintFactory) {
        return constraintFactory.fromUniquePair(Lecture.class,
                Joiners.equal(Lecture::getPeriod)).penalize("Period Conflict", HardSoftScore.ONE_HARD);
    }
}
