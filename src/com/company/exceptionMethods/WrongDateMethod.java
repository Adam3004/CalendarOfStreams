package com.company.exceptionMethods;

import com.company.date.LeapChecker;
import com.company.exceptions.ShorterMonthsException;
import com.company.exceptions.WrongDayException;
import com.company.exceptions.WrongMonthException;
import com.company.exceptions.WrongYearException;


public class WrongDateMethod {
    public static void method(int day, int month, int year) throws WrongDayException, WrongMonthException, WrongYearException, ShorterMonthsException {
        boolean isYearLeap = LeapChecker.IsYearLeap(year);
        if (month < 1 || month > 12) {
            throw new WrongMonthException();
        } else if (day < 1 || day > 31) {
            throw new WrongDayException();
        } else if (year < 1000 || year > 9999) {
            throw new WrongYearException();
        } else if ((!isYearLeap && month == 2 && day > 28)) {
            throw new ShorterMonthsException(28);
        } else if (month == 2 && isYearLeap && day > 29) {
            throw new ShorterMonthsException(29);
        } else if (((month % 2 == 0 && month < 8 && day > 30) || (month % 2 != 0 && month > 7 && day > 30))) {
            throw new ShorterMonthsException(30);
        }
    }
}
