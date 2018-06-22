package companychat.parser;

import com.google.gson.JsonObject;

import companychat.vo.MessageVO;

public class MessageParser {

	public static MessageVO parse(JsonObject jsonObject) {
		MessageVO msg = new MessageVO();
		msg.setSender(jsonObject.get("sender").getAsInt());
		msg.setReceiver(jsonObject.get("receiver").getAsInt());
		msg.setContent(jsonObject.get("content").getAsString());
		return msg;
	}

}
