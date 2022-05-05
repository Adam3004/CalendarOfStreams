package com.company.role;

import java.util.List;

public class Roles {
    private List<Role> roles;

    public Roles(List<Role> roles) {
        this.roles = roles;
    }

    public void roles(){
        roles.add(Role.MOD);
        roles.add(Role.STREAMER);
        roles.add(Role.VIEWER);
    }
}
