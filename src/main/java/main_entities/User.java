package main_entities;

import java.sql.ResultSet;
import java.sql.SQLException;

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


    public String getUname() {
        return uname;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getLocation() {
        return location;
    }


    public User(String password, String email) {
        this.password = password;
        this.email = email;
    }



    public static int userTypeByEmail(String email) {
        int userType = 0;
        String qry = "SELECT user_type FROM users WHERE user_email = '" + email + "';";

        try {
            ResultSet rs = Database.connectionToSelectFromDB(qry);
            if (rs != null && rs.next()) {
                userType = rs.getInt("user_type");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (DatabaseOperationException e) {
            System.err.println("Error fetching user type: " + e.getMessage());
        }

        return userType;
    }

}
