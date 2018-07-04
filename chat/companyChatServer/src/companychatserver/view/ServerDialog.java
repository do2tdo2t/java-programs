package companychatserver.view;

import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JDialog;
import javax.swing.JLabel;

public class ServerDialog extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JLabel lbl = new JLabel("");
	Toolkit toolkit = Toolkit.getDefaultToolkit();
	protected Image img = toolkit.getImage("./img/titleIcon.png");
	
	public ServerDialog(String str, String title) {
		this.setBackground(Color.white);
		this.setSize(200,150);
		  this.setTitle(title);
		  getContentPane().add(lbl);
		  this.setIconImage(img);
		  lbl.setText("  "+str.toString());
		  this.setSize(200,100);
		  this.setModal(true);
		  this.setVisible(true);
	}
}
