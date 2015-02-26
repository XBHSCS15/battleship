//Placeholder Class
package xbhs.battleship.network;

import java.net.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Network {
    //Just playing around with a few different implementations of this...

    //###USING HTTP GET REQUESTS###
    //Using tutorial from 
    //http://stackoverflow.com/questions/2793150/using-java-net-urlconnection-to-fire-and-handle-http-requests
    //and InputStream to String technique from
    //http://howtodoinjava.com/2013/10/06/how-to-read-data-from-inputstream-into-string-in-java/
    
    String url;
    String charset;
    String param1;
    String param2;
    String query;

    URLConnection uplink;

    public Network(String url) {
        this.url = url;
        charset = java.nio.charset.StandardCharsets.UTF_8.name();
        param1 = "value1";
        param2 = "value2";

        try {
            query = String.format("param1=%s&param2=%s",
                    URLEncoder.encode(param1, charset),
                    URLEncoder.encode(param2, charset));
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(Network.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * fireGET. Used for testing HTTP get requests
     * @return String of server response (would be some HTML usually)
     */
    public String fireGET() {
        try {
            uplink = new URL(url + "?" + query).openConnection();
            uplink.setRequestProperty("Accept-Charset", charset);
            
            InputStream in = uplink.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            StringBuilder out = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                out.append(line);
            }
            return out.toString();
        } catch (Exception ex) {
            Logger.getLogger(Network.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "err";
    }
    
    public static void main(String[] args){
        Network network = new Network("http://example.com");
        System.out.println(network.fireGET());
    }

}
