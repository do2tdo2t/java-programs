package companychatserver.vo;

import java.util.ArrayList;

import companychatserver.util.Constant;

public class RoomVO {
	int type = Constant.ROOM;
	int count = 0;
	String userId;
	String recvId;
	private String user;
	private String recv;
	ArrayList<MessageVO> list = new ArrayList<MessageVO>();
	
	public RoomVO() {
		
	}
	public RoomVO(String user, String recv) {
		this.recv= recv;
		this.user= user;
		this.userId = user.split("/")[1];
		this.recvId = recv.split("/")[1];
	}


	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public void setRecvId(String recvId) {
		this.recvId = recvId;
	}
	public int getType() {
		return type;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.userId = user.split("/")[1];
		this.user = user;
	}

	public String getRecv() {
		
		return recv;
	}

	public void setRecv(String recv) {
		this.recvId = recv.split("/")[1];
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
		count = list.size();
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
