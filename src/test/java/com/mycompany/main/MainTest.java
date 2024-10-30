/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.main;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author RC_Student_lab
 */



public class MainTest {

    @Test
    public void testValidUsername() {
        login system = new login();
        String validUsername = "kyl_1";
        assertTrue(system.validateUsername(validUsername));
    }

    @Test
    public void testInvalidUsername() {
        login system = new login();
        String invalidUsername = "kyle!!!!";
        assertFalse(system.validateUsername(invalidUsername));
    }

    @Test
    public void testValidPassword() {
        login system = new login();
        String validPassword = "Ch&&sec@ke99!";
        assertTrue(system.validatePassword(validPassword));
    }

    @Test
    public void testInvalidPassword() {
        login system = new login();
        String invalidPassword = "password";
        assertFalse(system.validatePassword(invalidPassword));
    }

    @Test
    public void testSuccessfulLogin() {
        login system = new login();
        system.registerUser("kyl_1", "Ch&&sec@ke99!", "John", "Doe");
        assertTrue(system.loginUser("kyl_1", "Ch&&sec@ke99!"));
    }

    @Test
    public void testFailedLogin() {
        login system = new login();
        system.registerUser("kyl_1", "Ch&&sec@ke99!", "John", "Doe");
        assertFalse(system.loginUser("kyl_1", "wrongpassword"));
    }
}

