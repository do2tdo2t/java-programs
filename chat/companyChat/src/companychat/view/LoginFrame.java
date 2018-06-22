package companychat.view;

import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class LoginFrame extends JFrame implements MouseListener{
	protected JTextField idTf = new JTextField("");
	protected JTextField pwTf = new JTextField("");
	protected JPanel centerPane = new JPanel();
	protected JPanel idPane = new JPanel(new GridLayout(1,2,10,10));
	protected JPanel pwPane = new JPanel(new GridLayout(1,2,10,10));
	protected JButton login = new JButton("로그인");
	
	public LoginFrame() {
		
		pwPane.add(new JLabel("패스워드"));
		pwPane.add(pwTf);
		
		idPane.add(new JLabel("아이디"));
		idPane.add(idTf);
		centerPane.add(idPane);
		centerPane.add(pwPane);
		add(centerPane,"Center");
		add(login,"South");
		setSize(300,500);
		
		login.addMouseListener(this);
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		
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
