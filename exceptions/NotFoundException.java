package com.hf.exceptions;

public class NotFoundException extends RuntimeException {

    private String message;

    public NotFoundException(Exception e) {
        super(e);
    }

    public NotFoundException(String message) {
        super(message);
        this.message = message;
    }

    public String getMessage(){
        return this.message;
    }
}
