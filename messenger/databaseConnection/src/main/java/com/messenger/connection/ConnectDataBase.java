package com.messenger.connection;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

import com.messenger.exception.ConnectionNotFoundException;

/**
 * Provide a Database Connection using properties files
 *
 * @author Venkatesh N
 * @version 1.0
 */
public class ConnectDataBase {
    private static ConnectDataBase connectDataBase;

    /**
     * Create a private Constructor
     */
    private ConnectDataBase() {
    }

    /**
     * Create a getInstance method()
     *
     * @return instance of the class
     */
    public static ConnectDataBase getInstance() {

        if (connectDataBase == null) {
            connectDataBase = new ConnectDataBase();
        }
        return connectDataBase;
    }

    /**
     * Provides a  database connection.
     *
     * @return connection
     */
    public Connection getConnection() {
        try (InputStream inputStream = this.getClass().getClassLoader()
                .getResourceAsStream("DatabaseDetails.properties")) {
            final Properties PROPERTIES = new Properties();

            PROPERTIES.load(inputStream);
            Class.forName("org.postgresql.Driver");

            return DriverManager.getConnection(PROPERTIES.getProperty("url"), PROPERTIES.getProperty("username"),
                    PROPERTIES.getProperty("password"));
        } catch (Exception exception) {
            throw new ConnectionNotFoundException("DataBase Not Connected");
        }
    }
}