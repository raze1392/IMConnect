package imconnect;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.SASLAuthentication;
import org.jivesoftware.smack.SmackConfiguration;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.packet.Presence;

/**
 * Connects to GTalk using Plain SASL
 * @author Shivam
 * @since Production
 * @version 1.0
 */
public class GTalkConnect implements ServerSettings {
    
    private static final int packetReplyTimeout = 500; // millis
    private XMPPConnection connection;
    private Presence presence;
    
    @Override
    public String serverSetup(String host, int port, boolean debug) {
        try {
            SASLAuthentication.supportSASLMechanism("PLAIN", 0);
            SmackConfiguration.setPacketReplyTimeout(packetReplyTimeout);
            ConnectionConfiguration config = new ConnectionConfiguration(host, port);
            config.setDebuggerEnabled(debug);
            config.setSASLAuthenticationEnabled(true); 
            config.setRosterLoadedAtLogin(true);
            connection = new XMPPConnection(config);
            if (connection != null) {
                connection.connect();
                return connection.getConnectionID();
            } else {
                return "ERROR";
            }
        } catch (XMPPException e) {
            System.out.println("Error: " + e);
            return "ERROR";
        }
    }
    
    @Override
    public String serverSetup(String host, int port, String service, boolean debug) {
        try {
            SASLAuthentication.supportSASLMechanism("PLAIN", 0);
            SmackConfiguration.setPacketReplyTimeout(packetReplyTimeout);
            ConnectionConfiguration config = new ConnectionConfiguration(host, port, service);
            config.setDebuggerEnabled(debug);
            config.setSASLAuthenticationEnabled(true); 
            config.setRosterLoadedAtLogin(true);
            connection = new XMPPConnection(config);
            if (connection != null) {
                connection.connect();
                return connection.getConnectionID();
            } else {
                return "Not Connected";
            }
        } catch (XMPPException e) {
            System.out.println("Error: " + e);
            return "ERROR";
        }
    }

    @Override
    public boolean serverLogin(String username, String password) {
        if (connection != null && connection.isConnected()) {
            try {
                connection.login(username, password, "IMConnect");
            } catch (XMPPException ex) {
                Logger.getLogger(GTalkConnect.class.getName()).log(Level.SEVERE, null, ex);
            }
            return connection.isAuthenticated();
        } else {
            return connection.isAuthenticated();
        }
    }

    @Override
    public boolean serverLogin(String username, String password, String resource) {
        if (connection != null && connection.isConnected()) {
            try {
                connection.login(username, password, resource);
            } catch (XMPPException ex) {
                Logger.getLogger(GTalkConnect.class.getName()).log(Level.SEVERE, null, ex);
            }
            return connection.isAuthenticated();
        } else {
            return connection.isAuthenticated();
        }
    }

    @Override
    public boolean serverDisconnect() {
        if (connection != null && connection.isConnected()) {
            presence = new Presence(Presence.Type.unavailable);
            connection.disconnect(presence);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public XMPPConnection getConnection() {
        if (connection != null && connection.isConnected()) {
            return connection;
        } else {
            return null;
        }
    }
    
}
