/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package account;

/**
 *
 * @author lab_services_student
 */

import javax.swing.*;
import java.lang.*;
import java.util.Random;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
public class Message {
    
   
    int menuChoice;
    int numberOfMessages;
    String[] messages;
    String[] cellNum;
    String[] messageID;
    String[] messageHash;
    
    
    
   
    
    public void menu(){
        JOptionPane.showMessageDialog(null,"Welcome to Quickchat");
         
        while(true){
        String menuChoice = JOptionPane.showInputDialog(null,"Select an option\n 1.Send Messages\n 2.Show recently sent messages(Coming Soon)\n 3.Quit\n" );
        
         if(menuChoice == null || menuChoice.trim().isEmpty()){
              JOptionPane.showMessageDialog(null, "Please select option");
              continue;
         }
         
         if(menuChoice.length() > 2 || !Character.isDigit(menuChoice.charAt(0))){
              JOptionPane.showMessageDialog(null, "Please Enter Valid Number"); 
              continue;
         }
         
        this.menuChoice= Integer.parseInt(menuChoice);
        
      
            switch (this.menuChoice) {
                case 1:
                       String numMsgStr = JOptionPane.showInputDialog(null, "How many messages would you like to send?");
                    if (numMsgStr == null || numMsgStr.trim().isEmpty() || !numMsgStr.chars().allMatch(Character::isDigit)) {
                        JOptionPane.showMessageDialog(null, "Invalid number, returning to menu.");
                        continue;
                    }
                    this.numberOfMessages = Integer.parseInt(numMsgStr);
                   
                    messages = new String[numberOfMessages];
                    cellNum = new String[numberOfMessages];
                    messageID = new String[numberOfMessages];
                    messageHash = new String[numberOfMessages];
                    
                    String sendResult = sendMessage();
                    JOptionPane.showMessageDialog(null, sendResult);
                    continue;
                case 2:
                    JOptionPane.showMessageDialog(null, "Feature not available yet");
                    continue;
                case 3:
                    JOptionPane.showMessageDialog(null, "Goodbye");
                    int totalmsg = returnTotalMessages();
                    JOptionPane.showMessageDialog(null, "Total amount of messages sent " + totalmsg);
                    String msgs = printMessages();
                    JOptionPane.showMessageDialog(null, msgs);
                    System.exit(0);
                default:
                    JOptionPane.showMessageDialog(null, "Please Enter Valid Number");
                    break;
            }
      }
    }
     public String singleMessage(String msg, String cell, int actionChoice, int count) {
        if (msg == null || msg.trim().isEmpty()) {
            return "Message cannot be empty.";
        }
        if (msg.length() > 250) {
            return "Message exceeds 250 characters by " + (msg.length() - 250) + ", please reduce size";
        }

        
        if (cell == null || cell.trim().isEmpty() || !checkRecipientCell(cell)) {
            return "Cell phone number incorrectly formatted or does not contain international code.";
        }
        
        this.messages[count] = msg;
        this.cellNum[count] = cell;

        if (actionChoice == 1) { 
            messageID[count] = generateMessageID();
            messageHash[count] = createMessageHash(messageID[count], msg, count);
            return "Message #" + (count + 1) + " sent\n"
                    + "MessageID: " + messageID[count] + "\n"
                    + "Message Hash: " + messageHash[count] + "\n"
                    + "Recipient: " + cellNum[count] + "\n"
                    + "Message: " + messages[count] + "\n\n";
        } else if (actionChoice == 2) { 
            this.messages[count] = null;
            this.cellNum[count] = null;
            this.messageID[count] = null;
            this.messageHash[count] = null;
            return "Meassge #" + count + " Disregarded";
        } else if (actionChoice == 3) {
            messageID[count] = generateMessageID();
            messageHash[count] = createMessageHash(messageID[count], msg,count);
            storeMessage(count);
            return "Message #" + (count + 1) + " Stored Successfully";
        } else {
            return "Invalid action choice";
        }
    }
   
    
    public String sendMessage() {
        for (int i = 0; i < this.numberOfMessages; i++) {
            
            String msgInput;
            while (true) {
                msgInput = JOptionPane.showInputDialog(null, "Enter message #" + (i + 1) + " (max 250 chars):");
                if (msgInput == null || msgInput.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Message cannot be empty.");
                } else if (msgInput.length() > 250) {
                    JOptionPane.showMessageDialog(null, "Message exceeds 250 characters.");
                } else {
                    break;
                }
            }
            
            
            String cellInput;
            while (true) {
                cellInput = JOptionPane.showInputDialog(null, "Enter recipient cell number with country code (e.g. +27831234567):");
                if (cellInput == null || cellInput.trim().isEmpty() || !checkRecipientCell(cellInput)) {
                    JOptionPane.showMessageDialog(null, "Cell phone number incorrectly formatted or missing international code.");
                } else {
                    break;
                }
            }
            
            messages[i] = msgInput;
            cellNum[i] = cellInput;
            
           
            while (true) {
                String actionStr = JOptionPane.showInputDialog(null, "Choose an option for message #" + (i + 1) + "\n 1. Send Message\n 2. Disregard Message\n 3. Store Message to Send Later");
                if (actionStr == null || actionStr.trim().isEmpty() || !actionStr.chars().allMatch(Character::isDigit) || actionStr.length() > 2) {
                    JOptionPane.showMessageDialog(null, "Please select a valid option.");
                    continue;
                }
                int choice = Integer.parseInt(actionStr);
                String result = singleMessage(messages[i], cellNum[i], choice, i);
                JOptionPane.showMessageDialog(null, result);
                if (choice == 1 || choice == 2 || choice == 3) {
                    break;
                }
            }
        }
        return "Finished processing all messages.";
    }
        
    
    
    public boolean checkMessageID(String messageID){
        return messageID.length() < 11;
    }
    
    public boolean checkRecipientCell(String cell){
        String cellPart = cell.substring(3);
        return cell.startsWith("+27") && cellPart.length() < 11;
    }
    
    public String createMessageHash(String messageID, String message, int count){
        String idPart = messageID.substring(0, 2);
        String[] words = message.trim().split(" ");
        String firstword = words[0];
        String lastword;
        if(words.length > 1){
            lastword = words[words.length - 1];
        }else{
           lastword = words[0]; 
        }
        return idPart + ":" +(count+1)+ ":"+ firstword.toUpperCase() + lastword.toUpperCase();
    }
    
   public String generateMessageID(){
        Random rand = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            sb.append(rand.nextInt(10));
        }
        
        String id = sb.toString();
        
        if(checkMessageID(id)){
            return id;
        }else{       
            return "invalid";
        } 
   }
   
   public int returnTotalMessages(){
        return this.numberOfMessages;    
    }
    
    
 public String printMessages() {
    String result = "";

    for (int i = 0; i < numberOfMessages; i++) {
        if (messages[i] != null) { // Skip disregarded messages
            result += "Message #" + (i + 1) + " sent\n"
                    + "MessageID: " + messageID[i] + "\n"
                    + "Message Hash: " + messageHash[i] + "\n"
                    + "Recipient: " + cellNum[i] + "\n"
                    + "Message: " + messages[i] + "\n\n";
        }
    }
    if(result.isEmpty()){
    return "No messages were sent.";
    }else{
        return result;
    }   
}

    
    
    public void storeMessage(int i) {
       
    String json = "{\n" +
        "  \"messageID\": \"" + messageID[i] + "\",\n" +
        "  \"messageHash\": \"" + messageHash[i] + "\",\n" +
        "  \"recipient\": \"" + cellNum[i] + "\",\n" +
        "  \"message\": \"" + messages[i].replace("\"", "\\\"") + "\"\n" +
        "}";

    try (FileWriter file = new FileWriter("stored_message.json")) {
        file.write(json);
        file.flush();
        JOptionPane.showMessageDialog(null, "Message stored to file successfully.");
        String content = new String(Files.readAllBytes(Paths.get("stored_message.json")));
        JOptionPane.showMessageDialog(null, "Stored content:\n" + content);

    } catch (IOException e) {
        JOptionPane.showMessageDialog(null, "Error writing to file: " + e.getMessage());
    }
}

    int length() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

   
    
    
}
