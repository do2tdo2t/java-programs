package threadProject;

import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class DigitalClockPane extends JPanel implements Runnable{
	JLabel lbl = new JLabel();
	Font fnt = new Font("±¼¸²Ã¼",Font.BOLD, 20);

	public DigitalClockPane() {
		this.add(lbl,"Center");
		lbl.setFont(fnt);
	}
	
	public void run() {
		System.out.println("start ");
		while(true) {
			Calendar now = Calendar.getInstance();
			SimpleDateFormat format = new SimpleDateFormat("hh:mm:ss");
			String time = format.format(now.getTime());
			setTime(time);
		}
	}
	public void setTime(String time) {
		lbl.setText(time);
		add(lbl,"Center");
		setVisible(true);
	}
	
}

