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
    private Database() {
        throw new UnsupportedOperationException("Cannot instantiate utility class.");
    }
    static Connection conn;
    static Statement stmt;
    private Database() {
        throw new UnsupportedOperationException("Cannot instantiate utility class.");
    }
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

            return stmt.executeQuery(qry);
        }
        catch(SQLException sqlException)
        {
            System.out.println(sqlException);
            return null;
        }catch (FileNotFoundException e) {
            throw new DatabaseOperationException("Configuration file not found.", e);
        } catch (IOException | ClassNotFoundException e) {
            throw new DatabaseOperationException("An error occurred during database connection or query execution.", e);
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
            System.out.println(sqlException);
        }
        catch (FileNotFoundException e ) {
            throw new RuntimeException(e);
        }
        catch (IOException e)
        {
            System.out.println(e);
        }
    }
}