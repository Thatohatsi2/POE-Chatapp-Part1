/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chatapp;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/**
 *
 * Holds the arrays for every message the user sends, disregards or stores, and the methods that search, delete and report on them
 * 
 * @author Student
 */
public class MessageManager {
    
    private static final int MAX_MESSAGES = 200;
    public String[] sentMessages = new String[MAX_MESSAGES];
    public String[] disregardedMessages = new String[MAX_MESSAGES];
    public String[] storedMessages = new String[MAX_MESSAGES];
    public String[] messageHashes = new String[MAX_MESSAGES];
    public String[] messageIDs = new String[MAX_MESSAGES];
    public String[] recipients = new String[MAX_MESSAGES];
    
    public int messageCount =0;
    
    public void addMessage(String id, String hash, String recipient, String text, String flag){
    if (!flag.equals("Sent") && !flag.equals("Disregard")&& !flag.equals("Stored")){
    System.out.println("Unknown message flag: " + flag);
    return;
    }
    if (messageCount >= MAX_MESSAGES){
    System.out.println("Message storage is full.");
    return;
    }
    messageIDs[messageCount]= id;
    messageHashes[messageCount] = hash;
    recipients[messageCount] = recipient;
    
    if(flag.equals("Sent")) {
    sentMessages[messageCount] = text;
    } else if (flag.equals("Disregard")){
          disregardedMessages[messageCount] = text;
    } else {
        storedMessages[messageCount] = text;
    }
       messageCount++;
    }
    
        public void loadTestData() {
        // Message 1 (Sent)
        addMessage("ID_1", "HASH_1", "+27834557896", "Did you get the cake?", "Sent");

        // Message 2 (Stored)
        addMessage("ID_2", "HASH_2", "+27838884567", "Where are you? You are late! I have asked you to be on time.", "Stored");

        // Message 3 (Disregarded)
        addMessage("ID_3", "HASH_3", "+27844484567", "Yohoooo, I am at your gate.", "Disregard");

        // Message 4 (Sent)
        addMessage("0838884567", "HASH_4", "0838884567", "It is dinner time!", "Sent");

        // Message 5 (Stored)
        addMessage("ID_5", "HASH_5", "+27838884567", "Ok, I am leaving without you.", "Stored");
    }
    
    
    //returns the text of a sent or stored messae position i (null if it was disregarded)
    private String getSentOrStoredText(int i){
    if (sentMessages[i] !=null){
        return sentMessages[i];
    }
    return storedMessages[i];
    }
    
    //Returns every sent message, one per line
    public String displaySentMessages() {
    String result ="";
    for(int i = 0; i < messageCount; i++){
    if(sentMessages[i] != null) {
    result =result + sentMessages[i] + "\n";
    }
    }
    return result;
    }
    
    //Display the sender and recipient of all stored messages
    public String displayStoredSendersAndRecipients(String sender){
     String result ="";
    for(int i = 0; i < messageCount; i++){
    if(storedMessages[i] != null) {
    result =result + "Sender: " + sender + "Recipient: " + recipients[i] + "\n";
    }
    }
    if(result.equals("")){
    return "No stored messages.";
    }
    return result;
    }
    
    //Display the longest stored message
    public String getLongestStoredMessage(){
     String longest  ="";
    for(int i = 0; i < messageCount; i++){
    if(storedMessages[i] != null && storedMessages[i].length()> longest.length()) {
    longest = storedMessages[i];
    }
    }
    if(longest.equals("")){
    return "No stored messages.";
    }
    return longest;
    }
    
    //Search for a message ID - returns the message(sent or stores)
     public String searchByMessageID(String id){
     for (int i = 0; i < messageCount; i++){
     if(messageIDs[i].equals(id)&& getSentOrStoredText(i) != null){
     return getSentOrStoredText(i);
     }
     }
     return "Message ID not found.";
     }
     
     //Search for a message ID - returns the recipient of that message
     public String getRecipientByMessageID(String id){
     for (int i = 0; i < messageCount; i++){
     if(messageIDs[i].equals(id)&& getSentOrStoredText(i) != null){
     return recipients[i];
     }
     }
     return "Message ID not found.";
     }
    
     //Search for all the messages sent or stored for one recipient
     public String searchByRecipient(String recipientNumber){
     String result = "";
     for(int i = 0; i < messageCount;i++){
     if (recipients[i].equals(recipientNumber) && getSentOrStoredText(i) != null) {
     result =result + getSentOrStoredText(i) + "\n";
     }
     }
     if (result.equals("")) {
     return "No message found for that  recipient.";
     }
     return result;
     }
    
    //Delete a stored message using its message Hash
     public String deleteMessageByHash(String hash){
     for (int i = 0; i < messageCount; i++){
     if(messageHashes[i].equals(hash) && storedMessages[i] != null){
     String deletedMessage = storedMessages[i];
    
     // fill the gaps by moving everything after position i one place to the left
      
       for (int j= i; j < messageCount - 1; j++){
       storedMessages[j] = storedMessages[j + 1];
       recipients[j] = recipients[j + 1];
       messageHashes[j] = messageHashes[j + 1];
       messageIDs[j] = messageIDs[j + 1];
       disregardedMessages[j]= disregardedMessages[j + 1];
       sentMessages[j] = sentMessages[j + 1];
     }
     //clear the last postion]
     messageCount--;
      storedMessages[messageCount] = null;
       recipients[messageCount] = null;
       messageHashes[messageCount] = null;
       messageIDs[messageCount] = null;
       disregardedMessages[messageCount]= null;
       sentMessages[messageCount] = null;
       
       return "Message: \""+ deletedMessage + "\" successfull deleted.";
      
     }
     }
     return "Hash not found";
     }
     
     //Report with the full details of every sent and stored message
     public String generateReport(){
     String report = "===MESSAGE REPORT===\n";
     for (int i = 0; i < messageCount; i++){
     String text = getSentOrStoredText(i);
     if (text != null){
     String status ="Sent";
     if(storedMessages[i] != null){
     status = "Stored";
     }
     report = report + "Message ID: " + messageIDs[i] + "\n"
                     +"Recipent: " + messageHashes[i] +"\n"
                     +"Message: " + text + "\n"
                     + "Status: " + status + "\n"
                     +"-----------------------------------\n";
            
     }
     }
     return report;
     }
     
     // Save every stored message to a JSON file (called after a message is stored or deleted)
     public void saveStoredToJson(String fileName){
     JSONArray array = new JSONArray();
     for(int i = 0; i < messageCount; i++){
     if(storedMessages[i] != null){
     JSONObject object = new JSONObject();
     object.put("messageID", messageIDs[i]);
     object.put("messageHash", messageHashes[i]);
     object.put("recipient", recipients[i]);
     object.put("message", storedMessages[i]);
     array.put(object);
     }
     }
     try{
     Files.writeString(Path.of(fileName), array.toString(2));
     }catch (IOException e){
     System.out.println("Error saving stored messages: " + e.getMessage());

     }
     }
     // Reads the JSON file back into the array as stored messages 
     public void loadStoredFromJson(String fileName){
     File file = new File(fileName);
     if(!file.exists()){
         return; // nothing has been stored yet
     }
     try{
     String content = Files.readString(Path.of(fileName));
   JSONArray array = new JSONArray(content);
     for(int i = 0; i < array.length(); i++){
     JSONObject object = array.getJSONObject(i);
     addMessage(object.getString("messageID"),
                object.getString("messageHash"),
                object.getString("recipient"),
                object.getString("message"),
                "Stored" );
                
     }
     }catch (IOException e){
     System.out.println("Error reading stored messages: " + e.getMessage());         
     } catch(JSONException e){
      System.out.println("The stored messages file is not valid JSON: " + e.getMessage());   
            }
         } 
     }
   