package companychat.net;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import companychat.dto.EmployeeDTO;
import companychat.dto.LoginDTO;
import companychat.service.ClientService;
import companychat.util.Constant;
import companychat.vo.EmployeesVO;

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
			
		} catch (IOException e) {
			e.printStackTrace();
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
				
				
			} catch (IOException e) {
				System.out.println("Error. ChatServer : run()");
				e.printStackTrace();
			}
		}
	}
	
	void log(String str) {
		System.out.println("ChatServer..."+str);
	}

}
