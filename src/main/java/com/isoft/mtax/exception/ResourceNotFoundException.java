package com.isoft.mtax.exception;

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(String message){
        super(message);
    }
}
