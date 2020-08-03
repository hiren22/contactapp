package com.sample.contactapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidContactException extends RuntimeException{

    public InvalidContactException(String message) {
        super(message);
    }

}
