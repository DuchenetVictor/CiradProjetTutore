package com.example.iem.cirad.Pojo;

/**
 * Created by iem on 05/01/2017.
 */

public class User {

    private int Id;
    private String Login;
    private String Password;

    public User(int id, String login, String password) {
        Id = id;
        Login = login;
        Password = password;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getLogin() {
        return Login;
    }

    public void setLogin(String login) {
        Login = login;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
