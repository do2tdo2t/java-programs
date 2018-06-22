package companychat.vo;

import java.util.ArrayList;

import companychat.util.Constant;

public class MessagesVO {
	int type = Constant.MSGS;
	int count = 0;
	ArrayList<MessageVO> list = new ArrayList<MessageVO>();
	
	public MessagesVO() {
		
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public ArrayList<MessageVO> getList() {
		return list;
	}

	public void setList(ArrayList<MessageVO> list) {
		this.list = list;
	}
	
	public void add(MessageVO m) {
		list.add(m);
		count = list.size();
	}
	
	public MessageVO get(int i) {
		return list.get(i);
	}
	
	

}
