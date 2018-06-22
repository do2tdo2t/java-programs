package companychat.parser;

import java.util.ArrayList;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import companychat.vo.MessageVO;
import companychat.vo.MessagesVO;

public class MessagesParser {
	
	public static MessagesVO parse(JsonObject jsonObject) {
		MessagesVO msgsVO = new MessagesVO();
		int count = jsonObject.get("count").getAsInt();
		JsonArray jsonArray = jsonObject.getAsJsonArray("list");
		JsonElement element = null;
		JsonObject object = null;
		MessageVO m = null;
		ArrayList<MessageVO> list = new ArrayList<MessageVO>();
		for(int i = 0 ; i<count ; i++) {
			element = jsonArray.get(i);
			object = element.getAsJsonObject();
			m = new MessageVO();
			m.setSender(object.get("sender").getAsInt());
			m.setReceiver(object.get("receiver").getAsInt());
			m.setContent(object.get("content").getAsString());
			list.add(m);
			System.out.println(m);
		}
		msgsVO.setCount(count);
		msgsVO.setList(list);
		return msgsVO;
		
	}

}
