package com.barberia.app.dto;

import java.time.LocalDateTime;

public class TimeAndCountDto {
    private LocalDateTime time;
    private int count;

    public TimeAndCountDto() {
    }

    public TimeAndCountDto(LocalDateTime time, int count) {
        this.time = time;
        this.count = count;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
