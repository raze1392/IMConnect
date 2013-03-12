package imconnect;

import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.MessageListener;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Presence;

/**
 * Handles all the messages
 * 
 * @author Shivam
 */
public class MessageAndPresenceHandler implements MessageListener {

    private XMPPConnection connection;
    Presence presence;
    
    public MessageAndPresenceHandler(XMPPConnection connection) {
        this.connection = connection;
        presence = new Presence(Presence.Type.available);
    }
    
    @Override
    public void processMessage(Chat chat, Message message) {
        System.out.println("Message is: " + message.getBody());
    }
    
    public String getConnectionId() {
        if (connection != null && connection.isConnected()) {
            return connection.getConnectionID();
        } else {
            return "Not Connected";
        }
    }
    
    public void setStatus(String status) {
        presence.setStatus(status);
    }
    
    public void setPresenceType(String type) {
        switch(type) {
            case "online":
                presence.setType(Presence.Type.available);
                break;
            case "offline":
                presence.setType(Presence.Type.unavailable);
                break;
            case "error":
                presence.setType(Presence.Type.error);
        }
    }
    
    
}
