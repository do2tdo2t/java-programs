package companychat.parser;

import com.google.gson.JsonObject;


public class LogoutParser {

	public static int parse(JsonObject jsonObject) {
		return jsonObject.get("id").getAsInt();
	}

}
