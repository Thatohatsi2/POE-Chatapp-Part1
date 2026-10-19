/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chatapp;
// Part 1 Main Application-Registration and Login
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
        String registrationResult = Login.registerUser(fName, lName, username,password, cellPhone);
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
        
        //User should only be able to send messages if they logged in sucessfully.
        if(!isLoggedIn){
           System.out.println("You must log in successfully to use QuickChat.");
        input.close();
        return;
        }
        
        //QuickChat Messaging
        System.out.println("Welcome to QuickChat.");
        
       System.out.print("How many messages would you like to send? ");
       int numMessages =Integer.parseInt(input.nextLine());
       int messagesEntered = 0;
       
       boolean quit = false;
       
       while (!quit){
       System.out.println("\n1) Send Messages");
       System.out.println("2) Show  recently sent messages");
       System.out.println("3) Quit");
       System.out.println("Choose an option: ");
       String choice = input.nextLine();
       
       switch (choice){
           case "1" :
               if (messagesEntered >= numMessages){
               System.out.println("You have already entered the maximum number of messages.");
               }else{
               System.out.println("Enter recipient cell number: ");
               String recipient = input.nextLine();
               System.out.println("Enter your message: ");
               String messageText = input.nextLine();
               
               Message message = new Message(recipient, messageText, messagesEntered);
               System.out.println(message.checkRecipientCell());
               System.out.println(message.checkMessageLength());
               
               if(!message.checkMessageID()){
               System.out.println("Message ID error- please try again.");
               break;
               }
               
               message.createMessageHash();
               System.out.println("1) Send Message");
               System.out.println("2) Disregard Message");
               System.out.println("3) Store Message to send later");
               System.out.println("Choose an option");
                int sendChoice = Integer.parseInt(input.nextLine());
                
                System.out.println(message.SentMessage(sendChoice));
                
                if(sendChoice == 1){
                System.out.println(message.getMessageDetails());
                }
                messagesEntered++;
                       }
               break;
           case "2":
               System.out.println("Coming soon");
               
           case "3":
               quit = true;
              System.out.println("Total messages sent: " + Message.returnTotalMessages());
              break;
              
           default:
               System.out.println("Invaild option, please try again.");
       }
       }
       input.close();
    }
}



