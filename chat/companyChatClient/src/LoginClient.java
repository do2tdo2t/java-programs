

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
				//���� ���� �õ�
				//���� ���� �α��� ���� �޾ƿ���
				if(!server.isClosed()) {
					String msg = reader.read();
					log("�޽����� �޾ҽ��ϴ�. "+msg);
					if(msg!= null && msg != "") {
						JsonObject jsonObject= json.toJsonObject(msg);
						int type = json.getInt(jsonObject , "type");
						
						if(type == Constant.EMP) {
							EmployeeVO user = EmployeeParser.parse(jsonObject);
							setVisible(false);
							new ChatClient(user, server, writer, reader, json);
							new ChatDialog("�α��� �Ǿ����ϴ�.","�α��� ����");		
					//ClientChat ��ü ���� �� Thread�� ���� ���� �Ѱ� �ֱ�
							break;
						}else if(type==Constant.LOGIN) {
							new ChatDialog("�α��� ���� �߽��ϴ�.","�α��� ����");
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
			
	}

	//���� ����, ��ü �ʱ�ȭ
	public void connectServer() {
		try {
			InetAddress ia = InetAddress.getByName(Constant.serverIP);
			//���� ���� �õ�
			server = new Socket(ia,Constant.serverPort);
			System.out.println("���� ���� !");
			
			//reader, wirter �����
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
		log("�����մϴ�.");
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
