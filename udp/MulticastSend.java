package udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class MulticastSend {
	
	/*���� �����Ǵ� 224.0.0.0 ~ 239.255.255.255*/
	//				230.0.0.12
	
	public MulticastSend() {
		String msg = "multicast testing... networkt data��������!";
		DatagramSocket ds = null;
		DatagramPacket dp = null;
		try {
			ds = new DatagramSocket();
			InetAddress ia = InetAddress.getByName("230.0.0.12");
			dp = new DatagramPacket(msg.getBytes(),msg.length(),ia,15000);
			ds.send(dp);
			ds.close();
		}catch(IOException ex) {
			ex.printStackTrace();
		}
		System.out.println("Ŭ���̾�Ʈ : ���� �Ϸ� ! ");
	}
	public static void main(String[] args) {
		new MulticastSend();

	}

}
