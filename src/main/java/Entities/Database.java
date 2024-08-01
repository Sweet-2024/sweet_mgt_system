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
            DriverManager.registerDriver(new org.postgresql.Driver());
            String connInfo = "jdbc:postgresql://localhost:5432/postgres";
            conn = DriverManager.getConnection(connInfo, "raheeq", "123654");

            stmt = conn.createStatement();
            String qry = cmdString;

            ResultSet rs = stmt.executeQuery(qry);
            return rs;
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
            DriverManager.registerDriver(new org.postgresql.Driver());
            String connInfo = "jdbc:postgresql://localhost:5432/postgres";
            conn = DriverManager.getConnection(connInfo, "raheeq", "123654");

            stmt = conn.createStatement();
            stmt.executeUpdate(cmdString);
        }
        catch(SQLException sqlException)
        {
            System.out.println(sqlException);
        }
    }

}