package com.company.exceptions;

public class WrongHourException extends Exception {
    public WrongHourException(){
        System.out.println("Something went wrong. You have to enter an hour from 00 to 23 and minutes from 00 to 59 ");
    }
}
