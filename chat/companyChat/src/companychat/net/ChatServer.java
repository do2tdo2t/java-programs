package companychat.net;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

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
	HashMap<String, ClientService> clientMap = new HashMap<String,ClientService>();
	Thread thread = null;
	
	public static void main(String[] args) {
		ChatServer cs  = new ChatServer();
	}
	
	public ChatServer() {
		try {
			serverSocket = new ServerSocket(Constant.serverPort);
			//정보 초기화 로그인
			initEmployeeInfo();
			
			//thread 시작
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
				String inetAddress = socket.getInetAddress().toString();
				log("connected ! at"+ inetAddress);
				//연결
				service = new ClientService(socket);
				clientMap.put(socket.getInetAddress().toString(),service);
			} catch (IOException e) {
				System.out.println("Error. ChatServer : run()");
				e.printStackTrace();
			}
		}
	}
	
	//로그인 정보 갱신 요청
	public void broadcast(String id, ClientService clientService) {
		   ArrayList<ClientService> clientList = (ArrayList<ClientService>) clientMap.values();
			for(ClientService user : clientList) {
				user.addClientMap(id,clientService);
		}
	}
	
	void log(String str) {
		System.out.println("ChatServer..."+str);
	}

}
