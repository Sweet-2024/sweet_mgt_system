package main_entities;

import org.example.Main;

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
import java.util.logging.Logger;
import java.util.logging.Level;

public class Database{
    static Connection conn;
    static Statement stmt;
    private static final Logger logger = Logger.getLogger(Database.class.getName());

    public static ResultSet connectionToSelectFromDB(String cmdString) throws DatabaseOperationException
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
        } catch(SQLException sqlException) {
            logger.log(Level.INFO, sqlException.getMessage(), sqlException);
            return null;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        catch (IOException | ClassNotFoundException e)
        {
            logger.log(Level.SEVERE, "An error occurred: " + e.getMessage(), e);
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

            conn.close();
            stmt.close();
        }
        catch(SQLException sqlException)
        {
            logger.log(Level.INFO, sqlException.getMessage(), sqlException);
        }
        catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        catch (IOException e)
        {
            logger.log(Level.SEVERE, "An error occurred: " + e.getMessage(), e);
        }
    }
}