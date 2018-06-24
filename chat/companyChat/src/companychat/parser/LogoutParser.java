package companychat.parser;

import com.google.gson.JsonObject;

import companychat.vo.LoginVO;
import companychat.vo.LogoutVO;


public class LogoutParser {

	public static int parse(JsonObject jsonObject) {
		return jsonObject.get("id").getAsInt();
	}

}
