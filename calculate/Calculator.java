import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

	public static void main(String[] args) {
		new Calculator();
	}
	
	public Calculator() {
		initFrame();
		setVisible(true);
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
		this.add(centerPane);
		this.pack();
	}
	
	//panel
	void setPanel() {
		//button
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
		System.out.println(e.getActionCommand());
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
	
	/* Add text at the label*/
	void append(char c){

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
	
	/* Check the order to add text to rule ! */
	boolean isOrderRight(char c) {
		boolean mIsRealNum = false;
		int len = lblStr.length();
		/*If the first letter is a number, append it*/
		if(len == 0) {
			if(isNumber(c)) {
				return true;
			}else {
				return false;
			}
		}
		
		/* Operators and dot are not allowed to enter consecutively,
		 * so the last character of the label is checked.
		*/
		char last = lblStr.substring(len-1).charAt(0);
		if(isOperator(c)) { 
			if(isNumber(last)) return true; //Operator consecutive
			return false; //Not operator consecutive
		}else if(isDot(c)) {
			if(!mIsRealNum && isNumber(last)) {
				mIsRealNum = true;
				return true;
			}
			return false;
		}else{
			//no matter if c is a number, append c to the label.
			mIsRealNum = false;
			return true;
		}
	}
	
	//check if c is a point
	boolean isDot(char c) {
		if(c == '.') return true;
		return false;
	}
	
	//check if c is a operator
	boolean isOperator(char c) {
		if(c=='+'||c=='-'||c=='/'||c=='*'||c=='=') {
			return true;
		}
		return false;
	}
	//check if c is a number
	boolean isNumber(char c) {
		for(int i = 1 ; i<=9 ; i++) {
			if(c==i+48) {
				return true;
			}
		}
		return false;
	}
	//Change the string to a number.
	Double toNumber(String token) {
		return Double.parseDouble(token);
	}
	
	//label analysis
	void analyzeStr() {
		char[] chArr = lblStr.toCharArray();
		Stack<Double> numStack = new Stack<Double>();
		Stack<Character> opStack = new Stack<Character>();
		int currentIdx = 0; 
		int startIdx = 0 ; //The start index of the number
		
		for(char c : chArr) {
			if(isOperator(c)) {
				//push a number on the stack.
				//startIdx update
				numStack.push(toNumber(lblStr.substring(startIdx,currentIdx)));
				startIdx = currentIdx+1; 
				
				//Calculate according to operator priority
				if(opStack.isEmpty()) { //if the operator stack is empty, put it.
					opStack.push(c);
				}else {
					char top = opStack.pop();
					//Compare operator priorities.
					//If greater than or equal to 1, -1 is returned.
					if(comparePriority(c,top) == 1) {
							double op2 = numStack.pop();
							double op1 = numStack.pop();
							numStack.push(calculate(op1,op2,c));
							opStack.push(top);
							System.out.println("push operation2 , "+top);
						}else {
							double op2 = numStack.pop();
							double op1 = numStack.pop();
							numStack.push(calculate(op1,op2,top));
							opStack.push(c);
							System.out.println("push operation3 , "+c);
						}
					}
				}
				currentIdx++;
			}
			//When the parsing is finished, the result is displayed.
			showResult(Double.toString(numStack.pop()));
	}
	//show the result.
	void showResult(String result){
		lblStr = "";
		lbl.setText(Double.toString(result));
	}
	//Compare the priorities : '*','/' > '+','-' > '='
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
}
