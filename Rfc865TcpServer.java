import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

//Name: James Tan Chee Min
//Group: SEP1
//IP Address: 172.21.145.17

public class Rfc865TcpServer {

	 public static void main(String[] argv) {
		 //
		 // 1. Open TCP socket at well-known port
		 //
		 ServerSocket parentSocket = null;
		 try {
		 parentSocket = new ServerSocket(17);
		 } catch (Exception e) {}

		 while (true) {
			 try {
				 //
				 // 2. Listen to establish TCP connection with client
				 //
				 Socket childSocket = parentSocket.accept();
				 //
				 // 3. Create new thread to handle client connection
				 //
				 ClientHandler client = new ClientHandler(childSocket);
				 Thread thread = new Thread(client);
				 thread.start();
			 } catch (Exception e) {}
		 }
	 }
}

class ClientHandler implements Runnable{

	private Socket socket;
	
	public ClientHandler(Socket socket){
		this.socket = socket;
	}
	public void run() {
		try{
			//
			//4. Receive TCP request from client
			//
			InputStream is = socket.getInputStream();

			byte[] request = new byte[512];
			is.read(request);
			
			String requestString = new String(request);
			System.out.println(requestString);
			//
			//5. Send TCP reply to client
			//
			OutputStream os = socket.getOutputStream();
			byte[] quoteByte = "Good stuff".getBytes();
			os.write(quoteByte);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
        
}

