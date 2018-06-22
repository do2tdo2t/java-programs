package companychat.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

import companychat.vo.EmployeeVO;
import companychat.vo.EmployeesVO;

public class ChatFrame extends JFrame implements ActionListener,MouseListener{
	
	HashMap<String,Integer> empHm;
	EmployeesVO emps = null;
	JLabel label = new JLabel("                                    ");
	JPanel mainPane = new JPanel();
	JPanel userListPane = new JPanel(new BorderLayout());
	JPanel chatPane = new JPanel();
	private JTree jTree = null;
	DefaultMutableTreeNode top = null;
	JScrollPane sp = null;
	
	public ChatFrame() {
		this.setTitle("chat program");
		
		setSize(500,500);
		chatPane.setBackground(Color.GREEN);
		chatPane.setVisible(false);
		
		sp = new JScrollPane(chatPane);
		add(userListPane, "West");
		add(sp);
		}
	
	public void initEmpTree(EmployeesVO emps) {
		this.emps = emps;
		System.out.println("ChatFrame , initEmpTree()");
		int count = emps.getCount();
		if(count == 0 ) return;
		System.out.println("make node.......");
		EmployeeVO emp = null;
		DefaultMutableTreeNode deptNode = null;
		DefaultMutableTreeNode nameNode = null;
		top = new DefaultMutableTreeNode("부서");
		empHm = new HashMap();
		for(int i = 0; i<count  ; i++ ) {
			emp = emps.get(i);
			nameNode = new DefaultMutableTreeNode(emp.getName()+"/"+emp.getId());
			empHm.put(emp.getName()+"/"+emp.getId(), i);
			top.add(nameNode);
		}
		
		jTree = new JTree(top);
		jTree.addMouseListener(this);
		userListPane.add(label,"North");
		userListPane.add(jTree,"Center");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		//Tree 클릭시 채팅방 오픈
		if(e.getSource().equals(jTree)) {
			DefaultMutableTreeNode node = (DefaultMutableTreeNode)
				jTree.getLastSelectedPathComponent();
         	if (node == null) return;
         	Object nodeInfo = node.getUserObject();
         	System.out.println(nodeInfo);
         	System.out.println(empHm.get(nodeInfo));
         	
		}
		
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
	
	
}
