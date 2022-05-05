package com.company.exceptionMethods;

import com.company.exceptions.WrongConvertToRoleException;

public class WrongConverToRoleMethod {
    public static void method(String role) throws WrongConvertToRoleException {
        if (!role.equals("VIEWER") && !role.equals("MOD") && !role.equals("STREAMER")) {
            throw new WrongConvertToRoleException();
        }
    }
}
