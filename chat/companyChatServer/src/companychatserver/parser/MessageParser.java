package companychatserver.parser;

import com.google.gson.JsonObject;

import companychatserver.vo.MessageVO;

public class MessageParser {

	public static MessageVO parse(JsonObject jsonObject) {
		MessageVO msg = new MessageVO();
		msg.setSender(jsonObject.get("sender").getAsInt());
		msg.setReceiver(jsonObject.get("receiver").getAsInt());
		msg.setSenderName(jsonObject.get("senderName").getAsString());
		msg.setRecvName(jsonObject.get("recvName").getAsString());
		msg.setWriteDate(jsonObject.get("writeDate").getAsString());
		msg.setContent(jsonObject.get("content").getAsString());
		return msg;
	}

}
