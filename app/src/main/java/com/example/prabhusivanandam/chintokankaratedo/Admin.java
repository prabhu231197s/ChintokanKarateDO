package com.example.prabhusivanandam.chintokankaratedo;

/**
 * Created by Prabhu Sivanandam on 12-May-17.
 */

public class Admin {

    //Model class for the admins

    String username,password;

    public Admin()
    {
    }

    public Admin(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
