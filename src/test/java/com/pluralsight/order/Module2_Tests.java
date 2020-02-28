package com.pluralsight.order;

import java.lang.reflect.Field;
import java.sql.Connection;

import com.pluralsight.order.util.Database;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class Module2_Tests {
    private Class databaseClass;

    private Field urlField;

    private Field userField;

    private Field passwordField;

    private Database databaseInstance = null;

    @Before
    public void setup() {
        try {
            databaseClass = Class.forName("com.pluralsight.order.util.Database");
        } catch (ClassNotFoundException e) {
            //e.printStackTrace();
        }

        try {
            urlField = databaseClass.getDeclaredField("url");
            urlField.setAccessible(true);
        } catch (NoSuchFieldException e) {
            //e.printStackTrace();
        }

        try {
            userField = databaseClass.getDeclaredField("user");
            userField.setAccessible(true);
        } catch (NoSuchFieldException e) {
            //e.printStackTrace();
        }

        try {
            passwordField = databaseClass.getDeclaredField("password");
            passwordField.setAccessible(true);
        } catch (NoSuchFieldException e) {
            //e.printStackTrace();
        }

        databaseInstance = Database.getInstance();
    }

    @Test
    public void task_1() {
        assertNotNull("Task 1: Field `url` doesn't exist in the Database class.",
                urlField);
        try {
            String urlValue = (String)urlField.get(databaseInstance);
            assertTrue("Task 1: Field `url` doesn't contain a valid H2 URL for an in-memory database.", urlValue.startsWith("jdbc:h2:mem:orders"));
        } catch (IllegalAccessException e) {
            //e.printStackTrace();
        }
    }

    @Test
    public void task_2() {
        assertNotNull("Task 2: Field `user` doesn't exist in the Database class.",
                userField);
        try {
            String userValue = (String)userField.get(databaseInstance);
            assertEquals("Task 2: Field `user` doesn't equal to \"sa\".", "sa", userValue);
        } catch (IllegalAccessException e) {
            //e.printStackTrace();
        }
    }

    @Test
    public void task_3() {
        assertNotNull("Task 3: Field `password` doesn't exist in the Database class.",
                passwordField);
        try {
            String passwordValue = (String)passwordField.get(databaseInstance);
            assertEquals("Task 3: Field `password` doesn't equal to \"\".", "", passwordValue);
        } catch (IllegalAccessException e) {
            //e.printStackTrace();
        }
    }

    @Test
    public void task_4() {
        Connection con = null;
        try {
            con = databaseInstance.getConnection();
            assertNotNull("Task 4: The method `getConnection()` returned `null`. Make sure you're opening the connection with the right parameters.",
                    con);
            assertTrue("Task 4: The connection is not valid. Make sure you're opening the connection with the right parameters.",
                    con.isValid(10));
            con.close();
        } catch (Exception e) {
            //e.printStackTrace();
            assertTrue("Task 4: The connection is not valid. The method `getConnection()` threw an exception. Make sure you're opening the connection with the right parameters.",
                    false);
        }
    }
}
