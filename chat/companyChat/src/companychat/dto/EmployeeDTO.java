package companychat.dto;

import java.sql.SQLException;

import companychat.vo.EmployeeVO;
import companychat.vo.EmployeesVO;

public class EmployeeDTO extends DBConnection {
	private String selectSQL = "select * from employee where id = ?" ;
	private String selectAllSQL = "select * from employee" ;
	//로그인 후 유저 정보 리턴
	public EmployeeVO getUserInfo(int id) {
		EmployeeVO emp = null;
		getConnection();
		try {
			emp = new EmployeeVO();
			psmt = conn.prepareStatement(selectSQL);
			psmt.setInt(1, id);
			rs = psmt.executeQuery();
			if(!rs.next()) {
				dbClose();
				return null;
			}
			emp.setId(id);
			emp.setName(rs.getString(2));
			emp.setDeptId(rs.getInt(3));
			emp.setEmail(rs.getString(4));
			emp.setTel(rs.getString(5));
			System.out.println(emp);
			dbClose();
			return emp;
		}catch(SQLException e) {
			dbClose();
			e.printStackTrace();
			return null;
		}
	}
	
	//사원 정보 리턴
	public EmployeesVO getAllEmployeeInfo() {
		int count = 0;
		EmployeesVO emps = null;
		EmployeeVO emp = null;
		getConnection();
		
		try {
			psmt = conn.prepareStatement(selectAllSQL);	
			rs = psmt.executeQuery();
			emps = new EmployeesVO();
			while(rs.next()) {
				emp = new EmployeeVO();
				emp.setId(rs.getInt(1));
				emp.setName(rs.getString(2));
				emp.setDeptId(rs.getInt(3));
				emp.setEmail(rs.getString(4));
				emp.setTel(rs.getString(5));
				emps.add(emp);
			}
			
			System.out.println(emps.getCount());
			dbClose();
			return emps;
		}catch(SQLException e) {
			dbClose();
			e.printStackTrace();
			return null;
		}
		
	}

}
