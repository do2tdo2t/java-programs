package companychat.util;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


public class Json {
	static JsonParser parser = new JsonParser();

	
	public JsonObject toJsonObject(JsonElement jsonElemnet) {
		return jsonElemnet.getAsJsonObject();
	}

	//string
	public JsonObject toJsonObject(String jsonStr) {
		return parser.parse(jsonStr).getAsJsonObject();
	}

	
	public String getString(JsonObject jsonObject, String name) {
		String value = jsonObject.get(name).getAsString();
		return value;
	}
	
	public int getInt(JsonObject jsonObject, String name) {
		int value = jsonObject.get(name).getAsInt();
		return value;
	}

}
