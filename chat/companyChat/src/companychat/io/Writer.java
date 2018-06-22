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
import companychat.vo.MessageVO;

/*�ѹ� �������*/
public final class Writer {
	PrintWriter pw = null;
	Gson gson = new Gson();
	
	public Writer(Socket server) {
		try {
		pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(server.getOutputStream())));
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
	
	public void write(LoginVO login) {
		write(gson.toJson(login));
	}
	
	public void write(MessageVO msg) {
		write(gson.toJson(msg));
	}
	
	public void write(String msg) {
		//message ��ü�� json ��Ʈ������ �����ؼ� ������ ����.
		pw.print(msg);
		pw.flush();
	}
	

}
