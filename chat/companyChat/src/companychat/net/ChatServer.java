package companychat.net;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collection;
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
		int i = 0;
		
		while(true) {
			try {
				socket = serverSocket.accept();
				String inetAddress = socket.getInetAddress().toString()+i;
				//아이피가 같아서 Map에 넣으면 최신걸로만 업데이트 됨. 나중에 들어온 클라이언트는 다른 클라이언트들 정보를 알수 X
				i++;
				
				if(inetAddress== null) log("inetAddress null");
				
				service = new ClientService(socket,clientMap);
				clientMap.put(inetAddress,service);
				
				addClientMapBroadcast(inetAddress,service);
				
			} catch (IOException e) {
				System.out.println("Error. ChatServer : run()");
				e.printStackTrace();
			}
		}
	}
	
	//로그인 정보 갱신 요청
	public void addClientMapBroadcast(String inetAddress, ClientService clientService) {
		   Collection<ClientService> clientList = (Collection<ClientService>) clientMap.values();
		   if(clientList.size()==0) log("clientList null");
		   for(ClientService cs : clientList) { 
				cs.addClientMap(inetAddress,clientService);
		}
	}
	
	void log(String str) {
		System.out.println("ChatServer..."+str);
	}

}
