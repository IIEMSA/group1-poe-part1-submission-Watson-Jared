/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package account;

import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;
import java.util.Scanner;
/**
 *
 * @author lab_services_student
 */
public class AccountTest {
    
   
    Account account = new Account();

    @Test
    public void testCheckUserName() {
        account.userName = "kyl_1";
        assertTrue(account.checkUserName());
        
        account.userName = "kyle!!!!!";
        assertFalse(account.checkUserName());
    }

    @Test
    public void testCheckPasswordComplexity() {
        account.password = "CH&&sec@ke99!";
        assertTrue(account.checkPasswordComplexity());
        
        account.password = "password";
        assertFalse(account.checkPasswordComplexity());
    }

    @Test
    public void testCheckCellPhoneNumber() {
        account.cellNum = "+27838968976";
        assertTrue(account.checkCellPhoneNumber());
        
        account.cellNum = "08966553";
        assertFalse(account.checkCellPhoneNumber());
    }

    @Test
    public void testRegisterUserValidInput() {  
        Scanner input = new Scanner("ky1_1\nCH&&sec@ke99!\n+27838968976");
        
         String expected = "Username successfully captured.\n"+
                    "Password successfully captured.\n"+
                    "Cellphone number successfully added.\n"+ "\n" +
                    "==== Registration Complete ====\n";
         String result = account.registerUser(input);
         
         assertEquals(expected, result);
    }
    
     @Test
    public void testRegisterUserInvalidUserName() {  
        Scanner input = new Scanner("kyle!!!!!\nCH&&sec@ke99!\n+27838968976");
        
         String expected = "Username is not correctly formatted,\n"
                        + " please ensure that your username contains an underscore and is no more than five characters in length.\n";
         String result = account.registerUser(input);
         
         assertEquals(expected, result);
    }
    
     @Test
    public void testRegisterUserInvalidPassword() {  
        Scanner input = new Scanner("ky1_1\npassword\n+27838968976");
        
         String expected = "Password is not correctly formatted;\n"
                        + " please ensure thst the password contains at leas eight characters,\n"
                        + " a capitail letter, a number and a special character.\n";
         String result = account.registerUser(input);
         
         assertEquals(expected, result);
    }
    
    @Test
    public void testRegisterUserInvalidCellPhoneNumber() {  
        Scanner input = new Scanner("ky1_1\nCH&&sec@ke99!\n08966553");
        
         String expected = "Cell phone number incorectly formatted or does not contain interanational code.\n";
         String result = account.registerUser(input);
         
         assertEquals(expected, result);
    }
    
   

   

    
}
