/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package account;

import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;
/**
 *
 * @author lab_services_student
 */
public class MessageTest {
    Message msg = new Message();
    
    public MessageTest() {
    msg.numberOfMessages = 2;
    msg.cellNum = new String[2];
    msg.messages = new String[2];
    msg.messageID = new String[2];
    msg.messageHash = new String[2];

    msg.cellNum[0] = "+27718693002";
    msg.messages[0] = "Hi Mike, can you join us for dinner tonight";
    msg.cellNum[1] = "08575975889";
    msg.messages[1] = "Hi Keegan, did you receive the payment?";
    }

    @Test
    public void testSendMessageLengthSuccess() {
        msg.cellNum[0] = "+27718693002";
        msg.messages[0] = "Hi Mike, can you join us for dinner tonight";
        String result = msg.singleMessage(msg.messages[0], msg.cellNum[0], 1, 0);
        String expected = "Message #" + (0 + 1) + " sent\n"
                    + "MessageID: " + msg.messageID[0] + "\n"
                    + "Message Hash: " + msg.messageHash[0] + "\n"
                    + "Recipient: " + msg.cellNum[0] + "\n"
                    + "Message: " + msg.messages[0] + "\n\n";
       assertEquals(expected,result);
    }
    
    @Test
    public void testSendMessageLengthFail() {
        msg.cellNum[1] = "08575975889";
        msg.messages[1] = "This message is deliberately made long enough to exceed the 250-character limit that has been set for validation purposes in your messaging application. It keeps going and going just to make sure it crosses the line. Here comes the last few characters... Done!";
        String result = msg.singleMessage(msg.messages[1], msg.cellNum[1], 1, 1);
        int overLimit = msg.messages[1].length() - 250;
        String expected = "Message exceeds 250 characters by " + overLimit + ", please reduce size";
       assertEquals(expected,result);
    }
    
    @Test
    public void testSendMessageCellSuccess() {
        msg.cellNum[0] = "+27718693002";
        msg.messages[0] = "Hi Mike, can you join us for dinner tonight";
        String result = msg.singleMessage(msg.messages[0], msg.cellNum[0], 1, 0);
        String expected = "Message #" + (0 + 1) + " sent\n"
                    + "MessageID: " + msg.messageID[0] + "\n"
                    + "Message Hash: " + msg.messageHash[0] + "\n"
                    + "Recipient: " + msg.cellNum[0] + "\n"
                    + "Message: " + msg.messages[0] + "\n\n";
       assertEquals(expected,result);
    }
    
    @Test
    public void testSendMessageCellFail() {
        msg.cellNum[1] = "08575975889";
        msg.messages[1] = "Hi Keegan, did you receive the payment?";
        String result = msg.singleMessage(msg.messages[1], msg.cellNum[1], 1, 1);
        int overLimit = msg.messages[1].length() - 250;
        String expected = "Cell phone number incorrectly formatted or does not contain international code.";
       assertEquals(expected,result);
    }
    
   @Test
   public void testCreateMessageHash() {
    msg.cellNum[0] = "+27718693002";
    msg.messages[0] = "Hi Mike, can you join us for dinner tonight";

    String iD = msg.generateMessageID(); // generate message ID
    msg.messageID[0] = iD; // make sure it's stored (if required by your logic)

    String[] words = msg.messages[0].trim().split(" ");
    String firstword = words[0];
    String lastword = words.length > 1 ? words[words.length - 1] : firstword;

    // Assume count is 0 since we’re working with the first message
    int count = 0;
    String expected = iD.substring(0, 2) + ":" + (count + 1) + ":" + firstword.toUpperCase() + lastword.toUpperCase();

    String result = msg.createMessageHash(iD, msg.messages[0], count);
    assertEquals(expected, result);
}

    @Test
    public void testCheckMessageID() {
        String iD = msg.generateMessageID();
        assertEquals(10,iD.length());
    }


    
}
