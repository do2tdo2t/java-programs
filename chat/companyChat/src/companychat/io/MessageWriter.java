package companychat.io;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.Gson;
import companychat.vo.MessageVO;

public class MessageWriter {
	static public void write(MessageVO msg){
		String senderInfo =  ""+msg.getSender();
		String receiverInfo = ""+msg.getReceiver();
		System.out.println(System.getProperty("user.dir"));
		File directory = new File("../files/"+senderInfo);
		if(!directory.exists()) directory.mkdirs();
		File  file = new File("../files/"+senderInfo, receiverInfo+".txt");
		BufferedWriter out = null;
		try {
			Gson gson = new Gson();
			
			out = new BufferedWriter(new FileWriter(file));
			out.write(gson.toJson(msg)+'\n');
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	/*
	public static void main(String arg[]) {
		int sender = 2;
		int receiver = 4;
		String content = "안녕하세요 !";
		String senderName = "김경주";
		String recvName = "박정연";

		MessageVO msg = new MessageVO(sender,receiver,senderName,recvName,content);
		MessageWriter.write(msg);
		
		System.out.println(MessageReader.read(sender+"", receiver+""));
		
	}
	*/
	
}
