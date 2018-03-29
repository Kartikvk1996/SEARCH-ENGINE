package se.dscore;

/**
 * Core of the domain. This implements the basic functionality of a
 * component process. 
 */

import java.io.IOException;
import se.ipc.pdu.IntroPDU;
import se.ipc.pdu.PDU;
import se.ipc.ESocket;
import se.ipc.pdu.AckPDU;
import se.ipc.pdu.ConnectPDU;
import se.ipc.pdu.GetPDU;
import se.ipc.pdu.PDUConsts;
import se.ipc.pdu.StatusPDU;

public class Node implements RequestHandler {

    private boolean running = false;
    private Server server;
    private final int HEARTBEAT_INTERVAL = 2000;
    protected MasterProxy mproxy;
    protected String ticket;
    
    /* just a hack to avoid mutiple assignement statements */
    final void commonInit() throws IOException {
         server = new Server(this);       
    }
    
    /* this is just for master port unavailibility issue */
    protected Node() throws IOException {
        commonInit();
    }
    
    public Node(MasterProxy mproxy, String ticket) throws IOException {
        this.mproxy = mproxy;
        this.ticket = ticket;
        commonInit();
    }

    public void run() throws IOException {
        /* Create a heartbeat sending thread */
        new Thread(new Heartbeat(mproxy, this, HEARTBEAT_INTERVAL), "Heartbeat").start();
        running = true;
        server.run();
    }
    
    public String getHost() {
        return server.getHost();
    }
    
    public int getPort() {
        return server.getPort();
    }
    
    @Override
    public void handle(ESocket socket) throws IOException {
        
    }
    
    void def_handler(ESocket socket, PDU pdu) throws IOException {
        switch(pdu.getMethod()) {
            case PDUConsts.METHOD_INTRO:
                handle_intro(socket, (IntroPDU) pdu);
                break;
        }
    }

    @Override
    public void handle_get(ESocket sock, GetPDU gpdu) throws IOException {
        
    }

    @Override
    public void handle_intro(ESocket sock, IntroPDU ipdu) throws IOException {
       AckPDU apdu = new AckPDU(ticket);
       ESocket gsock = new ESocket(ipdu.getGuestHost(), ipdu.getGuestPort());
       gsock.send(apdu);
    }

    @Override
    public void handle_connect(ESocket sock, ConnectPDU cpdu) throws IOException{
        
    }

    @Override
    public void handle_ack(ESocket sock, AckPDU apdu) throws IOException {
        /* ignore it */
    }

    public PDU getStatus() {
        return new StatusPDU();
    }
    
    public void setProxy(MasterProxy mproxy) {
        this.mproxy = mproxy;
    }
    
}
