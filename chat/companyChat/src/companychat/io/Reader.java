package companychat.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;

import companychat.parser.Parser;

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
		String result = null;
		try {
			String line;
			StringBuilder sb = new StringBuilder();
			while((line = br.readLine())!=null) {
				sb.append(line).append("\n");
			}
			result = sb.toString();
		}catch(IOException e) {
			e.printStackTrace();
		}
		return result;
	}

}
