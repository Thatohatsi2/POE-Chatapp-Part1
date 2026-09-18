/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chatapp;

/**
 *
 * @author Student
 */
public class Login {
   
    // Variables store the user's details after registration    
    static String registeredUsername;
    static String registeredPassword;
    static String registeredCellPhone;
    static String firstName;
    static String lastName;
    
    // Regex for South African cell phone numbers (+27 followed by 9 digits)
    public static final String CELL_REGEX = "\\+27[0-9]{9}$";
    
    //checkUserName
    public static boolean checkUserName(String username) {
        if (username == null) return false;
        return username.contains("_") && username.length() <= 5;
    }
    
    //checkPasswordComplexity
    public static boolean checkPasswordComplexity(String password) {
        //Check length first
        if (password == null || password.length() < 8) return false;
        
        boolean hasCapital = false;
        boolean hasNumber = false;
        boolean hasSpecial = false;
        
        //Loop through every character in the password
        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) {
                hasCapital = true;
            } else if (Character.isDigit(c)) {
                hasNumber = true;
            } else if (!Character.isLetterOrDigit(c)) {
                hasSpecial = true;
            }
        }
        //All three must be true for the password to be valid
        return hasCapital && hasNumber && hasSpecial;
    }
    
    public static boolean checkCellPhoneNumber(String cellPhoneNumber) {
        if (cellPhoneNumber == null) return false;
        return cellPhoneNumber.matches(CELL_REGEX);
    }
    
    //Register a new user. Checks the username, password, and cell number
   // 4. registerUser 
// (PARAMETERS MATCH LOGINTEST: fName, lName, username, password, cellPhoneNumber)
public static String registerUser(String fName, String lName, String username, String password, String cellPhoneNumber) {
    
    if (!checkUserName(username)) {
        System.out.println("Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.");
        return "Registration failed.";
    }

    if (!checkPasswordComplexity(password)) {
        System.out.println("Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.");
        return "Registration failed.";
    }

    if (!checkCellPhoneNumber(cellPhoneNumber)) {
        System.out.println("Cell phone number incorrectly formatted or does not contain international code.");
        return "Registration failed.";
    }

    // --- If we reach here, all checks passed! ---
    // Save the valid details into the static variables
    registeredUsername = username;
    registeredPassword = password;
    registeredCellPhone = cellPhoneNumber;
    firstName = fName; 
    lastName = lName;  

    System.out.println("User successfully registered.");
    return "User successfully registered.";
}
    
    //loginUser
    //Compares entered details with stored registration details.
    public static boolean loginUser(String enteredUsername, String enteredPassword) {
        if (registeredUsername == null) {
            return false;
        }
        
        if (enteredUsername.equals(registeredUsername) && enteredPassword.equals(registeredPassword)) {
            return true;
        } else {
            return false;
        }
    }
    
    //returnLoginStatus
    //Returns the final welcome or error message.
    public static String returnLoginStatus(boolean loginSuccessful) {
        if (loginSuccessful) {
            return "Welcome " + firstName + ", " + lastName + " it is great to see you again.";
        } else {
            return "Username or password incorrect, please try again.";
        }
    }
}

