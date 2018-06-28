package companychat.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
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
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import companychat.vo.EmployeeVO;
import companychat.vo.EmployeesVO;
import companychat.vo.MessageVO;
import companychat.vo.RoomVO;

public class ChatFrame extends JFrame implements ActionListener,MouseListener,WindowListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String userInfo = "";
	//HashMap<String,Integer> empHm;
	protected HashMap<String,RoomPane> rooms = new HashMap<String,RoomPane>();
	protected RoomPane curRoom = null;
	JLabel padding = new JLabel("                                    ");
	JLabel userInfolbl = new JLabel();
	Font font = new Font("���� ���",Font.PLAIN, 12);
	JPanel mainPane = new JPanel();
	JPanel chatPane = new JPanel(new BorderLayout());
	JPanel userListPane = new JPanel(new BorderLayout());
	protected EmpTree empTree = null;
	protected JButton sendBtn = new JButton("send");
	//������ ���̱�
	Toolkit toolkit = Toolkit.getDefaultToolkit();
	Image img = toolkit.getImage("./img/titleIcon.png");
	JMenuItem item1 = new JMenuItem("��� ���� ����");
	
	public ChatFrame() {
		this.setTitle("chat program");
		setSize(530,500);
		padding.setBackground(Color.white);
		sendBtn.addMouseListener(this);
		add(chatPane,"Center");
		add(userListPane, "West");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	
	//�������� ���� ������ �޾ƿ� employeesVO ��ü�� �����Ѵ�.
	public void initEmpTree(EmployeesVO emps, EmployeeVO user) {
		
		//userInfo label �ʱ�ȭ
		userInfo=user.getName()+"/"+user.getId();
		userInfolbl.setText(userInfo);
		userInfolbl.setFont(font);
		
		//���� Ʈ�� ����
		empTree = new EmpTree(emps);
		empTree.addMouseListener(this);
		
		//list �ҿ� ����
		userListPane.add(userInfolbl,"North");
		userListPane.add(padding,"South");
		userListPane.add(empTree,"Center");
		this.setIconImage(img);
		addWindowListener(this);
	}

	//message ���� �̺�Ʈ �߻� ��
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
	}
	
	protected void showRoom(RoomVO roomVO) {
		RoomPane room = null;
		if(curRoom !=null) {
			curRoom.setVisible(false);
		}
		
		room = new RoomPane(roomVO);
		curRoom = room;	
		
		//message ���̱�
		for(MessageVO msg : roomVO.getList()) {
			updateTextArea(msg);
		}
		room.addSendBtn(sendBtn);
		rooms.put(roomVO.getRecv()+"/"+roomVO.getRecvId(), room);
		
		chatPane.add(curRoom,"Center");
		chatPane.revalidate();
			
	}
	
	protected void loadRoom(String nodeInfo) {
		if(curRoom !=null) {
			//log(curRoom.getRecvInfo()+"���� ������ ����");
			curRoom.setVisible(false);
		}	
     	curRoom = rooms.get(nodeInfo);
     	//log(curRoom.getRecvInfo()+"������ �ٽ� ���̱�");
     	curRoom.restartRoom();
     	chatPane.add(curRoom,"Center");
    	curRoom.revalidate();
	}
	
	
	protected void updateInputlbl(String str) {
		//ȭ�� ������Ʈ
		curRoom.setInputlbl(str);
	}
	//��뿡�� �޽��� ������ chat frame ������Ʈ
	protected void updateTextArea(String str) {
		curRoom.setTextArea(userInfo,userInfo,str);
		curRoom.setInputlbl("");
	}
	
	//�޽����� �������� �� chat frame ������Ʈ
	protected void updateTextArea(MessageVO msg) {
		//ȭ�� ������Ʈ
		String head = msg.getSenderName()+"/"+msg.getSender();
		String str = msg.getContent();
		System.out.println(head + " " + str );
		curRoom.setTextArea(userInfo,head,str);
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
	
	protected void log(String str) {
		System.out.println("ChatFrame... "+str);
	}

	@Override
	public void windowActivated(WindowEvent arg0) {
		// 
		
	}

	@Override
	public void windowClosed(WindowEvent arg0) {
		
	}

	@Override
	public void windowClosing(WindowEvent arg0) {
	
		
	}

	@Override
	public void windowDeactivated(WindowEvent arg0) {

		
	}

	@Override
	public void windowDeiconified(WindowEvent arg0) {

		
	}

	@Override
	public void windowIconified(WindowEvent arg0) {
		
	}

	@Override
	public void windowOpened(WindowEvent arg0) {
		
	}
	
}
