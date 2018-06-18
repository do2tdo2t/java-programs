package udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UnicastSend {

	public UnicastSend() {
		try {
			String msg = "Spring Framework, 스프링 프레임워크";
			DatagramSocket ds = new DatagramSocket();
			InetAddress ia = InetAddress.getByName("172.16.2.20");
			//byte 배열로 보냄. DatagramPacket(byte[] buf, int length, InetAddress address, int port)
			DatagramPacket dp = new DatagramPacket(msg.getBytes(),msg.getBytes().length, ia, 20000);
			
			//전송
			ds.send(dp);
			System.out.println("Client 전송 완료....");
		}catch(IOException ex) {
			ex.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new UnicastSend();

	}

}
