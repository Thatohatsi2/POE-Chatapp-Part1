# Chat App POE

This project contains the registration and login feature for the Chat App

##Features
-Validates username (contains an underscore and be <=5 characters).
-Validates password complexity (8+ chars, 1 capital, 1 number , 1 special char).
-Validates SA cell phone numbers(+27 followed by 9 digits).
-Allows the user to log in with their registered credentials.

#How to run
Run Main.Java in NetBeans or use the command:mvn exec:java

## Part 2- Sending Messages
Adds a Message Class with ID  generation, validation, hash creation and users must be logged in to send  messages. Includes 10 unit tests (MessageTest.java) and automated GitHub Actions testing on every push
