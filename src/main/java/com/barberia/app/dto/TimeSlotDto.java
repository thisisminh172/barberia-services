package com.barberia.app.dto;

import java.sql.Time;

public class TimeSlotDto {

    private Time timeSlotAfterFilter;
    private boolean isFulll;

    public TimeSlotDto() {
    }

    public TimeSlotDto(Time timeSlotAfterFilter, boolean isFulll) {
        this.timeSlotAfterFilter = timeSlotAfterFilter;
        this.isFulll = isFulll;
    }

    public Time getTimeSlotAfterFilter() {
        return timeSlotAfterFilter;
    }

    public void setTimeSlotAfterFilter(Time timeSlotAfterFilter) {
        this.timeSlotAfterFilter = timeSlotAfterFilter;
    }

    public boolean isFulll() {
        return isFulll;
    }

    public void setFulll(boolean fulll) {
        isFulll = fulll;
    }
}
