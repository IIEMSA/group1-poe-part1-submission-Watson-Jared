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
public class LoginTest {
    
    Account account = new Account();   
    Login login = new Login(); 
    
    public LoginTest() {
    }

    @Test
    public void testLoginUser() {
        account.userName = "ky1_1";
        account.password =  "CH&&sec@ke99!";        
        login.loginUsername = "ky1_1";
        login.loginPassword = "CH&&sec@ke99!";
        
        assertTrue(login.loginUser(account));
        
        login.loginUsername = "kyle!!!!!";
        login.loginPassword = "password";
        
        assertFalse(login.loginUser(account));
    }

    @Test
    public void testReturnLoginStatusLoginSuccessful() {
        account.userName = "ky1_1";
        account.password =  "CH&&sec@ke99!";         
        Scanner input =  new Scanner("ky1_1\nCH&&sec@ke99!");
        String expected = "Welcome <user first name>, <user last name> it is great to see you again.";
        String result = login.returnLoginStatus(account, input);
        
        assertEquals(expected, result);   
    }
    
    @Test
    public void testReturnLoginStatusLoginFailed() {
        account.userName = "ky1_1";
        account.password =  "CH&&sec@ke99!";         
        Scanner input =  new Scanner("kyle!!!!\npassword");
        String expected = "Username or password incorrect, please try again.";
        String result = login.returnLoginStatus(account, input);
        
        assertEquals(expected, result);   
    }
    
}
