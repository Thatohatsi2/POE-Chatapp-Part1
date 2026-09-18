/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chatapp;

import java.util.Scanner;
/**
 *
 * @author Student
 */
public class Main {
   

    public static void main(String[] args) {
        
        Scanner input = new Scanner(System.in);
        
        //Registration Phase
        System.out.println("======REGISTRATION======");
        
        System.out.print("Enter first name: ");
        String fName = input.nextLine();
        
        System.out.print("Enter last name: ");
        String lName = input.nextLine();
        
        System.out.print("Enter username: ");
        String username = input.nextLine();
        
        System.out.print("Enter password: ");
        String password = input.nextLine();
        
        System.out.print("Enter cell phone (+27...): ");
        String cellPhone = input.nextLine();
        
        // Order matches Login.registerUser's signature:
        // (username, password, cellPhoneNumber, fName, lName)
        String registrationResult = Login.registerUser(username, password, cellPhone, fName, lName);
        System.out.println(registrationResult);
        
        // LOGIN PHASE
        System.out.println("\n===LOGIN===");
        
        System.out.print("Enter username: ");
        String loginUser = input.nextLine();
        
        System.out.print("Enter password: ");
        String loginPass = input.nextLine();
        
        // Check credentials first
        boolean isLoggedIn = Login.loginUser(loginUser, loginPass);
        
        // Then get the message based on that result
        String status = Login.returnLoginStatus(isLoggedIn);
        
        System.out.println(status);
        
        input.close();
    }
}

