
import java.awt.Image;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

import javax.imageio.ImageIO;

// �����带 ��� �޾Ƽ�
public class ChatServer implements Runnable{
	ArrayList<ChatService> clientList = new ArrayList<ChatService>();
	HashMap<String, String> clientNameList = new HashMap<String,String>();
	ServerSocket serverSocket;
	Socket clientSocket = null;
	int imgNum = 0;
	//���� ���
	
	public static void main(String[] args) {
		try {
			new ChatServer();
			
		}catch(IOException ex) {
			ex.printStackTrace();
		}
	}
	
	public ChatServer() throws IOException {
		serverSocket = new ServerSocket(7777);
		clientNameList.put("172.16.2.20", "ȿ");
		clientNameList.put("172.16.2.4", "���λѿ�");
		Thread thread = new Thread(this);
		thread.start();
	}

	@Override
	public void run(){
		try {
			while(true) {
				clientSocket = serverSocket.accept();
				System.out.println("wait....");
				ChatService chatService = new ChatService();
				clientList.add(chatService);
				//������ �˸�. �����尡 ���ư��� ��ü�� �޽����� ����
				chatService.broadcast(chatService.username+"�� �����߽��ϴ�..");
				chatService.start();
			}
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	//������ ���� ��ü ���� ������
	class ChatService extends Thread {
		BufferedReader br;
		PrintWriter pw;
		String username = "";
		
		ChatService(){
			try {
				username =clientSocket.getInetAddress().getHostAddress().toString();
				if(clientNameList.get(username)!=null) {
					username =(String) clientNameList.get(username);
					System.out.println(username);
				}
				br = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
				pw = new PrintWriter( new OutputStreamWriter(clientSocket.getOutputStream()));
				
			}catch(IOException ex) {
				ex.printStackTrace();
			}
		}
		
		public void run(){
			try {
				//���� ���ڸ� �ٸ� ��� �����ڿ��� ������
				while(true) {
					String header = br.readLine().toLowerCase();
					if(header.equals("<text>")) {
						String msg = br.readLine();
						broadcast(msg);
					}else if(header.equals("<img>")) {
						broadcastImg();
					}
				}
			}catch(IOException ex1) {
				ex1.printStackTrace();
			}catch(ClassNotFoundException ex2) {
				ex2.printStackTrace();
			}
		}
	
		void broadcast(String msg)  throws IOException{
			for(ChatService chatService :clientList) {
				chatService.sendMSG("<text>");
				chatService.sendMSG(username+" : "+msg);
			}
		}
		
		void broadcastImg() throws IOException,ClassNotFoundException{
			//�̹��� ��ü �б� 
			int c;
			File file = new File("D://javaFile/img");
			RandomAccessFile raf = new RandomAccessFile(file,"rw");
			
			InputStream is = clientSocket.getInputStream();
			BufferedInputStream bis = new BufferedInputStream(is);
			while((c=bis.read())!=-1) {
				raf.writeByte(c);
			}
			
			/*�̹��� �׽�Ʈ ���� �غ���*/
			 
			 for(ChatService chatService :clientList) {
				  if(clientSocket.isConnected()) {
					  chatService.sendMSG("<img>");
					  ObjectOutputStream oos = new ObjectOutputStream(clientSocket.getOutputStream());
				  	  oos.writeObject(img);
				  	  oos.close();
			  	}
			  }
		}

		void sendMSG(String msg) throws IOException{
			pw.println(msg);
			pw.flush();
		}
	}
}
