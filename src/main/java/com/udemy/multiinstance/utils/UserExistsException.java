package com.udemy.multiinstance.utils;

public class UserExistsException extends Exception {

    public UserExistsException(String message){
        super(message);
    }
}
