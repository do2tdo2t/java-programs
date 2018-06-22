package companychat.net;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.EventObject;

import javax.swing.JFrame;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import companychat.io.Reader;
import companychat.io.Writer;
import companychat.parser.EmployeesParser;
import companychat.parser.MessageParser;
import companychat.parser.MessagesParser;
import companychat.parser.Parser;
import companychat.util.Constant;
import companychat.util.Json;
import companychat.view.ChatFrame;
import companychat.vo.EmployeeVO;
import companychat.vo.EmployeesVO;
import companychat.vo.MessageVO;
import companychat.vo.MessagesVO;

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
	
	public ChatClient(EmployeeVO user, Socket server, Writer writer, Reader reader, Json json){
		this.user = user;
		this.server = server;
		this.writer =writer;
		this.reader = reader;
		this.json = json;
		
		initFrame();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		thread = new Thread(this);
		thread.start();
		
		//직원 초기화 메시지 전송
		EmployeesVO emps = new EmployeesVO();
		writer.write(emps);
		
	}

	public void initFrame() {
		//test 코드
		EmployeesVO emps = new EmployeesVO();
		emps.add(new EmployeeVO(1));
		emps.add(new EmployeeVO(2));
		emps.add(new EmployeeVO(3));
		emps.add(new EmployeeVO(4));
		emps.add(new EmployeeVO(5));
		System.out.println(emps.getCount());
		initEmpTree(emps);
	}
		
	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getButton() == 1 && e.getClickCount() > 1) {
			super.mouseClicked(e);
		}
		
	}

	@Override
	//응답 처리
	public void run() {
		System.out.println("start thread");
		while(true) {
			// Reader 객체에 의해 읽힌 메시지가 msg에 담김
			//String msg = reader.read();
			//type : 도착한 메시지가 어떤 형식인지. JsonString -> Object로
			
			String msg = reader.read();
			if(json == null) json = new Json();
			JsonObject jsonObject= json.toJsonObject(msg);
			int type = json.getInt(jsonObject , "type");
			
			if(type == Constant.MSG) {
				//객체 얻음
				MessageVO message = MessageParser.parse(jsonObject);
				//화면 업데이트
				
				
			}else if(type == Constant.MSGS) {
				//객체 얻음
				MessagesVO msgs = MessagesParser.parse(jsonObject);
				//화면 업데이트
			}else if(type == Constant.EMPS) {
				//객체 얻음
				EmployeesVO emps = EmployeesParser.parse(jsonObject);
				//화면 업데이트
				
				setVisible(true);
			}
		}			
	}
}
