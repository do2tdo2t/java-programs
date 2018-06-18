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
 * DatagramSocket , DatagramPacket : UDP. �ܹ��� ���, Ŭ���̾�Ʈ���� ������
 * */

public class FileUnicastSend {
	JFrame frame= new JFrame("Reciever");
	JButton sendBtn = new JButton("���� ������");
	
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
		//���� Ž��â
		JFileChooser chooser = new JFileChooser();
		int result = chooser.showOpenDialog(frame); //��Ҵ� 1, ����� 0
		try {
			if(result == 0) {
				/*�б�*/
				file = chooser.getSelectedFile(); //���� ����
				fis = new FileInputStream(file); //���� �б� ���� ��ǲ ��Ʈ�� ����

				
				//������ ����
				
				InetAddress ia = InetAddress.getByName("172.16.2.20");
				ds = new DatagramSocket();
				
				//���� �̸� ����
				fileName = "<name>"+ file.getName();
				dp = new DatagramPacket(fileName.getBytes(),fileName.getBytes().length,ia,20001);
				ds.send(dp);

				//���� ���� ����
				//<file> 512 - 6 = 506���� ������
				byte[] buf = new byte[max];
				
				System.arraycopy("<file>".getBytes() , 0, buf, 0, "<file>".getBytes().length );

				int startIndex = 6;
				int endIndex = buf.length - "<file>".getBytes().length;
				int index = 0;
				System.out.println("Sender : ���� ���� ����");
				while(true) {
					System.out.println(startIndex +", " +endIndex);
					//���Ϸκ��� �б�
					int cnt = fis.read(buf, startIndex ,endIndex);
					System.out.println((index)+". ���� ���� : "+ cnt +"\n"+ new String(buf));
					index++;
					if(cnt <= 0) break; //���� �����Ͱ� ������ -1�� return
					dp = new DatagramPacket(buf,max,ia,20001);
					ds.send(dp);
					startIndex = 0;
					endIndex = max;
					Thread.sleep(100);
				}
				//���� �Ϸ� �˸���
				dp = new DatagramPacket("<end!>".getBytes(),"<end!>".getBytes().length,ia,20001);
				ds.send(dp);
				System.out.println("Sender : ���� ���� �Ϸ�");
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
