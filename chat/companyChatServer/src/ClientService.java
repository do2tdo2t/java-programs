

import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;

import com.google.gson.JsonObject;
import companychatserver.dto.EmployeeDTO;
import companychatserver.dto.LoginDTO;
import companychatserver.io.MessageReader;
import companychatserver.io.MessageWriter;
import companychatserver.io.Reader;
import companychatserver.io.Writer;
import companychatserver.parser.LoginParser;
import companychatserver.parser.LogoutParser;
import companychatserver.parser.MessageParser;
import companychatserver.parser.RoomParser;
import companychatserver.util.Constant;
import companychatserver.util.Json;
import companychatserver.vo.EmployeeVO;
import companychatserver.vo.EmployeesVO;
import companychatserver.vo.LoginVO;
import companychatserver.vo.MessageVO;
import companychatserver.vo.RoomVO;

//��� ����
public class ClientService implements Runnable {
	boolean mIsLogin = false;
	int userId;
	
	EmployeeVO user = null;
	public Writer writer = null;
	public Reader reader = null;
	Json json = null;

	//HashMap<String, ClientService> clientMap = new HashMap<String,ClientService>();
	Thread thread = null;
	boolean flag = false;
	
	public ClientService(Socket clientSocket){
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
	

	@Override
	public void run() {
		try {
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
						writeMessageAtMyFile(msg);
					}else if(type == Constant.ROOM && mIsLogin) {
						sendBeforeChatData(RoomParser.parse(jsonObject));
						
					}else if(type == Constant.LOGOUT) {
						int id = LogoutParser.parse(jsonObject);
						doLogout(id);
					}
				}
			}
		}catch(NullPointerException e) {
			e.printStackTrace();
			thread.interrupt();
		}catch(SocketException e1) {
			e1.printStackTrace();
			thread.interrupt();
			ChatServer.clientList.remove(this);
			System.exit(0);
		}catch(IOException e2) {
			e2.printStackTrace();
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
		}else {
			LoginVO loginVO = new LoginVO();
			loginVO.setId(-1);
			writer.write(loginVO);
		}
	}
	
	void sendEmployeesInfo() {
		EmployeeDTO dto = new EmployeeDTO();
		EmployeesVO emps = dto.getAllEmployeeInfo();
		writer.write(emps);
	}

	void sendMessageToReceiver(MessageVO message) {
		ClientService client = null;
		int size = ChatServer.clientList.size(); 
		for(int i = 0 ; i<size  ;i++) {
			client = ChatServer.clientList.get(i);
			if(client.isLogin() && client.getUserId() == message.getReceiver()) {
				log("client�� ã�ҽ��ϴ� ! "+ client.getUserId());
				client.writer.write(message); 
				writeMessageAtRecvFile(message);
				return ;
			}
		}
		//�α����� ���°� �ƴҶ�
		writeMessageAtRecvFile(message);
	}
	
	void writeMessageAtRecvFile(MessageVO message) {
		//���� ������ ����
		MessageWriter.write(Integer.toString(message.getReceiver()),Integer.toString(message.getSender()),message);
	}
	void writeMessageAtMyFile(MessageVO message) {
		//���� ������ ����
		MessageWriter.write(Integer.toString(message.getSender()),Integer.toString(message.getReceiver()),message);
	}
	
	
	void sendBeforeChatData(RoomVO roomVO) {
		//Room ������
		ArrayList<MessageVO> messageList = MessageReader.read(roomVO.getUserId()+"", roomVO.getRecvId()+"");
		if(messageList !=null) {
			roomVO.setList(messageList);
		}
	    try {
			writer.write(roomVO);
	    }catch(Exception e) {
	    	
	    }
	}
	
	void doLogout(int id) {
		log("�α׾ƿ� �մϴ�.");
		user = null;
		writer = null;
		reader = null;
		json = null;
		
		try {Thread.sleep(1000);} catch (InterruptedException e) {}
		
		flag = false;
		if(thread.isAlive())thread.interrupt();
		
		LoginDTO loginDto = new LoginDTO();
		if(id !=-1) {
			loginDto.doLogout(id); // DB���� ����
		}
		ChatServer.clientList.remove(this);
		thread = null;
	}
	
	void log(String str) {
		if(user!=null) {
			System.out.println("ClientService"+user.getName()+"..."+str);
		}else {
			System.out.println("ClientService..."+str);
		}
	}
}
