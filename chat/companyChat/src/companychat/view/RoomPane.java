package companychat.view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Panel;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import companychat.vo.RoomVO;

public class RoomPane extends Panel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	RoomVO roomVO = null;
	//private JTextArea ta = new JTextArea("");
	private ChatPane chatPane = new ChatPane();
	private JLabel recvInfolbl = new JLabel("");
	private JTextArea inputTa = new JTextArea("");
	JPanel inputPane = new JPanel(new BorderLayout());
	JScrollPane sp = null;
	
	
	//최초 클릭 시
	public RoomPane(RoomVO roomVO){
		log("Room 생성..." + roomVO.getRecv());
		this.setLayout(new BorderLayout());
		this.roomVO = roomVO;
		recvInfolbl.setText(roomVO.getRecv());
		Font font = new Font("맑은 고딕",Font.PLAIN, 12);
		recvInfolbl.setFont(font);
		add(recvInfolbl,"North");
		add(chatPane,"Center");
		add(inputPane,"South");
		sp = new JScrollPane(inputTa);
		inputPane.add(sp,"Center");
		inputTa.setEnabled(true);
		//ta.setEditable(false);
		//ta.setBackground(Color.LIGHT_GRAY);
	}

	public void addSendBtn(JButton sendBtn) {
		inputPane.add(sendBtn,"East");
	}
	
	public String getRecvInfo() {
		return roomVO.getRecv();
	}
	
	public void setTextArea(String userInfo, String head, String str) {
		if(head.equals(userInfo)) {
			chatPane.addLeft(new JLabel(head+"  "+str));
		}else {
			chatPane.addRight(new JLabel(str+"  "+head));
		}
		//ta.append(head+": "+str+'\n');
	}
	public void setInputlbl(String str) {
		
		  
		inputTa.setText(str);
	}
	//다시 불렸을때
	public void restartRoom() {
		//이전에 보낸 텍스트를 다시 텍스트 area에 붙여야함!
		setVisible(true);
	}
	
	public int getRecvId() {
		return roomVO.getRecvId();
	}

	public String getInputlbl() {
		return inputTa.getText();
	}
	void log(String str) {
		System.out.println("Room..."+str);
	}
	
}
