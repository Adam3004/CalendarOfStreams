package com.company.convert;

import com.company.role.Role;

public class ConvertStringToRole {

    public static Role convert(String input) {
        if (input.equals("VIEWER")) {

            return Role.VIEWER;
        } else if (input.equals("MOD")) {

            return Role.MOD;
        } else {

            return Role.STREAMER;
        }
    }
}
