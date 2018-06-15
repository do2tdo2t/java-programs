import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Stack;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Calculator extends JFrame implements ActionListener{
	String lblStr = "";
	String bntLabels[] = {"7","8","9","+","4","5","6","-","1","2","3","*","0",".","=","/"};
	String fBntLabels[]  = {"BackSpace","Clear","End"};
	
	Toolkit t = Toolkit.getDefaultToolkit();
	JPanel centerPane = new JPanel(new BorderLayout()); //pane1(North),pane2(Center)
	JPanel pane1= new JPanel(new GridLayout(1,3));
	JPanel pane2= new JPanel(new GridLayout(4,4));
	
	JLabel lbl = new JLabel("0.0",JLabel.RIGHT);
	JLabel clockLbl = new JLabel(" ",JLabel.CENTER);
	Font fnt = new Font("굴림체",Font.BOLD, 12);
	
	public static void main(String[] args) {
		new Calculator();
	}
	
	public Calculator() {
		initFrame();
		setVisible(true);
		DigitalClockPane pane = new DigitalClockPane();
		add(pane,"South");
		//Thread clock = new Thread(this);
		Thread clock = new Thread(pane);
		clock.start();
		 
	}
	
	//panel
	void initFrame() {
		//set Icon
		Image icon = t.getImage("img/duck_icon.JPG");
		setIconImage(icon);
		
		//initialize text field
		setPanel();
		this.add(lbl,"North");
		centerPane.add(pane1,"North");
		centerPane.add(pane2,"Center");
		this.add(centerPane,"Center");
		//clockLbl.setFont(fnt);
		//this.add(clockLbl,"South");
		this.pack();
	}
	
	//panel
	void setPanel() {
		//button 붙이기
		for(int i = 0 ; i<fBntLabels.length  ; i++) {
			JButton bnt = new JButton(fBntLabels[i]);
			bnt.addActionListener(this);
			pane1.add(bnt);
		}
		
		for(int i = 0 ; i < 16; i++) {
			JButton btn = new JButton(bntLabels[i]);
			btn.addActionListener(this);
			pane2.add(btn);
		}
	}
	
	//Listener
	@Override
	public void actionPerformed(ActionEvent e) {
		String event = e.getActionCommand();
		//clrBnt.getText();
		
		if(event.equals(fBntLabels[0])) {
			backSpace();
		}else if(event.equals(fBntLabels[1])) {
			clear();
		}else if(event.equals(fBntLabels[2])) {
			end();
		}else {
			append(event.charAt(0));
		}
	}

	/*function*/
	void clear() {
		lbl.setText("0.0");
		lblStr = "";
	}
	
	void end() {
		System.exit(0);
	}
	
	void backSpace() {
		int len = lblStr.length();
		if((len - 1)>0) {
			lblStr = lblStr.substring(0, len-1);
			lbl.setText(lblStr);
		}else {
			clear();
		}
	}
	
	/*text field에 붙이기*/
	void append(char c){
		// 연산자 연산자는 안됨 ! 처음부터 연산자여도 안됨
		if(c=='=') {
			lblStr+=c;
			analyzeStr();
		}else {
			if(isOrderRight(c)) {
				lblStr+=c;
				lbl.setText(lblStr);
			}
		}
	}
	
	/*순서에 맞게 들어가도록 함 숫자 > 연산자(+,-,/,*)> 숫자 */
	boolean isOrderRight(char c) {
		boolean mIsRealNum = false;
		int len = lblStr.length();
		/*tfLabel에 아무 것도 없을 때, 입력 글자가 숫자면 들어가고 연산자나 dot이면 안들어감*/
		if(len == 0) {
			if(isNumber(c)) {
				return true;
			}else {
				return false;
			}
		}
		/* 입력 글자가 연산자일때 이전 문자가 연산자나 dot이면 연속해서 들어 갈 수 없으므로
		 * 마지막 글자 검사
		 * */
		char last = lblStr.substring(len-1).charAt(0);
		if(isOperation(c)) { 
			if(isNumber(last)) return true; //마지막 글자가 Operation 이였을 때
			return false;
		}else if(isDot(c)) {
			if(!mIsRealNum && isNumber(last) ) {
				mIsRealNum = true;
				return true;
			}
			return false;
		}else{
			//c가 숫자면 상관없이 추가
			mIsRealNum = false;
			return true;
		}
	}
	
	boolean isDot(char c) {
		if(c == '.') return true;
		return false;
	}
	
	boolean isOperation(char c) {
		if(c=='+'||c=='-'||c=='/'||c=='*'||c=='=') {
			return true;
		}
		return false;
	}
	
	boolean isNumber(char c) {
		for(int i = 1 ; i<=9 ; i++) {
			if(c==i+48) {
				return true;
			}
		}
		return false;
	}
	
	Double toNumber(String token) {
		return Double.parseDouble(token);
	}
	
	//lbl 분석
	void analyzeStr() {
		char[] chArr = lblStr.toCharArray();
		Stack<Double> numStack = new Stack<Double>();
		Stack<Character> opStack = new Stack<Character>();
		int currentIdx = 0;
		int startIdx = 0 ;
		
		//stack에 넣기
		for(char c : chArr) {
			if(isOperation(c)) {
				//숫자 넣기
				//startIdx 갱신
				numStack.push(toNumber(lblStr.substring(startIdx,currentIdx)));
				startIdx = currentIdx+1; 
				
				//연산자 우선순위에 따라 계산 후 스택에 넣기
				if(opStack.isEmpty()) { //연산자 Stack이 비었다면 무조건 넣음
					opStack.push(c);
					//System.out.println("push operation1 , "+c);
				}else {
					char top = opStack.pop();
					//우선 순위 비교 *,/ > +,- > =
					if(comparePriority(c,top) == 1) {
							double op2 = numStack.pop();
							double op1 = numStack.pop();
							numStack.push(calculate(op1,op2,c));
							opStack.push(top);
							//System.out.println("push operation2 , "+top);
						}else {
							double op2 = numStack.pop();
							double op1 = numStack.pop();
							numStack.push(calculate(op1,op2,top));
							opStack.push(c);
							//System.out.println("push operation3 , "+c);
						}
					}
				}
				currentIdx++;
			}
			double result=numStack.pop();
			lblStr = "";
			lbl.setText(Double.toString(result));
	}

	int comparePriority(char c1 , char c2) {
		if(c1 == '=') {
			return -1;
		}
		if(c1 == '+' || c1 == '-' ) {
			if(c2 == '*' || c2 == '/') 
				return -1;
		}
		return 1;
	}
	
	double calculate(double op1, double op2, char c) {
		switch(c) {
		case '+':
			return (op1+op2);
		case '-':
			return (op1-op2);
		case '*':
			return (op1*op2);
		case '/':
			return (op1/op2);
		default :
			return 0;
		}
	}
	public void setTime(String time) {
		clockLbl.setText(time);
		this.add(clockLbl,"South");
		this.setVisible(true);
	}

}
