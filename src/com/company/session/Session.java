package com.company.session;

import com.company.user.User;


public class Session {
    private User sessionUser;

    public Session(User sessionUser) {
        this.sessionUser = sessionUser;
    }

    public Session(){

    }

    public User getSessionUser() {
        return sessionUser;
    }

    public void setSessionUser(User sessionUser) {
        this.sessionUser = sessionUser;
    }
}
