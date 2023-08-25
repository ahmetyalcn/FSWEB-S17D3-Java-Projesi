package com.workintech.zoo.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class RestException {
    @ExceptionHandler
    public ResponseEntity<AnimalErrorResponse> handleException(AnimalException exception){
        AnimalErrorResponse response = new AnimalErrorResponse(exception.getMessage(),
                exception.getHttpStatus().value(),System.currentTimeMillis());
        log.error(exception.getMessage());
        return new ResponseEntity<>(response,exception.getHttpStatus());
    }
    @ExceptionHandler
    public ResponseEntity<AnimalErrorResponse> handleException(Exception exception){
        AnimalErrorResponse response = new AnimalErrorResponse(exception.getMessage(),
                HttpStatus.BAD_REQUEST.value(),System.currentTimeMillis());
        log.error(exception.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
