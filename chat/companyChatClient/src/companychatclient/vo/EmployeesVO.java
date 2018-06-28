package companychatclient.vo;

import java.util.ArrayList;

import companychatclient.util.Constant;

public class EmployeesVO {
	int type = Constant.EMPS;
	int count =0;
	
	ArrayList<EmployeeVO> list = new ArrayList<EmployeeVO>();
	
	public int getType() {
		return type;
	}
	
	public void setType(int type) {
		this.type = type;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public ArrayList<EmployeeVO> getList() {
		return list;
	}
	public void setList(ArrayList<EmployeeVO> list) {
		this.list = list;
	}
	
	public void add(EmployeeVO e) {
		list.add(e);
		count = list.size();
	}
	
	public EmployeeVO get(int i) {
		return list.get(i);
	}
}
