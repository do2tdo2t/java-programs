package companychat.parser;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import companychat.util.Constant;
import companychat.vo.EmployeeVO;
import companychat.vo.EmployeesVO;
import companychat.vo.MessageVO;
import companychat.vo.MessagesVO;

public class Parser {
	static JsonParser parser = null;
	static JsonObject jsonObject = null;
	/*
	static MessagesVO messages= null;
	static MessageVO message = null;
	static EmployeesVO employees = null;
	
	
	public MessagesVO getMessagesVO() {
		return messages;
	}
	public MessageVO getMessageVO() {
		return message;
	}
	public EmployeesVO getEmployeesVO() {
		return employees;
	}
	
	public Parser() {
		parser = new JsonParser();
	}

	public int toJsonObject(String jsonStr) {
		int type;
		int count = 0;
		jsonObject = parser.parse(jsonStr).getAsJsonObject();
		type = jsonObject.get("type").getAsInt();
		
		return type;
		
		
		try {
			if(type == Constant.MSGS) {
				
				parseMessages(jsonObject);
			}else if(type == Constant.MSG) {
				parseMessage(jsonObject);
			}else if(type == Constant.EMPS) {
				count = jsonObject.get("count").getAsInt();
				jsonArray = jsonObject.getAsJsonArray("list");
				parseEmployees(jsonArray,count);
			}else {
				System.out.println("error : 알 수 없는 메시지 type ="+type );
				return Constant.ERROR;
			}
			return type;
		}catch(JsonIOException e) {
			e.printStackTrace();
			return Constant.ERROR;
		}
		
	}
	
	public static void parseMessages(JsonObject jsonObject) {
		int count = jsonObject.get("count").getAsInt();
		JsonArray jsonArray = jsonObject.getAsJsonArray("list");
		JsonElement element = null;
		JsonObject object = null;
		MessageVO m = null;
		messages = new MessagesVO();
		for(int i = 0 ; i<count ; i++) {
			element = jsonArray.get(i);
			object = element.getAsJsonObject();
			m = new MessageVO();
			m.setSender(object.get("sender").getAsInt());
			m.setReceiver(object.get("receiver").getAsInt());
			m.setContent(object.get("content").getAsString());
			messages.add(m);
			System.out.println(m);
		}
	}
	
	
	public static void parseMessage(JsonObject json) {
		message = new MessageVO();
		message.setSender(json.get("sender").getAsInt());
		message.setReceiver(json.get("receiver").getAsInt());
		message.setContent(json.get("content").getAsString());
		System.out.println(message);
	}

	public static void parseEmployees(JsonArray jsonArray, int count) {
		JsonElement element = null;
		JsonObject object = null;
		EmployeeVO e = null;
		employees = new EmployeesVO();
		for(int i = 0 ; i<count ; i++) {
			element = jsonArray.get(i);
			object = element.getAsJsonObject();
			e = new EmployeeVO();
			e.setId(object.get("id").getAsInt());
			e.setName(object.get("name").getAsString());
			e.setDept(object.get("dept").getAsString());
			e.setTel(object.get("tel").getAsString());
			e.setEmail(object.get("email").getAsString());
			employees.add(e);
			System.out.println(e);
		}
	}
*/	
}
