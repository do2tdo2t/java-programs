
import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.URL;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

public class ChatClient extends JFrame implements ActionListener, Runnable{
	JTextField ipTextField = new JTextField("172.16.2.20");
	JTextField chatTextField = new JTextField();
	JTextField imgPathField = new JTextField("");
	JTextArea chatTextArea = new JTextArea();
	JButton connBnt = new JButton("connect");
	JButton selectImgBnt = new JButton("send img");
	JButton sendBnt = new JButton("send");
	JScrollPane scrollPane= new JScrollPane(chatTextArea);
	JPanel connPane = new JPanel(new BorderLayout());
	JPanel sendPane = new JPanel(new BorderLayout());
	JPanel imgPane = new JPanel(new BorderLayout());
	JFileChooser chooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
	
	Socket socket = null;
	BufferedReader br = null;
	PrintWriter pw = null;
	
	public static void main(String[] args) {
		new ChatClient();
	}

	public ChatClient() {
		initFrame();
		this.setVisible(true);
	}
	
	void initFrame() {
		this.setBounds(0,0,500,500);
		chatTextArea.setEditable(false);
		chatTextField.setEditable(true);
		imgPathField.setEditable(false);
		
		imgPane.add(imgPathField,"Center");
		imgPane.add(selectImgBnt,"East");
		connPane.add(ipTextField,"Center");
		connPane.add(connBnt,"East");
		sendPane.add(chatTextField,"Center");
		sendPane.add(sendBnt,"East");
		sendPane.add(imgPane,"North");
		
		ipTextField.addActionListener(this);
		connBnt.addActionListener(this);
		sendBnt.addActionListener(this);
		chatTextField.addActionListener(this);
		selectImgBnt.addActionListener(this);
		
		this.add(connPane,"North");
		this.add(sendPane,"South");
		this.add(scrollPane,"Center");	
		
		/*file chooser init*/
		chooser.setCurrentDirectory(new File("/"));
		chooser.setDialogTitle("파일 탐색기"); 
		chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES); 
		FileNameExtensionFilter filter = new FileNameExtensionFilter( "JPG & GIF Images", "jpg","jpeg" , "gif", "cd11");
		chooser.setFileFilter(filter);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if(obj.equals(ipTextField) || obj.equals(connBnt)) {
			connectServer();
		}else if(obj.equals(sendBnt)||obj.equals(chatTextField)) {
			sendMessage();
		}else if(obj.equals(selectImgBnt)) {
			System.out.println("파일 탐색기 실행");
			try {
			whenSelectedImg();
			}catch(IOException err) {
				err.printStackTrace();
			}
		}
			
	}
	
	void whenSelectedImg() throws IOException{
		int returnVal = chooser.showOpenDialog(null); // 열기용 창 오픈
		String folderPath = "";
        if(returnVal == JFileChooser.APPROVE_OPTION) { // 열기를 클릭 
           folderPath = chooser.getSelectedFile().toString();
        }else if(returnVal == JFileChooser.CANCEL_OPTION){ // 취소를 클릭
            System.out.println("cancel"); 
           folderPath = "";
        }
        System.out.println(folderPath);
        
		if(!folderPath.equals("") && !folderPath.equals(null)) {
			System.out.println(imgPathField.getText());
			writeImgMSG(folderPath);
		}
        
	}
	
	//서버와 연결, 
	void connectServer() {
		try {
			String ip = ipTextField.getText();
			InetAddress ia = InetAddress.getByName(ip);
			
			socket  = new Socket(ia,7777);
			
			// ip 텍스트 필드에서 엔터키, connBnt 클릭
			br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
			
			Thread thread = new Thread(this);
			thread.start();
			
			System.out.println("\n클라이언트 소켓 접속");
			
		}catch(UnknownHostException e){
			e.printStackTrace();
		}catch(IOException e2) {
			e2.printStackTrace();
		}
	}
	
	//msg 서버로 전송
	void sendMessage() {
		// chat 텍스트 필드에서 엔터키, sendBnt 클릭
		try {
			if(socket.isConnected()) {
				writeTextMSG("<text>");
				writeTextMSG(chatTextField.getText());
				chatTextField.setText("");
				scrollPane.getVerticalScrollBar().setValue(scrollPane.getVerticalScrollBar().getMaximum());
				//send Img

			}
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	//메시지 쓰기
	void writeTextMSG(String str) throws IOException {
		if(pw != null) {
			pw.println(str);
			pw.flush();
		}
	}
	
	//메시지 쓰기
	void writeImgMSG(String imgPath) throws IOException {
		pw.println("<img>");
		pw.flush();
		

		try {
			//파일 생성

			BufferedReader fin = new BufferedReader(new FileReader(imgPath));
			int i = 0;
			while((i=fin.read())!=-1) {
				pw.write(i);
			}
			pw.flush();
			imgPathField.setText("");
			
		}catch(Exception err) {
			System.out.println("파일을 열수 없습니다.");
		}
		
	}
	
	//서버에서 온 문자를 실시간 쓰레드로 처리
	@Override
	public void run() {
		try {
			while(true) {
				String header = br.readLine();
				if(header.equals("<text>")) {
					String msg = br.readLine();
					chatTextArea.append(msg+'\n');
				}else if(header.equals("<img>")) {
					ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
					Image img = (Image)ois.readObject();
					chatTextArea.append("이미지가 도착했어요~"+'\n');
					ois.close();
				}
				scrollPane.getVerticalScrollBar().setValue(scrollPane.getVerticalScrollBar().getMaximum());
			}
		}catch(IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
	}
	
}
