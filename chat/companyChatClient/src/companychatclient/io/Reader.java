package companychatclient.io;

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
			
			this.socket = socket;
			br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String read() throws NullPointerException, SocketException, IOException{
		System.out.println(socket.isClosed()+"" + socket.isConnected() + socket.isInputShutdown() );
		String result = br.readLine();
		return result;
	}
	
	
	void log(String str) {
		System.out.println("Reader..."+str);
	}

}
