/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package imconnect;

import java.util.Collection;
import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.Roster;
import org.jivesoftware.smack.RosterEntry;
import org.jivesoftware.smack.SASLAuthentication;
import org.jivesoftware.smack.SmackConfiguration;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.packet.Presence;

/**
 *
 * @author Shivam
 */
public class FacebookConnect {
    
    private String username;
    private String password;
    private static final int packetReplyTimeout = 500; // millis
    private XMPPConnection connection;
    private Collection<RosterEntry> contacts;
    private Presence presence;
    
    /**
     * Entries for GoogleTalk
     * @param username
     * @param password
     */
    public FacebookConnect(String username, String password) {
        this.username = username;
        this.password = password;
    }
    
    // Connect to GTalk
    public void connectToFacebook() throws InterruptedException {
        try {
            ConnectionConfiguration config = new ConnectionConfiguration("chat.facebook.com", 5222, "chat.facebook.com");
            SASLAuthentication.registerSASLMechanism("DIGEST-MD5", MySASLDigestMD5Mechanism.class);
            SASLAuthentication.supportSASLMechanism("DIGEST-MD5", 0);
            connection = new XMPPConnection(config);
            config.setSASLAuthenticationEnabled(true); 
            config.setDebuggerEnabled(false);
            connection.connect();
            // Log into the server
            System.out.println(connection);
            System.out.println(username);
            Thread.sleep(15000);
            connection.login(username, password);
            System.out.println(connection.isAuthenticated());
        } catch (XMPPException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    // Get Contacts / Roster
    public void getContacts() throws InterruptedException {
        Roster roster = connection.getRoster();
        Thread.sleep(2000);
        contacts = roster.getEntries();
        Thread.sleep(2000);
        System.out.println(contacts);
        for (RosterEntry contact : contacts) {
            System.out.println(contact);
        }
    }
    
    // Set a status
    public void setStatus(String status) {
        System.out.println(status);
        presence.setStatus(status);
        connection.sendPacket(presence);
    }
    
    // Disconnect from GTalk
    public void disconnectGTalk() {
        if (connection != null && connection.isConnected()) {
            presence = new Presence(Presence.Type.unavailable);
            connection.disconnect(presence);
        }
    }
    
}
