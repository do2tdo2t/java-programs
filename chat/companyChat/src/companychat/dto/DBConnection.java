package companychat.dto;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBConnection {
	//jdbc:oracle:thin@ip:port(1521), SID 전역변수(GOOTTDB)
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String user = "system";
	String pw = "tiger";
	Connection conn = null;
	PreparedStatement psmt = null;
	ResultSet rs = null;
	
	//생성자보다 먼저 실행, 멤버영역에서 실행문을 표기할 수 있다.
	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public Connection getConnection() {
		try {
			//DB 연결
			conn = DriverManager.getConnection(url,user,pw);
		}catch(SQLException e2) {
		e2.printStackTrace();
		}
		return conn;
	}
	
	public void dbClose() {
		try {
			if(rs!=null) if(!rs.isClosed()) rs.close();
			if(psmt!=null) if(!psmt.isClosed()) psmt.close();
			if(conn!=null) if(!conn.isClosed()) conn.close();
		}catch(SQLException e) {
			
			e.printStackTrace();
		}
	}

}
