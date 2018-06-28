package companychat.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import com.google.gson.Gson;
import companychat.vo.MessageVO;

public class MessageReader {
	//쓰는 파일 경로 D://javaFile/chat/[senderInfo]/[receiverInfo].txt
	//Message들이 쓰임 
	static public ArrayList<MessageVO> read(String senderInfo, String receiverInfo) {
		try {
			Gson gson = new Gson();
			File  file = new File("../files/"+senderInfo, receiverInfo+".txt");
			if(!file.exists()) return null;
			BufferedReader in = new BufferedReader(new FileReader(file));
		    String line = ""; 
		  
		    MessageVO msg = null;
		    ArrayList<MessageVO> list = new ArrayList<MessageVO>();
		    while((line = in.readLine())!=null) {
		    	msg = gson.fromJson(line, MessageVO.class);
		    	list.add(msg);
		    }
		    in.close();
		    if(msg.equals(null))return null;
		    return list;
		}catch(IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	static void log(String str) {
		System.out.println("MessageReader...."+ str);
	}
}
