package companychatserver.vo;

import companychatserver.util.Constant;

public class EmployeeVO {
	int type = Constant.EMP;
	int id;
	String name;
	int dept; 
	String deptName; 
	String email;
	String tel;
	
	public EmployeeVO() {
		
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getDept() {
		return dept;
	}
	public void setDept(int dept) {
		this.dept = dept;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	

	@Override
	public String toString() {
		return "EmployeeVO [id=" + id + ", name=" + name + ", deptId=" + dept + ", dept=" + deptName + ", email=" + email
				+ ", tel=" + tel + "]";
	}
	
}
