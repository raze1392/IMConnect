package imconnect;

import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.ChatManager;
import org.jivesoftware.smack.ChatManagerListener;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;

/**
 * Creates and Receives chats form user contacts
 * 
 * @author Shivam
 */
public class ChatHandler {
    
    private XMPPConnection connection;
    private ChatManager manager;
    private MessageAndPresenceHandler handler;
    
    public ChatHandler(XMPPConnection connection, MessageAndPresenceHandler handler) {
        this.connection = connection;
        this.handler = handler;
        manager = connection.getChatManager();
        manager.addChatListener(new ChatManagerHandler());
    }
    
    public Chat createChat(String chatWith) {
        return manager.createChat(chatWith, handler);
    }
    
    public boolean sendMessage(Chat chat, String message) {
        try {
            chat.sendMessage(message);
            return true;
        } catch(XMPPException e) {
            System.out.println(e.getStackTrace());
            return false;
        }
    }
    
    public String getConnectionId() {
        if (connection != null && connection.isConnected()) {
            return connection.getConnectionID();
        } else {
            return "Not Connected";
        }
    }
    
    
    /*
     * Class to add listener for incoming chats
     */
    public class ChatManagerHandler implements ChatManagerListener {
        
        @Override
        public void chatCreated(Chat chat, boolean createdLocally) {
            if (!createdLocally) {
                chat.addMessageListener(handler);
            }
        }
        
    }
}
