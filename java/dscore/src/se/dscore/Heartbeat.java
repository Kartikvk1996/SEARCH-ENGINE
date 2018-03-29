package se.dscore;

/**
 * This a Heartbeat thread which reports to Master after every millis 
 * milliseconds. Since we want to pass the mproxy and node we have 
 * made it a separate class.
 */


import java.io.IOException;
import jsonparser.JsonException;
import se.util.Logger;

public class Heartbeat implements Runnable {

    MasterProxy mproxy;
    Node node;
    int millis;
    public Heartbeat(MasterProxy mproxy, Node self, int millis) {
        this.mproxy = mproxy;
        node = self;
        this.millis = millis;
    }
    
    @Override
    public void run() {
        while(true) {
//            try {
//                mproxy.send(node.getStatus());
//            } catch (IOException | JsonException ex) {
//                Logger.elog(Logger.HIGH, "Unable to send heartbeat");
//            }
            try { Thread.sleep(millis); } catch (InterruptedException ex) { }
        }
    }
    
}
