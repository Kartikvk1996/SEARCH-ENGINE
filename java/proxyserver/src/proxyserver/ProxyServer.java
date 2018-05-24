/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proxyserver;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import se.util.http.HttpRequest;
import se.util.http.ProgressiveProcess;
import se.util.http.RESTServer;

/**
 *
 * @author mpataki
 */
public class ProxyServer extends RESTServer {

    HashMap<Long, SEUrl> urlMap;

    public ProxyServer(String docRoot, ProgressiveProcess process, HashMap<Long, SEUrl> urlMap)
            throws IOException {
        super(docRoot, process);
        this.urlMap = urlMap;
    }

    @Override
    protected void serve(HttpRequest req) throws IOException {
        switch (req.getUrl()) {
            case "proxy":
                Long index = Long.parseLong(req.getData());
                SEUrl seu = urlMap.get(index);
                seu.hits++;
                OutputStream out = req.getOutputStream();
                out.write(("HTTP/1.0 302 Found\n" +
                           "Location: " + seu.url + 
                           "\n\n").getBytes());
                break;
            default:
                super.serve(req);
        }
    }

}
