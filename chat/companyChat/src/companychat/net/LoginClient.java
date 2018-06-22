package companychat.net;

import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.EventObject;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import companychat.io.Reader;
import companychat.io.Writer;
import companychat.parser.EmployeeParser;
import companychat.util.ChatDialog;
import companychat.util.Constant;
import companychat.util.Json;
import companychat.view.LoginFrame;
import companychat.vo.EmployeeVO;
import companychat.vo.LoginVO;

public class LoginClient extends LoginFrame implements Runnable{
	EmployeeVO user = null;
	Socket server = null;
	Writer writer = null;
	Reader reader = null;
	Json json = null;
	
	public static void main(String args[]) {
		new LoginClient();
	
	}
	
	public LoginClient() {
		Thread thread = new Thread(this);
		thread.start();
	}

	@Override
	public void run() {
		while(true) {
			//서버 연결 시도
			connectServer();
			
			//연결 성공 로그인 정보 받아오기
			String msg = reader.read();
			JsonObject jsonObject= json.toJsonObject(msg);
			int type = json.getInt(jsonObject , "type");
			
			if(type == Constant.EMP) {
				EmployeeVO user = EmployeeParser.parse(jsonObject);
				new ChatClient(user, server, writer, reader, json);
				System.out.println(user);
				//ClientChat 객체 생성 후 Thread로 실행 정보 넘겨 주기
				new ChatDialog("로그인 되었습니다.");
			}
			
		}
	}

	//서버 연결, 객체 초기화
	public void connectServer() {
		try {
			InetAddress ia = InetAddress.getByName(Constant.serverIP);
			//서버 연결 시도
			server = new Socket(ia,Constant.serverPort);
			System.out.println("연결 성공 !");
			
			//reader, wirter 만들기
			writer = new Writer(server);
			reader = new Reader(server);
			json = new Json();
			
			setVisible(true);
		}catch(UnknownHostException e){
			e.printStackTrace();
		}catch(IOException e2) {
			e2.printStackTrace();
		}
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getButton() == 1 && idTf.getText()!="" && pwTf.getText()!="") {
			int id = Integer.parseInt(idTf.getText());
			String pw = pwTf.getText();
			InetAddress ia;
			try {
				ia = InetAddress.getLocalHost();
				sendLoginReq(id, pw,ia.getHostName());
			} catch (UnknownHostException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
	}
	
	void sendLoginReq(int id, String pw,String inetAddress) {
		Gson gson = new Gson();
		LoginVO loginInfo = new LoginVO(id,pw,inetAddress);
		//json string으로 바꿔서 전송하기
		writer.write(gson.toJson(loginInfo));
	}

	
}
