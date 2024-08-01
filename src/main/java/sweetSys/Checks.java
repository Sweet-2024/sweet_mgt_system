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
    public static boolean checkForType(Integer type)
    {
        try {

            String qry = "select * from sweetSys.users where users.user_type = "+type+";";

            ResultSet rs = Database.connectionToSelectFromDB(qry);

            if(rs.next()){
                return true;
            }
            else {
                return false;
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
            return false;
        }
    }
    public static boolean checkIfThereAreUsersInDatabase()
    {
        String qry = "select * from sweetsys.users;";
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
}