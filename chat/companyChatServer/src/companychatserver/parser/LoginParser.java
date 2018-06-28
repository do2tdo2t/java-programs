package companychatserver.parser;

import com.google.gson.JsonObject;

import companychatserver.vo.LoginVO;


public class LoginParser {

	public static LoginVO parse(JsonObject jsonObject) {
		LoginVO l =  new LoginVO();
		l.setId(jsonObject.get("id").getAsInt());
		l.setPw(jsonObject.get("pw").getAsString());
		l.setInetAddress(jsonObject.get("inetAddress").getAsString());
		return  l;
	}

}
