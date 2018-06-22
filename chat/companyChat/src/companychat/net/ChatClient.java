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

//�α��� ���� ��
//view actionPerformed
//��û ������ ����
//client ��û -> ����
//���� 

public class ChatClient extends ChatFrame implements Runnable{
	EmployeeVO user; //�α��� �� ���.
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
		
		//���� �ʱ�ȭ �޽��� ����
		EmployeesVO emps = new EmployeesVO();
		writer.write(emps);
		
	}

	public void initFrame() {
		//test �ڵ�
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
	//���� ó��
	public void run() {
		System.out.println("start thread");
		while(true) {
			// Reader ��ü�� ���� ���� �޽����� msg�� ���
			//String msg = reader.read();
			//type : ������ �޽����� � ��������. JsonString -> Object��
			
			String msg = reader.read();
			if(json == null) json = new Json();
			JsonObject jsonObject= json.toJsonObject(msg);
			int type = json.getInt(jsonObject , "type");
			
			if(type == Constant.MSG) {
				//��ü ����
				MessageVO message = MessageParser.parse(jsonObject);
				//ȭ�� ������Ʈ
				
				
			}else if(type == Constant.MSGS) {
				//��ü ����
				MessagesVO msgs = MessagesParser.parse(jsonObject);
				//ȭ�� ������Ʈ
			}else if(type == Constant.EMPS) {
				//��ü ����
				EmployeesVO emps = EmployeesParser.parse(jsonObject);
				//ȭ�� ������Ʈ
				
				setVisible(true);
			}
		}			
	}
}
