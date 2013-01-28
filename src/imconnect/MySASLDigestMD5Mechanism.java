package imconnect;

/**
 * 
 * DONT CHANGE THIS FILE
 * 
 * Digest-MD5 mechanism for facebook
 * @author Shivam
 * @since Production
 * @version 1.0
 */
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.security.auth.callback.CallbackHandler;
import javax.security.sasl.Sasl;
import org.jivesoftware.smack.SASLAuthentication;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.sasl.SASLMechanism;
import org.jivesoftware.smack.packet.Packet;
import org.jivesoftware.smack.util.Base64;

public class MySASLDigestMD5Mechanism extends SASLMechanism
{
    public MySASLDigestMD5Mechanism(SASLAuthentication saslAuthentication)
    {
        super(saslAuthentication);
    }

    @Override
    protected void authenticate()
        throws IOException, XMPPException
    {
        String mechanisms[] = {
            getName()
        };
        java.util.Map props = new HashMap();
        sc = Sasl.createSaslClient(mechanisms, null, "xmpp", hostname, props, this);
        super.authenticate();
    }

    @Override
    public void authenticate(String username, String host, String password)
    throws IOException, XMPPException
    {
        authenticationId = username;
        this.password = password;
        hostname = host;
        String mechanisms[] = {
            getName()
        };
        Map<String, String> props = new HashMap<>();
        sc = Sasl.createSaslClient(mechanisms, null, "xmpp", host, props, this);
        super.authenticate();
    }

    @Override
    public void authenticate(String username, String host, CallbackHandler cbh)
        throws IOException, XMPPException
    {
        String mechanisms[] = {
            getName()
        };
        Map<String, String> props = new HashMap<>();
        sc = Sasl.createSaslClient(mechanisms, null, "xmpp", host, props, cbh);
        super.authenticate();
    }

    @Override
    protected String getName()
    {
        return "DIGEST-MD5";
    }

    @Override
    public void challengeReceived(String challenge)
        throws IOException
    {
        byte response[];
        if(challenge != null) {
            response = sc.evaluateChallenge(Base64.decode(challenge));
        }
        else {
            response = sc.evaluateChallenge(new byte[0]);
        }
        Packet responseStanza;

        if (response == null){
            responseStanza = new Response();
        } else {
            responseStanza = new Response(Base64.encodeBytes(response,Base64.DONT_BREAK_LINES));   
        }
        
        getSASLAuthentication().send(responseStanza);
    }
}
