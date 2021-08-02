package com.barberia.app.dto;

import java.time.LocalDateTime;

public class MyLocalDateTime {
    private LocalDateTime chosenTime;
    private boolean full;

    public MyLocalDateTime() {
    }

    public MyLocalDateTime(LocalDateTime chosenTime, boolean full) {
        this.chosenTime = chosenTime;
        this.full = full;
    }

    public LocalDateTime getChosenTime() {
        return chosenTime;
    }

    public void setChosenTime(LocalDateTime chosenTime) {
        this.chosenTime = chosenTime;
    }

    public boolean isFull() {
        return full;
    }

    public void setFull(boolean full) {
        this.full = full;
    }
}
