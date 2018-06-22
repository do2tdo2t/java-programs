package companychat.vo;

import companychat.util.Constant;

public class LoginVO {
	int type = Constant.LOGIN;
	int id;
	String pw;
	String inetAddress;
	
	public LoginVO() {

	}
	
	public LoginVO(int id, String pw, String inetAddress) {
		this.id = id;
		this.pw = pw;
		this.inetAddress = inetAddress;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getInetAddress() {
		return inetAddress;
	}

	public void setInetAddress(String inetAddress) {
		this.inetAddress = inetAddress;
	}

}
