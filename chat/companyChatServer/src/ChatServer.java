
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;

import companychatserver.dto.EmployeeDTO;
import companychatserver.dto.LoginDTO;
import companychatserver.util.Constant;
import companychatserver.view.ServerFrame;
import companychatserver.vo.EmployeesVO;

public class ChatServer extends ServerFrame implements Runnable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	EmployeesVO emps = null;
	ServerSocket serverSocket = null;
	ClientService service = null;
	Socket socket = null;
	public static ArrayList<ClientService> clientList = new ArrayList<ClientService>();
	Thread thread = null;
	
	public static void main(String[] args) {
		new ChatServer();
	}
	
	public ChatServer() {
		initFrame();
		addEvent();
	}
	public void addEvent() {
		startBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {	
				start();
				log("������ ���۵Ǿ����ϴ�.");
			}
		});
	}
	
	public void start() {
		try {
			serverSocket = new ServerSocket(Constant.serverPort);
			//���� �ʱ�ȭ �α���
			initEmployeeInfo();
			
			//thread ����
			Thread thread = new Thread(this);
			thread.start();
			
		}catch (IOException e2) {
			
			e2.printStackTrace();
		}
	}
	
	void initEmployeeInfo() {
		EmployeeDTO empDto = new EmployeeDTO();
		emps = empDto.getAllEmployeeInfo();
		deleteLoginLog();
	}
	
	void deleteLoginLog() {
		LoginDTO loginDto = new LoginDTO();
		loginDto.doAllLogout();
	}

	@Override
	public void run(){
		while(true) {
			try {
				socket = serverSocket.accept();
				
				//�����ǰ� ���Ƽ� Map�� ������ �ֽŰɷθ� ������Ʈ ��. ���߿� ���� Ŭ���̾�Ʈ�� �ٸ� Ŭ���̾�Ʈ�� ������ �˼� X
				service = new ClientService(socket);
				clientList.add(service);
				
				
			} catch (SocketException e) {
				e.printStackTrace();
			}catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	public void log(String str) {
		ipTa.append("\n"+str);
	}

}
