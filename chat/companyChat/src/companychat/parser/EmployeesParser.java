package companychat.parser;

import java.util.ArrayList;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import companychat.vo.EmployeeVO;
import companychat.vo.EmployeesVO;

public class EmployeesParser {

	public static EmployeesVO parse(JsonObject jsonObject) {
		EmployeesVO empVO = new EmployeesVO();
		JsonArray jsonArray = jsonObject.getAsJsonArray("list");
		JsonElement element = null;
		JsonObject object = null;
		EmployeeVO e = null;
		int count = jsonObject.get("count").getAsInt();
		ArrayList<EmployeeVO> list = new ArrayList<EmployeeVO>();
		for(int i = 0 ; i<count ; i++) {
			element = jsonArray.get(i);
			object = element.getAsJsonObject();
			e = new EmployeeVO();
			e.setId(object.get("id").getAsInt());
			e.setName(object.get("name").getAsString());
			e.setDept(object.get("dept").getAsString());
			e.setTel(object.get("tel").getAsString());
			e.setEmail(object.get("email").getAsString());
			list.add(e);
			System.out.println(e);
		}
		empVO.setCount(count);
		empVO.setList(list);
		return empVO;
	}

}
