package companychatclient.io;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

import com.google.gson.Gson;

import companychatclient.vo.EmployeeVO;
import companychatclient.vo.EmployeesVO;
import companychatclient.vo.LoginVO;
import companychatclient.vo.LogoutVO;
import companychatclient.vo.MessageVO;
import companychatclient.vo.RoomVO;

/*한번 만들어짐*/
public final class Writer {
	PrintWriter pw = null;
	Gson gson = new Gson();
	
	public Writer(Socket socket) {
		try {
		pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())));
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void write(EmployeeVO emp) {
		log(emp.toString());
		write(gson.toJson(emp));
	}
	
	public void write(EmployeesVO emps) {
		log(emps.toString());
		write(gson.toJson(emps));
	}
	
	public void write(RoomVO room) {
		log(room.toString());
		write(gson.toJson(room));
	}
	
	
	public void write(LoginVO login) {
		log(login.toString());
		write(gson.toJson(login));
	}
	
	public void write(LogoutVO logout) {
		log(logout.toString());
		write(gson.toJson(logout));
	}
	
	public void write(MessageVO msg) {
		log(msg.toString());
		write(gson.toJson(msg));
	}
	
	public void write(String msg) {
		//System.out.println("Witer 서버로 요청...");
		//message 객체를 json 스트링으로 변경해서 서버에 보냄.
		try {
			if(msg!=null) {
				pw.println(msg);
				pw.flush();
			}
		}catch(Exception e) {
			
		}
	}
	void log(String str) {
		System.out.println("Writer..."+str);
	}
	

}
