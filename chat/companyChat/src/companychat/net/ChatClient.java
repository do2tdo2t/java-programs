package companychat.net;

import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.Socket;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import companychat.io.Reader;
import companychat.io.Writer;
import companychat.parser.EmployeeParser;
import companychat.parser.EmployeesParser;
import companychat.parser.MessageParser;
import companychat.parser.RoomParser;
import companychat.util.Constant;
import companychat.util.Json;
import companychat.view.ChatFrame;
import companychat.vo.EmployeeVO;
import companychat.vo.EmployeesVO;
import companychat.vo.LogoutVO;
import companychat.vo.MessageVO;
import companychat.vo.RoomVO;

//로그인 성공 후
//view actionPerformed
//요청 쓰레드 생성
//client 요청 -> 응답
//응답 

public class ChatClient extends ChatFrame implements Runnable{
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
		//채팅방(Room)열기 이벤트 발생
		if(e.getButton() == 1 && e.getClickCount() > 1 && e.getSource().equals(jTree)) {
			super.mouseClicked(e);
		}else if(e.getButton() == 1 && sendBtn.equals(e.getSource())) {
			//메시지 전송 실행. 1. 메시지 객체 생성(inputTa에서 읽어 들임) 2. 메시지 writer로 전송 3.inputlbl 갱신
			MessageVO msg = getMessageInfo();
			updateTextArea(msg.getContent());
			writer.write(msg);
		}	
	}
	

	@Override
	//응답 처리
	public void run() {
		while(flag) {
			String msg = reader.read();
			log(" 메시지를 받았습니다. "+msg);
			if(json == null) json = new Json();
				JsonObject jsonObject= json.toJsonObject(msg);
				int type = json.getInt(jsonObject , "type");
				if(type == Constant.MSG) {
					whenRecvMessageFromSender(MessageParser.parse(jsonObject));
				}else if(type == Constant.ROOM) {
					RoomVO msgs = RoomParser.parse(jsonObject);
				}else if(type == Constant.EMPS) {
					whenRecvEmployeeInfoFromServer(EmployeesParser.parse(jsonObject));
				}else if(type == Constant.EMP) {
					EmployeeVO emp = EmployeeParser.parse(jsonObject);
				}
			
		}
	}
	
	void whenRecvMessageFromSender(MessageVO message) {
		if(curRoom.getRecvId()==message.getSender()) {
			log(curRoom.getRecvInfo()+" update ui...");
			updateTextArea(message);
		}
	}
	
	void whenRecvEmployeeInfoFromServer(EmployeesVO emps) {
		//화면 업데이트
		initEmpTree(emps, user);
		setVisible(true);
	}
	
	void log(String str) {
		if(user==null) {
			System.out.println("ChatClient ..."+str);
		}else {
			System.out.println("ChatClient "+user.getName()+"..."+str);
		}
	}


	public void windowClosing(WindowEvent e) {
		log("종료 이벤트 발생");
		LogoutVO logoutVO = new LogoutVO(user.getId());
		writer.write(logoutVO);
		log(logoutVO.toString());
		try {
			server.close();
			flag = false;
			thread.join();
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}finally {
			System.exit(0);
		}
		
	}
	
	
}
