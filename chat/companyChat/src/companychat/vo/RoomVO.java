package companychat.vo;

import java.util.ArrayList;

import companychat.util.Constant;

public class RoomVO {
	int type = Constant.ROOM;
	
	private String user;
	private String recv;
	int count = 0;
	ArrayList<MessageVO> list = new ArrayList<MessageVO>();
	
	public RoomVO() {
		
	}
	public RoomVO(String recv, String user) {
		this.recv= recv;
		this.user= user;
	}

	public int getType() {
		return type;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getRecv() {
		return recv;
	}

	public void setRecv(String recv) {
		this.recv = recv;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getSize() {
		return list.size();
	}

	public ArrayList<MessageVO> getList() {
		return list;
	}

	public void setList(ArrayList<MessageVO> list) {
		this.list = list;
	}
	
	public void add(MessageVO m) {
		list.add(m);
	}
	
	public int getRecvId() {
		return Integer.parseInt(recv.split("/")[1]);
	}
	
	public MessageVO get(int i) {
		return list.get(i);
	}


}
