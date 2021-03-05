package com.sam.courseSchedule;

import com.sam.lecture.Lecture;
import org.optaplanner.core.api.domain.solution.PlanningEntityCollectionProperty;
import org.optaplanner.core.api.domain.solution.PlanningScore;
import org.optaplanner.core.api.domain.solution.PlanningSolution;
import org.optaplanner.core.api.domain.solution.drools.ProblemFactCollectionProperty;
import org.optaplanner.core.api.domain.valuerange.ValueRangeProvider;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;

import java.util.ArrayList;
import java.util.List;

@PlanningSolution
public class CourseSchedule {

    private List<Integer> roomList;
    private List<Integer> periodList;
    private List<Lecture> lectureList;
    private HardSoftScore score;

    public CourseSchedule(List<Integer> roomList, List<Integer> periodList, List<Lecture> lectureList, HardSoftScore score) {
        this.roomList = roomList;
        this.periodList = periodList;
        this.lectureList = lectureList;
        this.score = score;
    }

    public CourseSchedule() {

    }

    @PlanningEntityCollectionProperty
    public List<Lecture> getLectureList() {
        if (lectureList != null) {
            return lectureList;
        }
        return new ArrayList<Lecture>();
    }

    @ValueRangeProvider(id = "availableRooms")
    @ProblemFactCollectionProperty
    public List<Integer> getRoomList() {
        if (roomList != null) {
            return roomList;
        }
        return new ArrayList<Integer>();
    }

    @ValueRangeProvider(id = "availablePeriods")
    @ProblemFactCollectionProperty
    public List<Integer> getPeriodList() {
        if (periodList != null) {
            return periodList;
        }
        return new ArrayList<Integer>();
    }

    @PlanningScore
    public HardSoftScore getScore() {
        return score;
    }

    public void setScore(HardSoftScore score) {
        this.score = score;
    }

    public String printCourseSchedule() {
        return lectureList.toString();
    }

}
