package udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.nio.charset.Charset;

//����ĳ��Ʈ 1:1����� ������ ���

public class UnicastReceive {

	public UnicastReceive() {
		try {
			//512byte ��ŭ ���� 
			byte buf[] = new byte[512];
			
			//����ϴ� ���� DataGramSocket(in port)
			DatagramSocket ds = new DatagramSocket(20001); //���
			DatagramPacket dp = new DatagramPacket(buf,512);
			
			//������ �ʿ��� Packet���� �����͸� ��Ƽ� �����Ƿ�
			//�޴� �ʵ� Packet���� �޾ƾ� �Ѵ�.
			System.out.println("Server �ޱ� �����....");
			ds.receive(dp);
			System.out.println("Server ��Ŷ ����....");
			int cnt = dp.getLength();
			System.out.println(dp.getAddress() +"���� ��Ŷ�� �����߽��ϴ�.\n"+ new String(dp.getData(), 0 ,cnt));
			//packet�� ũ�� ��ŭ�� �ޱ�
			
		}catch(IOException ex1){
			ex1.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new UnicastReceive();
	}

}
