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
   
    //The JSON file that stored messages are saved to and read from
    private static final String JSON_FILE = "storedMessages.json";
    
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
        
         //Keep asking until the user types a whole number from 1 to 99
       int numMessages = 0;
       while (numMessages < 1) {
           System.out.print("How many messages would you like to send? ");
           String numText = input.nextLine();
           if (numText.matches("[1-9][0-9]?")) {
               numMessages = Integer.parseInt(numText);
           } else {
               System.out.println("Please enter a number between 1 and 99.");
           }
       }
       int messagesEntered = 0;

       //Create the arrays and read any stored messages back in from the JSON file
       MessageManager manager = new MessageManager();
       manager.loadStoredFromJson(JSON_FILE);
       
       boolean quit = false;
       
      while (!quit){
       System.out.println("\n ======MENU========");
       System.out.println("1) Send Messages");
       System.out.println("2) Show  recently sent messages");
       System.out.println("3) Quit");
       System.out.println("4) Stored Messages");
       System.out.println("Choose an option: ");
       String choice = input.nextLine();
       
       switch (choice){
                     case "1" :
               //The user may only enter the number of messages they chose at the start
               if (messagesEntered >= numMessages) {
                   System.out.println("You have already entered all " + numMessages + " messages.");
                   break;
               }

               //A for loop that runs based on how many messages the user wants to send
               for (int i = 0; i < numMessages; i++) {
                   System.out.println("\nMessage " + (i + 1) + " of " + numMessages);

                   //Input validation: keep asking until the recipient number is valid
                   String recipient = "";
                   while (!Login.checkCellPhoneNumber(recipient)) {
                       System.out.print("Enter recipient cell number (+27...): ");
                       recipient = input.nextLine();
                       if (!Login.checkCellPhoneNumber(recipient)) {
                           System.out.println("Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.");
                       }
                   }

                   //Input validation: keep asking until the message is 1 to 250 characters
                   String messageText = "";
                   while (messageText.length() < 1 || messageText.length() > 250) {
                       System.out.print("Enter your message (max 250 chars): ");
                       messageText = input.nextLine();
                       if (messageText.length() > 250) {
                           System.out.println("Please enter a message of less than 250 characters.");
                       } else if (messageText.length() < 1) {
                           System.out.println("A message cannot be empty.");
                       }
                   }

                   Message message = new Message(recipient, messageText, i);
                   System.out.println(message.checkRecipientCell());
                   System.out.println(message.checkMessageLength());
                   message.createMessageHash();

                   //Input validation: keep asking until the user types 1, 2 or 3
                   int sendChoice = 0;
                   while (sendChoice < 1 || sendChoice > 3) {
                       System.out.print("1) Send  2) Disregard  3) Store: ");
                       String sendText = input.nextLine();
                       if (sendText.matches("[1-3]")) {
                           sendChoice = Integer.parseInt(sendText);
                       } else {
                           System.out.println("Please enter 1, 2 or 3.");
                       }
                   }

                   System.out.println(message.SentMessage(sendChoice));

                   //Add the message to the arrays using its flag
                   if (sendChoice == 1) {
                       manager.addMessage(message.getMessageID(), message.getMessageHash(), recipient, messageText, "Sent");
                       System.out.println(message.getMessageDetails());
                   } else if (sendChoice == 2) {
                       manager.addMessage(message.getMessageID(), message.getMessageHash(), recipient, messageText, "Disregard");
                   } else {
                       manager.addMessage(message.getMessageID(), message.getMessageHash(), recipient, messageText, "Stored");
                       manager.saveStoredToJson(JSON_FILE);
                   }
               }
               //All the messages the user chose to enter have now been entered
               messagesEntered = numMessages;
               break; 
               
             
           case "2":
               System.out.println("Coming soon.");
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
               System.out.println("Invalid option, please try again.");
       }
       }
       input.close();
    }
}



