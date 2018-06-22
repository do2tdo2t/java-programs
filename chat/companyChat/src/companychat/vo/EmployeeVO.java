package companychat.vo;

import companychat.util.Constant;

public class EmployeeVO {
	int type = Constant.EMP;
	int id;
	String name;
	int deptId; 
	String dept; 
	String email;
	String tel;
	
	public EmployeeVO() {
		
	}

	
	public EmployeeVO(int id) {
		this.id = id;
		this.name = "가";
		this.deptId = 152354;
		this.dept = "영업부";
		this.email = "aaa@naver.com";
		this.tel = "010-5958-8888";
	}
	

	public EmployeeVO(int id, String name) {
		this.id = id;
		this.name = name;
		this.deptId = 152354;
		this.dept = "영업부";
		this.email = "aaa@naver.com";
		this.tel = "010-5958-8888";
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
	public int getDeptId() {
		return deptId;
	}
	public void setDeptId(int deptId) {
		this.deptId = deptId;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
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
		return "EmployeeVO [id=" + id + ", name=" + name + ", deptId=" + deptId + ", dept=" + dept + ", email=" + email
				+ ", tel=" + tel + "]";
	}
	
}
