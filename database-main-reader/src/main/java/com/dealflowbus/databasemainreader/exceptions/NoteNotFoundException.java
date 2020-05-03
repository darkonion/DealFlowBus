package com.dealflowbus.databasemainreader.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class NoteNotFoundException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = -7420521241835322633L;

    public NoteNotFoundException(String message) {
        super(message);
    }
}
