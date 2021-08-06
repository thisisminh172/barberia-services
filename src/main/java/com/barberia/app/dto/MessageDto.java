package com.barberia.app.dto;

public class MessageDto {
    private String message;
    private boolean available;

    public MessageDto(String message, boolean available) {
        this.message = message;
        this.available = available;
    }

    public MessageDto() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}
