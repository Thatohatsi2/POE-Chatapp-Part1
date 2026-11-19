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
       
       MessageManager manager = new MessageManager();
       manager.loadTestData();
       
       boolean quit = false;
       
      while (!quit){
       System.out.println("\n ======MENU========");
       System.out.println("1) Send Messages");
       System.out.println("2) Show  recently sent messages");
       System.out.println("3) Quit");
       System.out.println("Choose an option: ");
       String choice = input.nextLine();
       
       switch (choice){
           case "1" :
               //A for loop that runs based on how many messages the user wants to send
                for (int i = 0; i < numMessages; i++){
              System.out.println("\nMessage " + (i + 1)+ " of " + numMessages);
                
                //Input validation:Keep asking until they type a valid number
                String recipient = "";
                while(!recipient.startsWith("+27")|| recipient.length() !=12){
                System.out.println("Enter recipient cell number (+27...): ");
                recipient = input.nextLine();
                }
                
                //Input validation keep asking until they type a valid number
                String messageText = "";
                while (messageText.length() < 1 || messageText.length() > 250){
                System.out.println("Enter your message(max 250 chars):  ");
                messageText = input.nextLine();
                }
                
                Message message = new Message(recipient, messageText, i);
                 System.out.println(message.checkRecipientCell());
                  System.out.println(message.checkMessageLength());
                  
                  
                  message.createMessageHash();
                   System.out.println("1) Send 2) Disregard  3) Store");
                  int sendChoice = Integer.parseInt(input.nextLine());
                  
                   System.out.println(message.SentMessage(sendChoice));
                   
                   if (sendChoice == 1 ){
                   manager.sentMessages[manager.messageCount] = messageText;
                   manager.messageCount++;
                   }
                   else if (sendChoice == 3){
                   manager.storedMessages[manager.messageCount] = messageText;    
                   manager.recipients[manager.messageCount]= recipient;
                   manager.messageHashes[manager.messageCount]= message.getMessageHash();
                   manager.messageIDs[manager.messageCount] = message.getMessageID();
                   manager.messageCount++;
                   }
                }
                break;  
               
             
           case "2":
               System.out.println("Coming soon");
               break;
               
           case "3":
               quit = true;
              System.out.println("Total messages sent: " + Message.returnTotalMessages());
              break;
             
           case "4": 
               boolean subMenu = true;
               while (subMenu) {
               System.out.println("\n---STORED MESSAGES---");
               System.out.println("1) Display Recipients");
               System.out.println("2) Display Longest Message");
               System.out.println("3) Search by ID");
               System.out.println("4) Search by Recipient");
               System.out.println("5) Delete Hash");
               System.out.println("6) Display Report");
               System.out.println("0) Back");
               String subChoice = input.nextLine();
               
               switch (subChoice){
                   case "1":  System.out.println(manager.displayStoredSendersAndRecipients(Login.firstName)+ " " + Login.lastName); break;
                   case "2" :  System.out.println(manager.getLongestStoredMessage()); break;
                   case "3" :
                        System.out.print("Enter ID: ");
                        System.out.println(manager.searchByMessageID(input.nextLine()));
                        break;
                   case "4":
                        System.out.print("Enter Recipent: ");
                        System.out.println(manager.searchByRecipient(input.nextLine()));
                        break;
                   case "5":
                        System.out.print("Enter Hash: ");
                        System.out.println(manager.deleteMessageByHash(input.nextLine()));
                        manager.saveStoredToJson("storedMessages.json");
                         break;
                   case "6":  System.out.println(manager.generateReport()); break;
                   case "0": subMenu = false; break; 
                   
               }
               }
              break;
           default:
               System.out.println("Invaild option, please try again.");
       }
       }
       input.close();
    }
}



