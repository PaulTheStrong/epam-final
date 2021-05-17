package com.epam.web.enitity;

import java.util.List;

public class User implements Identifiable{

    private long id;

    private String login;
    private String name;
    private String surname;

    private Role role;
    private boolean blocked;

    public User(long id, String login, String name, String surname, Role role, boolean blocked) {
        this.id = id;
        this.login = login;
        this.name = name;
        this.surname = surname;
        this.role = role;
        this.blocked = blocked;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Override
    public long getId() {
        return id;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }
}
