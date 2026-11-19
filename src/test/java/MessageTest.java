/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import com.mycompany.chatapp.MessageManager;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
/**
 *
 * @author Student
 */

public class MessageTest {
    
    private MessageManager manager;

    @BeforeEach
    public void setUp() {
        manager = new MessageManager();
        // This loads the exact 5 test messages from the PDF into the arrays
        manager.loadTestData(); 
    }

    @Test
    public void testSentMessagesArrayPopulated() {
        // The rubric says the sent array should contain Message 1 and Message 4
        assertEquals("Did you get the cake?", manager.sentMessages[0]);
        assertEquals("It is dinner time!", manager.sentMessages[1]);
    }

    @Test
    public void testDisplayLongestMessage() {
        // The longest message is Message 2
        String expected = "Where are you? You are late! I have asked you to be on time.";
        assertEquals(expected, manager.getLongestStoredMessage());
    }

    @Test
    public void testSearchByMessageID() {
        // Message 4 has the ID "0838884567", expected return is the message text
        assertEquals("It is dinner time!", manager.searchByMessageID("0838884567"));
    }

    @Test
    public void testSearchByRecipient() {
        // Recipient +27838884567 has two stored messages (Message 2 and Message 5)
        String result = manager.searchByRecipient("+27838884567");
        assertTrue(result.contains("Where are you? You are late! I have asked you to be on time."));
        assertTrue(result.contains("Ok, I am leaving without you."));
    }

    @Test
    public void testDeleteMessageByHash() {
        // Message 2 has the hash "HASH_2". 
        // The rubric requires this exact success string.
        String result = manager.deleteMessageByHash("HASH_2");
        assertEquals("Message: \"Where are you? You are late! I have asked you to be on time.\" successfully deleted.", result);
    }

    @Test
    public void testDisplayReport() {
        // The report must contain the Hash, Recipient, and Message
        String report = manager.generateReport();
        assertTrue(report.contains("HASH_2"));
        assertTrue(report.contains("+27838884567"));
        assertTrue(report.contains("Where are you? You are late! I have asked you to be on time."));
    }
}