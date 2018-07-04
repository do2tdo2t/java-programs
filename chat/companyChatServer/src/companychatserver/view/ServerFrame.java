package companychatserver.view;


import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import companychatserver.util.Constant;

public class ServerFrame extends JFrame{
	/**
	 * 
	 */
	protected JButton startBtn = new JButton("시작");
	protected JTextArea ipTa = new JTextArea();
	JScrollPane sp = new JScrollPane(ipTa);
	private static final long serialVersionUID = 1L;
	public ServerFrame() {
		
	}
	protected void initFrame() {
		setSize(200,500);
		String ip ="";
		ipTa.setEditable(false);
		//현재 컴퓨터의 ip 얻기
		try {
			ip = InetAddress.getLocalHost().toString().split("/")[1];
			Constant.serverIP = ip;
		} catch (UnknownHostException e1) {
			e1.printStackTrace();
		}
		ipTa.setText(ip);
		
		add(sp,"Center");
		add(startBtn,"South");
		setVisible(true);
	}
	
}
