import com.sun.media.jfxmedia.logging.Logger;
import se.dscore.Master;
import java.io.IOException;
import se.ipc.pdu.PDU;

public class DataMaster {
    
    public static void main(String argv[]) throws IOException {

        PDU.setProcessRole("master");
        Logger.setLevel(Logger.DEBUG);
        
        Master master = new Master("configFile");
	System.out.printf("master running on port  [%d]\n", master.getPort());
        System.out.printf("Http server running on port [%d]\n", master.getHttpServer().getPort());
        System.out.printf("Http server document root : [%s]\n", master.getHttpServer().getDocumentRoot());
        master.run();
        
    }
}