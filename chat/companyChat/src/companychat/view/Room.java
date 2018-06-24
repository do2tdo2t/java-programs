package companychat.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Panel;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import companychat.vo.RoomVO;

public class Room extends Panel {
	RoomVO roomVO = null;
	private JTextArea ta = new JTextArea("");
	private JLabel recvInfolbl = new JLabel("");

	private JTextArea inputTa = new JTextArea("");

	JPanel inputPane = new JPanel(new BorderLayout());
	JScrollPane sp = null;
	
	//���� Ŭ�� ��
	public Room(RoomVO roomVO){
		log("Room ����..." + roomVO.getRecv());
		this.setLayout(new BorderLayout());
		this.roomVO = roomVO;
		recvInfolbl.setText(roomVO.getRecv());
		add(recvInfolbl,"North");
		add(ta,"Center");
		add(inputPane,"South");
		sp = new JScrollPane(inputTa);
		inputPane.add(sp,"Center");
		inputTa.setEnabled(true);
		ta.setEditable(false);
		ta.setBackground(Color.LIGHT_GRAY);
	}

	public void addSendBtn(JButton sendBtn) {
		inputPane.add(sendBtn,"East");
	}
	
	public String getRecvInfo() {
		return roomVO.getRecv();
	}
	
	public void setTextArea(String head, String str) {
		ta.append(head+": "+str+'\n');
	}
	public void setInputlbl(String str) {
		inputTa.setText(str);
	}
	//�ٽ� �ҷ�����
	public void restartRoom() {
		//������ ���� �ؽ�Ʈ�� �ٽ� �ؽ�Ʈ area�� �ٿ�����!
		setVisible(true);
	}
	
	public int getRecvId() {
		return roomVO.getRecvId();
	}

	public String getInputlbl() {
		// TODO Auto-generated method stub
		return inputTa.getText();
	}
	void log(String str) {
		System.out.println("Room..."+str);
	}
	
}
