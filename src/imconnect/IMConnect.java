package imconnect;

import org.jivesoftware.smack.XMPPConnection;

/**
 * Main File of IMConnect
 * 
 * Order of Connection:
 *      Create a ServerSettings Object and instantiate it with the Server class to connect
 *      Call 'serverSetup' with required parameters (details in ServerSettings.java)
 *      Login to server using 'serverLogin'
 *      If connection is authenticated fetch connection using 'getConnection'
 *      Pass the connection object around for various tasks such as roster
 *      Disconnect connection by calling 'serverDisconnect'
 * 
 * @author Shivam
 * @since Production
 * @version 1.0
 */
public class IMConnect {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
        XMPPConnection connection;
        ServerSettings gt;
        //ServerSettings ft;
        gt = new GTalkConnect();
        //ft = new FacebookConnect();
        String gId = gt.serverSetup(ServerSettings.GOOGLE_HOST, ServerSettings.PORT, ServerSettings.GOOGLE_SERVICE, ServerSettings.DISABLE_DEBUG);
        //String fId = ft.serverSetup(ServerSettings.FACEBOOK_HOST, ServerSettings.PORT, ServerSettings.FACEBOOK_SERVICE, ServerSettings.DISABLE_DEBUG);
        
        System.out.println("-----------CONNECTING-----------");
        System.out.println("-----please wait for 10 sec-----");
        
        Thread.sleep(10000);
        boolean auth = gt.serverLogin("imconnect.bot@gmail.com", "imconnect12345");
        if (auth) {
            connection = gt.getConnection();
            System.out.println("-----------CONNECTED------------");
        } else {
            connection = null;
        }
        System.out.println("Connection id: "+gId);
        System.out.println("User: "+auth);
        
        RosterHandler rh = new RosterHandler(connection);
        Thread.sleep(2000);
        rh.displayRoster();
        
        gt.serverDisconnect();
    
        // TBD code for CodeListener, MessageListener
    }
}
