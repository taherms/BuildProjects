package firstTime;

import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

public class MacAddress {
public String getMac() {
	StringBuilder macAddressStr = new StringBuilder();
	
	try {
        // get all network interfaces of the current system
        Enumeration<NetworkInterface> networkInterface = NetworkInterface.getNetworkInterfaces();
        // iterate over all interfaces
        while (networkInterface.hasMoreElements()) {
           // get an interface
           NetworkInterface network = networkInterface.nextElement();
           // get its hardware or mac address
           byte[] macAddressBytes = network.getHardwareAddress();
           if (macAddressBytes != null) {
              //System.out.print("MAC address of interface \""+network.getName()+"\" is : ");
              //macAddressStr.append("MAC address of interface \""+network.getName()+"\" is : ");
              // initialize a string builder to hold mac address
              
              // iterate over the bytes of mac address  
              for (int i = 0; i < macAddressBytes.length; i++) {
                 // convert byte to string in hexadecimal form
                 macAddressStr.append(String.format("%02X", macAddressBytes[i]));
                 // check if there are more bytes, then add a "-" to make it more readable
                 if(i < macAddressBytes.length - 1) {
                    macAddressStr.append("-");
                 }
              }macAddressStr.append("\n");
            
           }
        }//System.out.println(macAddressStr.toString());
     } catch (SocketException e) {
        e.printStackTrace();
     }
	
	return macAddressStr.toString();
	
}
}
