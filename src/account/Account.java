/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package account;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class Account {
    // Define variables to store user information
    String userName;
    String password;
    String cellNum;
    
    // Method to check whether the username is formatted correctly
    // Requirements: must contain an underscore and be less than 6 characters
    public boolean checkUserName(){    
            return userName.contains("_") && userName.length() < 6; 
    }
    // Method to check if the password meets complexity requirements
    // Requirements: at least 8 characters, includes uppercase letter, number, and special character
    public boolean checkPasswordComplexity(){
     
          boolean hasUppercase = false;
          boolean hasNumber = false;
          boolean hasSpecialChar = false;
          
        for(int count = 0; count < password.length(); count++){
         if(Character.isUpperCase(password.charAt(count))){
             hasUppercase = true;             
         }
                 
         if(Character.isDigit(password.charAt(count))){
             hasNumber = true;
         }

         if(Character.isUpperCase(password.charAt(count)) && Character.isLowerCase(password.charAt(count)) && Character.isDigit(password.charAt(count))){
             hasSpecialChar = false;
         }else{
             hasSpecialChar = true;
         }
        }
        return password.length() >= 8 && hasUppercase && hasNumber && hasSpecialChar;   
    }
   // Method to check if the cellphone number is formatted correctly
   // Requirements: must include international code and only digits (e.g. +27838968976)
  
    public boolean checkCellPhoneNumber(){ 
       //Code provided by ChatGPT
       String regex = "^\\+\\d{1,4}\\d{1,10}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(cellNum);
        return matcher.matches(); 
    }
    // Method that prompts the user for input and validates it
    // Calls validation methods for username, password, and cellphone number
    public String registerUser(Scanner input){
        
                  
               System.out.println("""
                           ===Register===
                           
                           Enter username""");
            userName = input.nextLine();
            
            if(!checkUserName()){
                return "Username is not correctly formatted,\n"
                        + " please ensure that your username contains an underscore and is no more than five characters in length.\n";
                
                
            }
            System.out.println("Enter password");
            password = input.nextLine();
            if(!checkPasswordComplexity()){
                    return "Password is not correctly formatted;\n"
                        + " please ensure thst the password contains at leas eight characters,\n"
                        + " a capitail letter, a number and a special character.\n";               
            }
            System.out.println("Enter cellphone number");
            cellNum = input.nextLine();
            if(!checkCellPhoneNumber()){
                return "Cell phone number incorectly formatted or does not contain interanational code.\n";
                
            }
            
            return "Username successfully captured.\n"+
                    "Password successfully captured.\n"+
                    "Cellphone number successfully added.\n"+ "\n" +
                    "==== Registration Complete ====\n";
        
    }
    


    
    public static void main(String[] args) {
        // Create an Account object and a Scanner for input
        Account account1 = new Account();   
        Scanner input = new Scanner(System.in);
       // Repeat registration until successful
       while(true){
        String msg = account1.registerUser(input);
        System.out.println(msg);
         if (msg.contains("==== Registration Complete ====")) {
            break;
        }
       }
       // Repeat login until successful
       while(true){
        Login login = new Login();
        String loginmsg = login.returnLoginStatus(account1, input);
        System.out.println(loginmsg); 
        if(loginmsg.contains("Welcome <user first name>, <user last name> it is great to see you again.\n")){
        break;
        }
       }
       
        
       
        
            
    }
    
}

