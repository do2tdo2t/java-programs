package companychat.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;



public final class Reader{
	BufferedReader br = null;

	public Reader(Socket server) {
		try {

			br = new BufferedReader(new InputStreamReader(server.getInputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String read() {
		try {

			String result = br.readLine();
			return result;
		}catch(IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	void log(String str) {
		System.out.println("Reader..."+str);
	}

}
