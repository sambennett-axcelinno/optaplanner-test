package com.sam.room;

import java.util.List;

public class Room {

    private Integer roomNumber;
    private List<Integer> periodsNotAvailable;

    public Room() {

    }

    public Room(Integer roomNumber, List<Integer> periodsNotAvailable) {
        this.roomNumber = roomNumber;
        this.periodsNotAvailable = periodsNotAvailable;
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
}
