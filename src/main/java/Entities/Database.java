package Entities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Database {
    static Connection conn;
    static Statement stmt;
    public static boolean checkForUsers(String email, String pass)
    {
        try {
            DriverManager.registerDriver(new org.postgresql.Driver());
            String connInfo = "jdbc:postgresql://localhost:5432/postgres";
            conn = DriverManager.getConnection(connInfo, "user1", "654321");

            stmt = conn.createStatement();
            String qry = "select * from sweetSystem.users where users.user_email = '"+email+"' and users.user_password = '"+pass+"';";

            ResultSet rs = stmt.executeQuery(qry);

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
}
