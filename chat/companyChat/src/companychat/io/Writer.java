package companychat.io;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import com.google.gson.Gson;

import companychat.vo.EmployeeVO;
import companychat.vo.EmployeesVO;
import companychat.vo.LoginVO;
import companychat.vo.LogoutVO;
import companychat.vo.MessageVO;
import companychat.vo.RoomVO;

/*�ѹ� �������*/
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
		write(gson.toJson(emp));
	}
	
	public void write(EmployeesVO emps) {
		write(gson.toJson(emps));
	}
	
	public void write(RoomVO room) {
		write(gson.toJson(room));
	}
	
	
	public void write(LoginVO login) {
		write(gson.toJson(login));
	}
	
	public void write(LogoutVO logout) {
		write(gson.toJson(logout));
	}
	
	public void write(MessageVO msg) {
		write(gson.toJson(msg));
	}
	
	public void write(String msg){
		//System.out.println("Witer ������ ��û...");
		//message ��ü�� json ��Ʈ������ �����ؼ� ������ ����.
		try {
			if(msg!=null) {
				pw.println(msg);
				pw.flush();
			}
		}catch(Exception e) {
			
		}
	}
	

}
