package Entities;

import io.cucumber.java.sl.In;

import java.util.ArrayList;

public class User {

    private String uname;
    private String password;
    private String email;
    private String location;
    private Integer type;
    public User(String uname, String password, String email, String location, int userType) {
        this.uname = uname;
        this.password = password;
        this.email = email;
        this.location = location;
        this.type = userType;
    }

    public Integer getUserType()
    {
        return type;
    }

    public void setUserType(Integer userType)
    {
        this.type = userType;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public User(String email, String pass) {
        this.email = email;
        this.password = pass;
    }

    @Override
    public String toString() {
        return "User{" +
                "uname='" + uname + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", location='" + location + '\'' +
                '}';
    }

}
