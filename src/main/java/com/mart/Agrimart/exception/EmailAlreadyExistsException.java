package com.mart.Agrimart.exception;

public class EmailAlreadyExistsException extends RuntimeException {

    public EmailAlreadyExistsException(String message)
    {
        super(message);
    }
}
