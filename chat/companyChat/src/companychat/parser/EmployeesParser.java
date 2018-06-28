package companychat.parser;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import companychat.vo.EmployeeVO;
import companychat.vo.EmployeesVO;

public class EmployeesParser {

	public static EmployeesVO parse(JsonObject jsonObject) {
		EmployeesVO emps = new EmployeesVO();
		JsonArray jsonArray = jsonObject.getAsJsonArray("list");
		JsonElement element = null;
		JsonObject object = null;
		EmployeeVO e = null;
		int count = jsonObject.get("count").getAsInt();
		for(int i = 0 ; i<count ; i++) {
			element = jsonArray.get(i);
			object = element.getAsJsonObject();
			e = new EmployeeVO();
			e.setId(object.get("id").getAsInt());
			e.setName(object.get("name").getAsString());
			e.setDept(object.get("dept").getAsInt());
			e.setTel(object.get("tel").getAsString());
			e.setEmail(object.get("email").getAsString());
			emps.add(e);
		}
		return emps;
	}
	
	void log(String str) {
		System.out.println("EmployeesParser..."+str);
	}

}
