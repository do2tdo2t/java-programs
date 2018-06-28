package companychatserver.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.SocketException;



public final class Reader{
	BufferedReader br = null;
	Socket socket = null;
	public Reader(Socket socket) {
		try {
				log("input stream 상태:"+socket.isInputShutdown());
				this.socket = socket;
				br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String read() throws NullPointerException, SocketException,IOException{
	
		log("read...input stream 상태:"+socket.isInputShutdown());
		
		String result = br.readLine();
		return result;
			
	}
	
	
	void log(String str) {
		System.out.println("Reader..."+str);
	}

}
