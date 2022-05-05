package com.company.exceptions;

public class WrongConvertToRoleException extends Exception {
    public WrongConvertToRoleException(){
        System.out.println("This role does not exist");
    }
}
