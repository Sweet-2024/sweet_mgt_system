package Entities;

import io.cucumber.java.sl.In;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class User {

    private String uname;
    private String password;
    private String email;
    private String location;
 
    public User(String uname, String password, String email, String location, int userType) {
        this.uname = uname;
        this.password = password;
        this.email = email;
        this.location = location;
        this.type = userType;
    }

    private int type;

    public int getType() {return type;}

    public void setType(int type) {this.type = type;}


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

    public User(String password, String email) {
        this.password = password;
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "uname='" + uname + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", location='" + location + '\'' +
                ", type=" + type +
                '}';
    }

    public static int userTypeByEmail(String email){
        int userType = 0;
        String qry = "SELECT user_type FROM users WHERE user_email = '"+email+"';";
        ResultSet rs = Database.connectionToSelectFromDB(qry);
        try {
            if(rs.next()){
                userType = rs.getInt("user_type");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return userType;
    }
}
