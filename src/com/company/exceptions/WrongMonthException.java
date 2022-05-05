package com.company.exceptions;

public class WrongMonthException extends Exception {
    public WrongMonthException() {
        System.out.println("Month have to be a number from 1 to 12");
    }
}
