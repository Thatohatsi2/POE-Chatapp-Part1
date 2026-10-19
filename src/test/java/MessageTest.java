/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import com.mycompany.chatapp.Message;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
/**
 *
 * @author Student
 */
public class MessageTest {
    
     // ===== checkMessageID() =====
 
    @Test
    public void testMessageIDIsNotMoreThanTenCharacters() {
        Message message = new Message("+27718693002", "Hi Mike, can you join us for dinner tonight?", 0);
        // The ID is generated automatically as a ten-digit number,
        // so this should always pass.
        assertTrue(message.checkMessageID());
    }
 
    // ===== checkRecipientCell() =====
 
    @Test
    public void testRecipientCellCorrectlyFormatted() {
        Message message = new Message("+27718693002", "Hi Mike, can you join us for dinner tonight?", 0);
        assertEquals("Cell phone number successfully captured.", message.checkRecipientCell());
    }
 
    @Test
    public void testRecipientCellIncorrectlyFormatted() {
        // 08575975889 has no international code, so this should fail.
        Message message = new Message("08575975889", "Hi Keegan, did you receive the payment?", 1);
        assertEquals("Cell phone number is incorrectly formatted or does not contain an + international code. Please correct the number and try again.", message.checkRecipientCell());
    }
 
    // ===== checkMessageLength() =====
 
    @Test
    public void testMessageWithinLengthLimit() {
        Message message = new Message("+27718693002", "Hi Mike, can you join us for dinner tonight?", 0);
        assertEquals("Message ready to send.", message.checkMessageLength());
    }
 
    @Test
    public void testMessageExceedsLengthLimit() {
        // Build a message text that is deliberately over 250 characters.
        String longText = "a".repeat(260);
        Message message = new Message("+27718693002", longText, 0);
        assertEquals("Message exceeds 250 characters by 10; please reduce the size.", message.checkMessageLength());
    }
 
    // ===== createMessageHash() =====
 
    @Test
    public void testMessageHashIsCorrect() {
        // Test Case 1 from the brief: "Hi Mike, can you join us for
        // dinner tonight?" should produce a hash ending in HITONIGHT.
        Message message = new Message("+27718693002", "Hi Mike, can you join us for dinner tonight?", 0);
        String hash = message.createMessageHash();
        // The first two digits of the ID are random, so we only
        // check that the hash ENDS with the expected pattern.
        assertTrue(hash.endsWith(":0:HITONIGHT"));
    }
 
    // ===== SentMessage() =====
 
    @Test
    public void testMessageSentOptionReturnsSuccessMessage() {
        Message message = new Message("+27718693002", "Hi Mike, can you join us for dinner tonight?", 0);
        message.createMessageHash();
        assertEquals("Message successfully sent.", message.SentMessage(1));
    }
 
    @Test
    public void testMessageDisregardOptionReturnsCorrectMessage() {
        Message message = new Message("08575975889", "Hi Keegan, did you receive the payment?", 1);
        message.createMessageHash();
        assertEquals("Press 0 to delete the message.", message.SentMessage(2));
    }
 
    @Test
    public void testMessageStoreOptionReturnsCorrectMessage() {
        Message message = new Message("+27718693002", "Hi Mike, can you join us for dinner tonight?", 0);
        message.createMessageHash();
        assertEquals("Message successfully stored.", message.SentMessage(3));
    }
 
    // ===== returnTotalMessagess() =====
 
    @Test
    public void testReturnTotalMessagesIncreasesAfterSending() {
        int before = Message.returnTotalMessages();
        Message message = new Message("+27718693002", "Hi Mike, can you join us for dinner tonight?", 0);
        message.createMessageHash();
        message.SentMessage(1); // choice 1 = send
        int after = Message.returnTotalMessages();
        assertEquals(before + 1, after);
    }
}
