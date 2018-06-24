package companychat.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

import companychat.vo.EmployeeVO;
import companychat.vo.EmployeesVO;
import companychat.vo.MessageVO;
import companychat.vo.RoomVO;

public class ChatFrame extends JFrame implements ActionListener,MouseListener,WindowListener{
	//HashMap<String,RoomVO> roomMap = new HashMap<String,RoomVO>();
	

	String userInfo = "";
	HashMap<String,Integer> empHm;
	protected HashMap<String,Room> rooms = new HashMap<String,Room>();
	protected Room curRoom = null;
	JLabel padding = new JLabel("                                    ");
	JLabel userInfolbl = new JLabel();
	
	JPanel mainPane = new JPanel();
	JPanel chatPane = new JPanel(new BorderLayout());
	JPanel userListPane = new JPanel(new BorderLayout());
	protected JTree jTree = null;
	DefaultMutableTreeNode top = null;
	protected JButton sendBtn = new JButton("send");
	
	public ChatFrame() {
		this.setTitle("chat program");
		setSize(500,500);
		padding.setBackground(Color.white);
		sendBtn.addMouseListener(this);
		add(chatPane,"Center");
		add(userListPane, "West");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void initEmpTree(EmployeesVO emps, EmployeeVO user) {
		int count = emps.getCount();
		if(count == 0 ) return;
		EmployeeVO emp = null;
		DefaultMutableTreeNode deptNode = null;
		DefaultMutableTreeNode nameNode = null;
		top = new DefaultMutableTreeNode("부서");
		empHm = new HashMap<String,Integer>();
		for(int i = 0; i<count  ; i++ ) {
			emp = emps.get(i);
			nameNode = new DefaultMutableTreeNode(emp.getName()+"/"+emp.getId());
			empHm.put(emp.getName()+"/"+emp.getId(), i);
			top.add(nameNode);
		}
		userInfo=user.getName()+"/"+user.getId();
		userInfolbl.setText(userInfo);
		jTree = new JTree(top);
		jTree.addMouseListener(this);
		userListPane.add(userInfolbl,"North");
		userListPane.add(padding,"South");
		userListPane.add(jTree,"Center");
		addWindowListener(this);
	}

	//message 전송 이벤트 발생 시
	protected MessageVO getMessageInfo() {
		MessageVO msg= new MessageVO();
		String recvInfo = curRoom.getRecvInfo();
		String recvName = recvInfo.split("/")[0];
		int recvId = Integer.parseInt(recvInfo.split("/")[1]);
		String content = curRoom.getInputlbl();
		
		String userName = userInfo.split("/")[0];
		int userId = Integer.parseInt(userInfo.split("/")[1]);
		
		msg.setReceiver(recvId);
		msg.setContent(content);
		msg.setRecvName(recvName);
		msg.setSender(userId);
		msg.setSenderName(userName);
		return msg;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getSource().equals(jTree)) {
			
		}
	}
	
	protected void showRoom(RoomVO roomVO) {
		Room room = null;
		if(curRoom !=null) {
			//log(curRoom.getRecvInfo()+"이전 프레임 감춤");
			curRoom.setVisible(false);
		}
		
		room = new Room(roomVO);
		room.addSendBtn(sendBtn);
		rooms.put(roomVO.getRecv()+"/"+roomVO.getRecvId(), room);
		chatPane.add(curRoom,"Center");
		chatPane.revalidate();
		curRoom = room;		
	}
	
	protected void loadRoom(String nodeInfo) {
		RoomVO roomVO = null;
		Room room = null;
		if(curRoom !=null) {
			//log(curRoom.getRecvInfo()+"이전 프레임 감춤");
			curRoom.setVisible(false);
		}	
     	curRoom = rooms.get(nodeInfo);
     	//log(curRoom.getRecvInfo()+"프레임 다시 보이기");
     	curRoom.restartRoom();
     	chatPane.add(curRoom,"Center");
    	curRoom.revalidate();
	}
	
	
	protected void updateInputlbl(String str) {
		//화면 업데이트
		curRoom.setInputlbl(str);
	}
	//상대에게 메시지 보낼때 ta 업데이트
	protected void updateTextArea(String str) {
		curRoom.setTextArea(userInfo,str);
	}
	
	//메시지가 도착했을 떄 ta 업데이트
	protected void updateTextArea(MessageVO msg) {
		//화면 업데이트
		String head = msg.getSenderName()+"/"+msg.getSender();
		String str = msg.getContent();
		curRoom.setTextArea(head,str);
		curRoom.setInputlbl("");
	}


	@Override
	public void mousePressed(MouseEvent e) {	
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}
	
	void log(String str) {
		System.out.println("ChatFrame... "+str);
	}

	@Override
	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent arg0) {
		
	}

	@Override
	public void windowClosing(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
}
