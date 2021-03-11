package com.sam.room;

import java.util.List;

public class Room {

    private Integer roomNumber;
    private List<Integer> periodsNotAvailable;
    private String building;

    public Room() {

    }

    public Room(Integer roomNumber, List<Integer> periodsNotAvailable, String building) {
        this.roomNumber = roomNumber;
        this.periodsNotAvailable = periodsNotAvailable;
        this.building = building;
    }

    public Integer getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(Integer roomNumber) {
        this.roomNumber = roomNumber;
    }

    public List<Integer> getPeriodsNotAvailable() {
        return periodsNotAvailable;
    }

    public void setPeriodsNotAvailable(List<Integer> periodsNotAvailable) {
        this.periodsNotAvailable = periodsNotAvailable;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }
}
