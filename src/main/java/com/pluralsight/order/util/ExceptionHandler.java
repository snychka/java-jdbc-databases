package com.pluralsight.order.util;

import java.sql.SQLException;

/**
 * Utility class to handle exceptions
 */
public class ExceptionHandler {

    /**
     * Method to extract and print information from a SQLException
     * @param sqlException Exception from which information will be extracted
     */
    public static void handleException(SQLException sqlException) {
        // error code, the SQL value, the message and
        // the stack strace for the `SQLException` object received as parameter of the method.
        System.out.println(sqlException.getErrorCode() +
                ", " + sqlException.getSQLState() + ", "
                + sqlException.getMessage()  + ", "
                + sqlException.getMessage() + ", " + sqlException.getStackTrace());
        sqlException.printStackTrace();

    }
}
