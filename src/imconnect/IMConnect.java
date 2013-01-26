/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package imconnect;

/**
 *
 * @author Shivam
 */
public class IMConnect {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
        
        FacebookConnect ft = new FacebookConnect("<Username Here>", "<Password here>");
        ft.connectToFacebook();
        ft.getContacts();
        /*
        GTalkConnect gt = new GTalkConnect("<Username Here>", "<Password here>");
        gt.connectToGTalk();
        gt.getContacts();
        * */
    }
}
