package com.company.date;

public class LeapChecker {
    public static boolean IsYearLeap(int Year) {
        return Year % 400 == 0 || (Year % 4 == 0 && Year % 100 != 0);
    }
}
