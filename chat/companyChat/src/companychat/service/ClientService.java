package companychat.service;

import java.net.Socket;
import java.util.HashMap;

import com.google.gson.JsonObject;

import companychat.dto.EmployeeDTO;
import companychat.dto.LoginDTO;
import companychat.io.Reader;
import companychat.io.Writer;
import companychat.net.ChatServer;
import companychat.parser.LoginParser;
import companychat.parser.MessageParser;
import companychat.util.Constant;
import companychat.util.Json;
import companychat.vo.EmployeeVO;
import companychat.vo.LoginVO;
import companychat.vo.MessageVO;

//��� ����
public class ClientService implements Runnable {
	boolean isEmployee = false;
	EmployeeVO user = null;
	public Writer writer = null;
	public Reader reader = null;
	Json json = null;
	private Socket clientSocket = null;
	HashMap<String, ClientService> clientMap = new HashMap<String,ClientService>();
	
	public ClientService(Socket clientSocket){
		this.clientSocket = clientSocket;
		writer = new Writer(clientSocket);
		reader = new Reader(clientSocket);
		json = new Json();
		Thread thread = new Thread(this);
		thread.start();
	}
	
	public 	void addClientMap(String inetAddress, ClientService service) {
		clientMap.put(inetAddress, service);
	}

	@Override
	public void run() {
		while(true) {
			log("�޽����� �޾ҽ��ϴ�.");
			String msg = reader.read();
			JsonObject jsonObject= json.toJsonObject(msg);
			int type = json.getInt(jsonObject, "type");
			if(type == Constant.LOGIN) {
				doLogin(LoginParser.parse(jsonObject));
			}else if(type == Constant.EMPS && isEmployee) {
				//���� ó��
				
			}else if(type == Constant.MSG && isEmployee) {
				//��뿡�� ����
				//json parsing
				MessageVO message = MessageParser.parse(jsonObject);
				message.getReceiver();
				
			}else if(type == Constant.MSGS && isEmployee) {
				
			}//else if(type == Constant.LOGOUT && isEmployee) {
				
			//}
		}
	}
	
	void doLogin(LoginVO loginInfo) {
		System.out.println("server... login ��û ����");
		//DB����Ȯ��
		LoginDTO loginDto = new LoginDTO();
		if(loginDto.doLogin(loginInfo)) {
			isEmployee = true;
			//������� ���� �޾ƿͼ� �����ֱ�
			EmployeeDTO empDTO = new EmployeeDTO();
			EmployeeVO user = empDTO.getUserInfo(loginInfo.getId());
			//�α��� �� ���� ���� ����
			writer.write(user);
		}
	}
	
	void log(String str) {
		System.out.println("ClientService..."+str);
	}
}
