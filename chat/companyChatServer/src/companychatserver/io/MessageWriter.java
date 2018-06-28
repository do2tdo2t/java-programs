package companychatserver.io;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.Gson;

import companychatserver.vo.MessageVO;

public class MessageWriter {
	static public void write(String senderInfo, String receiverInfo,MessageVO msg){
		System.out.println(System.getProperty("user.dir"));
		File directory = new File("../files/"+senderInfo);
		if(!directory.exists()) directory.mkdirs();
		File file = new File("../files/"+senderInfo, receiverInfo+".txt");
		BufferedWriter out = null;
		try {
			Gson gson = new Gson();
			
			out = new BufferedWriter(new FileWriter(file,true));
			out.write(gson.toJson(msg)+'\n');
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
