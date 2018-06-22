package companychat.util;

import javax.swing.JDialog;
import javax.swing.JLabel;

public class ChatDialog extends JDialog {
	JLabel lbl = new JLabel("");
	public ChatDialog(String str) {
		  getContentPane().add(lbl);
		  lbl.setText(str.toString());
		  this.setSize(200,100);
		  this.setModal(true);
		  this.setVisible(true);
	}
}
