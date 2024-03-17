package com.hf.controller;

import com.hf.exceptions.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(value = NotFoundException.class)
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    public String notFoundException(Exception e){
        log.error(e.getMessage());
        return "not found";
    }


    @ExceptionHandler(value = IllegalArgumentException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public String illegalArgumentException(Exception e){
        log.error(e.getMessage());
        return "illegal arguments";
    }
}
