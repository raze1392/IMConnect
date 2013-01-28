package imconnect;

import org.jivesoftware.smack.XMPPConnection;

/**
 * Interface for connecting to a server
 * Every new Server Connection class needs to implement it
 * 
 * @author Shivam
 * @since Production
 * @version 1.0
 */
public interface ServerSettings {
    
    public static final int PORT = 5222;
    public static final boolean ENABLE_DEBUG = true;
    public static final boolean DISABLE_DEBUG = false;
    public static final String GOOGLE_HOST = "talk.google.com";
    public static final String GOOGLE_SERVICE = "gmail.com";
    public static final String FACEBOOK_HOST = "chat.facebook.com";
    public static final String FACEBOOK_SERVICE = "chat.facebook.com";
    
    /**
     *
     * @param host - contains the host name to connect like "talk.google.com"
     * @param port - port number to connect default: 5222
     * @param debug - if enabled, opens a window to debug XMPP connection
     * @return - a String representing connection
     *      connection id: if connection is successful
     *      "Not Connected": if connection return as null
     *      "ERROR": if connection fails due to some exception
     */
    public String serverSetup(String host, int port, boolean debug);
    
    /**
     *
     * @param host - contains the host name to connect like "talk.google.com"
     * @param port - port number to connect default: 5222
     * @param service - the service which hosts the XMPP server
     * @param debug - if enabled, opens a window to debug XMPP connection
     * @return - a String representing connection
     *      connection id: if connection is successful
     *      "Not Connected": if connection return as null
     *      "ERROR": if connection fails due to some exception
     */
    public String serverSetup(String host, int port, String service, boolean debug);
    
    /**
     *
     * @param username - the username of the user
     * @param password - the password of user
     * @return - boolean indication whether the user is successfully authenticated or not
     */
    public boolean serverLogin(String username, String password);
    
    /**
     *
     * @param username - the username of the user
     * @param password - the password of user
     * @param resource - indicating the name of device with which the user connects
     * @return - boolean indication whether the user is successfully authenticated or not
     */
    public boolean serverLogin(String username, String password, String resource);
    
    /**
     * 
     * @return - XMPPConnection object of a particular server for further communication
     */
    public XMPPConnection getConnection();
    
    /**
     *
     * @return boolean indicating whether successfully disconnected of not
     */
    public boolean serverDisconnect();
    
}
