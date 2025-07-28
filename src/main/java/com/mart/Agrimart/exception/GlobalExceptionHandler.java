package com.mart.Agrimart.exception;

import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<?> handleEmailException(EmailAlreadyExistsException ex)
    {
        return ResponseEntity.badRequest().body(Map.of("message",ex.getMessage()));
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> handleRuntimeException(RuntimeException ex)
    {
        return ResponseEntity.badRequest().body(Map.of("message",ex.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentException(MethodArgumentNotValidException ex)
    {
        Map<String,String> errors=new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error ->errors.put(error.getField(),error.getDefaultMessage()));

        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(MissingServletRequestPartException.class)
    public ResponseEntity<?> handleMissingPart(MissingServletRequestPartException ex) {
        String missingPart = ex.getRequestPartName();
        return ResponseEntity.badRequest()
                .body(Map.of("message", "Required part '" + missingPart + "' is missing"));
    }

    @ExceptionHandler(AccessDeniedForEditException.class)
    public ResponseEntity<?> handleEditException(AccessDeniedForEditException ex)
    {
        return  ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message",ex.getMessage()));
    }

    @ExceptionHandler(EmailNotVerifiedException.class)
    public ResponseEntity<?> handleEmailNotVerifyException(EmailNotVerifiedException ex)
    {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(Map.of("message",ex.getMessage()));
    }
}
