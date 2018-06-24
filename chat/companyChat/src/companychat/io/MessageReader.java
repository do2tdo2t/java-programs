package companychat.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import companychat.vo.EmployeeVO;

public class MessageReader {
	//쓰는 파일 경로 D://javaFile/chat/[senderInfo]/[receiverInfo].txt
	//Message들이 쓰임 
	static public String read(String senderInfo, String receiverInfo) {
		try {
			File  file = new File("../files/"+senderInfo, receiverInfo+".txt");
			if(!file.exists()) return "";
			BufferedReader in = new BufferedReader(new FileReader(file));
		    String line = ""; 
		    StringBuffer sb = new StringBuffer();
		    while((line = in.readLine())!=null) {
		    	sb.append(line+'\n');
		    }
		    
		    in.close();
		    
		    return sb.toString();
		}catch(IOException e) {
			e.printStackTrace();
			return "";
		}
	}
}
