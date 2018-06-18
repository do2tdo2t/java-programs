package udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.nio.charset.Charset;

//유니캐스트 1:1방식의 데이터 통신

public class UnicastReceive {

	public UnicastReceive() {
		try {
			//512byte 만큼 권장 
			byte buf[] = new byte[512];
			
			//대기하는 쪽은 DataGramSocket(in port)
			DatagramSocket ds = new DatagramSocket(20001); //대기
			DatagramPacket dp = new DatagramPacket(buf,512);
			
			//보내는 쪽에서 Packet으로 데이터를 담아서 보내므로
			//받는 쪽도 Packet으로 받아야 한다.
			System.out.println("Server 받기 대기중....");
			ds.receive(dp);
			System.out.println("Server 패킷 도착....");
			int cnt = dp.getLength();
			System.out.println(dp.getAddress() +"에서 패킷이 도착했습니다.\n"+ new String(dp.getData(), 0 ,cnt));
			//packet의 크기 만큼만 받기
			
		}catch(IOException ex1){
			ex1.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new UnicastReceive();
	}

}
