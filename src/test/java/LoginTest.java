/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

package com.mycompany.chatapp;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * LoginTest contains the JUnit tests for the Login class.
 * Uses the exact test data from the Part 1 PoE.
 * 
 * @author Student
 */
public class LoginTest {

    // --- USERNAME TESTS ---
    
    @Test
    public void testUsernameCorrectlyFormatted() {
        // Test Data: "kyl_1" -> Expected: True
        assertTrue(Login.checkUserName("kyl_1")); 
    }

    @Test
    public void testUsernameIncorrectlyFormatted() {
        // Test Data: "kyle!!!!!!" -> Expected: False
        assertFalse(Login.checkUserName("kyle!!!!!!"));
    }

    // --- PASSWORD TESTS ---

    @Test
    public void testPasswordMeetsRequirements() {
        // Test Data: "Ch&&sec@ke99!" -> Expected: True
        assertTrue(Login.checkPasswordComplexity("Ch&&sec@ke99!"));
    }

    @Test
    public void testPasswordDoesNotMeetRequirements() {
        // Test Data: "password" -> Expected: False
        assertFalse(Login.checkPasswordComplexity("password"));
    }

    // --- CELL PHONE TESTS ---

    @Test
    public void testCellPhoneCorrectlyFormatted() {
        // Test Data: "+27838968976" -> Expected: True
        assertTrue(Login.checkCellPhoneNumber("+27838968976"));
    }

    @Test
    public void testCellPhoneIncorrectlyFormatted() {
        // Test Data: "08966553" -> Expected: False
        assertFalse(Login.checkCellPhoneNumber("08966553"));
    }

    // --- LOGIN TESTS ---

    @Test
    public void testLoginSuccessful() {
        // We must register the user first so the static variables are filled!
        Login.registerUser("Kyle", "Smith", "kyl_1", "Ch&&sec@ke99!", "+27838968976");
        
        // Now test that logging in with the SAME details returns true
        assertTrue(Login.loginUser("kyl_1", "Ch&&sec@ke99!"));
    }

    @Test
    public void testLoginFailed() {
        Login.registerUser("Kyle", "Smith", "kyl_1", "Ch&&sec@ke99!", "+27838968976");
        
        // Test that logging in with a WRONG password returns false
        assertFalse(Login.loginUser("kyl_1", "wrongPassword!"));
    }

    // --- LOGIN STATUS TESTS ---

    @Test
    public void testReturnLoginStatusSuccess() {
        Login.registerUser("Kyle", "Smith", "kyl_1", "Ch&&sec@ke99!", "+27838968976");
        
        // Verify the exact welcome message is returned
        String status = Login.returnLoginStatus(Login.loginUser("kyl_1", "Ch&&sec@ke99!"));
        assertEquals("Welcome Kyle, Smith it is great to see you again.", status);
    }

    @Test
    public void testReturnLoginStatusFailure() {
        Login.registerUser("Kyle", "Smith", "kyl_1", "Ch&&sec@ke99!", "+27838968976");
        
        // Verify the exact error message is returned
        String status = Login.returnLoginStatus(Login.loginUser("kyl_1", "wrongPassword!"));
        assertEquals("Username or password incorrect, please try again.", status);
    }
}
   
    

  