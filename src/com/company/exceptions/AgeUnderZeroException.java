package com.company.exceptions;

public class AgeUnderZeroException extends Exception {
    public AgeUnderZeroException() {
        System.out.println("Age have to be higher than 0!");
    }

}
