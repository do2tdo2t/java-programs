package companychat.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.SocketException;



public final class Reader{
	BufferedReader br = null;
	Socket server = null;
	public Reader(Socket server) {
		try {
			if(!server.isClosed()) {
				this.server = server;
				br = new BufferedReader(new InputStreamReader(server.getInputStream()));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String read() throws NullPointerException, SocketException{
		try {
			if(!server.isClosed()) {
				String result = br.readLine();
				return result;
			}
		}catch(IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	void log(String str) {
		System.out.println("Reader..."+str);
	}

}
