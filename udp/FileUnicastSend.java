package udp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

/*
 * DatagramSocket , DatagramPacket : UDP. 단방향 통신, 클라이언트에서 서버로
 * */

public class FileUnicastSend {
	JFrame frame= new JFrame("Reciever");
	JButton sendBtn = new JButton("파일 보내기");
	
	public FileUnicastSend() {
		initFrame();
	}
	
	void initFrame() {
		frame.setSize(500, 500);
		frame.add(sendBtn, "South");
		frame.setVisible(true);
		
		//add action listener
		sendBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				if(e.getSource().equals(sendBtn)) {
					whenSendButtonClick();
				}
			}
		});
	}
	
	//send file
	void whenSendButtonClick() {
		final int max = 512;
		File file = null;
		String fileName = null;
		FileInputStream fis= null;
		DatagramPacket dp = null;
		DatagramSocket ds = null;
		//파일 탐색창
		JFileChooser chooser = new JFileChooser();
		int result = chooser.showOpenDialog(frame); //취소는 1, 열기는 0
		try {
			if(result == 0) {
				/*읽기*/
				file = chooser.getSelectedFile(); //파일 생성
				fis = new FileInputStream(file); //파일 읽기 위한 인풋 스트림 생성

				
				//데이터 전송
				
				InetAddress ia = InetAddress.getByName("172.16.2.20");
				ds = new DatagramSocket();
				
				//파일 이름 전송
				fileName = "<name>"+ file.getName();
				dp = new DatagramPacket(fileName.getBytes(),fileName.getBytes().length,ia,20001);
				ds.send(dp);

				//파일 내용 전송
				//<file> 512 - 6 = 506글자 보내기
				byte[] buf = new byte[max];
				
				System.arraycopy("<file>".getBytes() , 0, buf, 0, "<file>".getBytes().length );

				int startIndex = 6;
				int endIndex = buf.length - "<file>".getBytes().length;
				int index = 0;
				System.out.println("Sender : 파일 전송 시작");
				while(true) {
					System.out.println(startIndex +", " +endIndex);
					//파일로부터 읽기
					int cnt = fis.read(buf, startIndex ,endIndex);
					System.out.println((index)+". 읽은 문자 : "+ cnt +"\n"+ new String(buf));
					index++;
					if(cnt <= 0) break; //읽은 데이터가 없으면 -1이 return
					dp = new DatagramPacket(buf,max,ia,20001);
					ds.send(dp);
					startIndex = 0;
					endIndex = max;
					Thread.sleep(100);
				}
				//전송 완료 알리기
				dp = new DatagramPacket("<end!>".getBytes(),"<end!>".getBytes().length,ia,20001);
				ds.send(dp);
				System.out.println("Sender : 파일 전송 완료");
			}
		}catch(IOException ex) {
			ex.getStackTrace();
		}catch(InterruptedException ex2) {
			ex2.getStackTrace();
		}
		
	}

	public static void main(String[] args) {
		new FileUnicastSend();

	}

}
