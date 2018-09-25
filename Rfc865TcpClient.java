import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

//Name: James Tan Chee Min
//Group: SEP1
//IP Address: 172.21.145.17

public class Rfc865TcpClient {
    public static void main(String[] argv){
        
        //
		// 1. Establish TCP connection with server
		//
        Socket socket = null;
        try{
            socket = new Socket();
            InetAddress IpAddress = InetAddress.getByName("localhost");
            SocketAddress address = new InetSocketAddress(IpAddress, 17);
            socket.connect(address);
        }catch(Exception e){
            e.printStackTrace();
            System.exit(-1);
        }
        
        try{
			//
			// 2. Send TCP request to server
			//
            OutputStream os = socket.getOutputStream();
            byte[] buf = "James Tan Chee Min, SEP1, 172.21.145.17".getBytes("UTF-8");
            os.write(buf);
			//
			// 3. Receive TCP reply from server
			//
            InputStream is = socket.getInputStream();
            byte[] quoteMessage = new byte[512];
            is.read(quoteMessage);
            
            String quote = new String(quoteMessage);
            System.out.println(quote);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}