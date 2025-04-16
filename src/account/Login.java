/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package account;

import java.util.Scanner;
public class Login {
      
        // Variables to store login input
        String loginUsername;
        String loginPassword;
        
    // Method to check if the username and password entered match the account's credentials
    public boolean loginUser(Account account){        
        
        return loginUsername.equals(account.userName) && loginPassword.equals(account.password);
    } 
    // Method that prompts the user to log in and returns a message based on whether login is successful
    public String returnLoginStatus(Account account, Scanner input){
        while(true){
           System.out.println("===Login===\n");
           System.out.println("Enter Username");
           loginUsername = input.nextLine();
           System.out.println("Enter Password");
           loginPassword = input.nextLine();
           
           if(!loginUser(account)){
               return "Username or password incorrect, please try again.\n";
               
           }else{           
           return "Welcome <user first name>, <user last name> it is great to see you again.\n";
           }
        
        } 
        
    }
}
