package com.barberia.app.dto;

import java.time.LocalDateTime;

public class MyLocalDateTime {
    private LocalDateTime chosenTime;
    private boolean full;
    private boolean passed;

    public MyLocalDateTime() {
    }

    public MyLocalDateTime(LocalDateTime chosenTime, boolean full) {
        this.chosenTime = chosenTime;
        this.full = full;
    }

    public MyLocalDateTime(LocalDateTime chosenTime, boolean full, boolean passed) {
        this.chosenTime = chosenTime;
        this.full = full;
        this.passed = passed;
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

    public boolean isPassed() {
        return passed;
    }

    public void setPassed(boolean passed) {
        this.passed = passed;
    }
}
