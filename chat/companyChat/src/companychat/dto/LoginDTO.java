package companychat.dto;

import java.sql.SQLException;

import companychat.vo.LoginVO;

public class LoginDTO extends DBConnection {
	private final String ID = "DBConnection...";
	
	private String updateSQL = "insert into login values (?,1,?)";
	private String deleteSQL = "delete from login where id = ?";
	private String deleteAllSQL = "delete from login";

	public boolean doLogin(LoginVO loginInfo) {
		String selectSQL = "select * from employee where id = ? and pw = ?";
		int id= loginInfo.getId();
		String pw =loginInfo.getPw();
		log("id = "+id+", pw = "+pw);
		String inetAddress = loginInfo.getInetAddress();
		dbClose();
		getConnection();
		try {
			psmt = conn.prepareStatement(selectSQL);
			psmt.setInt(1, id);
			psmt.setString(2, pw);
			rs = psmt.executeQuery();
			if(!rs.next()) {
				dbClose();
				return false;
			}
			
			psmt = conn.prepareStatement(updateSQL);
			psmt.setInt(1, id);
			psmt.setString(2, inetAddress);
			int cnt = psmt.executeUpdate();
			if(cnt <=0) {
				dbClose();
				return false;
			}
			
			log(ID+"db에 로그인 성공");
			dbClose();
			return true;
		}catch(SQLException e) {
			e.printStackTrace();
			dbClose();
			return false;
		}
	}
	
	public boolean doLogout(int id) {
		dbClose();
		getConnection();
		try {
			psmt = conn.prepareStatement(deleteSQL);
			psmt.setInt(1, id);
			int cnt = psmt.executeUpdate();
			if(cnt <=0) {
				dbClose();
				return false;
			}
			log(ID+" 로그아웃 성공");
			dbClose();
			return true;
		}catch(SQLException e) {
			e.printStackTrace();
			dbClose();
			return false;
		}
	}
	
	public boolean doAllLogout() {
		dbClose();
		getConnection();
		try {
			psmt = conn.prepareStatement(deleteAllSQL);
			int cnt = psmt.executeUpdate();
			if(cnt <=0) {
				dbClose();
				return false;
			}
			log("모든 유저 로그아웃 성공");
			dbClose();
			return true;
		}catch(SQLException e) {
			e.printStackTrace();
			dbClose();
			return false;
		}
	}
	
	public String getLoginInfo(int id){
		dbClose();
		String selectSQL = "select * from login where id = ?";
		getConnection();
		String inetAddress = null;
		try {
			psmt = conn.prepareStatement(selectSQL);
			psmt.setInt(1, id);
			rs = psmt.executeQuery();
			if(!rs.next()) {
				dbClose();
				return null;
			}
			inetAddress = rs.getString(3);
			dbClose();
			return inetAddress;
		}catch(SQLException e) {
			e.printStackTrace();
			dbClose();
			return null;
		}
	}
	
	void log(String str) {
		System.out.println("LoginDTO..."+str);
	}

}
