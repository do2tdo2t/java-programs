

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;

import companychatserver.dto.EmployeeDTO;
import companychatserver.dto.LoginDTO;
import companychatserver.util.Constant;
import companychatserver.vo.EmployeesVO;

public class ChatServer implements Runnable{
	EmployeesVO emps = null;
	ServerSocket serverSocket = null;
	ClientService service = null;
	Socket socket = null;
	//HashMap<String, ClientService> clientMap = new HashMap<String,ClientService>();
	public static ArrayList<ClientService> clientList = new ArrayList<ClientService>();
	Thread thread = null;
	
	public static void main(String[] args) {
		new ChatServer();
	}
	
	public ChatServer() {
		try {
			serverSocket = new ServerSocket(Constant.serverPort);
			//���� �ʱ�ȭ �α���
			initEmployeeInfo();
			
			//thread ����
			Thread thread = new Thread(this);
			thread.start();
			
		}catch (IOException e2) {
			
			e2.printStackTrace();
		}
	}
	
	void initEmployeeInfo() {
		EmployeeDTO empDto = new EmployeeDTO();
		emps = empDto.getAllEmployeeInfo();
		deleteLoginLog();
	}
	
	void deleteLoginLog() {
		LoginDTO loginDto = new LoginDTO();
		loginDto.doAllLogout();
	}

	@Override
	public void run(){
		while(true) {
			try {
				socket = serverSocket.accept();
				
				//�����ǰ� ���Ƽ� Map�� ������ �ֽŰɷθ� ������Ʈ ��. ���߿� ���� Ŭ���̾�Ʈ�� �ٸ� Ŭ���̾�Ʈ�� ������ �˼� X
				service = new ClientService(socket);
				clientList.add(service);
				
				
			} catch (SocketException e) {
				e.printStackTrace();
			}catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	void log(String str) {
		System.out.println("ChatServer..."+str);
	}

}
