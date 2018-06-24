package companychat.parser;

import java.util.ArrayList;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import companychat.vo.MessageVO;
import companychat.vo.RoomVO;

public class RoomParser {
	
	public static RoomVO parse(JsonObject jsonObject) {
		JsonElement element = null;
		JsonObject object = null;
		MessageVO m = null;
		
		RoomVO roomVO = new RoomVO();
		int count = jsonObject.get("count").getAsInt();
		JsonArray jsonArray = jsonObject.getAsJsonArray("list");
		
		roomVO.setRecv(jsonObject.get("recv").getAsString());
		roomVO.setUser(jsonObject.get("user").getAsString());
		
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

		roomVO.setList(list);
		return roomVO;
		
	}

}
