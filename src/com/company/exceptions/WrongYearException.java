package com.company.exceptions;

public class WrongYearException extends Exception {
    public WrongYearException(){
        System.out.println("Enter a year as a four-digit number");
    }
}
