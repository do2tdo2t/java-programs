package companychat.service;

import java.net.Socket;
import java.util.Collection;
import java.util.HashMap;

import com.google.gson.JsonObject;

import companychat.dto.EmployeeDTO;
import companychat.dto.LoginDTO;
import companychat.io.MessageReader;
import companychat.io.MessageWriter;
import companychat.io.Reader;
import companychat.io.Writer;
import companychat.parser.LoginParser;
import companychat.parser.LogoutParser;
import companychat.parser.MessageParser;
import companychat.parser.RoomParser;
import companychat.util.Constant;
import companychat.util.Json;
import companychat.vo.EmployeeVO;
import companychat.vo.EmployeesVO;
import companychat.vo.LoginVO;
import companychat.vo.MessageVO;
import companychat.vo.RoomVO;

//��� ����
public class ClientService implements Runnable {
	boolean mIsLogin = false;
	int userId;
	
	EmployeeVO user = null;
	public Writer writer = null;
	public Reader reader = null;
	Json json = null;
	private Socket clientSocket = null;
	HashMap<String, ClientService> clientMap = new HashMap<String,ClientService>();
	Thread thread = null;
	boolean flag = false;
	
	public ClientService(Socket clientSocket,HashMap<String, ClientService> clientMap ){
		this.clientMap = clientMap;
		this.clientSocket = clientSocket;
		writer = new Writer(clientSocket);
		reader = new Reader(clientSocket);
		json = new Json();
		flag = true;
		thread = new Thread(this);
		thread.start();
	}
	
	public boolean isLogin() {
		return mIsLogin;
	}
	public int getUserId() {
		return userId;
	}
	
	public 	void addClientMap(String inetAddress, ClientService service) {
		log("���� ������ ����� �ֽ��ϴ�.");
		clientMap.put(inetAddress, service);
	}

	@Override
	public void run() {
		while(flag) {
			String resp = reader.read();
			log("�޽����� �޾ҽ��ϴ�. "+resp);
			if(resp !=null) {
				JsonObject jsonObject= json.toJsonObject(resp);
				int type = json.getInt(jsonObject, "type");
				if(type == Constant.LOGIN) {
					doLogin(LoginParser.parse(jsonObject));
				}else if(type == Constant.EMPS && mIsLogin) {
					sendEmployeesInfo();
				}else if(type == Constant.MSG && mIsLogin) {
					//�޽��� ����
					MessageVO msg = MessageParser.parse(jsonObject);
					sendMessageToReceiver(msg);
				}else if(type == Constant.ROOM && mIsLogin) {
					sendBeforeChatData(RoomParser.parse(jsonObject));
				}else if(type == Constant.LOGOUT) {
					int id = LogoutParser.parse(jsonObject);
					doLogout(id);
				}
			}
		}
	}
	
	void doLogin(LoginVO loginInfo) {
		//DB����Ȯ��
		LoginDTO loginDto = new LoginDTO();
		if(loginDto.doLogin(loginInfo)) {
			mIsLogin = true;
			//������� ���� �޾ƿͼ� �����ֱ�
			EmployeeDTO empDTO = new EmployeeDTO();
			user = empDTO.getUserInfo(loginInfo.getId());
			userId = user.getId();
			//�α��� �� ���� ���� ����
			writer.write(user);
		}
	}
	
	void sendEmployeesInfo() {
		EmployeeDTO dto = new EmployeeDTO();
		EmployeesVO emps = dto.getAllEmployeeInfo();
		writer.write(emps);
	}

	void sendMessageToReceiver(MessageVO message) {
		Collection<ClientService> clients = clientMap.values();
		for(ClientService client : clients) {
			if(client.isLogin() && client.getUserId() == message.getReceiver()) {
				log("client�� ã�ҽ��ϴ� ! "+ client.getUserId());
				client.writer.write(message);
				return ;
			}
		}
		//�α����� ���°� �ƴҶ�
		writeMessageAtFile(message);
	}
	
	void writeMessageAtFile(MessageVO message) {
		MessageWriter.write(message);
	}
	
	void sendBeforeChatData(RoomVO roomVO) {
		String roomInfo = MessageReader.read(roomVO.getUser(), roomVO.getRecv());
		writer.write(roomInfo);
	}
	
	void doLogout(int id) {
		log("�α׾ƿ� �մϴ�.");
		LoginDTO loginDto = new LoginDTO();
		loginDto.doLogout(id); // DB���� ����
		mIsLogin =false;
		flag = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	void log(String str) {
		if(user!=null) {
			System.out.println("ClientService"+user.getName()+"..."+str);
		}else {
			System.out.println("ClientService..."+str);
		}
	}
}
