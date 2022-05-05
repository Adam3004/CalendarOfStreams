package com.company.menus;

import com.company.role.Role;

import java.io.FileNotFoundException;

public interface Menu {
    String getMenusName();

    Role role();

    void execute() throws FileNotFoundException;


}
