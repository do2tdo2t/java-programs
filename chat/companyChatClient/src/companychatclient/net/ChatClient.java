package companychatclient.net;

import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import companychatclient.io.Reader;
import companychatclient.io.Writer;
import companychatclient.parser.EmployeesParser;
import companychatclient.parser.MessageParser;
import companychatclient.parser.RoomParser;
import companychatclient.util.Constant;
import companychatclient.util.Json;
import companychatclient.view.ChatDialog;
import companychatclient.view.ChatFrame;
import companychatclient.vo.EmployeeVO;
import companychatclient.vo.EmployeesVO;
import companychatclient.vo.LogoutVO;
import companychatclient.vo.MessageVO;
import companychatclient.vo.RoomVO;


public class ChatClient extends ChatFrame implements Runnable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	EmployeeVO user; //�α��� �� ���.
	Socket server = null;
	Writer writer = null;
	Reader reader = null;
	Json json = null;
	Thread thread = null;
	Gson gson = new Gson();
	boolean flag = false;

	public ChatClient(EmployeeVO user, Socket server, Writer writer, Reader reader, Json json){
		this.user = user;
		this.server = server;
		this.writer =writer;
		this.reader = reader;
		this.json = json;

		thread = new Thread(this);
		flag = true;
		thread.start();
		
		
		//���� �ʱ�ȭ �޽��� ����
		EmployeesVO emps = new EmployeesVO();
		writer.write(emps);
	}

		
	@Override
	public void mouseClicked(MouseEvent e) {
		String recvInfo = "";
		//ä�ù�(Room)���� �̺�Ʈ �߻�
		if(e.getButton() == 1 && e.getClickCount() > 1 && e.getSource().equals(empTree)) {
			recvInfo = empTree.getSelectedNodeInfo();
			System.out.println(recvInfo);
			if(!"����".equals(recvInfo)) {
	         	if(!rooms.containsKey(recvInfo)) {
	         		sendReqRoomInfoToServer(recvInfo);
	         	}else {
	         		loadRoom(recvInfo);
	         	}
			}
		}else if(e.getButton() == 1 && sendBtn.equals(e.getSource())) {
			//�޽��� ���� ����. 1. �޽��� ��ü ����(inputTa���� �о� ����) 2. �޽��� writer�� ���� 3.inputlbl ����
			MessageVO msg = getMessageInfo();
			updateTextArea(msg.getContent());
			writer.write(msg);
		}else if(e.getButton() == 3 && e.getSource().equals(empTree)) {
       
		}else if(e.getButton()==1 ) {
			log(e.getComponent().toString());
		}
	}
	
	void sendReqRoomInfoToServer(String recvInfo) {
		RoomVO roomVO = new RoomVO(user.getName()+'/'+user.getId(), recvInfo);
		writer.write(roomVO);
	}
	

	@Override
	//���� ó��
	public void run() {
		try {
		while(flag) {
			String msg = reader.read();
			log(" �޽����� �޾ҽ��ϴ�. "+msg);
			if(json == null) json = new Json();
				if(msg!=null) {
				JsonObject jsonObject= json.toJsonObject(msg);
					int type = json.getInt(jsonObject , "type");
					if(type == Constant.MSG) {
						whenRecvMessageFromSender(MessageParser.parse(jsonObject));
					}else if(type == Constant.ROOM) {
						RoomVO roomVO = RoomParser.parse(jsonObject);
						showRoom(roomVO);
					}else if(type == Constant.EMPS) {
						whenRecvEmployeeInfoFromServer(EmployeesParser.parse(jsonObject));
					}
				}
			}
		}catch(NullPointerException e1) {
			thread.interrupt();
		}catch(SocketException e2) {
			thread.interrupt();
			new ChatDialog("���� ���ӿ� ���� �߽��ϴ�.","���� ���� ����");
			System.exit(0);
		}catch(IOException e3) {
			thread.interrupt();
			new ChatDialog("���� ���ӿ� ���� �߽��ϴ�.","���� ���� ����");
			System.exit(0);
		}
	}
	
	void whenRecvMessageFromSender(MessageVO message) {
		log(message.getRecvName()+" update ui...");
		updateTextArea(message);
		
	}
	
	void whenRecvEmployeeInfoFromServer(EmployeesVO emps) {
		//ȭ�� ������Ʈ
		initEmpTree(emps, user);
		setVisible(true);
	}

	public void windowClosed(WindowEvent e) {
		log("���� �̺�Ʈ �߻�");
		LogoutVO logoutVO = new LogoutVO(user.getId());
		writer.write(logoutVO);
		
		
		try {
			flag = false;
			thread.interrupt();
			server.close();
		}catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}finally {
			System.exit(0);
		}
	}
	
	protected void log(String str) {
		if(user==null) {
			//System.out.println("ChatClient ..."+str);
		}else {
			//System.out.println("ChatClient "+user.getName()+"..."+str);
		}
	}


	
	
}
