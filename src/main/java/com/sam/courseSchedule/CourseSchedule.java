package com.sam.courseSchedule;

import com.sam.lecture.Lecture;
import com.sam.room.Room;
import com.sam.timeslot.Timeslot;
import org.optaplanner.core.api.domain.lookup.PlanningId;
import org.optaplanner.core.api.domain.solution.PlanningEntityCollectionProperty;
import org.optaplanner.core.api.domain.solution.PlanningScore;
import org.optaplanner.core.api.domain.solution.PlanningSolution;
import org.optaplanner.core.api.domain.solution.ProblemFactCollectionProperty;
import org.optaplanner.core.api.domain.valuerange.ValueRangeProvider;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import java.util.ArrayList;
import java.util.List;

@PlanningSolution
public class CourseSchedule {

    private List<Room> roomList;
    private List<Integer> periodList;
    private List<Lecture> lectureList;
    private HardSoftScore score;
    private List<Timeslot> timeslotList;


    public CourseSchedule(List<Room> roomList, List<Integer> periodList, List<Lecture> lectureList, HardSoftScore score, List<Timeslot> timeslotList) {
        this.roomList = roomList;
        this.periodList = periodList;
        this.lectureList = lectureList;
        this.score = score;
        this.timeslotList = timeslotList;
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
    public List<Room> getRoomList() {
        if (roomList != null) {
            return roomList;
        }
        return new ArrayList<Room>();
    }

    @ValueRangeProvider(id = "availablePeriods")
    @ProblemFactCollectionProperty
    public List<Integer> getPeriodList() {
        if (periodList != null) {
            return periodList;
        }
        return new ArrayList<Integer>();
    }

    @ValueRangeProvider(id = "availableTimeslot")
    @ProblemFactCollectionProperty
    public List<Timeslot> getTimeslotList() {
        if (timeslotList != null) {
            return timeslotList;
        }
        return new ArrayList<Timeslot>();
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
