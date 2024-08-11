package Entities;

public class User {

    private String uname;
    private String password;
    private String email;
    private String location;
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

    public User( String uname, String pass,String email, String location, int userType) {
        this.email = email;
        this.password = pass;
        this.uname = uname;
        this.location = location;
        this.type=userType;
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
}
