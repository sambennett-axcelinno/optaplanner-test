package com.sam.lecture;

import com.sam.room.Room;
import com.sam.teacher.Teacher;
import com.sam.timeslot.Timeslot;
import org.optaplanner.core.api.domain.entity.PlanningEntity;
import org.optaplanner.core.api.domain.lookup.PlanningId;
import org.optaplanner.core.api.domain.variable.PlanningVariable;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@PlanningEntity
public class Lecture {

    public Room roomNumber;
    public Integer period;
    public Teacher teacher;
    public Timeslot timeslot;

    @PlanningId
    @Id
    @GeneratedValue
    private Integer id;

    public Lecture(Room roomNumber, Integer period, Teacher teacher, Timeslot timeslot, Integer  id) {
        this.roomNumber = roomNumber;
        this.period = period;
        this.teacher = teacher;
        this.timeslot = timeslot;
        this.id = id;
    }

    public Lecture(Teacher teacher, Integer id) {
        this.teacher = teacher;
        this.id = id;
    }

    public Lecture() {

    }

    @PlanningVariable(
            valueRangeProviderRefs = {"availablePeriods"})
    public Integer getPeriod() {
        return period;
    }

    public void setPeriod(Integer period) {
        this.period = period;
    }

    @PlanningVariable(
            valueRangeProviderRefs = {"availableRooms"})
    public Room getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(Room roomNumber) {
        this.roomNumber = roomNumber;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    @PlanningVariable(valueRangeProviderRefs = {"availableTimeslot"})
    public Timeslot getTimeslot(){
        return  timeslot;
    }

    public void setTimeslot(Timeslot timeslot) {
        this.timeslot = timeslot;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        if (roomNumber != null) {
            builder.append(teacher.getName()).append(" teaches in room ").append(roomNumber.getRoomNumber()).append(" during period ").append(period);
            return builder.toString();
        }
        return builder.toString();
    }
}
