package com.mart.Agrimart.exception;

public class EmailNotVerifiedException extends RuntimeException
{

    public EmailNotVerifiedException()
    {
            super("Email needs to be verified for login");
    }
}

