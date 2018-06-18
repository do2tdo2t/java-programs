package udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class MulticastReieve {

	public MulticastReieve() {
		MulticastSocket ms  = null;
		DatagramPacket dp = null;
		final int MAX = 512;
		byte[] buf = new byte[MAX];
		try {
			ms = new MulticastSocket(15000);
			dp = new DatagramPacket(buf,MAX);
			InetAddress ia = InetAddress.getByName("230.0.0.12");
			ms.joinGroup(ia); //��������� ����
			ms.receive(dp);
			System.out.println(dp.getAddress()+"���� ��Ƽ ĳ��Ʈ ������ ���� !..." + dp.getLength());
			System.out.println(new String(dp.getData()));
			
		    ms.close();
		}catch(IOException ex) {
			ex.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new MulticastReieve();

	}

}
