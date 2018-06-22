package companychat.parser;

import java.util.ArrayList;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import companychat.vo.EmployeeVO;
import companychat.vo.EmployeesVO;

public class EmployeeParser {

	public static EmployeeVO parse(JsonObject jsonObject) {
		EmployeeVO e =  new EmployeeVO();
		e.setId(jsonObject.get("id").getAsInt());
		e.setName(jsonObject.get("name").getAsString());
		e.setDept(jsonObject.get("dept").getAsString());
		e.setTel(jsonObject.get("tel").getAsString());
		e.setEmail(jsonObject.get("email").getAsString());
		System.out.println(e);

		return e;
	}


}
