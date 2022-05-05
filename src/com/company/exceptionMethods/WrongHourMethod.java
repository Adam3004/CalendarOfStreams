package com.company.exceptionMethods;

import com.company.exceptions.WrongHourException;

public class WrongHourMethod {
    public static void method(int hour, int minutes) throws WrongHourException {
        if (hour < 0 || hour > 24 || minutes < 0 || minutes > 59) {
            throw new WrongHourException();
        }
    }
}
