package com.sam.lecture;

import org.optaplanner.core.api.domain.entity.PlanningEntity;
import org.optaplanner.core.api.domain.variable.PlanningVariable;

@PlanningEntity
public class Lecture {

    public Integer roomNumber;
    public Integer period;
    public String teacher;

    public Lecture(Integer roomNumber, Integer period, String teacher) {
        this.roomNumber = roomNumber;
        this.period = period;
        this.teacher = teacher;
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
    public Integer getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(Integer roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getTeacher() {
        return teacher;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(teacher).append(" teaches in room ").append(roomNumber).append(" during period ").append(period);
        return builder.toString();
    }
}
