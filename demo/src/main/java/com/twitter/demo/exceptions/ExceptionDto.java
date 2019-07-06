package com.twitter.demo.exceptions;

public class ExceptionDto {
    String message;

    public ExceptionDto(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
