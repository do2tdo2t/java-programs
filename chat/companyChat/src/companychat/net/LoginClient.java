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
			//���� ���� �õ�
			connectServer();
			
			//���� ���� �α��� ���� �޾ƿ���
			String msg = reader.read();
			JsonObject jsonObject= json.toJsonObject(msg);
			int type = json.getInt(jsonObject , "type");
			
			if(type == Constant.EMP) {
				EmployeeVO user = EmployeeParser.parse(jsonObject);
				new ChatClient(user, server, writer, reader, json);
				System.out.println(user);
				//ClientChat ��ü ���� �� Thread�� ���� ���� �Ѱ� �ֱ�
				new ChatDialog("�α��� �Ǿ����ϴ�.");
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
		//json string���� �ٲ㼭 �����ϱ�
		writer.write(gson.toJson(loginInfo));
	}

	
}
