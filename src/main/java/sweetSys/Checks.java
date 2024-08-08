package sweetSys;

import Entities.Database;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Checks {
    public static boolean checkIfUserInDatabase(String email, String pass)
    {
        String qry = "select * from sweetsys.users where users.user_email = '"+email+"' and users.user_password = '"+pass+"';";
        ResultSet rs = Database.connectionToSelectFromDB(qry);

        try {
            if(rs.next()){
                return true;
            }
            else {
                return false;
            }
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }
    public static boolean checkIfThereAreUsersInDatabase()
    {
        String qry = "SELECT * FROM sweetsys.users WHERE user_type = 2 OR user_type = 3 OR user_type = 4;";
        ResultSet rs = Database.connectionToSelectFromDB(qry);

        try {
            if(rs.next()){
                return true;
            }
            else {
                return false;
            }
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }
    public static boolean checkIfProductInDatabase(String name)
    {
        String qry = "select * from sweetsys.Product where Product.product_name = '"+name+"';";
        ResultSet rs = Database.connectionToSelectFromDB(qry);

        try {
            if(rs.next()){
                return true;
            }
            else {
                return false;
            }
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }
    public static boolean checkIfThereAreProductsInDatabase()
    {
        String qry = "select * from sweetsys.Product;";
        ResultSet rs = Database.connectionToSelectFromDB(qry);

        try {
            if(rs.next()){
                return true;
            }
            else {
                return false;
            }
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }
    public static boolean isValidProductName(String productName)
    {
        if (productName == null) return false;
        return (productName.length() <= 50);
    }
    public static boolean isValidEmail(String email)
    {
        if (email == null)
            return false;
        else if(!email.contains("@"))
            return false;
        else if (email.length() > 50)
            return false;
        String domain = email.substring(email.indexOf("@")+1);

        if(!domain.contains("."))
            return false;

        return true;
    }
}