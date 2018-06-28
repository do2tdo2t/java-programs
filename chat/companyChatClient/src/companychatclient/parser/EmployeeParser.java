package companychatclient.parser;


import com.google.gson.JsonObject;

import companychatclient.vo.EmployeeVO;

public class EmployeeParser {

	public static EmployeeVO parse(JsonObject jsonObject) {
		EmployeeVO e =  new EmployeeVO();
		e.setId(jsonObject.get("id").getAsInt());
		e.setName(jsonObject.get("name").getAsString());
		e.setDept(jsonObject.get("dept").getAsInt());
		e.setTel(jsonObject.get("tel").getAsString());
		e.setEmail(jsonObject.get("email").getAsString());
		return e;
	}


}
