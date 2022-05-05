package com.company.exceptionMethods;

import com.company.exceptions.AgeUnderZeroException;

public class AgeUnderZeroMethod {
    public static void method(int age) throws AgeUnderZeroException {
        if (age <= 0) {
            throw new AgeUnderZeroException();
        }
    }
}
