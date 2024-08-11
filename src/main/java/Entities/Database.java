package Entities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
    static Connection conn;
    static Statement stmt;
    public static ResultSet connectionToSelectFromDB(String cmdString)
    {
        try {


            String connInfo = "jdbc:mysql://localhost:3306/sweetsystem"; // URL of your database
            conn = DriverManager.getConnection(connInfo, "root", "");
            Class.forName("com.mysql.cj.jdbc.Driver");
            stmt = conn.createStatement();
            String qry = cmdString;

            ResultSet rs = stmt.executeQuery(qry);
            return rs;
        }
        catch(SQLException sqlException)
        {
            System.out.println(sqlException);
            return null;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    public static void connectionToInsertOrUpdateDB(String cmdString)
    {
        try {

            String connInfo = "jdbc:mysql://localhost:3306/sweetsystem"; // URL of your database
            conn = DriverManager.getConnection(connInfo, "root", "");

            Class.forName("com.mysql.cj.jdbc.Driver");

            stmt = conn.createStatement();
            stmt.executeUpdate(cmdString);
        }
        catch(SQLException sqlException)
        {
            System.out.println(sqlException);
        } catch (ClassNotFoundEception e) {
            throw new RuntimeException(e);
        }
    }

}