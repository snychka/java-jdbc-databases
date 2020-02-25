package com.pluralsight.order.util;

import org.h2.tools.RunScript;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Singleton class to get database connections
 */
public class Database {
    private static Database instance = null;
    private String URL = "jdbc:h2:mem:orders;DB_CLOSE_DELAY=-1";
    private String USER = "sa";
    private String PASSWORD = "";

    /**
     * Private constructor
     */
    private Database() { }

    /**
     * Method that creates an instance of this class (if it's not created already), initializing the database
     * @return Instance of the class
     */
    public static Database getInstance() {
        if(instance == null) {
            instance = new Database();

            try {
                InputStream is = Database.class.getResourceAsStream("/db.sql");
                RunScript.execute(instance.getConnection(), new InputStreamReader(is));
            } catch(Exception ex) {
                throw new RuntimeException("Database couldn't be initialized", ex);
            }
        }
        return instance;
    }

    /**
     * Method to get a connection to the database
     * @return A connection object
     * @throws SQLException In case of a database error
     */
    public Connection getConnection() throws SQLException {
        Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);

        return connection;
    }
}
