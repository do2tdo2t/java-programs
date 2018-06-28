

import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import companychatclient.io.Reader;
import companychatclient.io.Writer;
import companychatclient.net.ChatClient;
import companychatclient.parser.EmployeeParser;
import companychatclient.util.Constant;
import companychatclient.util.Json;
import companychatclient.view.ChatDialog;
import companychatclient.view.LoginFrame;
import companychatclient.vo.EmployeeVO;
import companychatclient.vo.LoginVO;
import companychatclient.vo.LogoutVO;

public class LoginClient extends LoginFrame implements Runnable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	EmployeeVO user = null;
	Socket server = null;
	Writer writer = null;
	Reader reader = null;
	Json json = null;
	boolean flag = false;
	Thread thread = null;
	public static void main(String args[]) {
		new LoginClient();
	
	}
	
	public LoginClient() {
		connectServer();
		thread = new Thread(this);
		flag = true;
		thread.start();
	}

	@Override
	public void run() {
		
		while(flag) {
			try {
				//서버 연결 시도
				//연결 성공 로그인 정보 받아오기
				if(!server.isClosed()) {
					String msg = reader.read();
					log("메시지를 받았습니다. "+msg);
					if(msg!= null && msg != "") {
						JsonObject jsonObject= json.toJsonObject(msg);
						int type = json.getInt(jsonObject , "type");
						
						if(type == Constant.EMP) {
							EmployeeVO user = EmployeeParser.parse(jsonObject);
							setVisible(false);
							new ChatClient(user, server, writer, reader, json);
							new ChatDialog("로그인 되었습니다.","로그인 성공");		
					//ClientChat 객체 생성 후 Thread로 실행 정보 넘겨 주기
							break;
						}else if(type==Constant.LOGIN) {
							new ChatDialog("로그인 실패 했습니다.","로그인 실패");
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
		if(e.getButton() == 1 ) {

			if(idTf.getText().trim()!="" && pwTf.getText().trim()!="") {
				int id = Integer.parseInt(idTf.getText());
				String pw = pwTf.getText();
				InetAddress ia;
				try {
					ia = InetAddress.getLocalHost();
					sendLoginReq(id, pw,ia.toString().split("/")[1]);
				} catch (UnknownHostException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
		}
	}
	
	void sendLoginReq(int id, String pw,String inetAddress) {
		Gson gson = new Gson();
		LoginVO loginInfo = new LoginVO(id,pw,inetAddress);
		log("send login req : "+gson.toJson(loginInfo));
		writer.write(gson.toJson(loginInfo));
	}

	@Override
	public void windowClosed(WindowEvent e) {
		log("종료합니다.");
		LogoutVO logoutVO = new LogoutVO();
		logoutVO.setId(-1);
		flag = false;
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e2) {
			e2.printStackTrace();
		}
		
		thread.interrupt();
		writer.write(logoutVO);
		
		if(server!= null && !server.isClosed()) {
			try {
				server.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	void log(String str) {
		System.out.println("LoginClient..."+str);
	}
	
}
