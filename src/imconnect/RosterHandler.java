package imconnect;

import java.util.Collection;
import org.jivesoftware.smack.Roster;
import org.jivesoftware.smack.RosterEntry;
import org.jivesoftware.smack.XMPPConnection;

/**
 * Handles Roster for any connection
 * 
 * @author Shivam
 * @since Production
 * @version 1.0
 */
public class RosterHandler {
    
    private XMPPConnection connection;
    private Roster roster;
    private Collection<RosterEntry> contacts;
    
    /**
     * Constructor
     * @param connection - the resource connection from whose roster is to be fetched
     */
    public RosterHandler(XMPPConnection connection) {
        this.connection = connection;
        getRosterList();
    }
    
    public String getConnectionId() {
        if (connection != null && connection.isConnected()) {
            return connection.getConnectionID();
        } else {
            return "Not Connected";
        }
    }
    
    private void getRosterList() {
        if (connection != null && connection.isConnected()) {
            roster = connection.getRoster();
        } 
    }
    
    public void displayRoster() {
        if (roster != null) {
            contacts = roster.getEntries();
            for (RosterEntry cn : contacts) {
                // Add code for display in UI
                System.out.println(cn.getUser());
            }
        }
    }
    
    public String getContact(String user) {
        RosterEntry contact = roster.getEntry(user);
        if (contact != null) {
            return contact.getUser();
        } else {
            return null;
        }
    }
    
    // To be added Groups, add, delete
    
}
