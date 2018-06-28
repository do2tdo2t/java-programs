package companychatclient.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import companychatclient.util.Constant;

//RoomPane¾È¿¡ µé¾î°¨
public class ChatPane extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Insets insets = this.getInsets();
	int y = 0;
	Font font = new Font("¸¼Àº °íµñ",Font.PLAIN, 12);
	public ChatPane() {
	
		this.setLayout(null);
		this.setSize(Constant.CHAT_PANE_WIDTH,Constant.CHAT_PANE_HEIGHT);
		Border border = BorderFactory.createLineBorder(Color.BLACK,1);
		this.setBorder(border);
		this.setBackground(Color.white);
	}
	
	public void addLeft(JLabel lbl) {
		this.add(lbl);
		
		lbl.setFont(font);
		Dimension size = lbl.getPreferredSize();
		lbl.setBounds(10+insets.left, y+insets.top, size.width, size.height);
		y+=20;
	}
	public void addRight(JLabel lbl) {
		this.add(lbl);
		lbl.setFont(font);
		Dimension size = lbl.getPreferredSize();
		int x= (int) (Constant.CHAT_PANE_WIDTH-size.getWidth()-10);
		lbl.setBounds(x +insets.left, y+insets.top, size.width, size.height);
		y+=20;
	}
	
}
