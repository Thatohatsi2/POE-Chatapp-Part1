/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chatapp;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Student
 */
public class Message {
    //Shared across every Message object -tracks everything sent while the progrem is running
    private static int totalMessageSent = 0;
    private static ArrayList<String> allSentMessageDetails = new ArrayList<>();
    
    //Details that belong to this one specific message
   private String messageID;
   private String recipientCell;
   private String messageText;
   private String messageHash;
   private int messageNumber; // the position of this message in the loop (0,1,2...)
   
   public Message(String recipientCell, String messageText,int messageNumber){
   this.recipientCell=recipientCell;
   this.messageText = messageText;
   this.messageNumber = messageNumber;
   this.messageID = generatesMessageID();   
   }
   
   //Generates a random ten-digit number to use as the Message ID.
   
   private String generatesMessageID(){
   long id = (long) (Math.random()*9000000000L) + 1000000000L;
   return String.valueOf(id); 
   }
   
   //Confirms the message ID is not more than ten characters long.
 
   public boolean checkMessageID(){
       return messageID.length() <= 10;
   }
   
   //Confirms the recipient cell number is correctly formatted reusing the same validation logic written for Login in Part 1
   
   public String checkRecipientCell(){
   if(Login.checkCellPhoneNumber(recipientCell)){
   return "Cell phone number successfully captured.";
   } else {
   return "Cell phone number is incorrectly formatted or does not contain an + international code. Please correct the number and try again.";
       }
   }
   
   public String checkMessageLength(){
   if(messageText.length()<=250){
   return "Message ready to send.";
   }else{
   int excess = messageText.length() - 250;
   return "Message exceeds 250 characters by " + excess + "; please reduce the size.";
   }
   }
   
   public String createMessageHash(){
   String idPrefix = messageID.substring(0,2);
   
   //Remove punctuation so "tonight? becomes "tonight" before, converting to capitals, matching the briefs example."
   String[] words = messageText.trim().split("\\s+");
   String firstWord = words[0].replaceAll("[^a-zA-Z0-9]","").toUpperCase();
   String lastWord = words[words.length - 1].replaceAll("[^a-zA-Z0-9]","").toUpperCase();
   
   messageHash = idPrefix + ":" + messageNumber + ":" + firstWord + lastWord;
   return messageHash;
   }
   
   //Handles the user's choice after entering a message: 1 = Send, 2 = Disregard, 3 = Store for later.
   
   public String SentMessage(int choice) {
   if(choice == 1) {
   totalMessageSent++;
   allSentMessageDetails.add(getMessageDetails());
   return "Message successfully sent.";
   } else if (choice == 2){
   return "Press 0 to delete the message.";
   }else if (choice == 3){
   storeMessage();
   return "Message successfully stored.";
   }else{
   return "Invaild option selected.";
     }
   }
   //Return this message's details in the order the brief requires: Message ID, Message Hash, Recipient, Message.
   
   public String getMessageDetails() {
   return "Message ID: " + messageID 
           +"\nMessageHash:" + messageHash
           +"\nRecipient:" + recipientCell
           +"\nMessage: " + messageText;         
   }
   
   //Returns every message that has been sent so far, while the program runs
   
   public static String printMessage(){
   StringBuilder allMessages = new StringBuilder();
   for(String details : allSentMessageDetails){
   allMessages.append(details).append("\n\n");
   }
   return allMessages.toString();
   }
   
   //Returns the total number of messages sent so far.
   
   public static int returnTotalMessages(){
   return totalMessageSent;
   }
   
   //JSON file created per message , named  using message ID.
   
  private void storeMessage(){
  String filename = "message_" + messageID + ".json";
  String json = "{\n"
           + "\"messageID\" :\"" + messageID + "\",\n"
           + "\"messageHash\" :\"" + messageHash + "\",\n"
           + "\"recipient\" :\"" + recipientCell+ "\",\n"
           + "\"message\" :\"" + messageText+ "\",\n" 
          +"}";
  try(FileWriter writer = new FileWriter(filename)){
  writer.write(json);
  } catch(IOException e){
  System.out.println("Error storing Message: " + e.getMessage());
  }
  }
  
  public String getMessageID(){
  return messageID;
  }
  
  public String getMessageHash(){
  return messageHash;
  }
  
  public String getRecipientCell(){
  return recipientCell;
  }
  
  public String getMessageText(){
  return messageText;
  }
}
