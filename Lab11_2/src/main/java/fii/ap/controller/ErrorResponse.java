package fii.ap.controller;

import java.time.LocalDateTime;

public class ErrorResponse {
    private String msg;
    private LocalDateTime timestamp;

    public ErrorResponse(String msg, LocalDateTime timestamp) {
        this.msg = msg;
        this.timestamp = timestamp;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}

