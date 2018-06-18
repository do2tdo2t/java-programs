package udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UnicastSend {

	public UnicastSend() {
		try {
			String msg = "Spring Framework, ������ �����ӿ�ũ";
			DatagramSocket ds = new DatagramSocket();
			InetAddress ia = InetAddress.getByName("172.16.2.20");
			//byte �迭�� ����. DatagramPacket(byte[] buf, int length, InetAddress address, int port)
			DatagramPacket dp = new DatagramPacket(msg.getBytes(),msg.getBytes().length, ia, 20000);
			
			//����
			ds.send(dp);
			System.out.println("Client ���� �Ϸ�....");
		}catch(IOException ex) {
			ex.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new UnicastSend();

	}

}
