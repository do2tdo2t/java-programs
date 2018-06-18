package udp;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class FileUnicastReieve {

	public static void main(String[] args)  {
		FileUnicastReieve fr = new FileUnicastReieve();
		fr.start();
	}
	
	public FileUnicastReieve() {
	}
	

	void start() {
		DatagramSocket ds = null;
		DatagramPacket dp  = null;
		FileOutputStream fos = null;
		File file = null;
		try {
			ds = new DatagramSocket(20001);
			byte[] data = new byte[512];
			System.out.println("Server : ready...");
			while(true) {	
				dp = new DatagramPacket(data,512);
				ds.receive(dp);
				data = dp.getData();
				
				//파일 이름, 내용, 끝 문자
				int cnt = dp.getLength(); //몇글자가 전송 되었는가?
				String part= new String(data,0,6);
				System.out.println("part :"+ part );
				if(part.equals("<name>")) {
					file = new File("D:\\javaFile",new String(data, 6 ,cnt-6));
					fos  = new FileOutputStream(file);
				}else if(part.equals("<file>")){
					fos.write(data, 6, cnt-6);
				}else if(part.equals("<end!>")) {
					System.out.println("end");
					break;
				}else {
					fos.write(data, 0, cnt);
				}
			}
			
			fos.close();
			ds.close();
		}catch(IOException ex) {
			ex.printStackTrace();
		}
		
	}

}
