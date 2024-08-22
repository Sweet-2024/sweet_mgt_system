package main_entities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class Database{
    static Connection conn;
    static Statement stmt;

    public static ResultSet connectionToSelectFromDB(String cmdString)
    {
        try
        {
            Properties p = new Properties();
            InputStream inputStream = new FileInputStream("config.properties");
            p.load(inputStream);
            String connInfo = p.getProperty("db.url");
            String username = p.getProperty("db.username");
            String password = p.getProperty("db.password");
            conn = DriverManager.getConnection(connInfo, username, password);
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
        }
        catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        catch (IOException | ClassNotFoundException e)
        {
            System.out.println(e);
            return null;
        }
    }


    public static void connectionToInsertOrUpdateDB(String cmdString)
    {
        try {
            Properties p = new Properties();
            InputStream inputStream = new FileInputStream("config.properties");
            p.load(inputStream);
            String connInfo = p.getProperty("db.url");
            String username = p.getProperty("db.username");
            String password = p.getProperty("db.password");
            conn = DriverManager.getConnection(connInfo, username, password);
            stmt = conn.createStatement();
            stmt.executeUpdate(cmdString);
        }
        catch(SQLException sqlException)
        {
            System.out.println(sqlException);
        }
        catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        catch (IOException e)
        {
            System.out.println(e);
        }
    }
}