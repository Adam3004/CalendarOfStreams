package com.company.exceptionMethods;

import com.company.exceptions.NullInputException;

public class NullInputExceptionMethod {
    public static void method(String input) throws NullInputException {
        if (input.equals("")) {
            throw new NullInputException();
        }

    }
}
