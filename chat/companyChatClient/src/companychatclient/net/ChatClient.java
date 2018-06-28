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
	EmployeeVO user; //로그인 한 사람.
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
		
		
		//직원 초기화 메시지 전송
		EmployeesVO emps = new EmployeesVO();
		writer.write(emps);
	}

		
	@Override
	public void mouseClicked(MouseEvent e) {
		String recvInfo = "";
		//채팅방(Room)열기 이벤트 발생
		if(e.getButton() == 1 && e.getClickCount() > 1 && e.getSource().equals(empTree)) {
			recvInfo = empTree.getSelectedNodeInfo();
			System.out.println(recvInfo);
			if(!"직원".equals(recvInfo)) {
	         	if(!rooms.containsKey(recvInfo)) {
	         		sendReqRoomInfoToServer(recvInfo);
	         	}else {
	         		loadRoom(recvInfo);
	         	}
			}
		}else if(e.getButton() == 1 && sendBtn.equals(e.getSource())) {
			//메시지 전송 실행. 1. 메시지 객체 생성(inputTa에서 읽어 들임) 2. 메시지 writer로 전송 3.inputlbl 갱신
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
	//응답 처리
	public void run() {
		try {
		while(flag) {
			String msg = reader.read();
			log(" 메시지를 받았습니다. "+msg);
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
			new ChatDialog("서버 접속에 실패 했습니다.","서버 접속 실패");
			System.exit(0);
		}catch(IOException e3) {
			thread.interrupt();
			new ChatDialog("서버 접속에 실패 했습니다.","서버 접속 실패");
			System.exit(0);
		}
	}
	
	void whenRecvMessageFromSender(MessageVO message) {
		log(message.getRecvName()+" update ui...");
		updateTextArea(message);
		
	}
	
	void whenRecvEmployeeInfoFromServer(EmployeesVO emps) {
		//화면 업데이트
		initEmpTree(emps, user);
		setVisible(true);
	}

	public void windowClosed(WindowEvent e) {
		log("종료 이벤트 발생");
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
