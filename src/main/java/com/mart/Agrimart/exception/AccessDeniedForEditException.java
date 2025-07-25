package com.mart.Agrimart.exception;

public class AccessDeniedForEditException extends RuntimeException{

    public AccessDeniedForEditException(String message)
    {
        super(message);
    }
}
