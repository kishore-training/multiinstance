package com.udemy.multiinstance.utils;

public class AppointmentExistsException extends Exception {

    public AppointmentExistsException(String message){
        super(message);
    }
}
