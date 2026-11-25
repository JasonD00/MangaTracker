package com.mangatracker.exception;

// Custom error message for duplicate entries
public class DuplicateResourceException extends RuntimeException{
    public DuplicateResourceException(String message){
        super(message);
    }
}
