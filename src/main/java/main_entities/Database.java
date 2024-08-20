package main_entities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Database{
    static Connection conn;
    static Statement stmt;




    public static ResultSet connectionToSelectFromDB(String cmdString)
    {
        try
        {
            String connInfo = "jdbc:mysql://localhost:3306/sweetsystem";
            String username = "root";
            String password = "";
            conn = DriverManager.getConnection(connInfo, username, password);
            stmt = conn.createStatement();
            return stmt.executeQuery(cmdString);
        }
        catch(SQLException sqlException)
        {
            System.out.println(sqlException);
            return null;
        }
    }


    public static void connectionToInsertOrUpdateDB(String cmdString)
    {
        try {
            String connInfo = "jdbc:mysql://localhost:3306/sweetsystem";
            String username = "root";
            String password = "";
            conn = DriverManager.getConnection(connInfo, username, password);
            stmt = conn.createStatement();
            stmt.executeUpdate(cmdString);
        }
        catch(SQLException sqlException)
        {
            System.out.println(sqlException);
        }
    }

}