package com.s212037943.KurvinHendricks.databaseapplication;

/**
 * Created by Home on 8/17/2014.
 */
public class User {

    private int id;
    private String email;
    private String auth;

    public User(){};

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }
}
