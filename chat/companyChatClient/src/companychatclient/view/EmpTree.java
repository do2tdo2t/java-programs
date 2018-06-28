package companychatclient.view;


import javax.swing.ImageIcon;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;

import companychatclient.vo.EmployeeVO;
import companychatclient.vo.EmployeesVO;

public class EmpTree extends JTree {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static DefaultMutableTreeNode top = new DefaultMutableTreeNode("직원");
	public EmpTree(EmployeesVO emps) {
		super(top);
		EmployeeVO emp = null;
		int count = emps.getCount();
		if(count == 0 ) return;
		DefaultMutableTreeNode nameNode = null;
		for(int i = 0 ;i<count  ; i++) {
			emp = emps.get(i);
			nameNode = new DefaultMutableTreeNode(emp.getName()+"/"+emp.getId());
			top.add(nameNode);
		}
		
		//아이콘 변경하기
		ImageIcon iconLeaf = new ImageIcon(("./img/nodeImg.JPG"));
		DefaultTreeCellRenderer renderer = new DefaultTreeCellRenderer();
		
		renderer.setLeafIcon(iconLeaf);
		this.setCellRenderer(renderer);
		this.setEditable(false);
	}
	public String getSelectedNodeInfo() {
		DefaultMutableTreeNode node = (DefaultMutableTreeNode)this.getLastSelectedPathComponent();
     	if (node == null) return "";
     	return node.getUserObject().toString();
	}

}
