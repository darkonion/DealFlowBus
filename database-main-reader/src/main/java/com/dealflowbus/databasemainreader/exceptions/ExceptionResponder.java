package com.dealflowbus.databasemainreader.exceptions;

import java.util.Date;

public class ExceptionResponder {
    private Date timestamp;
    private String message;
    private String details;

    public ExceptionResponder() {
    }

    public ExceptionResponder(Date timestamp, String message, String details) {
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String massage) {
        this.message = massage;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
