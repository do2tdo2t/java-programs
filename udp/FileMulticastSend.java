package udp;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class FileMulticastSend {
	final int MAX = 512;
	String address =null;
	InetAddress ia = null;
	DatagramSocket ds = null;
	int port;

	void sendData(byte[] data) throws IOException {
		System.out.println(data.length+"만큼 보내졌습니다.");
		DatagramPacket dp = new DatagramPacket(data,data.length,ia,port);
		ds.send(dp);
	}
	
	byte[] readFile(FileInputStream fis ) throws IOException{
		byte[] buf = new byte[MAX];
		int cnt = fis.read(buf, 0, MAX);
		if(cnt <= 0) {
			return null;
		}
		return buf;
	}
	
	File openFile() throws IOException{
		return new File("C:\\Users\\gt-2-p-018\\Desktop","file_1.txt");
	}
	
	void startSendFile() throws IOException {
		ia = InetAddress.getByName(address);
		ds = new DatagramSocket();
		
		//file choose
		File file = openFile();
		FileInputStream fis = new FileInputStream(file);
		
		//send file name
		byte[] data = new byte[MAX];
		sendData( "<name>".getBytes());
		sendData( file.getName().getBytes());
		
		//<file> 헤더 보내기
		sendData("<file>".getBytes());
		while(true) {
			data = readFile(fis);
			if(data == null) break;
			sendData(data);
		}
		//send end file
		sendData("<end>".getBytes());
	}
	
	public FileMulticastSend(String address, int port) {
		this.address = address;
		this.port = port;
		try {
			startSendFile();

		}catch(IOException ex) {
			ex.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new FileMulticastSend("230.0.0.12",20001);

	}

}
