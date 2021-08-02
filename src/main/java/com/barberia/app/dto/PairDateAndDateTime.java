package com.barberia.app.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class PairDateAndDateTime {
    private LocalDate key;
    private List<MyLocalDateTime> value;

    public PairDateAndDateTime() {
    }

    public PairDateAndDateTime(LocalDate key, List<MyLocalDateTime> value) {
        this.key = key;
        this.value = value;
    }

    public LocalDate getKey() {
        return key;
    }

    public void setKey(LocalDate key) {
        this.key = key;
    }

    public List<MyLocalDateTime> getValue() {
        return value;
    }

    public void setValue(List<MyLocalDateTime> value) {
        this.value = value;
    }
}
