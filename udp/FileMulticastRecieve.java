package udp;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class FileMulticastRecieve {
	final int MAX = 512;
	String address = null;
	int port;
	
	void startReceiveFile() throws IOException{
		InetAddress ia = InetAddress.getByName(address);
		MulticastSocket ms = new MulticastSocket(port);
		ms.joinGroup(ia);
		byte buf[] = new byte[MAX];
		byte data[] = null;
		DatagramPacket dp = new DatagramPacket(buf,MAX);
		System.out.println("패킷을 기다리는 중...");
		File file = null;
		FileOutputStream fos = null;
		while(true) {
			ms.receive(dp);
			int cnt =dp.getLength();
			data = new byte[cnt];
			data = dp.getData();

			if((new String(data,0,cnt)).equals("<name>")) {
				dp = new DatagramPacket(buf,MAX);
				ms.receive(dp);
				String filename = new String(dp.getData(), 0 ,dp.getLength());
				System.out.println(filename);
				file = new File("D:\\javaFile",filename);
				fos = new FileOutputStream(file);
			}else if(data.equals("<file>")) {
				System.out.println("file을 읽읍시다...");
			}else if(data.equals("<end>")) {
				System.out.println("읽기 작업이 끝났습니다..");
				break;
			}else {
				fos.write(data, 0, cnt);
			}
		}
		fos.close();
		ms.close();
	}
	public FileMulticastRecieve(String address, int port) {
		this.address = address;
		this.port = port;
		
		// init
		try {
			startReceiveFile();
		}catch(IOException ex) {
			ex.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new FileMulticastRecieve("230.0.0.12",20001);

	}

}
