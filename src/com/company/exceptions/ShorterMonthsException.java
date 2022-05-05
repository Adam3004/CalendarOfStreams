package com.company.exceptions;

public class ShorterMonthsException extends Exception {
    public ShorterMonthsException(int days) {
        System.out.println("This month have " + days + " days");
    }
}
