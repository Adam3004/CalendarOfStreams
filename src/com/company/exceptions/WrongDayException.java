package com.company.exceptions;

public class WrongDayException extends Exception {
    public WrongDayException(){
        System.out.println("Day have to be a number from 1 to 31");
    }
}
