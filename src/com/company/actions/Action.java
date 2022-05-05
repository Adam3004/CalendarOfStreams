package com.company.actions;

import java.io.FileNotFoundException;

public interface Action {
    String getActionName();

    void execute() throws FileNotFoundException;
}
