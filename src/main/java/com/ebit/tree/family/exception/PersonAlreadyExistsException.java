package com.ebit.tree.family.exception;

public class PersonAlreadyExistsException extends RuntimeException{
    public PersonAlreadyExistsException(String message){
        super(message);
    }
}
