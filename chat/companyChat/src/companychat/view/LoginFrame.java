package companychat.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class LoginFrame extends JFrame implements MouseListener, WindowListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected JTextField idTf = new JTextField(10);
	protected JTextField pwTf = new JTextField(10);
	private Image bgImg = null;
	
	protected JPanel centerPane = new JPanel(new GridBagLayout()) {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(bgImg,0,0,null);
		}
		
	};
	
	protected JButton login = new JButton("로그인");
	
	Toolkit toolkit = Toolkit.getDefaultToolkit();
	protected Image img = toolkit.getImage("./img/titleIcon.png");
	
	public LoginFrame(){
		try {
		//font 적용
			bgImg = ImageIO.read(new File("./img/backImg.JPG"));
			initComponent();
			
			
			this.setIconImage(img);
			setSize(300,500);
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			login.addMouseListener(this);
	
			this.addWindowListener(this);
			this.setBackground(Color.white);
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void initComponent() throws IOException {
		GridBagConstraints c = new GridBagConstraints();
		Image idImg = null;
		Image pwImg = null;

		idImg = ImageIO.read(new File("./img/idImg.JPG"));
		pwImg = ImageIO.read(new File("./img/pwImg.JPG"));
	
		//박스 붙이기
		c.weightx = 0;
		c.weighty = 0;
		c.gridwidth = 1;
		c.gridwidth = 1;
		setGridLocation(c,0,0);
		centerPane.add(new JLabel(new ImageIcon(idImg)),c);			
		setGridLocation(c,1,0);
		centerPane.add(idTf,c);
		setGridLocation(c,0,1);
				
		centerPane.add(new JLabel(new ImageIcon(pwImg)),c);
		setGridLocation(c,1,1);
		centerPane.add(pwTf,c);
				
		setGridLocation(c,2,1);
		centerPane.add(login,c);
			
		add(centerPane,"Center");
		
	}
	
	public void setGridLocation(GridBagConstraints c ,int x, int y) {
		c.gridx=x;
		c.gridy=y;
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
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		
	}
}
