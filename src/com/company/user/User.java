package com.company.user;

import com.company.role.Role;

import java.util.List;

public class User {
    private String login;
    private String password;
    private int age;
    private Role role;
    private String followingStreamers;

    public User(String login, String password, int age, Role role,String followingStreamers) {
        this.login = login;
        this.password = password;
        this.age = age;
        this.role = role;
        this.followingStreamers = followingStreamers;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getFollowingStreamers() {
        return followingStreamers;
    }

    public void setFollowingStreamers(String followingStreamers) {
        this.followingStreamers = followingStreamers;
    }
}
