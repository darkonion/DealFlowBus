package com.dealflowbus.databasemainreader.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class WrongHTTPQueryFormula extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = -7073847123116598939L;

    public WrongHTTPQueryFormula(String message) {
        super(message);
    }
}
