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
import com.mycompany.chatapp.MessageManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

/** Tests the arrays, search, delete and report features using the brief's test data. */
public class MessageTest {
    private MessageManager manager;

    @BeforeEach
    public void setUp() {
        manager = new MessageManager();
        manager.loadTestData();
    }

    @Test
    public void testSentMessagesArrayPopulated() {
        assertEquals("Did you get the cake?", manager.getSentMessages()[0]);
        assertEquals("It is dinner time!", manager.getSentMessages()[1]);
    }

    @Test
    public void testDisregardedAndStoredArraysPopulated() {
        assertEquals("Yohoooo, I am at your gate.", manager.getDisregardedMessages()[3]);
        assertEquals("Ok, I am leaving without you.", manager.getStoredMessages()[4]);
    }

    @Test
    public void testHashAndIdArraysPopulated() {
        assertEquals("HASH_2", manager.getMessageHashes()[2]);
        assertEquals("0838884567", manager.getMessageIDs()[1]);
    }

    @Test
    public void testDisplayLongestMessage() {
        assertEquals("Where are you? You are late! I have asked you to be on time.",
                manager.getLongestStoredMessage());
    }

    @Test
    public void testSearchByMessageID() {
        assertEquals("It is dinner time!", manager.searchByMessageID("0838884567"));
        assertEquals("0838884567", manager.getRecipientByMessageID("0838884567"));
    }

    @Test
    public void testSearchByRecipient() {
        assertEquals("Where are you? You are late! I have asked you to be on time.\n"
                + "Ok, I am leaving without you.\n",
                manager.searchByRecipient("+27838884567"));
    }

    @Test
    public void testDeleteMessageByHash() {
        assertEquals("Message: \"Where are you? You are late! I have asked you to be on time.\" successfully deleted.",
                manager.deleteMessageByHash("HASH_2"));
        assertEquals("Message ID not found.", manager.searchByMessageID("ID_2"));
    }

    @Test
    public void testDeleteUnknownHash() {
        assertEquals("Hash not found", manager.deleteMessageByHash("NOPE"));
    }

    @Test
    public void testDisplayReport() {
        String line = "------------------------------\n";
        String expected = "===== MESSAGE REPORT =====\n"
                + "Message ID: ID_1\nMessage Hash: HASH_1\nRecipient: +27834557896\n"
                + "Message: Did you get the cake?\nStatus: Sent\n" + line
                + "Message ID: 0838884567\nMessage Hash: HASH_4\nRecipient: 0838884567\n"
                + "Message: It is dinner time!\nStatus: Sent\n" + line
                + "Message ID: ID_2\nMessage Hash: HASH_2\nRecipient: +27838884567\n"
                + "Message: Where are you? You are late! I have asked you to be on time.\nStatus: Stored\n" + line
                + "Message ID: ID_5\nMessage Hash: HASH_5\nRecipient: +27838884567\n"
                + "Message: Ok, I am leaving without you.\nStatus: Stored\n" + line;
        assertEquals(expected, manager.generateReport());
    }
}