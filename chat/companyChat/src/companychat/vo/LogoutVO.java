package companychat.vo;

import companychat.util.Constant;

public class LogoutVO {
	int type = Constant.LOGOUT;
	int id;

	public LogoutVO() {
		
	}
	public LogoutVO(int id) {
		this.id = id;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
}
