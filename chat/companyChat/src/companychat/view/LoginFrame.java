package companychat.view;

import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class LoginFrame extends JFrame implements MouseListener, WindowListener{
	protected JTextField idTf = new JTextField("");
	protected JTextField pwTf = new JTextField("");
	protected JPanel centerPane = new JPanel();
	protected JPanel idPane = new JPanel(new GridLayout(1,2,10,10));
	protected JPanel pwPane = new JPanel(new GridLayout(1,2,10,10));
	protected JButton login = new JButton("로그인");
	
	Toolkit toolkit = Toolkit.getDefaultToolkit();
	Image img = toolkit.getImage("./img/titleIcon.png");
	
	public LoginFrame() {

		pwPane.add(new JLabel("패스워드"));
		pwPane.add(pwTf);
		
		idPane.add(new JLabel("아이디"));
		idPane.add(idTf);
		centerPane.add(idPane);
		centerPane.add(pwPane);
		add(centerPane,"Center");
		add(login,"South");
		this.setIconImage(img);
		setSize(300,500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
}
